package HPP_PROJECT;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class TraitementWriter implements Runnable {
	BlockingQueue<Top3> queueTop3;
	Top3 nextTop;

	public TraitementWriter(BlockingQueue<Top3> queueTop3) {
		super();
		this.queueTop3 = queueTop3;
	}

	@Override
	public void run() {
		// INIT element Blocking Queue
		try {
			nextTop = queueTop3.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (nextTop.getStr_tk() != "POISONPILL") {
			// prendre element Blocking Queue
			writeTop3(nextTop);
			try {
				nextTop = queueTop3.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void writeTop3(Top3 topPost) {
		try {
			FileWriter writer = new FileWriter("export/historique.txt", true);
			writer.write(topPost.getStr_tk() + ",");
			if (topPost.getPost1().getUser() != "NOPOST") {
				writer.write(topPost.getPost1().getPost_id() + "," + topPost.getPost1().getUser() + ","
						+ topPost.getPost1().getScoreTotal() + "," + topPost.getPost1().getNbCommentateur() + ",");
			} else {
				writer.write("-,-,-,-,");
			}

			if (topPost.getPost2().getUser() != "NOPOST") {
				writer.write(topPost.getPost2().getPost_id() + "," + topPost.getPost2().getUser() + ","
						+ topPost.getPost2().getScoreTotal() + "," + topPost.getPost2().getNbCommentateur() + ",");
			} else {
				writer.write("-,-,-,-,");
			}
			if (topPost.getPost3().getUser() != "NOPOST") {
				writer.write(topPost.getPost3().getPost_id() + "," + topPost.getPost3().getUser()
						+ "," + topPost.getPost3().getScoreTotal() + ","
						+ topPost.getPost3().getNbCommentateur() + "\r\n");
			} else {
				writer.write("-,-,-,-\r\n");
			}
			writer.close();
		} catch (IOException e) {

		}
	}

}
