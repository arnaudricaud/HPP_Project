package HPP_PROJECT;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.omg.CORBA.COMM_FAILURE;

import java.io.BufferedReader;
import java.io.FileReader;

public final class ReaderComment {

    private BufferedReader BR;
    private static volatile ReaderComment instance = null;
    private String adresseFichier = "../data/comments.dat";

    private ReaderComment(){
        try{
            BR = new BufferedReader(new FileReader(adresseFichier));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public final static ReaderComment getInstance(){
        if(ReaderComment.instance == null){
            synchronized (ReaderComment.class){
                ReaderComment.instance = new ReaderComment();
            }
        }
        return instance;
    }

    private Comment readNextComment() {
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
            return createComment(line);
        }
    }

    private Comment createComment(String line){
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