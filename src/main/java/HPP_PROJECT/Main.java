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
	public static int z = 0;
	static String str_tk = "";
    private ArrayList<Post> displayedPosts = new ArrayList<Post>();



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
		TraitementPost tp = new TraitementPost(tk, tabPost, rdPost);
		TraitementComment tc = new TraitementComment(tk, tabComment, tabPost, rdComment);

		while (tk != null) {
			//ETAPE 1:
			//Parralelisable:
			tp.traitement();
			tc.traitement();
			// Fin parralelisable
			
			calculScore(tk);
			suppression();
			sortPost(tabPost);
            if(!checkDisplayedPosts(displayedPosts)){
                writeTop3(tabPost, tk, str_tk);
            }
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

	public static void sortPost(ArrayList<Post> tabPost) {
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

	public void writeTop3(ArrayList<Post> tabPost, DateTime tk, String str_tk) {
		sortPost(tabPost);
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

        sortPost(tabPost);
        while(index < tabPost.size() && index < 3){
            samePosts = samePosts && tabPost.get(index).equals(displayedPosts.get(index));
            ++index;
        }

        return samePosts;
    }

}
