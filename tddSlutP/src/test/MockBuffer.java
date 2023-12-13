package test;

import java.util.Queue;

import main.Buffer;
import main.Item;

public class MockBuffer extends Buffer {
	
	
	// To test size of the queue
	public Queue<Item> getBuffer() {
		return buffer;
	}
}
