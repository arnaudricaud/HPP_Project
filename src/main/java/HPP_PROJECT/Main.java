package HPP_PROJECT;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class Main {
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

}
