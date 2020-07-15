package items;

/**
 * The ItemContainer class creates a list of items that can hold different types of items. The container 
 * can be used to hold all items found in a room and all items in a player's inventory.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import java.util.ArrayList;

public class ItemContainer<I extends Item> {

	private ArrayList<I> container = new ArrayList<I>();
	
	public ArrayList<I> getContainer() {return container;} // Getter
	
	public void setItem(ArrayList<I> container) {this.container = container;} // Setter
	
	
	/**
	 * This method is used to add an item to a container.
	 */
	public void add(I item) {
		this.container.add(item);
	}
	
	
	/**
	 * This method is used to remove an item from a container.
	 */
	public void remove(I item) {
		this.container.remove(item);
	}
	
	
	/**
	 * This method is used when player finds an item. The item is added to the player's
	 * inventory if it doesn't already exist there.
	 * @param item This is an item found by the player.
	 */
	public void itemFound(I item) {
		
		if (container.contains(item)) {
			System.out.println(item.getName() + " is already in your inventory.");
		} else {
			container.add(item);
			System.out.println(item.getName() + " has been added to your inventory.");
		}
	}

	
	/**
	 * This method is used when the player uses an item. The item is removed from the
	 * player's inventory.
	 * @param item This is an item used by the player.
	 */
	public void itemUsed(I item) {
		
		if (container.remove(item)) {
			System.out.println(item.getName() + " has been removed from your inventory.");
		} else {
			System.out.println(item.getName() + " could not be removed your inventory.");
		}
	}
	
	
	/**
	 * This method prints all the items in the player's inventory to the console.
	 */
	public void printAllContainer() {
		if (container.size() == 0) {
			System.out.println("There are no items.");
		} else {
			for (Item item : container) {
				System.out.println((container.indexOf(item) + 1) + ". " + item.getName());
			}
		}
	}
	
	
	/**
	 * This method is used when the player inspects an item and prints the item
	 * description to the console.
	 */
	public void onInspect(I item) {
		item.onInspect();
	}
	
	
	/**
	 * This method is returns the number of items in the container.
	 * @return int This returns the container size.
	 */
	public int containerSize() {
		// Returns container's size
		return this.container.size();
	}
	
	
	/**
	 * This method returns an item selected by the player at a specific index.
	 * @param int This is the index option selected by the player.
	 * @return I This returns the item at the selected index.
	 */
	public I getItem(int index) {
		// Returns the item at a specific index
		return this.container.get(index);
	}
	
	
	/**
	 * This method returns an item that matches the name selected by the player.
	 * @param String This is the item name selected by the player.
	 * @return I This returns the item matching the specific name.
	 */
	public I getItem(String name) {
		// Returns the item at a specific index
		return container
				.stream()
				.filter(i -> (name.equals(i.getName())))
				.findAny()
				.orElse(null);
	}
	
	
	/**
	 * This method clears all items from the container.
	 */
	public void clearContainer() {
		// Removes all items in container
		this.container.clear();
	}
	
	
	/**
	 * This main method is to test the ItemContainer class.
	 */
	public static void main(String[] args) {
		
		// Given
		ItemContainer<Item> itemsList1 = new ItemContainer<>();
		ToolItem key1 = new ToolItem("key", "Use it to unlock the door and escape.");
		ClueItem clue1 = new ClueItem("a folded paper", "There is something written on it.", "blue");
		ToolItem tool1 = new ToolItem("screwdriver", "Use it to unscrew something.");
		
		itemsList1.itemFound(key1);
		itemsList1.itemFound(clue1);
		itemsList1.itemFound(tool1);
		
		// Test
		System.out.println("\nAll " + itemsList1.containerSize() + " items:");
		itemsList1.printAllContainer();
		
		System.out.println("\nRetrieving item #1: " + itemsList1.getItem(0).getName());
		System.out.println("\nRetrieving item #2: " + itemsList1.getItem(1).getName());
		System.out.println("\nRetrieving item #3: " + itemsList1.getItem(2).getName());
		
		itemsList1.remove(tool1);
		
		System.out.println("\nAll " + itemsList1.containerSize() + " items:");
		itemsList1.printAllContainer();
		
		System.out.println("\nRetrieving item #1: " + itemsList1.getItem(0).getName());
		System.out.println("\nRetrieving item #2: " + itemsList1.getItem(1).getName());
		
		System.out.println("\nClearing items...");
		itemsList1.clearContainer();
		
		System.out.println("\nAll " + itemsList1.containerSize() + " items:");
		itemsList1.printAllContainer();
		
	}
	
}
