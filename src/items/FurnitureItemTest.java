package items;

/**
 * The FurnitureItemTest class performs testing on non-trivial methods from the FurnitureItem class.
 * 
 * Author: Linda Fung Date: 05-17-2020 Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class FurnitureItemTest {

  @Test
  void testGetHiddenItem() {
    // Given
    ToolItem key = new ToolItem("key", "Use it to unlock the door and escape.");
    PuzzleItem box =
        new PuzzleItem("gray lock box", "It requires a 4-digit code to unlock.", "9522", key);
    FurnitureItem couch =
        new FurnitureItem("comfy couch", "There's something underneath the cushions.", box);

    // Then
    Assert.assertEquals(box, couch.getHiddenItem());
    Assert.assertNotEquals(key, couch.getHiddenItem());
  }

  @Test
  void testReveal() {

    // Given
    ToolItem key = new ToolItem("key", "Use it to unlock the door and escape.");
    PuzzleItem box =
        new PuzzleItem("gray lock box", "It requires a 4-digit code to unlock.", "9522", key);
    FurnitureItem couch =
        new FurnitureItem("comfy couch", "There's something underneath the cushions.", box);

    // Then
    Assert.assertEquals(box, couch.reveal());
    Assert.assertNotEquals(key, couch.reveal());
  }

}
