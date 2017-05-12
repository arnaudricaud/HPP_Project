package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.junit.Test;

import HPP_PROJECT.Comment;
import HPP_PROJECT.Main;
import HPP_PROJECT.Post;
import HPP_PROJECT.TraitementPost;

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

	@Test
	public void updateScorePost10(){
		Post p = new Post(new DateTime("2010-02-09T04:05:20.777+0000") ,299101, 4661, "Michael Wang");
		DateTime tk = new DateTime("2010-02-09T10:05:20.777+0000");
		TraitementPost.updatePost(p, tk);
		assertEquals(10, p.getScorePost());
	}
	
	@Test
	public void updateScorePost5(){
		Post p = new Post(new DateTime("2010-02-09T04:05:20.777+0000") ,299101, 4661, "Michael Wang");
		DateTime tk = new DateTime("2010-02-14T10:05:20.777+0000");
		TraitementPost.updatePost(p, tk);
		assertEquals(5, p.getScorePost());
	}
	
	@Test
	public void updateScorePost0(){
		Post p = new Post(new DateTime("2010-02-09T04:05:20.777+0000") ,299101, 4661, "Michael Wang");
		DateTime tk = new DateTime("2010-02-25T10:05:20.777+0000");
		TraitementPost.updatePost(p, tk);
		assertEquals(0, p.getScorePost());
	}
	
	@Test
	public void sortByScoreTotalTest() {

		ArrayList<Post> expected = new ArrayList<Post>();
		ArrayList<Post> actual = new ArrayList<Post>();
		expected.add(new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu"));
		expected.add(new Post(new DateTime("2010-02-02T19:53:43.226+0000") ,299101, 4661, "Michael Wang"));
		expected.get(0).setScoreTotal(20);
		expected.get(1).setScoreTotal(10);		
		actual.add(expected.get(1));
		actual.add(expected.get(0));
		Main.sortPost(actual);

		
		Boolean test;
		if ((actual.get(0).equals(expected.get(0))) && (actual.get(1).equals(expected.get(1)))){
			test = true;
		} else{
			test = false;
		}

		assertEquals(true, test);
	}
	
	@Test
	public void nbCommentateursTest() {
		Main test = new Main();
		Post p = new Post(new DateTime("2010-02-01T05:12:32.921+0000") ,1039993, 3981, "Lei Liu");
		
		ArrayList<Comment> tabCom = new ArrayList<Comment>();
		tabCom.add(new Comment(new DateTime("2010-02-09T04:05:20.777+0000") ,529590, 2886, "Baoping Wu", -1, 1039993));
		tabCom.add(new Comment(new DateTime("2010-02-09T04:20:53.281+0000") ,529589, 2886, "Baoping Wu", -1, 1039993));
		tabCom.add(new Comment(new DateTime("2010-02-09T06:08:38.206+0000") ,529594, 3633, "Jun Wu", -1, 1039993));
		tabCom.add(new Comment(new DateTime("2010-02-09T06:08:38.206+0000") ,529595, 3635, "Samuel Goussault", -1, 123));
		tabCom.add(new Comment(new DateTime("2010-02-09T06:08:38.206+0000") ,529596, 3635, "Samuel Goussault", -1, 1039993));
		test.setTabComment(tabCom);
		test.countCommenters(p);
		assertEquals(3, p.getNbCommentateur());
	}

    @Test
    public void traitementTotalTest() {
        Main.traitementTotal();

        File file1 = new File("ressources/data/test/Q1Basic2/_expectedQ1.txt");
        File file2 = new File("export/historique.txt");

        Boolean sameFile = false;

        try {
            Scanner scnr1 = new Scanner(file1);
            Scanner scnr2 = new Scanner(file2);

            while(!sameFile && scnr1.hasNext() && scnr2.hasNext()){
                sameFile = !scnr1.nextLine().equals(scnr2.nextLine());
            }

            sameFile = !sameFile && !(scnr1.hasNext()||scnr2.hasNext());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(sameFile);
    }

}
