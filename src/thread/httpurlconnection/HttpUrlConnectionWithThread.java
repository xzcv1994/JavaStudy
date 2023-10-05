package thread.httpurlconnection;

public class HttpUrlConnectionWithThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Thread.currentThread().getName() + " start!");

		for(int i=0; i<2; i++) {
			HttpClientRunnable httpClientRunnable = new HttpClientRunnable();
			String threadName = "runnable" + (i + 1);
			Thread newThread = new Thread(httpClientRunnable, threadName);
			newThread.start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for(int i=0; i<2; i++) {
			HttpClientThread httpClientThread = new HttpClientThread();
			httpClientThread.setName("thread" + (i+1));
			httpClientThread.start();
		}
	}
}
