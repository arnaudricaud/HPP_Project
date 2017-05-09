package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderComment {
	
    public void ReadComment(String fichier) throws FileNotFoundException, IOException{
    	try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	       // process the line.
    	    }
    	}
    }

}
