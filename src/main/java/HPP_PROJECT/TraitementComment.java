package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class TraitementComment {

	public static void traitement(DateTime tk, ArrayList<Comment> tabComment, ReaderComment rdComment) {

		Comment cmt = rdComment.getCurrentComment();

		while (cmt.getTs().equals(tk)) {
			tabComment.add(cmt);
			cmt = rdComment.readNextComment();
		}
	}
}
