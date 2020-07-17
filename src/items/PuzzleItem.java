package items;

/**
 * The PuzzleItem class creates an puzzle that can be found in a room or inside furniture. A puzzle
 * can be solved by entering a solution or using a tool. A puzzle will contain a key or a clue.
 * 
 * Author: Linda Fung Date: 05-17-2020 Course: CS622
 */

public class PuzzleItem extends Item {

  private static final long serialVersionUID = 1L;

  // Additional attributes for PuzzleItem
  private String puzzleSolution;
  private Item itemContained;
  private boolean isPuzzleSolved = false;

  // Constructor
  public PuzzleItem(String name, String desc, String solution, Item contained) {
    super(name, desc);
    puzzleSolution = solution;
    itemContained = contained;
  }

  // Getter methods
  public String getPuzzleSolution() {
    return puzzleSolution;
  }

  public Item getItemContained() {
    return itemContained;
  }

  public boolean getIsPuzzleSolved() {
    return isPuzzleSolved;
  }

  // Setter methods
  public void setPuzzleSolution(String solution) {
    puzzleSolution = solution;
  }

  public void setItemContained(Item contained) {
    itemContained = contained;
  }

  public void setIsPuzzleSolved(boolean puzzleSolvedStatus) {
    isPuzzleSolved = puzzleSolvedStatus;
  }


  /**
   * This method is used when a player attempts to solve the puzzle. The method will compare the
   * player's solution to the puzzle's solution.
   * 
   * @param userSolution This is the player's solution to unlock the puzzle.
   * @return boolean This returns true if the player's solution matches the puzzle's solution.
   *         returns false otherwise.
   */
  public boolean solvePuzzle(String userSolution) {

    return puzzleSolution.equals(userSolution);
  }


  /**
   * This method is used when a player inspects a puzzle item and if it hasn't been solved, prints
   * the description to the console. Otherwise, state that it has already been solved.
   */
  @Override
  public void onInspect() {

    if (this.getIsPuzzleSolved()) {
      System.out.println(this.getName() + " is already solved.");
    } else
      System.out.println(this.getDescription());
  }


  /**
   * This method is used when a the puzzle reveals an item contained inside.
   * 
   * @return Item This returns the contained item revealed.
   */
  public Item reveal() {

    System.out.println(this.getName() + " contained " + this.getItemContained().getName() + ".");
    return this.getItemContained();
  }

}
