package items;

/**
 * The ClueItemTest class performs testing on non-trivial methods from the ClueItem class.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ClueItemTest {

	@Test
	void testGetClueMessage() {
		
		// Given
		ClueItem clue = new ClueItem("folded paper", "There's something written on it.", "blah");
		
		// Then
		Assert.assertEquals("blah", clue.getClueMessage());
		Assert.assertNotEquals("folded paper", clue.getClueMessage());
	}

	@Test
	void testReveal() {
		
		// Given
		ClueItem clue = new ClueItem("folded paper", "There's something written on it.", "blah");
		
		// Then
		Assert.assertEquals("blah", clue.reveal());
		Assert.assertNotEquals("folded paper", clue.reveal());
	}

}
