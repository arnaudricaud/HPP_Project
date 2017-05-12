package HPP_PROJECT;

import java.io.BufferedReader;
import java.io.FileReader;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ReaderPost {
	
	private BufferedReader br;
	private static volatile ReaderPost instance = null;
	private Post currentPost;
	
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
	
    public static void reset(String adresseFichier) {
        instance = new ReaderPost(adresseFichier);
    }

    public Post getCurrentPost(){
    	return currentPost;
    }
    
    
    public Post readNextPost(){
    	String line = "";
    	
    	try {  		  
    		 line = br.readLine();
    	}catch(Exception e){
    		e.printStackTrace();
    	}

		if (line == null){
			currentPost = new Post(new DateTime(), -1, -1, "");
			return currentPost;
		}
		else{
			currentPost = createPost(line);
			currentPost.setStr_ts(line);
			return currentPost;
		}
    }
    
    
    
    
    public Post createPost(String line)
    {
    	
    	String[] list = line.split("\\|");
    	DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    	DateTime dt = formatter.parseDateTime(list[0]);
    	
    	return new Post(dt, Long.parseLong(list[1]),  Long.parseLong(list[2]), list[4]);
    }
    
    
}