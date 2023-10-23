package thread.httpurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientRunnable implements Runnable{

	public HttpClientRunnable() {}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "'s run() has been called!");
		URL url;
		try {
			url = new URL("https://www.naver.com");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			InputStreamReader nowInputStreamReader = new InputStreamReader(httpUrlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(nowInputStreamReader);
			String result = null;

			for(int i=0; i<2; i++) {
				result += bufferedReader.readLine();
			}

			httpUrlConnection.disconnect();
			System.out.println(Thread.currentThread().getName() + "'s result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
