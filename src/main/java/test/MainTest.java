package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import HPP_PROJECT.Comment;
import HPP_PROJECT.Main;
import HPP_PROJECT.Post;

public class MainTest {

	@Test
	public void nextTickDayTest() {
		Comment c = new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Post p = new Post(new DateTime("2010-02-08T04:05:20.777+0000") ,299101, 4661, "Michael Wang");
		assertEquals(p.getTs(), Main.nextTick(p,c));
	}
	
	@Test
	public void nextTickHoursTest() {
		Comment c = new Comment(new DateTime("2010-02-09T03:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Post p = new Post(new DateTime("2010-02-09T04:05:20.777+0000") ,299101, 4661, "Michael Wang");
		assertEquals(c.getTs(), Main.nextTick(p,c));
	}

	@Test
	public void nextTickSecTest() {
		Comment c = new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Post p = new Post(new DateTime("2010-02-09T04:05:19.777+0000") ,299101, 4661, "Michael Wang");
		assertEquals(p.getTs(), Main.nextTick(p,c));
	}

	@Test
	public void nextTickMilliTest() {
		Comment c = new Comment(new DateTime("2010-02-09T04:05:20.771+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Post p = new Post(new DateTime("2010-02-09T04:05:20.772+0000") ,299101, 4661, "Michael Wang");
		assertEquals(c.getTs(), Main.nextTick(p,c));
	}

	@Test
	public void nextTickEqualTest() {
		Comment c = new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Post p = new Post(new DateTime("2010-02-09T04:05:20.777+0000") ,299101, 4661, "Michael Wang");
		assertEquals(c.getTs(), Main.nextTick(p,c));
	}

}
