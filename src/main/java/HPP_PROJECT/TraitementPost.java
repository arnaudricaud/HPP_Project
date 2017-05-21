package HPP_PROJECT;

import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;


public class TraitementPost implements Runnable{
	
	private DateTime tk;
	private BlockingQueue<Post> queuePost;
	private ReaderPost rdPost;
	

	public TraitementPost(DateTime tk, BlockingQueue<Post> queuePost, ReaderPost rdPost) {
		super();
		this.tk = tk;
		this.queuePost = queuePost;
		this.rdPost = rdPost;
	}

	public void traitement(){
		Post pst = rdPost.getCurrentPost();
		
		try {
			queuePost.put(pst);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pst = rdPost.readNextPost();
	}

	@Override
	public void run() {
		traitement();
	}

	public DateTime getTk() {
		return tk;
	}

	public void setTk(DateTime tk) {
		this.tk = tk;
	}
	
}

