package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.joda.time.DateTime;

public class ReaderPost {

    public static Post ReadPost(String fichier){
    	String line = "";
    	BufferedReader br;
    	
    	try {
    		 br = new BufferedReader(new FileReader(fichier)); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return split(line);

    }
    
    public static Post split(String line)
    {
    	
    	String[] list = line.split("\\|");
    	
    	return new Post()
    }
    
    
}