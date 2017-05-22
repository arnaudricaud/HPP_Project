package HPP_PROJECT;

import java.util.concurrent.BlockingQueue;

import org.joda.time.DateTime;

public class TraitementPost implements Runnable {

	private BlockingQueue<Post> queuePost;
	private ReaderPost rdPost;

	public TraitementPost(BlockingQueue<Post> queuePost, ReaderPost rdPost) {
		super();
		this.queuePost = queuePost;
		this.rdPost = rdPost;
		rdPost.readNextPost();
	}

	public void traitement() {
		Post pst = rdPost.getCurrentPost();
		while (pst.getUser_id() != -1) {
			try {
				queuePost.put(pst);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst = rdPost.readNextPost();
		}

		try {
			queuePost.put(pst);
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
