package HPP_PROJECT;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;

public class Main {
	static BlockingQueue<Post> queuePost = new ArrayBlockingQueue<Post>(1000);
	static BlockingQueue<Comment> queueComment = new ArrayBlockingQueue<Comment>(1000);
	//static BlockingQueue<Post> queueTop3 = new ArrayBlockingQueue<Post>(1000);
	
	static ArrayList<Post> tabPost = new ArrayList<Post>();
	static ArrayList<Comment> tabComment = new ArrayList<Comment>();
	public static int z = 0;
	static String str_tk = "";

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
		ReaderComment rdComment = ReaderComment.getInstance(commentFile); // zbra

		rdPost.readNextPost();
		rdComment.readNextComment();

		DateTime tk = nextTick(rdPost.getCurrentPost(), rdComment.getCurrentComment());

		while (tk != null) {			
			//Thread post
			TraitementPost tp = new TraitementPost(tk, queuePost, rdPost);
			Thread thread = new Thread(tp);
			thread.setName("Post " + tk);
			thread.start();
			
			//Thread Comment
			TraitementComment tc = new TraitementComment(tk, queueComment, rdComment);		
			Thread thread2 = new Thread(tc);
			thread2.setName("Comment " + tk);
			thread2.start();
			
			//Thread Traitement
			TraitementScore ts = new TraitementScore(tk, queuePost, queueComment, str_tk);	
			Thread thread3 = new Thread(ts);
			thread3.setName("Score " + tk);
			thread3.start();
			
            tk = nextTick(rdPost.getCurrentPost(), rdComment.getCurrentComment());
			tp.setTk(tk);
			tc.setTk(tk);
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
