package HPP_PROJECT;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.FileReader;

public final class ReaderComment {

    private BufferedReader BR;
    private static volatile ReaderComment instance = null;
    private Comment currentComment;
    private String str_tk;

    private ReaderComment(String adresseFichier){
        try{
            BR = new BufferedReader(new FileReader(adresseFichier));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public final static ReaderComment getInstance(String adresseFichier){
        if(ReaderComment.instance == null){
            synchronized (ReaderComment.class){
                ReaderComment.instance = new ReaderComment(adresseFichier);
            }
        }
        return ReaderComment.instance;
    }
    
    public static void reset(String adresseFichier) {
        instance = new ReaderComment(adresseFichier);
    }
    
    
    public Comment getCurrentComment(){
    	return currentComment;
    }

    public Comment readNextComment() {
        String line = "vide";

        try {
            line = BR.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (line == null || line.equals("")){
        	currentComment = new Comment(new DateTime(), -1, -1, "", -1, -1);
            return currentComment;
        }
        else{
        	currentComment = createComment(line);
        	currentComment.setStr_ts(str_tk);
            return currentComment;
        }
    }

    public Comment createComment(String line){
        String[] list = line.split("\\|");
        
        
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTime dt = formatter.parseDateTime(list[0]);
        str_tk = list[0];
        

        if(list[5].equals("")){
            return new Comment(dt,
                    Long.parseLong(list[1]), Long.parseLong(list[2]),
                    list[4],
                    -1,  Long.parseLong(list[6]));
        }
        else{
        	return new Comment(dt,
                    Long.parseLong(list[1]), Long.parseLong(list[2]),
                    list[4],
                    Long.parseLong(list[5]), -1);
        }
    }
}