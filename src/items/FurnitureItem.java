package items;

/**
 * The FurnitureItem class creates an item that can be found in a room. This item can either hold a
 * puzzle, tool, or reveal a clue on inspection.
 * 
 * Author: Linda Fung Date: 05-17-2020 Course: CS622
 */

public class FurnitureItem extends Item {

  private static final long serialVersionUID = 1L;

  // Additional attribute for FurnitureItem
  private Item hiddenItem;

  // Constructor
  public FurnitureItem(String name, String description, Item hidden) {
    super(name, description);
    hiddenItem = hidden;
  }

  // Getter method for hiddenItem
  public Item getHiddenItem() {
    return hiddenItem;
  }

  // Setter method for hiddenItem
  public void setHiddenItem(Item hidden) {
    hiddenItem = hidden;
  }


  /**
   * This method is used when a player inspects a furniture item and reveals a hidden item.
   */
  @Override
  public void onInspect() {

    System.out.println(this.getDescription());
  }


  /**
   * This method is used when an item is revealed hidden inside the furniture item.
   * 
   * @return Item This returns the hidden item revealed.
   */
  public Item reveal() {

    System.out.println(hiddenItem.getName() + " is found inside.");
    return this.getHiddenItem();
  }

}
