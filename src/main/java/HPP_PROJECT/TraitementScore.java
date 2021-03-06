package HPP_PROJECT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TraitementScore implements Runnable {

	ArrayList<Post> tabPost = new ArrayList<Post>();
	ArrayList<Comment> tabComment = new ArrayList<Comment>();
	ArrayList<Post> displayedPosts = new ArrayList<Post>();
	BlockingQueue<Post> queuePost;
	BlockingQueue<Comment> queueComment;
	BlockingQueue<Top3> queueTop3;
	Top3 top3Post;
	Post timePost = new Post(DateTime.now(), -1, -1, "");
	Post currentPost;
	Comment currentComment;

	DateTime tk;
	String str_tk = "";

	public TraitementScore(BlockingQueue<Post> queuePost, BlockingQueue<Comment> queueComment,
			BlockingQueue<Top3> queueTop3) {
		this.queuePost = queuePost;
		this.queueComment = queueComment;
		this.queueTop3 = queueTop3;
	}

	@Override
	public void run() {
		// RECUPERATION DU PREMIER POST
		try {
			currentPost = queuePost.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// RECUPERATION DU PREMIER COMMENT
		try {
			currentComment = queueComment.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// INIT DU PREMIER TK
		nextTick(currentPost, currentComment);
		while (tk != null) {
			// RECUPERATION DES POSTS CORRESPONDANTS AU TIC
			while (tk.equals(currentPost.getTs())) {
				tabPost.add(currentPost);
				try {
					currentPost = queuePost.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			// RECUPERATION DES COMMENTS CORRESPONDANTS AU TIC
			while (tk.equals(currentComment.getTs())) {
				tabComment.add(currentComment);
				try {
					currentComment = queueComment.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			checkComments();

			updatePost(tk);
			updateComment(tk);

			calculScore(tk);
			suppression();
			sortPost();
			
			// INIT TABLEAU TOP 3
			if (!checkDisplayedPosts(displayedPosts)) {
				// Changement du tableau displayPost
				displayedPosts = new ArrayList<Post>();
				int j = 0;
				for (int i = 0; i < tabPost.size() && i < 3; i++) {
					displayedPosts.add(new Post(tabPost.get(i)));
					j++;
				}
				for (int i = j; i < 3; i++) {
					displayedPosts.add(new Post(null, -1, -1, "NOPOST"));
				}
				
				try {
					
					queueTop3.put(new Top3(displayedPosts.get(0), displayedPosts.get(1), displayedPosts.get(2), str_tk));
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nextTick(currentPost, currentComment);
		}
		
		// POISON PILL
		int j = 0;
		for (int i = 0; i < tabPost.size() && i < 3; i++) {
			displayedPosts.add(new Post(tabPost.get(i)));
			j++;
		}
		for (int i = j; i < 3; i++) {
			displayedPosts.add(new Post(null, -1, -1, "NOPOST"));
		}
		try {
			queueTop3.put(new Top3(displayedPosts.get(0), displayedPosts.get(1), displayedPosts.get(2), "POISONPILL"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void nextTick(Post p, Comment c) {

		if (p.getUser_id() == -1 && c.getComment_id() == -1)
			tk = null;
		else if (p.getUser_id() == -1) {
			str_tk = c.getStr_ts();
			tk = c.getTs();
		} else if (c.getUser_id() == -1) {
			str_tk = p.getStr_ts();
			tk = p.getTs();
		} else {
			long diff = p.getTs().getMillis() - c.getTs().getMillis();
			if (diff >= 0) {
				str_tk = c.getStr_ts();
				tk = c.getTs();
			} else {
				str_tk = p.getStr_ts();
				tk = p.getTs();
			}
		}
	}

	public void checkComments() {
		boolean truePost = false;
		for (int j = 0; j < tabComment.size(); j++) {
			truePost = false;
			if (tabComment.get(j).getPost_commented() == -1) {
				for (int i = 0; i < tabComment.size(); i++) {
					if (tabComment.get(i).getComment_id() == tabComment.get(j).getComment_replied()) {
						tabComment.get(j).setPost_commented(tabComment.get(i).getPost_commented());
						truePost = true;
						break;
					}
				}
			}

			for (int k = 0; k < tabPost.size(); k++) {
				if (tabPost.get(k).getPost_id() == tabComment.get(j).getPost_commented()) {
					truePost = true;
					break;
				}
			}
			if (!truePost) {
				tabComment.remove(j);
				j--;

			}
		}
	}

	public void updatePost(DateTime tk) {
		for (int i = 0; i < tabPost.size(); i++) {
			Duration diff = new Duration(tabPost.get(i).getTs(), tk);
			tabPost.get(i).setAge((int) diff.getStandardDays());
			if (tabPost.get(i).getAge() < 10) {
				tabPost.get(i).setScorePost(10 - tabPost.get(i).getAge());
			} else {
				tabPost.get(i).setScorePost(0);
			}
		}
	}

	public void updateComment(DateTime tk) {
		for (int i = 0; i < this.tabComment.size(); i++) {
			Duration diff = new Duration(tabComment.get(i).getTs(), tk);
			tabComment.get(i).setAge((int) diff.getStandardDays());
			if (tabComment.get(i).getAge() < 10) {
				tabComment.get(i).setScore(10 - tabComment.get(i).getAge());
			} else {
				tabComment.get(i).setScore(0);
			}
		}
	}

	public void suppression() {
		for (int i = 0; i < tabPost.size(); i++) {
			if (tabPost.get(i).getScoreTotal() == 0) {
				long idPost = tabPost.get(i).getPost_id();
				tabPost.remove(i);
				i--;// Le remove reagence les index
				for (int j = 0; j < tabComment.size(); j++) {
					if (tabComment.get(j).getPost_commented() == idPost) {
						tabComment.remove(j);
						j--;
					}
				}

			}
		}
	}

	public void calculScore(DateTime tk) {
		for (int i = 0; i < tabPost.size(); i++) {
			tabPost.get(i).setScoreTotal(tabPost.get(i).getScorePost());
			for (int j = 0; j < tabComment.size(); j++) {
				if (tabComment.get(j).getPost_commented() == tabPost.get(i).getPost_id()) {
					tabPost.get(i).setScoreTotal(tabPost.get(i).getScoreTotal() + tabComment.get(j).getScore());
				}
			}

			countCommenters(tabPost.get(i));
		}
	}

	public void sortPost() {
		Collections.sort(tabPost, new Comparator<Post>() {
			public int compare(Post post1, Post post2) {
				Integer i = ((Integer) post2.getScoreTotal()).compareTo((Integer) post1.getScoreTotal());
				if (i != 0) {
					return i;
				} else {
					return post2.getTs().compareTo(post1.getTs());
				}
			}
		});
	}

	public void countCommenters(Post post) {
		HashSet<Long> commentersSet = new HashSet<Long>();

		for (Comment comment : tabComment) {
			if (comment.getPost_commented() == post.getPost_id()) {
				commentersSet.add(comment.getUser_id());
			}
		}
		post.setNbCommentateur(commentersSet.size());
	}

	public boolean checkDisplayedPosts(ArrayList<Post> displayedPosts) {
		boolean samePosts = true;
		int index = 0;

		sortPost();
		while (index < tabPost.size() && index < 3) {
			if (displayedPosts.size() > index){
				samePosts = samePosts && (tabPost.get(index).getPost_id() == displayedPosts.get(index).getPost_id());
			} else {
				samePosts = false;
			}
			++index;
		}

		return samePosts;
	}
}
