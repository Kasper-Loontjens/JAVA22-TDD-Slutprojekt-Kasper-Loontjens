package test;

import main.Buffer;
import main.Consumer;

public class MockConsumer implements Consumer{
	
	MockBuffer buffer;
	
	public MockConsumer(MockBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {		
	}

	@Override
	public void stopRunning() {		
	}
	
	public synchronized void removeItem() {
		buffer.remove();
	}
	

}
