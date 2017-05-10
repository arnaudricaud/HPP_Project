package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import HPP_PROJECT.Comment;
import HPP_PROJECT.ReaderComment;
import HPP_PROJECT.TraitementComment;


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

	@Test
	public void readMultipleCommentTest() {
		ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest1.dat");
		reader.readNextComment();
		ArrayList<Comment> expected = new ArrayList<Comment>();
		ArrayList<Comment> actual = new ArrayList<Comment>();
		expected.add(new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 529360));
		expected.add(new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 529360));
		TraitementComment.traitement(new DateTime("2010-02-09T04:05:20.777+0000"), actual, reader);
		TraitementComment.traitement(new DateTime("2010-02-09T04:20:53.281+0000"), actual, reader);
		
		Boolean test;
		if ((actual.get(0).equals(expected.get(0))) && (actual.get(1).equals(expected.get(1)))){
			test = true;
		} else{
			test = false;
		}

		assertEquals(true, test);
	}
	

}
