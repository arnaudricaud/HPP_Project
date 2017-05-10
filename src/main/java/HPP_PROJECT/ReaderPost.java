package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileReader;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ReaderPost {
	
	private BufferedReader br;
	private static volatile ReaderPost instance = null;
	
	private ReaderPost(String fichier) {
		try{
			br = new BufferedReader(new FileReader(fichier));
		}catch(Exception e){
    		e.printStackTrace();
    	}
	}

	public final static ReaderPost getInstance(String fichier){
		if(ReaderPost.instance == null){
			synchronized (ReaderPost.class){
				ReaderPost.instance = new ReaderPost(fichier);
			}
		}
		return ReaderPost.instance;
	}

    public Post readNextPost(){
    	String line = "";
    	
    	try {  		  
    		 line = br.readLine();
    	}catch(Exception e){
    		e.printStackTrace();
    	}

		if (line == null){
			return new Post(new DateTime(), -1, -1, "");
		}
		else{
			return createPost(line);
		}
    }
    
    public Post createPost(String line)
    {
    	
    	String[] list = line.split("\\|");
    	DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    	DateTime dt = formatter.parseDateTime(list[0]);
    	
    	return new Post(dt, Integer.parseInt(list[1]), Integer.parseInt(list[2]), list[4]);
    }
    
    
}