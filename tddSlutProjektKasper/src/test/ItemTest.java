package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import main.Item;

class ItemTest {


	@Test
	@RepeatedTest(1)
	@DisplayName("all uppercase letters should be returned as lowercase")
	public void itemToStringUpperToLowerTest() {
		Item item = new Item("ITEM");
		assertEquals("item", item.toString());
	}
	
	@Test
	@RepeatedTest(1)
	@DisplayName("setId Test")
	public void setIdTest() {
		Item item = new Item("ITEM");
		item.setId("HanD Bag");
		assertEquals("hand bag", item.toString());
	}
	
	@Test
	@DisplayName("Item id is null test ")
	public void itemIdIsNullTest() {
		
		Item item = new Item(null);
		assertNull(item.toString() );
		assertNull(new Item(null));
		
		// When id is null no function works but the item is also neither null nor does it throw any exception
	}
	
	@Test
	@DisplayName("Item setId is null test ")
	public void itemSetIdIsNullTest() {
		
		Item item = new Item("test");
		item.setId(null);
		assertNotNull(item.toString());
		assertEquals("test", item.toString());
		assertNull(item.toString());
		
		// When id is set with null an error occurs, no Try/catch.
	}

}
