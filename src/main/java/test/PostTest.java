package test;

import HPP_PROJECT.Post;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.joda.time.DateTime;
import org.junit.Test;

public class PostTest {

	@Test
	public void ReadPostTest() {
		//TODO Remplir format DateTime
		Post expected = new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu", 10);
		Post actual = Post.ReadPost("ressources/test/ReadPost/postsTest1.dat");
		assertEquals(true, expected.equals(actual));
	}

}
