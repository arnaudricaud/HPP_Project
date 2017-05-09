package HPP_PROJECT;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderComment {

    public String ReadComment(String fichier) {
        BufferedReader br;
        String line = "vide";

        try {
            br = new BufferedReader(new FileReader(fichier));
            line = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return line;
    }

    public static Comment commentCreate(String line){
        String[] list = line.split("\\|");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTime dt = formatter.parseDateTime(list[0]);

        return new Comment(dt,
                Integer.parseInt(list[1]), Integer.parseInt(list[2]),
                list[3], list[4],
                Integer.parseInt(list[5]), Integer.parseInt(list[6]));
    }
}