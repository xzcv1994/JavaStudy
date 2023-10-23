package thread.httpurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionWithThread {

	public static void main(String[] args) {
		//1.Runnable 구현객체 실행
		//Runnable을 구현하면 Thread를 사용할때 보다 확장성이 좋다.
		for(int i=0; i<2; i++) {
			HttpClientRunnable httpClientRunnable = new HttpClientRunnable();
			Thread newThread = new Thread(httpClientRunnable, "runnable" + (i + 1));
			newThread.start();
		}

		//쓰레드 슬립
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//2.Thread 상속객체 실행
		//Thread의 실행방법이 아주 미세하게 더 간단하다.
		for(int i=0; i<2; i++) {
			HttpClientThread httpClientThread = new HttpClientThread("thread" + (i+1));
			httpClientThread.start();
		}

		//쓰레드 슬립
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//3.람다를 이용한 Runnable사용
		for(int i=0; i<2; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "'s run() has been called!");
				URL url;
				try {
					url = new URL("https://www.naver.com");
					HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
					InputStreamReader nowInputStreamReader = new InputStreamReader(httpUrlConnection.getInputStream());
					BufferedReader bufferedReader = new BufferedReader(nowInputStreamReader);
					String result = null;

					for(int j=0; j<2; j++) {
						result += bufferedReader.readLine();
					}

					httpUrlConnection.disconnect();
					System.out.println(Thread.currentThread().getName() + "'s result : " + result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			},"runnable with ramda" + (i + 1)).start();
		}
	}
}
