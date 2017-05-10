package HPP_PROJECT;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;



public class Main {
	static ArrayList <Post> tabPost= new ArrayList<Post>() ;
	static ArrayList <Comment> tabComment= new ArrayList<Comment>();
	public static void main(String[] args) {

		
		
	}

	public static DateTime nextTick(Post p, Comment c) {
		long diff = p.getTs().getMillis() - c.getTs().getMillis();
		if (diff >= 0) {
			return c.getTs();
		} else {
			return p.getTs();
		}
	}
	
	public static void updatePost(Post p, DateTime tk){
		Duration diff = new Duration(p.getTs(), tk);
		p.setAge((int) diff.getStandardDays());
		if (p.getAge() < 10){
			p.setScorePost(10 - p.getAge());
		}else{
			p.setScorePost(0);
		}
	}
	
	public static void updateComment(Comment c, DateTime tk){
		Duration diff = new Duration(c.getTs(), tk);
		c.setAge((int) diff.getStandardDays());
		if (c.getAge() < 10){
			c.setScore(10 - c.getAge());
		}else{
			c.setScore(0);
		}
	}
	
	public static void supression(){
		for(int i=0;i<tabPost.size();i++){
			if(tabPost.get(i).getScorePost()==0) {
				tabPost.remove(i);
			}
		}	
	}
	
	public static void calculScore(){
		//appel fonction
		for(int i=0;i<tabPost.size();i++){
			for(int j=0;j<tabComment.size();j++){
				
				if(tabComment.get(j).getPost_commented()==tabPost.get(i).getPost_id()){
					tabPost.get(i).setScoreTotal(tabPost.get(i).getScoreTotal() +tabComment.get(j).getScore());
					
					
				}
				
				
			}
			supression();
			
		}
		
	}

}
