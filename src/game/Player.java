package game;

/**
 * The Player class creates a player with an inventory, total number of levels completed and the
 * player's current location.
 * 
 * Author: Linda Fung Date: 05-17-2020 Course: CS622
 */

import items.Item;
import items.ItemContainer;

public class Player {

  // Player attributes
  private String name;
  private Room currentRoom; // Player's location
  private int levelsCompleted = 0; // Count of number of levels completed
  private ItemContainer<Item> inventory = new ItemContainer<Item>(); // Inventory to hold items
                                                                     // found

  // Constructors
  public Player() {}

  public Player(String playerName, Room room) {
    name = playerName;
    currentRoom = room;
  }

  // Getter methods
  public String getName() {
    return name;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public int getLevelsCompleted() {
    return levelsCompleted;
  }

  public ItemContainer<Item> getInventory() {
    return inventory;
  }

  // Setter methods
  public void setName(String playerName) {
    name = playerName;
  }

  public void setCurrentRoom(Room room) {
    currentRoom = room;
  }

  public void setLevelsCompleted() {
    levelsCompleted++;
  }

  /**
   * This method is used when player unlocks the current room. The player advances to the next room,
   * the total number of levels completed is incremented, and the player's inventory is cleared.
   */
  public void completeLevel() {

    this.getCurrentRoom().setIsDoorLocked(false);
    levelsCompleted++;
    this.setCurrentRoom(currentRoom.getNextRoom());
    inventory.clearContainer();
  }

}
