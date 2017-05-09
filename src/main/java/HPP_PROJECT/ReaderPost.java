package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ReaderPost {

    public static Post ReadPost(String fichier){
    	String line = "";
    	BufferedReader br;
    	
    	try {
    		 br = new BufferedReader(new FileReader(fichier)); 
    		 line = br.readLine();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return split(line);
    }
    
    public static Post split(String line)
    {
    	
    	String[] list = line.split("\\|");
    	DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    	DateTime dt = formatter.parseDateTime(list[0]);
    	
    	return new Post(dt, Integer.parseInt(list[1]), Integer.parseInt(list[2]), list[4]);
    }
    
    
}