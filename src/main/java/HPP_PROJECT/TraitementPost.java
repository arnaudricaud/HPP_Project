package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class TraitementPost {
	
	public void traitement(DateTime tk, ArrayList<Post> tabPost, ReaderPost rdPost){
		//ReaderPost rdPost = new ReaderPost("import/post.dat");
		
		Post pst = rdPost.readNextPost();
		
		while(pst.getTs() == tk)
		{
			tabPost.add(pst);
			
			pst = rdPost.readNextPost();
		}
	}
}
