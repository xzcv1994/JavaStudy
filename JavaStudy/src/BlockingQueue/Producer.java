package BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

	private BlockingQueue<Message> queue;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<30; i++) {
			Message msg = new Message(""+i);
			try{
				Thread.sleep(1000);
				queue.put(msg);
				System.out.println("Produces " + msg.getMsg());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Message msg = new Message("exit");
		
		try {
			queue.put(msg);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Producer(BlockingQueue<Message> q) {
		this.queue = q;
	}
	
	
}
