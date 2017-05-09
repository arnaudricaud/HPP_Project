package HPP_PR/OJECT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderComment {
	
    public void ReadComment(String fichier) throws FileNotFoundException, IOException{
    	BufferedReader br;
    	
    	try {
    		 br = new BufferedReader(new FileReader(fichier)); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	    String line;
    	  /*  while ((line = br.readLine()) != null) {
    	       // process the line.
    	    }*
    	}
    }

}
