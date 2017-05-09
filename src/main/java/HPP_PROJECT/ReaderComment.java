package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReaderComment {

    private ArrayList<Comment> commentList = new ArrayList();

    public void ReadComment(String fichier) {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fichier));
            StringBuffer line;
            while (br.readLine() != null) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}