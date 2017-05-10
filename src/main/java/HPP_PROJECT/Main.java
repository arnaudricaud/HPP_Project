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

	public static void main(String[] args) {
		ArrayList<Post> expected = new ArrayList<Post>();
		// expected.add(new Post(new DateTime("2010-02-01T05:12:32.921+0000")
		// ,1039993, 3981, "Lei Liu"));
		// expected.add(new Post(new DateTime("2010-02-02T19:53:43.226+0000")
		// ,299101, 4661, "Michael Wang"));
		expected.add(new Post(new DateTime("2010-02-03T19:53:43.226+0000"), 255120, 4661, "Michael Wang"));
		expected.get(0).setScoreTotal(10);
		// expected.get(1).setScoreTotal(20);
		// expected.get(2).setScoreTotal(30);
		clearHistoriqueFile();
		writeTop3(expected, new DateTime("2010-02-03T19:53:43.226+0000"));
		writeTop3(expected, new DateTime("2010-02-03T20:53:43.226+0000"));
	}

	public static DateTime nextTick(Post p, Comment c) {
		long diff = p.getTs().getMillis() - c.getTs().getMillis();
		if (diff >= 0) {
			return c.getTs();
		} else {
			return p.getTs();
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

	
	public static void writeTop3(ArrayList<Post> tabPost, DateTime tk) {
		sortPost(tabPost);
		try {
			FileWriter writer = new FileWriter("export/historique.txt", true);
			writer.write("<" + tk + ",");
			for (int i = 0; i < 2; i++) {
				if (i < tabPost.size()) {
					writer.write(tabPost.get(i).getPost_id() + "," + tabPost.get(i).getUser() + ","
							+ tabPost.get(i).getScoreTotal() + ",\r\n");
				} else {
					writer.write("-,-,-,-,\r\n");
				}
			}
			if (tabPost.size() >= 3) {
				writer.write(tabPost.get(2).getPost_id() + "," + tabPost.get(2).getUser() + ","
						+ tabPost.get(2).getScoreTotal() + ">\r\n");
			} else {
				writer.write("-,-,-,->\r\n");
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

	public static void supression() {
		for (int i = 0; i < tabPost.size(); i++) {
			if (tabPost.get(i).getScorePost() == 0) {
				tabPost.remove(i);
			}
		}
	}

	public static void calculScore() {
		// appel fonction
		for (int i = 0; i < tabPost.size(); i++) {
			for (int j = 0; j < tabComment.size(); j++) {

				if (tabComment.get(j).getPost_commented() == tabPost.get(i).getPost_id()) {
					tabPost.get(i).setScoreTotal(tabPost.get(i).getScoreTotal() + tabComment.get(j).getScore());

				}

			}
			supression();

		}

	}

	public int countCommenters(Post post){
		HashSet<Integer> commentersSet = new HashSet<Integer>();

		for (Comment comment : tabComment) {
			if(comment.getPost_commented() == post.getPost_id()){
				commentersSet.add(comment.getUser_id());
			}
		}

		return commentersSet.size();
	}

}
