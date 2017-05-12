package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TraitementComment implements Runnable{

	private DateTime tk;
	private ArrayList<Comment> tabComment;
	private ArrayList<Post> tabPost;
	private ReaderComment rdComment;
	
	public TraitementComment(DateTime tk, ArrayList<Comment> tabComment, ArrayList<Post> tabPost,
			ReaderComment rdComment) {
		super();
		this.tk = tk;
		this.tabComment = tabComment;
		this.tabPost = tabPost;
		this.rdComment = rdComment;
	}

	public void traitement() {

		Boolean truePost;
		
		
		Comment cmt = rdComment.getCurrentComment();

		while (cmt.getTs().equals(tk)) {
			truePost = false;
			if (cmt.getPost_commented() == -1) {
				for (int i = 0; i < tabComment.size(); i++) {
					if (tabComment.get(i).getComment_id() == cmt.getComment_replied()) {
						cmt.setPost_commented(tabComment.get(i).getPost_commented());
						truePost = true;
						break;
					}
				}
			}
			
			for (int j = 0; j < tabPost.size(); j++) {
				if (tabPost.get(j).getPost_id() == cmt.getPost_commented()) {
					truePost = true;
					break;
				}
			}
			
			if (truePost == true)
			{
				tabComment.add(cmt);
			}
			
			cmt = rdComment.readNextComment();
		}
		for (int i = 0; i < tabComment.size(); i++)
			updateComment(tabComment.get(i), tk);
	}
	
	public DateTime getTk() {
		return tk;
	}

	public void setTk(DateTime tk) {
		this.tk = tk;
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

	@Override
	public void run() {
		traitement();
		
	}
	
	
}
