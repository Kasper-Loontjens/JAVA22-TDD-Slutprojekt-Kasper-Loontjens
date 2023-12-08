package test;

import main.Buffer;
import main.Consumer;

public class MockConsumer implements Consumer, Runnable {
	
	Buffer buffer;
	boolean isRunning = true;
	int interval;
	
	public MockConsumer(Buffer buffer, int interval) {
		this.buffer=buffer;
		this.interval = interval;
	}
	

	public void run() {
		while (isRunning) {
			try {
				// Removes an item from buffer each interval * second
				buffer.remove(); 
				Thread.sleep(interval*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRunning() {
		isRunning = false;

	}
	

}
