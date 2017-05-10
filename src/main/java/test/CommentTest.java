package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import HPP_PROJECT.Comment;
import HPP_PROJECT.ReaderComment;

public class CommentTest {

	private ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest1.dat");
	
	@Test
	public void readCommentTest() {
		Comment expected = new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Comment actual = reader.readNextComment();
		assertEquals(true, expected.equals(actual));
	}
	
	@Test
	public void read2ndCommentTest() {
		Comment expected = new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 529360);
		Comment actual = reader.readNextComment();
		assertEquals(true, expected.equals(actual));
	}


}
