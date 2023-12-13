package test;

import main.Buffer;
import main.Item;
import main.Producer;

public class MockProducer implements Producer {
	
	MockBuffer buffer;
	
	public MockProducer(MockBuffer buffer) {
		this.buffer = buffer;
	}


	@Override
	public void run() {		
	}

	@Override
	public void stopRunning() {		
	}
	
	public synchronized void addItem(Item item) {
		buffer.add(item);
	}

}
