package game;

/**
 * The Room class creates a locked room containing different items.
 * 
 * Author: Linda Fung Date: 05-17-2020 Course: CS622
 */

import items.Item;
import items.ItemContainer;

public class Room {

  // Room attributes
  private int level;
  private String description;
  private Room prev;
  private Room next;
  private ItemContainer<Item> allItems = new ItemContainer<>(); // List of all items in the room
  private boolean isDoorLocked = true;
  private ItemContainer<Item> inspectedFurniture = new ItemContainer<>();

  // Constructors
  public Room() {}

  public Room(int roomLevel, String roomDescription, Room prevRoom, Room nextRoom,
      ItemContainer<Item> allRoomItems) {
    level = roomLevel;
    description = roomDescription;
    prev = prevRoom;
    next = nextRoom;
    allItems = allRoomItems;
  }

  // Getter methods
  public int getLevel() {
    return level;
  }

  public String getDescription() {
    return description;
  }

  public Room getPrevRoom() {
    return prev;
  }

  public Room getNextRoom() {
    return next;
  }

  public ItemContainer<Item> getAllItems() {
    return allItems;
  }

  public boolean getIsDoorLocked() {
    return isDoorLocked;
  }

  public ItemContainer<Item> getInspectedFurniture() {
    return inspectedFurniture;
  }

  // Setter methods
  public void setLevel(int roomLevel) {
    level = roomLevel;
  }

  public void setDescription(String roomDescription) {
    description = roomDescription;
  }

  public void setPrevRoom(Room prevRoom) {
    prev = prevRoom;
  }

  public void setNextRoom(Room nextRoom) {
    next = nextRoom;
  }

  public void setAllItems(ItemContainer<Item> allRoomItems) {
    allItems = allRoomItems;
  }

  public void setIsDoorLocked(boolean lockStatus) {
    isDoorLocked = lockStatus;
  }

  public void setInspectedFurniture(ItemContainer<Item> inspectedFurnitureItems) {
    inspectedFurniture = inspectedFurnitureItems;
  }


  /**
   * This method checks if current room is the last room in the game.
   * 
   * @return boolean This returns true if current room doesn't have a next room. False otherwise.
   */
  public boolean isLastRoom() {

    if (this.next == null) {
      return true;
    } else
      return false;
  }


  /**
   * This method prints all items in the room to the console.
   */
  public void printAllItemsList() {
    allItems.printAllContainer();
  }

}
