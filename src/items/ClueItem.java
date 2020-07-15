package items;

/**
 * The ClueItem class creates an clue that can be found in furniture or puzzle items.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

public class ClueItem extends Item {

	private static final long serialVersionUID = 1L;
	
	// Additional attribute for ClueItem
	private String clueMessage;
	
	// Constructor
	public ClueItem(String name, String desc, String txt) {
		super(name, desc);
		clueMessage = txt;
	}
	
	// Getter method
	public String getClueMessage() {return clueMessage;}
	
	// Setter method
	public void setClueMessage(String txt) {clueMessage = txt;}
	
	
	/**
	 * This method is used when a player inspects a clue item and prints the 
	 * description on the console.
	 */
	@Override
	public void onInspect() {
		
		System.out.println(this.getDescription());
	}
	
	
	/**
	 * This method reveals a clue message and prints the message on the console.
	 * @return String This returns the clue message revealed.
	 */
	public String reveal() {
		
		System.out.println("There is a clue..." + clueMessage);
		return this.getClueMessage();
	}
	
}
