package test;

import HPP_PROJECT.Post;
import HPP_PROJECT.ReaderPost;

import static org.junit.Assert.assertEquals;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class PostTest {

	@Before
	public void initReader(){
	    ReaderPost reader = new ReaderPost("ressources/test/BasicTest/postsTest1.dat");
	}
	
	@Test
	public void readPostTest() {
		Post expected = new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu");
		Post actual = ReaderPost.ReadPost();
		assertEquals(true, expected.equals(actual));
	}
	
	@Test
	public void read2ndPostTest() {
		Post expected = new Post(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang");
		Post actual = ReaderPost.ReadPost();
		actual = ReaderPost.ReadPost();
		assertEquals(true, expected.equals(actual));
	}


}
