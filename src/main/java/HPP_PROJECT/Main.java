package HPP_PROJECT;

import org.joda.time.DateTime;

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

}
