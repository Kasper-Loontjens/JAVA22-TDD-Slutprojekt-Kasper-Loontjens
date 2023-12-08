package test;

import main.Buffer;
import main.Producer;
import main.Item;


public class MockProducer implements Producer, Runnable {
	
	private Buffer buffer;
	private boolean isRunning = true;
	int interval = 1;
	String id;
	
	public MockProducer(Buffer buffer, int interval, String id) {
		this.buffer = buffer;
		this.interval = interval;
		this.id = id;
	}

	public void run() {
		while (isRunning) {
			try {
				// Adds an item from buffer each interval * second
				buffer.add(new Item(id));
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
