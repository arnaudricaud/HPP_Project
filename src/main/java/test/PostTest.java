package test;

import HPP_PROJECT.Main;
import HPP_PROJECT.Post;
import org.junit.runners.MethodSorters;
import HPP_PROJECT.ReaderPost;
import HPP_PROJECT.TraitementPost;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostTest {

	@Before
	public void init(){
		ReaderPost.reset("ressources/test/BasicTest/postsTest1.dat");
	}

	@Test
	public void readPostTest() {
		ReaderPost reader = ReaderPost.getInstance("ressources/test/BasicTest/postsTest1.dat");
		Post expected = new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu");
		Post actual = reader.readNextPost();
		assertEquals(true, expected.equals(actual));
	}
	
	@Test
	public void readPostTest2() {
		ReaderPost reader = ReaderPost.getInstance("ressources/test/BasicTest/postsTest1.dat");
		Post expected = new Post(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang");
		Post actual = reader.readNextPost();
		actual = reader.readNextPost();
		assertEquals(true, expected.equals(actual));
	}
	
	
	@Test
	public void readMultiplePostTest() {
		ReaderPost reader = ReaderPost.getInstance("ressources/test/BasicTest/postsTest1.dat");
		reader.readNextPost();
		ArrayList<Post> expected = new ArrayList<Post>();
		ArrayList<Post> actual = new ArrayList<Post>();
		expected.add(new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu"));
		expected.get(0).setAge(1);
		expected.get(0).setScorePost(9);
		expected.add(new Post(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang"));
		TraitementPost.traitement(new DateTime("2010-02-01T05:12:32.921+0000"), actual, reader);
		TraitementPost.traitement(new DateTime("2010-02-02T19:53:43.226+0000"), actual, reader);
		
		Boolean test;
		if ((actual.get(0).equals(expected.get(0))) && (actual.get(1).equals(expected.get(1)))){
			test = true;
		} else{
			test = false;
		}

		assertEquals(true, test);
	}
	

}
