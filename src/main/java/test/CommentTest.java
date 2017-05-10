package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import HPP_PROJECT.Comment;
import HPP_PROJECT.ReaderComment;
import HPP_PROJECT.ReaderPost;


public class CommentTest {

	@Before
	public void init(){
		ReaderComment.reset("ressources/test/BasicTest/commentsTest1.dat");
	}
	
	@Test
	public void readCommentTest() {
		ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest1.dat");
		Comment expected = new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360);
		Comment actual = reader.readNextComment();
		assertEquals(true, expected.equals(actual));
	}
	
	@Test
	public void readCommentTest2() {
		ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest1.dat");
		Comment expected = new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 529360);
		Comment actual = reader.readNextComment();
		actual = reader.readNextComment();
		assertEquals(true, expected.equals(actual));
	}


}
