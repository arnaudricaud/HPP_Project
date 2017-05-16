package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import HPP_PROJECT.Main;

public class FileTest {

    @Test
    public void TestQ1() {
        Main main = new Main();
        main.traitementTotal("ressources/data/test/Q1Basic/posts.dat","ressources/data/test/Q1Basic/comments.dat");

        File file1 = new File("ressources/data/test/Q1Basic/_expectedQ1.txt");
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
