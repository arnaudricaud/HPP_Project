package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import HPP_PROJECT.Post;
import HPP_PROJECT.ReaderPost;

public class CommentTest {

	@Before
	public void initReader(){
	    ReaderPost reader = new ReaderComment("ressources/test/BasicTest/commentsTest1.dat");
	}
	
	@Test
	public void ReadCommentTest() {
		Comment expected = new Comment(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu");
		Comment actual = ReaderComment.ReadPost();
		assertEquals(true, expected.equals(actual));
	}
	
	@Test
	public void Read2ndCommentTest() {
		Comment expected = new Comment(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang");
		String actualStr = ReaderComment.ReadComment();
		actual = ReaderComment.ReadComment();
		assertEquals(true, expected.equals(actual));
	}


}
