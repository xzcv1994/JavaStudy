package thread.httpurlconnection;

public class HttpUrlConnectionWithThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Thread.currentThread().getName() + " start!");
		
		for(int i=0; i<10; i++) {
			HttpClientThread httpClientThread = new HttpClientThread(String.valueOf(i));
			String threadName = "thread" + (i+1);
			Thread newThread = new Thread(httpClientThread, threadName);
			newThread.start();
		}
		
		for(int i=10; i<10; i++) {
			HttpClientThread httpClientThread = new HttpClientThread(String.valueOf(i));
			httpClientThread.start();
		}
		
	}

}
