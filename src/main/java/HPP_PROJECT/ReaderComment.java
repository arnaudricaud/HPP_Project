package HPP_PROJECT;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.omg.CORBA.COMM_FAILURE;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderComment {

    private static BufferedReader BR;

    public ReaderComment(String fichier){
        try{
            BR = new BufferedReader(new FileReader(fichier));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Comment ReadComment() {
        String line = "vide";

        try {
            line = BR.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (line == null){
            return new Comment(new DateTime(), -1, -1, "", -1, -1);
        }
        else{
            return commentCreate(line);
        }
    }

    public static Comment commentCreate(String line){
        String[] list = line.split("\\|");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTime dt = formatter.parseDateTime(list[0]);

        if(list[5].equals("")){
            list[5] = "-1";
        }
        else{
            list[6] = "-1";
        }

        return new Comment(dt,
                Integer.parseInt(list[1]), Integer.parseInt(list[2]),
                list[4],
                Integer.parseInt(list[5]), Integer.parseInt(list[6]));
    }
}