package HPP_PROJECT;

import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;

public class TraitementComment implements Runnable{

	private DateTime tk;
	private BlockingQueue<Comment> queueComment;
	private ReaderComment rdComment;
	
	public TraitementComment(DateTime tk, BlockingQueue<Comment> queueComment, ReaderComment rdComment) {
		super();
		this.tk = tk;
		this.queueComment = queueComment;
		this.rdComment = rdComment;
	}

	public void traitement() {	
		Comment cmt = rdComment.getCurrentComment();
		
		try {
			queueComment.put(cmt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cmt = rdComment.readNextComment();
	}
	
	public DateTime getTk() {
		return tk;
	}


	public void setTk(DateTime tk) {
		this.tk = tk;
	}

	@Override
	public void run() {
		traitement();
		
	}
	
	
}
