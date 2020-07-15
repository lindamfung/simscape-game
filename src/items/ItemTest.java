package items;

/**
 * The ItemTest class performs testing on non-trivial methods from the Item class.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void testGetName() {
		
		//Given
		Item item = new ToolItem("book", "Red book.");

		// Then
		Assert.assertEquals("book", item.getName());
	}

	@Test
	void testGetDescription() {
		
		//Given
		Item item = new ToolItem("book", "Red book.");

		// Then
		Assert.assertEquals("Red book.", item.getDescription());
	}

}
