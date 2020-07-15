package items;

/**
 * The ItemContainerTest class performs testing on non-trivial methods from the ItemContainer class.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ItemContainerTest {
	
	@Test
	void testItemFound() {
		
		// Given
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ClueItem clue1 = new ClueItem("a folded paper", "There is something written on it.", "blue");
		ToolItem tool1 = new ToolItem("screwdriver", "Use it to unscrew something.");

		// When
		itemsList1.itemFound(key1);
		itemsList1.itemFound(clue1);

		// Then
		Assert.assertTrue(itemsList1.getContainer().contains(key1));
		Assert.assertTrue(itemsList1.getContainer().contains(clue1));
		Assert.assertFalse(itemsList1.getContainer().contains(tool1));
	}

	@Test
	void testItemUsed() {
		
		// Given
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ClueItem clue1 = new ClueItem("a folded paper", "There is something written on it.", "blue");
		ToolItem tool1 = new ToolItem("screwdriver", "Use it to unscrew something.");
		itemsList1.itemFound(key1);
		itemsList1.itemFound(clue1);
		itemsList1.itemFound(tool1);

		// When
		itemsList1.itemUsed(key1);
		itemsList1.itemUsed(clue1);

		// Then
		Assert.assertFalse(itemsList1.getContainer().contains(key1));
		Assert.assertFalse(itemsList1.getContainer().contains(clue1));
		Assert.assertTrue(itemsList1.getContainer().contains(tool1));
	}

	@Test
	void testContainerSize() {
		
		// Given
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ClueItem clue1 = new ClueItem("a folded paper", "There is something written on it.", "blue");
		ToolItem tool1 = new ToolItem("screwdriver", "Use it to unscrew something.");
		itemsList1.itemFound(key1);
		itemsList1.itemFound(clue1);
		itemsList1.itemFound(tool1);
		
		// Then
		Assert.assertEquals(3, itemsList1.containerSize());
		Assert.assertNotEquals(5, itemsList1.containerSize());
		
		// When
		itemsList1.itemUsed(clue1);
		
		// Then
		Assert.assertEquals(2, itemsList1.containerSize());
		Assert.assertNotEquals(5, itemsList1.containerSize());
	}

	@Test
	void testGetItem() {

		// Given
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ClueItem clue1 = new ClueItem("a folded paper", "There is something written on it.", "blue");
		ToolItem tool1 = new ToolItem("screwdriver", "Use it to unscrew something.");
		itemsList1.itemFound(key1);
		itemsList1.itemFound(clue1);
		itemsList1.itemFound(tool1);
		
		// Then
		Assert.assertEquals(key1, itemsList1.getItem(0));
		Assert.assertEquals(clue1, itemsList1.getItem(1));
		Assert.assertEquals(tool1, itemsList1.getItem(2));
		Assert.assertNotEquals(clue1, itemsList1.getItem(0));
	}

	@Test
	void testClearContainer() {

		// Given
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ClueItem clue1 = new ClueItem("a folded paper", "There is something written on it.", "blue");
		ToolItem tool1 = new ToolItem("screwdriver", "Use it to unscrew something.");
		itemsList1.itemFound(key1);
		itemsList1.itemFound(clue1);
		itemsList1.itemFound(tool1);
		
		// When
		itemsList1.clearContainer();
		
		// Then
		Assert.assertEquals(0, itemsList1.containerSize());
		Assert.assertNotEquals(5, itemsList1.containerSize());
	}

}
