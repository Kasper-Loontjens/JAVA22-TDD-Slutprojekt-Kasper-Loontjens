package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import main.Item;

class BufferTest {
	
	@Test
	@DisplayName("Should throw an exception when an empty buffer tries to remove item")
	public void checkExceptionWhenEmptyTest()  {
		MockBuffer buffer = new MockBuffer();
		MockConsumer consumer = new MockConsumer(buffer);
		Thread thread = new Thread(()->
			assertThrows(InterruptedException.class, ()->
			consumer.removeItem()));
		// starting and immediately interrupting remove() should throw 
		thread.start();
		thread.interrupt();
	}
	
	@Test
	@DisplayName("When one item is added and one is removed buffer size should be 0")
	public void checkAddOneRemoveOneSizeTest() {
		MockBuffer buffer = new MockBuffer();
		MockConsumer consumer = new MockConsumer(buffer);
		MockProducer producer = new MockProducer(buffer);
		Item item = new Item("item 1");
		producer.addItem(item);
		consumer.removeItem();
		assertEquals(0, buffer.getBuffer().size());
	}
	
	@Test
	@DisplayName("When two items are added, buffer size should be 2")
	public void checkSizeAdd2Test() {
		MockBuffer buffer = new MockBuffer();
		MockConsumer consumer = new MockConsumer(buffer);
		MockProducer producer = new MockProducer(buffer);
		producer.addItem(new Item("i1"));
		producer.addItem(new Item("i2"));
		assertEquals(2, buffer.getBuffer().size());
	}
	
	@Test
	@DisplayName("When Remove has been called it should wait to remove until add has been called, buffer size should be 0")
	public void checkRemoveShouldWaitUntilAddHasNotifiedTest() throws InterruptedException {
		MockBuffer buffer = new MockBuffer();
		MockConsumer consumer = new MockConsumer(buffer);
		MockProducer producer = new MockProducer(buffer);
		Item item = new Item("item 1");
		
		Thread thread = new Thread(()->{
			consumer.removeItem();
			});		
		Thread thread2 = new Thread(()->{
			producer.addItem(item);
		});
		// thread tries to remove item but there is no item to remove
		thread.start();
		// thread2 adds item which first thread then removes because of wait function
		thread2.start();
		// Sleep is here to make sure the threads aren´t interrupted too early
		thread.sleep(100);
		thread2.sleep(100);

		assertEquals(0, buffer.getBuffer().size());
		thread.interrupt();
		thread2.interrupt();
	}
	
	@Test
	@DisplayName("When Remove has been called it should wait to remove until add has been called, buffer size should be 1")
	public void checkRemoveShouldWaitUntilAddHasNotifiedTwiceTest() throws InterruptedException {
		MockBuffer buffer = new MockBuffer();
		MockConsumer consumer = new MockConsumer(buffer);
		MockProducer producer = new MockProducer(buffer);
		
		Thread threadRemove = new Thread(()->
			consumer.removeItem());	
		Thread threadRemove2 = new Thread(()->
			consumer.removeItem());		
		// thread tries to remove item but there is no item to remove
		threadRemove.start();
		threadRemove2.start();
		
		// adds item which threads then removes because of wait function
		producer.addItem(new Item("itemx"));
		producer.addItem(new Item("itemx"));
		producer.addItem(new Item("itemx"));
		// Sleep is here to make sure the threads aren´t interrupted too early
		threadRemove.sleep(100);
		threadRemove2.sleep(100);

		assertEquals(1, buffer.getBuffer().size());
		threadRemove.interrupt();
		threadRemove2.interrupt();

	}
	
	@Test
	@DisplayName("Buffer list test with null items")
	public void addingNullItemsTest() {
		MockBuffer buffer = new MockBuffer();
		buffer.add(null);
		assertTrue(buffer.getBuffer().isEmpty());
		// Buffer adds object despite it being null
	}
	
	@Test
	@DisplayName("The remove function should return the same item that was added.")
	public void checkRemoveReturnsCorrectItemTest() {
		MockBuffer buffer = new MockBuffer();
		Item item = new Item("returnTest");
		buffer.add(item);
		assertEquals(item, buffer.remove());
	}
	
	@Test
	@DisplayName("k")
	public void aTest() {
		
	}
	
	
}
