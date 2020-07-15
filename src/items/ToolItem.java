package items;

/**
 * The ToolItem class creates an item that can be found inside furniture. 
 * A tool can be used to solve a puzzle item or it can be a key to unlock a room.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

public class ToolItem extends Item {

	private static final long serialVersionUID = 1L;

	// Constructor
	public ToolItem(String name, String description) {
		super(name, description);
	}

	
	/**
	 * This method is used when a player inspects a tool item and prints the
	 * description to the console.
	 */
	@Override
	public void onInspect() {
		
		System.out.println(this.getDescription());
	}
	
}
