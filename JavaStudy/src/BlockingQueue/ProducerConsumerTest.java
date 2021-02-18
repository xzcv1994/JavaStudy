package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerTest {
	public static void main(String[] args) {
		BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(30);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		System.out.println("Producer and Consumer roll play");
	}
}

