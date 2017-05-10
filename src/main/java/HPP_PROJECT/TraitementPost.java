package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class TraitementPost {
	
	public static void traitement(DateTime tk, ArrayList<Post> tabPost, ReaderPost rdPost){
		//ReaderPost rdPost = new ReaderPost("import/post.dat");
		
		Post pst = rdPost.getCurrentPost();
		
		while(pst.getTs().equals(tk))
		{
			tabPost.add(pst);
			pst = rdPost.readNextPost();
		}
	}
}
