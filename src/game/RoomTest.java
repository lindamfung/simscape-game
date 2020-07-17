package game;

/**
 * The RoomTest class performs testing on non-trivial methods from the Room class.
 * 
 * Author: Linda Fung Date: 05-17-2020 Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import items.Item;
import items.ItemContainer;
import items.PuzzleItem;
import items.ToolItem;

class RoomTest {

  @Test
  void testSetIsDoorLocked() {

    // Given
    String desc1 = "The room is plain white with a gray lock box in the middle of the floor. "
        + "\nThere's a door directly in front of you labeled 9522 but it is locked...";
    ItemContainer<Item> itemsList1 = new ItemContainer<>();
    ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
    PuzzleItem grayLockBox =
        new PuzzleItem("gray lock box", "It requires a 4-digit code to unlock.", "9522", key1);
    itemsList1.add(grayLockBox);
    Room roomLevel1 = new Room(1, desc1, null, null, itemsList1);

    // When
    roomLevel1.setIsDoorLocked(false);

    // Then
    Assert.assertFalse(roomLevel1.getIsDoorLocked());
  }

  @Test
  void testIsLastRoom() {

    // Given
    String desc1 = "The room is plain white with a gray lock box in the middle of the floor. "
        + "\nThere's a door directly in front of you labeled 9522 but it is locked...";
    ItemContainer<Item> itemsList1 = new ItemContainer<>();
    ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
    PuzzleItem grayLockBox =
        new PuzzleItem("gray lock box", "It requires a 4-digit code to unlock.", "9522", key1);
    itemsList1.add(grayLockBox);
    Room roomLevel1 = new Room(1, desc1, null, null, itemsList1);

    // When
    roomLevel1.setIsDoorLocked(false);

    // Then
    Assert.assertTrue(roomLevel1.isLastRoom());
  }

}
