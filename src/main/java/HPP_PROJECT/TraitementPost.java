package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class TraitementPost {
	
	public static void traitement(DateTime tk, ArrayList<Post> tabPost, ReaderPost rdPost){
		//ReaderPost rdPost = new ReaderPost("import/post.dat");
		
		Post pst = rdPost.getCurrentPost();
		
		while(pst.getTs().equals(tk))
		{
			tabPost.add(pst);
			pst = rdPost.readNextPost();
		}
		for (int i = 0; i < tabPost.size(); i++)
			updatePost(tabPost.get(i), tk);
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
	
}
