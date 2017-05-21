package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import HPP_PROJECT.Main;
import HPP_PROJECT.ReaderComment;
import HPP_PROJECT.ReaderPost;

public class FileTest {

	@Before
	public void init(){
		Main main = new Main();
		main.getTabPost().clear();
		main.getTabComment().clear();
	}
	
	
    @Test
    public void TestQ1() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Basic/posts.dat");
		ReaderComment.reset("ressources/test/Q1Basic/comments.dat");
        main.traitementTotal("ressources/test/Q1Basic/posts.dat","ressources/data/test/Q1Basic/comments.dat");

        File file1 = new File("ressources/test/Q1Basic/_expectedQ1.txt");
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
    
    @Test
    public void TestQ2() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Basic2/posts.dat");
		ReaderComment.reset("ressources/test/Q1Basic2/comments.dat");
        main.traitementTotal("ressources/test/Q1Basic2/posts.dat","ressources/test/Q1Basic2/comments.dat");

        File file1 = new File("ressources/test/Q1Basic2/_expectedQ1.txt");
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
    
    
    @Test
    public void TestBigTest() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1BigTest/posts.dat");
		ReaderComment.reset("ressources/test/Q1BigTest/comments.dat");
        main.traitementTotal("ressources/test/Q1BigTest/posts.dat","ressources/test/Q1BigTest/comments.dat");

        File file1 = new File("ressources/test/Q1BigTest/_expectedQ1.txt");
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
    
    /*
     * Modifications par rapport au test initial => retrait des trois dernière lignes car pas
     *  d'écriture lors de la mort d'un post.
     */
    @Test
    public void TestCase1() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Case1/posts.dat");
		ReaderComment.reset("ressources/test/Q1Case1/comments.dat");
        main.traitementTotal("ressources/test/Q1Case1/posts.dat","ressources/test/Q1Case1/comments.dat");

        File file1 = new File("ressources/test/Q1Case1/_expectedQ1.txt");
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
    
    @Test
    public void TestCase2() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Case2/posts.dat");
		ReaderComment.reset("ressources/test/Q1Case2/comments.dat");
        main.traitementTotal("ressources/test/Q1Case2/posts.dat","ressources/test/Q1Case2/comments.dat");

        File file1 = new File("ressources/test/Q1Case2/_q1.txt");
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
    
    /*
     * Modifications par rapport au test initial => Suppression des deux premières lignes car
     * tous les events à un même tick sont traités en même temps. Passage à 30 points du post
     * n°1 lors du premier tick (répercuté sur les suivants) (1 post + 2 comment = 30 points).
     * Modif du nombre de commentateurs (suivant le même problème)
     * 
     * Suppression des 2 dernières lignes car pas d'écriture lors de la mort d'un post.
     */
    @Test
    public void TestCase3() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Case3/posts.dat");
		ReaderComment.reset("ressources/test/Q1Case3/comments.dat");
        main.traitementTotal("ressources/test/Q1Case3/posts.dat","ressources/test/Q1Case3/comments.dat");

        File file1 = new File("ressources/test/Q1Case3/_expectedQ1.txt");
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
    
    
    @Test
    public void TestCase4() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Case4/posts.dat");
		ReaderComment.reset("ressources/test/Q1Case4/comments.dat");
        main.traitementTotal("ressources/test/Q1Case4/posts.dat","ressources/test/Q1Case4/comments.dat");

        File file1 = new File("ressources/test/Q1Case4/_q1.txt");
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
    
    /*
     * Modifications par rapport au test initial => suppression de la première et de la 
     * dernière ligne car tous les events sur un même tick sont traités en même temps et 
     * pas d'event à la mort d'un post.
     * Inversion du classement des posts (même nombre de points et en même temps, placés en 1 et 2)
     */
    @Test
    public void TestCase5() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1Case5/posts.dat");
		ReaderComment.reset("ressources/test/Q1Case5/comments.dat");
        main.traitementTotal("ressources/test/Q1Case5/posts.dat","ressources/test/Q1Case5/comments.dat");

        File file1 = new File("ressources/test/Q1Case5/_expectedQ1.txt");
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
    
    /*
     * Echec du test : ce test n'a pas lieu d'être, pas de maj lors de l'ajout d'un commentateur
     * qui ne modifie pas le classement.
     * 
     */
    @Test
    public void TestCommentCount() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1CommentCount/posts.dat");
		ReaderComment.reset("ressources/test/Q1CommentCount/comments.dat");
        main.traitementTotal("ressources/test/Q1CommentCount/posts.dat","ressources/test/Q1CommentCount/comments.dat");

        File file1 = new File("ressources/test/Q1CommentCount/_expectedQ1.txt");
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
    
    
    
    /*
     * Modif par rapport au test initial => Retrait de la dernière ligne du fichier "expected"
     * car nous n'écrivons pas dans le fichier s'il n'y a pas de modifications dans l'ordre du
     * classement des posts.
     */
    @Test
    public void TestPostExpire() {
    	Main main = new Main();
		ReaderPost.reset("ressources/test/Q1PostExpiredComment/posts.dat");
		ReaderComment.reset("ressources/test/Q1PostExpiredComment/comments.dat");
        main.traitementTotal("ressources/test/Q1PostExpiredComment/posts.dat","ressources/test/Q1PostExpiredComment/comments.dat");

        File file1 = new File("ressources/test/Q1PostExpiredComment/_expectedQ1.txt");
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
    
    /*
     * Modification par rapport au test initial => Suppression de la seconde ligne (pas de
     * changements dans le classement donc pas décriture)
     * 
     * 
     */
    @Test
    public void TestPostExpire2() {
		Main main = new Main();
		ReaderPost.reset("ressources/test/Q1PostExpiredComment2/posts.dat");
		ReaderComment.reset("ressources/test/Q1PostExpiredComment2/comments.dat");
        main.traitementTotal("ressources/test/Q1PostExpiredComment2/posts.dat","ressources/test/Q1PostExpiredComment2/comments.dat");

        File file1 = new File("ressources/test/Q1PostExpiredComment2/_expectedQ1.txt");
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
