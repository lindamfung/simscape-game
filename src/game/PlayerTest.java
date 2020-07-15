package game;

/**
 * The PlayerTest class performs testing on non-trivial methods from the Player class.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import items.Item;
import items.ItemContainer;
import items.PuzzleItem;
import items.ToolItem;

class PlayerTest {

	@Test
	void testCompleteLevel() {
		
		//Given
		String desc1 = "The room is plain white with a gray lock box in the middle of the floor. "
				+ "\nThere's a door directly in front of you labeled 9522 but it is locked...";
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		PuzzleItem grayLockBox = new PuzzleItem(
				"gray lock box", 
				"It requires a 4-digit code to unlock.", 
				"9522", 
				key1);
		itemsList1.add(grayLockBox);
		Room roomLevel1 = new Room(1, desc1, null, null, itemsList1);
		Player player = new Player("Linda", roomLevel1);
				
		// When
		player.completeLevel();
		
		// Then
		Assert.assertFalse(roomLevel1.getIsDoorLocked());
		Assert.assertNull(player.getCurrentRoom());
		Assert.assertEquals(1, player.getLevelsCompleted());
	}

}
