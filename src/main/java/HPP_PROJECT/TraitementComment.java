package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;

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
	}
}
