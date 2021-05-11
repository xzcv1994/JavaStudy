package thread.httpurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpClientThread implements Runnable{

	private String threadName;
	
	public HttpClientThread(String name) {
		this.threadName = name;
	}
	public void start() {
		System.out.println("thread" + this.threadName + "'s start() has been called!");
		Thread thread = new Thread(this, "thread" + this.threadName);
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "'s run() has been called!");
		URL url;
		try {
			url = new URL("http://www.naver.com");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			InputStreamReader nowInputStreamReader = new InputStreamReader(httpUrlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(nowInputStreamReader);
			String result = null;
			
			for(int i=0; i<3; i++) {
				result += bufferedReader.readLine();
			}
			
			httpUrlConnection.disconnect();
			System.out.println(Thread.currentThread().getName() + "'s result : " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
