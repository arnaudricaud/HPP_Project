package HPP_PROJECT;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TraitementScore implements Runnable{
	ArrayList<Post> tabPost;
	ArrayList<Comment> tabComment;
	ArrayList<Post> displayedPosts =  new ArrayList<Post>();; 

	BlockingQueue<Post> queueTop3 = new ArrayBlockingQueue<Post>(1000);
	String str_tk = "";

	DateTime tk;
	public TraitementScore(DateTime tk, BlockingQueue<Post> queuePost,  BlockingQueue<Comment> queueComment, String str_tk) {
		this.tk = tk;
		this.tabPost = new ArrayList<Post>(queuePost);
		this.tabComment = new ArrayList<Comment>(queueComment);
		this.str_tk = str_tk;
	}

	@Override
	public void run() {
		Boolean truePost;
		
		//Post
		for (int i = 0; i < tabPost.size(); i++)
			updatePost(tabPost.get(i), tk);
		
		//commment
		for(int j = 0; j < tabComment.size(); j++) 
		{
			truePost = false;
			if (tabComment.get(j).getPost_commented() == -1)
			{
				for (int i = 0; i < tabComment.size(); i++)
				{
					if (tabComment.get(i).getComment_id() == tabComment.get(j).getComment_replied())
					{
						tabComment.get(j).setPost_commented(tabComment.get(i).getPost_commented());
						truePost = true;
						break;
					}
				}
			}
			
			for (int k = 0; k < tabPost.size(); k++) 
			{
				if (tabPost.get(k).getPost_id() == tabComment.get(j).getPost_commented()) 
				{
					truePost = true;
					break;
				}
			}
			
			if(!truePost)
			{
				tabComment.remove(j);
				j--;
			}
		}
		
		for (int i = 0; i < tabComment.size(); i++)
			updateComment(tabComment.get(i), tk);
		
		calculScore(tk);
		suppression();
		sortPost();
		if(!checkDisplayedPosts(displayedPosts)){
            writeTop3();
        }
	}
	
	public void updatePost(Post p, DateTime tk) {
		Duration diff = new Duration(p.getTs(), tk);
		p.setAge((int) diff.getStandardDays());
		if (p.getAge() < 10) {
			p.setScorePost(10 - p.getAge());
		} else {
			p.setScorePost(0);
		}
	}
	
	public static void updateComment(Comment c, DateTime tk) {
		Duration diff = new Duration(c.getTs(), tk);
		c.setAge((int) diff.getStandardDays());
		if (c.getAge() < 10) {
			c.setScore(10 - c.getAge());
		} else {
			c.setScore(0);
		}
	}
	
	public void writeTop3() {
		sortPost();
        displayedPosts = new ArrayList<Post>();
		try {
			FileWriter writer = new FileWriter("export/historique.txt", true);
			writer.write(str_tk + ",");
			for (int i = 0; i < 2; i++) {
				if (i < tabPost.size()) {
				    displayedPosts.add(tabPost.get(i));
					writer.write(tabPost.get(i).getPost_id() + "," + tabPost.get(i).getUser() + ","
							+ tabPost.get(i).getScoreTotal() + "," + tabPost.get(i).getNbCommentateur() + ",");
				} else {
					writer.write("-,-,-,-,");
				}
			}
			if (tabPost.size() >= 3) {
			    displayedPosts.add(tabPost.get(2));
				writer.write(tabPost.get(2).getPost_id() + "," + tabPost.get(2).getUser() + ","
						+ tabPost.get(2).getScoreTotal() + "," + tabPost.get(2).getNbCommentateur() + "\r\n");
			} else {
				writer.write("-,-,-,-\r\n");
			}
			writer.close();
		} catch (IOException e) {
			// do something
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
				if (i != 0){
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

	
	public boolean checkDisplayedPosts(ArrayList<Post> displayedPosts){
	    boolean samePosts = true;
        int index = 0;

        if(displayedPosts.size() < tabPost.size() && tabPost.size() < 4){
            for(int nbRajout = 0; nbRajout < tabPost.size() - displayedPosts.size(); ++nbRajout){
                displayedPosts.add(null);
            }
        }

        sortPost();
        while(index < tabPost.size() && index < 3){
            samePosts = samePosts && tabPost.get(index).equals(displayedPosts.get(index));
            ++index;
        }

        return samePosts;
    }
}
