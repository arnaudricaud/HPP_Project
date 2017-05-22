package HPP_PROJECT;

import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;

public class TraitementComment implements Runnable {

	private BlockingQueue<Comment> queueComment;
	private ReaderComment rdComment;

	public TraitementComment(BlockingQueue<Comment> queueComment, ReaderComment rdComment) {
		super();
		this.queueComment = queueComment;
		this.rdComment = rdComment;
		rdComment.readNextComment();
	}

	public void traitement() {
		Comment cmt = rdComment.getCurrentComment();
		while (cmt.getUser_id() != -1) {
			try {
				queueComment.put(cmt);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cmt = rdComment.readNextComment();
		}
		
		try {
			//POISONPILL
			queueComment.put(cmt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		traitement();
	}

}
