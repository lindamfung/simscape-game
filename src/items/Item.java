package items;

/**
 * The Item class creates an item that can be found in a room. It can be either a piece of furniture, 
 * a tool, a puzzle or a clue.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

import java.io.Serializable;

public abstract class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Item attributes
	protected String name;
	protected String description;
	
	// Constructor
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	// Getter methods
	public String getName() {return name;}
	public String getDescription() {return description;}
	
	// Setter methods
	public void setName(String itemName) {name = itemName;}
	public void setDescription(String itemDesc) {description = itemDesc;}

	
	/**
	 * This method is used to print out the item's name.
	 * @return String this returns the item name
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * This method is used when a player chooses to inspect an item. This abstract method is to be
	 * implemented by the subclasses.
	 */
	public abstract void onInspect();
	
}
