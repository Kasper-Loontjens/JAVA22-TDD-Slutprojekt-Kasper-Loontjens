package test;

public class MockTimer implements Runnable {
	
	// This class is made to stop other methods from running indefinitely 
	
	MockConsumer mockConsumer;
	MockProducer mockProducer;
	MockBuffer mockBuffer;
	int time;
	boolean toNotify = false;
	
	public MockTimer(MockConsumer mockConsumer, int time) {
		this.mockConsumer = mockConsumer;
		this.time = time;
	}
	
	public MockTimer(MockProducer mockProducer, int time) {
		this.mockProducer = mockProducer;
		this.time = time;
	}
	public MockTimer(MockProducer mockProducer,MockConsumer mockConsumer, int time) {
		this.mockProducer = mockProducer;
		this.mockConsumer = mockConsumer;
		this.time = time;
	}
	
	public MockTimer(MockBuffer mockBuffer, int time, boolean toNotify) {
		this.mockBuffer = mockBuffer;
		this.time = time;
		this.toNotify=toNotify;
	}

	public void run() {
		try {
			Thread.sleep(time*100);
			
			if (mockConsumer != null) {
				mockConsumer.stopRunning();
			}
			
			if (mockProducer != null) {
				mockProducer.stopRunning();
			}
			if (toNotify == true) {
				mockBuffer.notifyMe();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
	}

}
