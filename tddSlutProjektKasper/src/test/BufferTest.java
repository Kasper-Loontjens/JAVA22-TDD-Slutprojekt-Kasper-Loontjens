package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import main.Item;

public class BufferTest {
	
	@Test
	@RepeatedTest(10)
	@DisplayName("Test producer run for 0.4 seconds 0.1 second interval")
	public void producerRun4TimesTest() {
		MockBuffer buffer = new MockBuffer();
		
		MockProducer producer = new MockProducer(buffer, 1, "t1");
		Thread producerThread = new Thread(producer);
		producerThread.start();
		
		MockTimer mockTimer = new MockTimer(producer, 4);
//		Thread timerThread = new Thread(mockTimer);
//		timerThread.start();
		mockTimer.run();

		assertEquals(4, buffer.getBuffer().size());
		
		// Fails at irregular times, added repeated tests as proof but the fails are most likely because of how the mock classes were written, not buffer.
	}
	
	@Test
	@RepeatedTest(5)
	@DisplayName("Test producer, consumer run for 0.4 seconds 0.1 second interval")
	public void producerAndConsumerRunTest1() {

		MockBuffer buffer = new MockBuffer();
		
		MockProducer producer = new MockProducer(buffer, 1,"t2");
		Thread producerThread = new Thread(producer);
		producerThread.start();
		
		MockConsumer consumer = new MockConsumer(buffer, 1);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		MockTimer mockTimer = new MockTimer(producer,consumer, 4);
		mockTimer.run();

		assertEquals(0, buffer.getBuffer().size());
		
	}
	
	@Test
	@RepeatedTest(5)
	@DisplayName("Test producer, consumer run for 0.8 seconds consumer takes faster")
	public void consumerWaitTest() {
		MockBuffer buffer = new MockBuffer();
		
		MockProducer producer = new MockProducer(buffer, 3,"t3");
		Thread producerThread = new Thread(producer);
		producerThread.start();
		
		MockConsumer consumer = new MockConsumer(buffer, 1);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		MockTimer mockTimer = new MockTimer(producer,consumer, 8);
		mockTimer.run();

		assertEquals(0, buffer.getBuffer().size());

	}
	
	@Test
	@RepeatedTest(5)
	@DisplayName("Test producer, consumer run for 0.8 seconds producer creates faster")
	public void producerAndConsumerTest2() {
		MockBuffer buffer = new MockBuffer();
		
		MockProducer producer = new MockProducer(buffer, 1,"t4");
		Thread producerThread = new Thread(producer);
		producerThread.start();
		
		MockConsumer consumer = new MockConsumer(buffer, 2);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		MockTimer mockTimer = new MockTimer(producer,consumer, 8);
		mockTimer.run();

		assertEquals(4, buffer.getBuffer().size());
	}
	
	// remove tests
	
	@Test
	@RepeatedTest(1)
	@DisplayName("Trying to take from empty buffer when notified should return exception")
	public void emptyBufferTest() {
		MockBuffer buffer = new MockBuffer();
		buffer.add(new Item("ddd"));
		buffer.remove();
		MockTimer mockTimer = new MockTimer(buffer,20, true);
		Thread timerThread = new Thread(mockTimer);
		timerThread.start();
		mockTimer.run();

		fail();
		//assertThrows(InterruptedException.class,()-> buffer.remove());
		
		// Did not get this to work, the wait function hindered the program from continuing despite notify
	}
	
	@Test
	@RepeatedTest(1)
	@DisplayName("Trying to take from buffer")
	public void remove1ItemFromBufferTest() {
		MockBuffer buffer = new MockBuffer();
		Item item = new Item("Test");
		buffer.add(item);
		assertEquals(item, buffer.remove());
	}	
	
	// add tests
	
	@Test
	@RepeatedTest(1)
	@DisplayName("Buffer add test with 2 items")
	public void adding2ItemsTest() {
		MockBuffer buffer = new MockBuffer();
		Item item1 = new Item("item 1");
		buffer.add(item1);
		Item item2 = new Item("item 2");
		buffer.add(item2);
		
		assertTrue(buffer.getBuffer().contains(item1));
		assertTrue(buffer.getBuffer().contains(item2));

	}
	
	@Test
	@RepeatedTest(1)
	@DisplayName("Buffer list test with null items")
	public void addingNullItemsTest() {
		MockBuffer buffer = new MockBuffer();
		buffer.add(null);
		
		assertTrue(buffer.getBuffer().isEmpty());
		
		// Buffer adds object despite it being null

	}
	
	
}
