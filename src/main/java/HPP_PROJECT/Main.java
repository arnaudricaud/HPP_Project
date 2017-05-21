package HPP_PROJECT;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;

public class Main {
	static BlockingQueue<Post> queuePost = new ArrayBlockingQueue<Post>(1000);
	static BlockingQueue<Comment> queueComment = new ArrayBlockingQueue<Comment>(1000);
	// static BlockingQueue<Post> queueTop3 = new
	// ArrayBlockingQueue<Post>(1000);

	static ArrayList<Post> tabPost = new ArrayList<Post>();
	static ArrayList<Comment> tabComment = new ArrayList<Comment>();
	public static int z = 0;

	public static void main(String[] args) {
		Main main = new Main();
		main.traitementTotal("ressources/data/test/Q1Case2/posts.dat", "ressources/data/test/Q1Case2/comments.dat");
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

	public void traitementTotal(String postFile, String commentFile) {
		clearHistoriqueFile();

		ReaderPost rdPost = ReaderPost.getInstance(postFile);
		ReaderComment rdComment = ReaderComment.getInstance(commentFile);

		// Thread PostReader
		TraitementPost tp = new TraitementPost(queuePost, rdPost);
		Thread thread = new Thread(tp);
		thread.setName("PostReader");
		thread.start();

		// Thread CommentReader
		TraitementComment tc = new TraitementComment(queueComment, rdComment);
		Thread thread2 = new Thread(tc);
		thread2.setName("CommentReader");
		thread2.start();

		System.out.println("START");

		// Thread Caclul
		TraitementScore ts = new TraitementScore(queuePost, queueComment);
		Thread thread3 = new Thread(ts);
		thread3.setName("Score Calculator");
		thread3.start();
		
		try {
			thread3.join();
			thread2.join();
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
