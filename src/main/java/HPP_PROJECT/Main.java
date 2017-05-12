package HPP_PROJECT;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Main {
	static ArrayList<Post> tabPost = new ArrayList<Post>();
	static ArrayList<Comment> tabComment = new ArrayList<Comment>();
<<<<<<< HEAD
	public static int z = 0;
	static String str_tk = "";
=======
>>>>>>> origin/master
	
	
	public static void main(String[] args) {
		traitementTotal();
	}

	public ArrayList<Post> getTabPost() {
		return tabPost;
	}

	public void setTabPost(ArrayList<Post> tabPost) {
		Main.tabPost = tabPost;
	}

	public ArrayList<Comment> getTabComment() {
		return tabComment;
	}

	public void setTabComment(ArrayList<Comment> tabComment) {
		Main.tabComment = tabComment;
	}

	public static void traitementTotal() {

		clearHistoriqueFile();
		ReaderPost rdPost = ReaderPost.getInstance("ressources/data/test/Q1Basic2/posts.dat");
		ReaderComment rdComment = ReaderComment.getInstance("ressources/data/test/Q1Basic2/comments.dat"); // zbra

		rdPost.readNextPost();
		rdComment.readNextComment();
<<<<<<< HEAD
		

		DateTime tk = nextTick(rdPost.getCurrentPost(), rdComment.getCurrentComment());
		
		while(tk  != null)
		{
=======

		DateTime tk = nextTick(rdPost.getCurrentPost(), rdComment.getCurrentComment());
		while (tk != null) {
>>>>>>> origin/master
			TraitementPost.traitement(tk, tabPost, rdPost);
			TraitementComment.traitement(tk, tabComment, tabPost, rdComment);

			for (int i = 0; i < tabComment.size(); i++)
				updateComment(tabComment.get(i), tk);

			for (int i = 0; i < tabPost.size(); i++) {
				updatePost(tabPost.get(i), tk);
				calculScore(tk);
			}

			suppression();
			sortPost(tabPost);
			writeTop3(tabPost, tk, str_tk);
			tk = nextTick(rdPost.getCurrentPost(), rdComment.getCurrentComment());
		}

	}

	public static DateTime nextTick(Post p, Comment c) {
		if (p.getUser_id() == -1 && c.getComment_id() == -1)
			return null;
		else {
			long diff = p.getTs().getMillis() - c.getTs().getMillis();
			if (diff >= 0) {
				str_tk = c.getStr_ts();
				return c.getTs();
			} else {
				str_tk = p.getStr_ts();
				return p.getTs();
			}
		}
	}

	public static void updatePost(Post p, DateTime tk) {
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

	public static void sortPost(ArrayList<Post> tabPost) {
		Collections.sort(tabPost, new Comparator<Post>() {
			public int compare(Post post1, Post post2) {
				return ((Integer) post2.getScoreTotal()).compareTo((Integer) post1.getScoreTotal());
			}
		});
	}

	public static void writeTop3(ArrayList<Post> tabPost, DateTime tk, String str_tk) {
		sortPost(tabPost);
		try {
			FileWriter writer = new FileWriter("export/historique.txt", true);
			writer.write(str_tk + ",");
			for (int i = 0; i < 2; i++) {
				if (i < tabPost.size()) {
					writer.write(tabPost.get(i).getPost_id() + "," + tabPost.get(i).getUser() + ","
							+ tabPost.get(i).getScoreTotal() + "," + tabPost.get(i).getNbCommentateur() + ",");
				} else {
					writer.write("-,-,-,-,");
				}
			}
			if (tabPost.size() >= 3) {
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

	public static void clearHistoriqueFile() {
		try {
			PrintWriter writer = new PrintWriter("export/historique.txt");
			writer.print("");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void suppression() {
		for (int i = 0; i < tabPost.size(); i++) {
			if (tabPost.get(i).getScorePost() == 0) {
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

	public static void calculScore(DateTime tk) {

		for (int i = 0; i < tabPost.size(); i++) {
			tabPost.get(i).setScoreTotal(tabPost.get(i).getScorePost());
			for (int j = 0; j < tabComment.size(); j++) {
				if (tabComment.get(j).getPost_commented() == tabPost.get(i).getPost_id()) {
					tabPost.get(i).setScoreTotal(tabPost.get(i).getScoreTotal() + tabComment.get(j).getScore());
				}
			}
		}

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

}
