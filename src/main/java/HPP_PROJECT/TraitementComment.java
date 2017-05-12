package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TraitementComment {

	public static void traitement(DateTime tk, ArrayList<Comment> tabComment, ArrayList<Post> tabPost, ReaderComment rdComment) {

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
	
	public static void updateComment(Comment c, DateTime tk) {
		Duration diff = new Duration(c.getTs(), tk);
		c.setAge((int) diff.getStandardDays());
		if (c.getAge() < 10) {
			c.setScore(10 - c.getAge());
		} else {
			c.setScore(0);
		}
	}
	
	
}
