package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.joda.time.DateTime;

public class ReaderPost {

    public static Post ReadPost(String fichier){
    	String line = "";
    	try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
     	    	line = br.readLine();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	//return line;
    }
    
    public Post split(String line)
    {
    	String[] list = line.split("\\|");
    	
    	return new Post()
    }
    
    
}