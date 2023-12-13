package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {


	@Test
	@DisplayName("all uppercase letters should be returned as lowercase")
	public void itemToStringUpperToLowerTest() {
		MockItem item = new MockItem("ITEM");
		assertEquals("item", item.getId());
	}
	
	@Test
	@DisplayName("id with numbers test")
	public void checkThatIdCanBeNumbersTest() {
		MockItem item = new MockItem("item212");
		assertEquals("item212", item.getId());
	}
	
	@Test
	@DisplayName("id with symbols test")
	public void checkThatIdCanBeSymbolsTest() {
		MockItem item = new MockItem("item %&!");
		assertEquals("item %&!", item.getId());
	}
	
	@Test
	@DisplayName("id is empty test")
	public void checkThatIdCanBeEmptyTest() {
		MockItem item = new MockItem("");
		assertEquals("", item.getId());
	}
	
	@Test
	@DisplayName("set id with numbers test")
	public void checkThatSetIdCanBeNumbersTest() {
		MockItem item = new MockItem("item");
		item.setId("item212");
		assertEquals("item212", item.getId());
	}
	
	@Test
	@DisplayName("set id with symbols test")
	public void checkThatSetIdCanBeSymbolsTest() {
		MockItem item = new MockItem("item");
		item.setId("item %&!");
		assertEquals("item %&!", item.getId());
	}
	
	@Test
	@DisplayName("set id is empty test")
	public void checkThatSetIdCanBeEmptyTest() {
		MockItem item = new MockItem("item");
		item.setId("");
		assertEquals("", item.getId());
	}
	
	@Test
	@DisplayName("setId Test")
	public void setIdTest() {
		MockItem item = new MockItem("ITEM");
		item.setId("HanD Bag");
		assertEquals("hand bag", item.getId());
	}
	
	@Test
	@DisplayName("Item id is null test ")
	public void itemIdIsNullTest() {		
		assertThrows(NullPointerException.class,()-> new MockItem(null));
	}
	
	@Test
	@DisplayName("Item setId is null test ")
	public void itemSetIdIsNullTest() {
		
		MockItem item = new MockItem("test");
		assertThrows(NullPointerException.class,()-> item.setId(null));
		assertNotNull(item.getId());
		assertEquals("test", item.getId());
		}

	@Test
	@DisplayName("toString should return id as string test")
	public void checkThatToStringReturnsIdAsStringTest() {
		MockItem item = new MockItem("iTem %&!");
		assertEquals("item %&!", item.toString());
	}	
}
