package items;

/**
 * The PuzzleItemTest class performs testing on non-trivial methods from the PuzzleItem class.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PuzzleItemTest {

	@Test
	void testGetPuzzleSolution() {
		
		//Given
		ToolItem key = new ToolItem("key", "Use it to unlock the door and escape.");
		PuzzleItem box = new PuzzleItem(
				"gray lock box", 
				"It requires a 4-digit code to unlock.", 
				"9522", 
				key);
		
		// Then
		Assert.assertEquals("9522", box.getPuzzleSolution());
		Assert.assertNotEquals("key", box.getPuzzleSolution());
	}

	@Test
	void testGetItemContained() {
		
		//Given
		ToolItem key = new ToolItem("key", "Use it to unlock the door and escape.");
		ToolItem book = new ToolItem("book", "Red book.");
		PuzzleItem box = new PuzzleItem(
				"gray lock box", 
				"It requires a 4-digit code to unlock.", 
				"9522", 
				key);
		
		// Then
		Assert.assertEquals(key, box.getItemContained());
		Assert.assertNotEquals(book, box.getItemContained());
	}

	@Test
	void testGetIsPuzzleSolved() {

		//Given
		ToolItem key = new ToolItem("key", "Use it to unlock the door and escape.");
		PuzzleItem box = new PuzzleItem(
				"gray lock box", 
				"It requires a 4-digit code to unlock.", 
				"9522", 
				key);
		
		// Then
		Assert.assertFalse(box.getIsPuzzleSolved());
		
		// When
		box.setIsPuzzleSolved(true);
		
		// Then
		Assert.assertTrue(box.getIsPuzzleSolved());
	}

	@Test
	void testSolvePuzzle() {

		//Given
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		PuzzleItem item = new PuzzleItem(
				"gray lock box", 
				"It requires a 4-digit code to unlock.", 
				"9522", 
				key1);
		
		// When
		String userSolution = "9522";
		String badSolution = "blah";
		
		// Then
		Assert.assertTrue(item.solvePuzzle(userSolution));
		Assert.assertFalse(item.solvePuzzle(badSolution));
	}

	@Test
	void testReveal() {

		//Given
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ToolItem tool1 = new ToolItem("book", "Red book.");
		PuzzleItem item = new PuzzleItem(
				"gray lock box", 
				"It requires a 4-digit code to unlock.", 
				"9522", 
				key1);

		// Then
		Assert.assertEquals(key1, item.reveal());
		Assert.assertNotEquals(tool1, item.reveal());
	}

}
