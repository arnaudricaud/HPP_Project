package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import HPP_PROJECT.Comment;
import HPP_PROJECT.Post;
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
		Comment expected = new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 1039993);
		Comment actual = reader.readNextComment();
		assertEquals(true, expected.equals(actual));
	}
	
	@Test
	public void readCommentTest2() {
		ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest1.dat");
		Comment expected = new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 299101);
		Comment actual = reader.readNextComment();
		actual = reader.readNextComment();
		assertEquals(true, expected.equals(actual));
	}

	@Test
	public void readMultipleCommentTest() {
		ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest1.dat");
		reader.readNextComment();
		ArrayList<Post> tabPost = new ArrayList<Post>();
		tabPost.add(new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu"));
		tabPost.add(new Post(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang"));
		
		ArrayList<Comment> expected = new ArrayList<Comment>();
		ArrayList<Comment> actual = new ArrayList<Comment>();
		expected.add(new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 1039993));
		expected.add(new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 299101));
		TraitementComment tc = new TraitementComment(new DateTime("2010-02-09T04:05:20.777+0000"), actual, tabPost, reader);
		tc.traitement();
		tc.setTk(new DateTime("2010-02-09T04:20:53.281+0000"));
		tc.traitement();
		Boolean test;
		if ((actual.get(0).equals(expected.get(0))) && (actual.get(1).equals(expected.get(1)))){
			test = true;
		} else{
			test = false;
		}

		assertEquals(true, test);
	}
	
	@Test
	public void readMultipleCommentWithFalseTest() {
		ReaderComment reader = ReaderComment.getInstance("ressources/test/BasicTest/commentsTest2.dat");
		reader.readNextComment();
		ArrayList<Post> tabPost = new ArrayList<Post>();
		tabPost.add(new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu"));
		tabPost.add(new Post(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang"));
		
		
		ArrayList<Comment> expected = new ArrayList<Comment>();
		ArrayList<Comment> actual = new ArrayList<Comment>();
		expected.add(new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 1039993));
		expected.add(new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 299101));
		expected.add(new Comment(new DateTime("2010-02-09T06:08:38.206+0000") ,529594, 3633, "Jun Wu", 529590, 1039993));
		
		TraitementComment tc = new TraitementComment(new DateTime("2010-02-09T04:05:20.777+0000"), actual, tabPost, reader);
		tc.traitement();
		tc.setTk(new DateTime("2010-02-09T04:20:53.281+0000"));
		tc.traitement();
		tc.setTk(new DateTime("2010-02-09T05:19:19.802+0000"));
		tc.traitement();
		tc.setTk(new DateTime("2010-02-09T06:08:38.206+0000"));
		tc.traitement();
		
		Boolean test;
		if ((actual.get(0).equals(expected.get(0))) && (actual.get(1).equals(expected.get(1)))){
			test = true;
		} else{
			test = false;
		}

		assertEquals(true, test);
	}
	
	

}
