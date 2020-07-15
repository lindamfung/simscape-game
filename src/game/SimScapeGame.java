package game;

/**
 * The SimScapeGame is a simulation of a text-based escape room game.
 * This program will setup the rooms with items and setup a player.
 * This program will prompt the player to select options until the player wins the game.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */


import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import items.Item;
import items.ItemContainer;
import items.ClueItem;
import items.FurnitureItem;
import items.PuzzleItem;
import items.ToolItem;


public class SimScapeGame {
	
	private static Room roomLevel1, roomLevel2, roomLevel3; // Declare rooms
	private static ToolItem key1, key2, key3, screwdriver; // Declare tools
	private static ClueItem foldedPaper, crumpledPaper; // Declare clues
	private static PuzzleItem grayLockBox, orangeDresser, smallSafe, pinkLockBox; // Declare puzzles
	private static FurnitureItem orangeDesk, purpleCouch; // Declare furniture
	
	private static Player player; // Only one player in game
	private static boolean puzzleFlag = true; // Flag to end getSolvePuzzleOptions loop
	private static int SLEEP_TIME = 2000;
	
	private static Scanner in = new Scanner(System.in); // For user input
	private static PrintWriter out; // For file output
	
	private static Thread firstRoomSetup = new Thread() {
		private Scanner inFile1; // For file input
		
		/**
		 * This method setups the first room for the SimScapeGame with item names and details 
		 * from room_details1.txt.
		 */
		public void run() {
			try {
				inFile1 = new Scanner(new File("room_details1.txt")); // Read item details
							
				// Create items for room level 1
				ItemContainer<Item> itemsList1 = new ItemContainer<>();
				String desc1 = inFile1.nextLine() + "\n" + inFile1.nextLine();
				key1 = new ToolItem(inFile1.nextLine(), inFile1.nextLine());
				grayLockBox = new PuzzleItem(
					inFile1.nextLine(), 
					inFile1.nextLine(), 
					inFile1.nextLine(), 
					key1);
				itemsList1.add(grayLockBox);
				roomLevel1 = new Room(1, desc1, null, null, itemsList1); // Create room level 1
				
				inFile1.close();
				
                try {
					Thread.sleep(SLEEP_TIME);
					System.out.println("==> First room loaded.");
					out.println("==> First room loaded.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Write all items to item file and load all items
				try (ObjectOutputStream outfile = new ObjectOutputStream(
						new FileOutputStream("item_file1.dat"));) 
				{
					outfile.writeObject(grayLockBox);
					outfile.writeObject(key1);
				}
			} 
			catch (EOFException eofe) {
				System.out.println("EOF reached in item_file1.dat\n");
				out.println("EOF reached in item_file1.dat\n");
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("File not found.");
				out.println("File not found.");
			}
			catch (IOException ioe) {
				System.out.println("File not found.");
				out.println("File not found.");
			}
		}
	};
	
	
	private static Thread otherRoomSetup = new Thread() {
		private Scanner inFile2; // For file input
		
		/**
		 * This method setups all remaining rooms for the SimScapeGame with item names and details 
		 * from room_details2.txt.
		 */
		public void run() {
			try {
				inFile2 = new Scanner(new File("room_details2.txt")); // Read item details
				
				// Create items for room level 2
				ItemContainer<Item> itemsList2 = new ItemContainer<>();
				String desc2 = inFile2.nextLine() + "\n" + inFile2.nextLine();
				key2 = new ToolItem(inFile2.nextLine(), inFile2.nextLine());
				foldedPaper = new ClueItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						inFile2.nextLine());
				orangeDesk = new FurnitureItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						foldedPaper);
				orangeDresser = new PuzzleItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						key2);
				itemsList2.add(orangeDesk);
				itemsList2.add(orangeDresser);
				roomLevel2 = new Room(2, desc2, null, null, itemsList2); // Create room level 2
				
				// Create items for room level 3
				ItemContainer<Item> itemsList3 = new ItemContainer<>();
				String desc3 = inFile2.nextLine() + "\n" + inFile2.nextLine();
				key3 = new ToolItem(inFile2.nextLine(), inFile2.nextLine());
				screwdriver = new ToolItem(inFile2.nextLine(), inFile2.nextLine());
				crumpledPaper = new ClueItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						inFile2.nextLine());
				smallSafe = new PuzzleItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						inFile2.nextLine(),
						crumpledPaper);
				pinkLockBox = new PuzzleItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						key3);
				purpleCouch = new FurnitureItem(
						inFile2.nextLine(), 
						inFile2.nextLine(), 
						screwdriver);
				itemsList3.add(smallSafe);
				itemsList3.add(pinkLockBox);
				itemsList3.add(purpleCouch);
				roomLevel3 = new Room(3, desc3, null, null, itemsList3);

				inFile2.close();
				
                try {
					Thread.sleep(2 * SLEEP_TIME);
					System.out.println("==> Other rooms loaded.");
					out.println("==> Other rooms loaded.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				
				// Write all items to item file and load all items
				try (ObjectOutputStream outfile = new ObjectOutputStream(
						new FileOutputStream("item_file2.dat"));) 
				{
					outfile.writeObject(orangeDesk);
					outfile.writeObject(orangeDresser);
					outfile.writeObject(foldedPaper);
					outfile.writeObject(key2);
					outfile.writeObject(smallSafe);
					outfile.writeObject(pinkLockBox);
					outfile.writeObject(purpleCouch);
					outfile.writeObject(crumpledPaper);
					outfile.writeObject(screwdriver);
					outfile.writeObject(key3);
				}
			} 
			catch (EOFException eofe) {
				System.out.println("EOF reached in item_file2.dat\n");
				out.println("EOF reached in item_file2.dat\n");
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("File not found.");
				out.println("File not found.");
			}
			catch (IOException ioe) {
				System.out.println("File not found.");
				out.println("File not found.");
			}
		}
	};
	
	
	/**
	 * Postcondition1 (Input Name): Player name requested from console.
	 * Post2 (Display Welcome Message): Welcome message with name and room level displayed
	 * 		on console.
	 * Post3 (Display Player Options): Display available player options on console.
	 * Post4 (Process Player Option): Validate player option and process option.
	 * Post5 (Display Room Description): Option 1 selected. Current room description 
	 * 		displayed on console.
	 * Post6 (Display Room Items): Option 2 selected. All room items available displayed
	 * 		on console.
	 * Post7 (Display Room Item Options): Option 3 selected. Prompt player to enter room 
	 * 		item selection to inspect item.
	 * Post8 (Display Inventory Items): Option 4 selected. All inventory items available 
	 * 		displayed on console.
	 * Post9 (Display Inventory Item Options): Option 5 selected. Prompt player to enter  
	 * 		inventory item selection to inspect item.
	 * Post10 (Advance to Next Room): When player finds a key, player automatically escapes
	 * 		the current room and advances to the next room.
	 * Post11 (Win Game): When player finds a key to the last room, player escapes and wins
	 * 		the game. Congratulatory message displayed on console.
	 * 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws InterruptedException, SQLException {
		
		try {
			out = new PrintWriter(new FileWriter("simscape_gameplay.txt"), true); // Game log	
		} catch (IOException e) {
			e.printStackTrace();
		}	

		// Setup all rooms
		firstRoomSetup.start();
		otherRoomSetup.start();
		firstRoomSetup.join();
		otherRoomSetup.join();
		
		// Connect the rooms
		roomLevel1.setNextRoom(roomLevel2);
		roomLevel2.setPrevRoom(roomLevel1);
		roomLevel2.setNextRoom(roomLevel3);
		roomLevel3.setPrevRoom(roomLevel2);

		Thread.sleep(SLEEP_TIME);
		System.out.println("==> All rooms connected. Game is fully loaded.\n");
		out.println("==> All rooms connected. Game is fully loaded.\n");
		
		String url = "jdbc:sqlite:SimScapeDatabase.db";
		try (Connection conn = DriverManager.getConnection(url)) {
			try {
				insert(conn); // Insert game items into db
			}
			catch (SQLException sqle) {
				conn.endRequest();
			}
			
			System.out.println("==> Item Distribution...");
			out.println("==> Item Distribution...");
			Thread.sleep(SLEEP_TIME);
			query(conn); // Query db
			Thread.sleep(SLEEP_TIME);
		}
		
		setupPlayer(); // Create a player for the game
		
		// While player hasn't won, prompt player for options
		while (!winGame()) {
			try {
				getPlayerOptions();
			} 
			catch(InvalidInputException iie) {
				System.out.println("\nOption not valid. Please try again.");
				out.println("\nOption not valid. Please try again.");
			} 
			catch(InputMismatchException ime) {
				System.out.println("Option not a number. Please try again.");
				out.println("Option not a number. Please try again.");
			}
		}
		
		in.close();
		out.close();
	
	}
	
	
	/**
	 * This method inserts each SimScapeGame item using its name, description, type, and the room it is in as a 
	 * record into the SimScapeDatabase.
	 */
	private static void insert(Connection conn) throws SQLException {
	    String sql = "INSERT INTO Item(item_id, item_name, item_desc, item_type) "
	    		+ "VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	      pstmt.setInt(1, 1);
	      pstmt.setString(2, key1.getName());
	      pstmt.setString(3, key1.getDescription());
	      pstmt.setString(4, "Tool");
	      pstmt.executeUpdate();
	       
	      pstmt.setInt(1, 2);
	      pstmt.setString(2, grayLockBox.getName());
	      pstmt.setString(3, grayLockBox.getDescription());
	      pstmt.setString(4, "Puzzle");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 3);
	      pstmt.setString(2, key2.getName());
	      pstmt.setString(3, key2.getDescription());
	      pstmt.setString(4, "Tool");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 4);
	      pstmt.setString(2, foldedPaper.getName());
	      pstmt.setString(3, foldedPaper.getDescription());
	      pstmt.setString(4, "Clue");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 5);
	      pstmt.setString(2, orangeDesk.getName());
	      pstmt.setString(3, orangeDesk.getDescription());
	      pstmt.setString(4, "Furniture");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 6);
	      pstmt.setString(2, orangeDresser.getName());
	      pstmt.setString(3, orangeDresser.getDescription());
	      pstmt.setString(4, "Puzzle");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 7);
	      pstmt.setString(2, key3.getName());
	      pstmt.setString(3, key3.getDescription());
	      pstmt.setString(4, "Tool");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 8);
	      pstmt.setString(2, screwdriver.getName());
	      pstmt.setString(3, screwdriver.getDescription());
	      pstmt.setString(4, "Tool");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 9);
	      pstmt.setString(2, crumpledPaper.getName());
	      pstmt.setString(3, crumpledPaper.getDescription());
	      pstmt.setString(4, "Clue");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 10);
	      pstmt.setString(2, smallSafe.getName());
	      pstmt.setString(3, smallSafe.getDescription());
	      pstmt.setString(4, "Puzzle");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 11);
	      pstmt.setString(2, pinkLockBox.getName());
	      pstmt.setString(3, pinkLockBox.getDescription());
	      pstmt.setString(4, "Puzzle");
	      pstmt.executeUpdate();
	      
	      pstmt.setInt(1, 12);
	      pstmt.setString(2, purpleCouch.getName());
	      pstmt.setString(3, purpleCouch.getDescription());
	      pstmt.setString(4, "Furniture");
	      pstmt.executeUpdate();
	    }
	    
	    String sql2 = "INSERT INTO Room(item_id, room_num) VALUES (?, ?)";
	    try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
	    	pstmt2.setInt(1, 1);
	    	pstmt2.setInt(2, 1);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 2);
	    	pstmt2.setInt(2, 1);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 3);
	    	pstmt2.setInt(2, 2);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 4);
	    	pstmt2.setInt(2, 2);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 5);
	    	pstmt2.setInt(2, 2);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 6);
	    	pstmt2.setInt(2, 2);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 7);
	    	pstmt2.setInt(2, 3);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 8);
	    	pstmt2.setInt(2, 3);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 9);
	    	pstmt2.setInt(2, 3);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 10);
	    	pstmt2.setInt(2, 3);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 11);
	    	pstmt2.setInt(2, 3);
	    	pstmt2.executeUpdate();
	    	
	    	pstmt2.setInt(1, 12);
	    	pstmt2.setInt(2, 3);
	    	pstmt2.executeUpdate();
	    }
	}
   
	
	/**
	 * This method queries the SimScapeDatabase to get information on the item distribution.
	 */
	private static void query(Connection conn) throws SQLException {
		String sql = "SELECT item_type, count(Item.item_id) AS item_count FROM Item\n" + 
				"GROUP BY item_type;";
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				System.out.println("There are " + rs.getInt(2) + " total " + rs.getString(1) + 
						" items in the game.");
				out.println("There are " + rs.getInt(2) + " total " + rs.getString(1) + 
						" items in the game.");
			}
		}
		
		String sql2 = "SELECT room_num, count(Item.item_id) AS item_count FROM Item\n" + 
				"JOIN Room ON Room.item_id = Item.item_id\n" + 
				"GROUP BY room_num;";
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2)) {
			while (rs.next()) {
				System.out.println("There are " + rs.getInt(2) + " items total in Room " + rs.getInt(1) + ".");
				out.println("There are " + rs.getInt(2) + " items total in Room " + rs.getInt(1) + ".");
			}
		}
		
		System.out.println("\n==> All Available Items...");
		out.println("\n==> All Available Items...");
		String sql3 = "SELECT item_type, item_name FROM Item\n" + 
				"ORDER BY item_type, item_name;";
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql3)) {
			while (rs.next()) {
				System.out.printf("%-10s\t%-10s\n", rs.getString(1), rs.getString(2));
				out.printf("%-10s\t%-10s\n", rs.getString(1), rs.getString(2));
			}
		}
	}
	
	
	/**
	 * This method sets up a new player for the SimScapeGame.
	 * This method prints an introduction to the game, prompts the player to enter 
	 * their name, and prints out a welcome message on the console.
	 */
	public static void setupPlayer() {
		
		in = new Scanner(System.in);

		System.out.print("\n==> This is SIMSCAPE: A text-based simulation of an escape room game. "
							+ "\nEnter your name to start the game: ");
		out.print("\n==> This is SIMSCAPE: A text-based simulation of an escape room game. "
					+ "\nEnter your name to start the game: ");
		
		String playerName = in.nextLine();
		player = new Player(playerName, roomLevel1); // Create a player to start at room level 1
		
		System.out.println("\n==> Welcome, " + player.getName() + "! \nYou just entered Room Level " 
							+ roomLevel1.getLevel() + ".");
		out.println("\n==> Welcome, " + player.getName() + "! \nYou just entered Room Level " 
					+ roomLevel1.getLevel() + ".");
		
	}
	
	
	/**
	 * This method prompts the player to choose an option to perform while in a room.
	 * Some options can lead to more new options.
	 */
	public static void getPlayerOptions() throws InvalidInputException, InputMismatchException {
		
		in = new Scanner(System.in);
		
		System.out.println("\n==> What would you like to do? (Enter a number)"
							+ "\n1. Examine the room."
							+ "\n2. View all items in the room."
							+ "\n3. Inspect an item in the room."
							+ "\n4. View all items in your inventory."
							+ "\n5. Inspect an item in your inventory.");
		out.println("\n==> What would you like to do? (Enter a number)"
					+ "\n1. Examine the room."
					+ "\n2. View all items in the room."
					+ "\n3. Inspect an item in the room."
					+ "\n4. View all items in your inventory."
					+ "\n5. Inspect an item in your inventory.");
			
		int option = in.nextInt();
		
		if (option == 1) { // Displays description of room
			System.out.println("\n==> You selected... 1. Examine the room.");
			out.println("\n==> You selected... 1. Examine the room.");
			
			System.out.println(player.getCurrentRoom().getDescription());
			out.println(player.getCurrentRoom().getDescription());
		} 
		else if (option == 2) { // Displays all items in room
			System.out.println("\n==> You selected... 2. View all items in the room.");
			out.println("\n==> You selected... 2. View all items in the room.");
			
			player.getCurrentRoom().printAllItemsList();
		} 
		else if (option == 3) { // Displays room item options
			System.out.println("\n==> You selected... 3. Inspect an item in the room.");
			out.println("\n==> You selected... 3. Inspect an item in the room.");
			
			try {
				getRoomItemOptions(); // MORE OPTIONS
			} catch (IndexOutOfBoundsException iobe) {
				System.out.println("\nItem not found. Please try again.");
				out.println("\nItem not found. Please try again.");
			} catch (InputMismatchException ime) {
				System.out.println("\nOption not a number. Please try again.");
				out.println("\nOption not a number. Please try again.");
			} 
		} 
		else if (option == 4) { // Displays all items in inventory
			System.out.println("\n==> You selected... 4. View all items in your inventory.");
			out.println("\n==> You selected... 4. View all items in your inventory.");
			
			player.getInventory().printAllContainer();
		}
		else if (option == 5) { // Displays inventory item options
			System.out.println("\n==> You selected... 5. Inspect an item in your inventory.");
			out.println("\n==> You selected... 5. Inspect an item in your inventory.");
			
			try {
				getInventoryItemOptions(); // MORE OPTIONS
			} catch (IndexOutOfBoundsException iobe) {
				System.out.println("\nItem not found. Please try again.");
				out.println("\nItem not found. Please try again.");
			} catch (InputMismatchException ime) {
				System.out.println("\nOption not a number. Please try again.");
				out.println("\nOption not a number. Please try again.");
			}
		}
		else { // If input is not valid number option, throw exception
			throw new InvalidInputException(null);
		}
		
	}
	
	
	/**
	 * This method displays all room items and prompts player to choose an item to inspect.
	 * Only furniture and puzzle items are available to select in a room.
	 * If the inspected item is a puzzle, player is prompted to solve the puzzle.
	 * If the inspected item is furniture, another item it contains is revealed.
	 */
	public static void getRoomItemOptions() throws IndexOutOfBoundsException, 
													InputMismatchException {
		
		in = new Scanner(System.in);
		
		System.out.println("\n==> Which room item would you like to inspect? (Enter a number)");
		out.println("\n==> Which room item would you like to inspect? (Enter a number)");
		
		player.getCurrentRoom().printAllItemsList();
		
		int option = in.nextInt();

		// Use player's input as index to get item to inspect
		Item selectedItem = player.getCurrentRoom().getAllItems().getItem(option - 1);
		
		System.out.println("\n==> You selected... " + option + ". " + selectedItem.getName());
		out.println("\n==> You selected... " + option + ". " + selectedItem.getName());
		
		selectedItem.onInspect();

		if (selectedItem instanceof PuzzleItem) { // If item is a puzzle
			PuzzleItem selectedPuzzleItem = (PuzzleItem) selectedItem; // Down-casting
			puzzleFlag = true;
			
			while (!selectedPuzzleItem.getIsPuzzleSolved() && puzzleFlag == true) {
				try {
					getSolvePuzzleOptions(selectedPuzzleItem); // MORE OPTIONS
				} catch (InvalidInputException iie) {
					System.out.println("\nOption not valid. Please try again.");
					out.println("\nOption not valid. Please try again.");	
				} catch (IndexOutOfBoundsException iobe) {
					System.out.println("\nItem not found. Please try again.");
					out.println("\nItem not found. Please try again.");
				} catch (InputMismatchException ime) {
					System.out.println("\nOption not a number. Please try again.");
					out.println("\nOption not a number. Please try again.");
				}
			}
		}
		else if (selectedItem instanceof FurnitureItem) { // If item is furniture
			FurnitureItem selectedFurnitureItem = (FurnitureItem) selectedItem; // Down-casting
			itemFound(selectedFurnitureItem.reveal()); // Reveal hidden item
		}
		
	}

	
	/**
	 * This method displays all inventory items and prompts player to choose an item 
	 * to inspect. Only tool and clue items are available to select in the inventory.
	 * This method will display the description of the tool or clue message when inspected.
	 */
	public static void getInventoryItemOptions() throws IndexOutOfBoundsException, 
														InputMismatchException {
		
		in = new Scanner(System.in);

		// If inventory is not empty, prompt player to choose an item and display all items
		if (player.getInventory().containerSize() > 0) {
			System.out.println("\n==> Which inventory item would you like to inspect? "
								+ "(Enter a number)");
			out.println("\n==> Which inventory item would you like to inspect? "
						+ "(Enter a number)");
			
			player.getInventory().printAllContainer();
			
			int option = in.nextInt();

			// Use player's input as index to get item to inspect
			Item selectedItem = player.getInventory().getItem(option - 1);
			
			System.out.println("\n==> You selected... " + option + ". " + selectedItem.getName());
			out.println("\n==> You selected... " + option + ". " + selectedItem.getName());
			
			selectedItem.onInspect();

			if (selectedItem instanceof ClueItem) { // If item is a clue
				ClueItem selectedClueItem = (ClueItem) selectedItem; // Down-casting
				selectedClueItem.reveal(); // Reveal the clue text
			}
		} 
		else {
			System.out.println("\nYour inventory is empty.");
			out.println("\nYour inventory is empty.");
		}		
		
	}
	
	
	/**
	 * This method prompts a player to choose an option to solve a puzzle.
	 * A puzzle can be solved by either using a tool or entering a solution.
	 * After solving a puzzle, it will reveal a tool (can be a key) or a clue.
	 * @param puzzle This is a puzzle that requires a solution.
	 */
	public static void getSolvePuzzleOptions(PuzzleItem puzzle) throws InvalidInputException, 
																		IndexOutOfBoundsException,
																		InputMismatchException {
		
		in = new Scanner(System.in);

		System.out.println("\n==> What would you like to do with... " + puzzle.getName() + "? "
							+ "(Enter a number)"
							+ "\n1. Use a tool in your inventory to solve it."
							+ "\n2. View a clue in your inventory."
							+ "\n3. Enter a solution."
							+ "\n4. Go back to player options.");
		out.println("\n==> What would you like to do with... " + puzzle.getName() + "? "
					+ "(Enter a number)"
					+ "\n1. Use a tool in your inventory to solve it."
					+ "\n2. View a clue in your inventory."
					+ "\n3. Enter a solution."
					+ "\n4. Go back to player options.");
			
		int option = in.nextInt();
		
		if (option == 1) { // Displays all items in inventory and prompts player to select a tool
			System.out.println("\n==> You selected... 1. Use a tool in your inventory.");
			out.println("\n==> You selected... 1. Use a tool in your inventory.");
			
			if (player.getInventory().containerSize() > 0) {
				System.out.println("\n==> Which tool would you like to use? (Enter a number)");
				out.println("\n==> Which tool would you like to use? (Enter a number)");

				// Display all tool items only
				player.getInventory().getContainer()
				.stream()
				.filter(t -> (t instanceof ToolItem))
				.forEach(t -> System.out.println(
						(player.getInventory().getContainer()
								.indexOf(t) + 1) + ". " + t));

				// Use player input as index to find tool
				int toolIdx = in.nextInt();
				Item selectedItem = player.getInventory().getItem(toolIdx - 1);

				// Only use item if it is a tool item
				if (selectedItem instanceof ToolItem) {
					System.out.println("\n==> You selected... " + toolIdx + ". " + selectedItem.getName());
					out.println("\n==> You selected... " + toolIdx + ". " + selectedItem.getName());
					
					selectedItem.onInspect();
					
					solvePuzzle(puzzle, selectedItem.getName());
				} 
				else {
					System.out.println("\nOption not valid. Please try again.");
					out.println("\nOption not valid. Please try again.");
				}
			} 
			else {
				System.out.println("\nYour inventory is empty.");
				out.println("\nYour inventory is empty.");
			}
		} 
		else if (option == 2) { // Displays all items in inventory and prompts player to select a clue
			System.out.println("\n==> You selected... 2. View a clue in your inventory.");
			out.println("\n==> You selected... 2. View a clue in your inventory.");
			
			if (player.getInventory().containerSize() > 0) {
				System.out.println("\n==> Which clue would you like to view? (Enter a number)");
				out.println("\n==> Which clue would you like to view? (Enter a number)");

				// Display all clue items only
				player.getInventory().getContainer()
				.stream()
				.filter(c -> (c instanceof ClueItem))
				.forEach(c -> System.out.println(
						(player.getInventory().getContainer()
								.indexOf(c) + 1) + ". " + c));

				// Use player input as index to find clue
				int clueIdx = in.nextInt();
				Item selectedItem = player.getInventory().getItem(clueIdx - 1);

				// Only view item if it is a clue item
				if (selectedItem instanceof ClueItem) {
					System.out.println("\n==> You selected... " + clueIdx + ". " + selectedItem.getName());
					out.println("\n==> You selected... " + clueIdx + ". " + selectedItem.getName());
					
					selectedItem.onInspect();
					((ClueItem) selectedItem).reveal();
				} 
				else {
					System.out.println("\nOption not valid. Please try again.");
					out.println("\nOption not valid. Please try again.");
				}
			} 
			else {
				System.out.println("\nYour inventory is empty.");
				out.println("\nYour inventory is empty.");
			}	
		} 
		else if (option == 3) { // Prompts player to enter a solution for the puzzle
			System.out.println("\n==> You selected... 3. Enter a solution. " + puzzle.getDescription() 
								+ "\nEnter your solution here: ");
			out.println("\n==> You selected... 3. Enter a solution. " + puzzle.getDescription() 
						+ "\nEnter your solution here: ");

			String userSolution = in.next();

			System.out.println("\n==> You entered... " + userSolution);
			out.println("\n==> You entered... " + userSolution);

			solvePuzzle(puzzle, userSolution); // Check if player's input matches puzzle solution
		}
		else if (option == 4) { // Ends loop and goes back to previous question
			System.out.println("\n==> You selected... 4. Go back to player options.");
			out.println("\n==> You selected... 4. Go back to player options.");
			puzzleFlag = false; // End loop
		}
		else { // If input is not valid number option, throw exception
			throw new InvalidInputException(null);
		}

	}
	
	
	/**
	 * This method checks if a solution provided by the player matches the 
	 * solution of the puzzle.
	 * If solution matches, an item contained in the puzzle will be revealed.
	 * @param puzzle This is a puzzle that requires a solution.
	 * @param userSolution This is the solution provided by the player.
	 */
	public static void solvePuzzle(PuzzleItem puzzle, String userSolution) {
		
		if (puzzle.solvePuzzle(userSolution)) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\n... " + userSolution + " worked. " 
								+ puzzle.getName() + " is solved.");
			out.println("\n... " + userSolution + " worked. " 
						+ puzzle.getName() + " is solved.");
			
			Item revealedItem = puzzle.reveal();
			itemFound(revealedItem);
			puzzle.setIsPuzzleSolved(true);
		} 
		else {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\n... " + userSolution + " is not correct. Please try again.");
			out.println("\n... " + userSolution + " is not correct. Please try again.");
		}
		
	}
	
	
	/**
	 * This method is used when a player finds a tool (could be a key) or a clue item.
	 * If the item is a key, then initiate key found to escape the room.
	 * If the item is not a key, it is added to the player's inventory.
	 * @param foundItem This is an item found by the player.
	 */
	public static void itemFound(Item foundItem) {
		
		if (foundItem.getName().equals("key")) {
			keyFound();
		} 
		else {
			player.getInventory().itemFound(foundItem);
		}
		
	}
	
	
	/**
	 * This method is used when a player uses a tool or a clue item to solve a puzzle.
	 * The item will be removed from the player's inventory when it is used to solve 
	 * a related puzzle.
	 * @param usedItem This is an item used by the player.
	 */
	public static void itemUsed(Item usedItem) {
		
		player.getInventory().itemUsed(usedItem);
		
	}
		
	
	/**
	 * This method is used when a player finds a key.
	 * If a key is found, the player will successfully unlock the door of the current room.
	 * If the current room is not the last room in the game, the player will advance to the 
	 * next room.
	 */
	public static void keyFound() { 
		
		if (!player.getCurrentRoom().isLastRoom()) {
			System.out.println("\n==> You found a key and unlocked Room Level " 
								+ player.getCurrentRoom().getLevel() + ".");
			out.println("\n==> You found a key and unlocked Room Level " 
						+ player.getCurrentRoom().getLevel() + ".");
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			player.completeLevel(); // Player advances
			
			System.out.println("\n==> You have now advanced to Room Level " 
								+ player.getCurrentRoom().getLevel()
								+ ". So far, you have completed " 
								+ player.getLevelsCompleted() + " level(s).");
			out.println("\n==> You have now advanced to Room Level " 
						+ player.getCurrentRoom().getLevel()
						+ ". So far, you have completed " 
						+ player.getLevelsCompleted() + " level(s).");
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		else {
			player.getCurrentRoom().setIsDoorLocked(false); // Unlock room
			player.setLevelsCompleted();
			
			System.out.println("\n==> You found a key and unlocked Room Level " 
								+ player.getCurrentRoom().getLevel() + ".");
			out.println("\n==> You found a key and unlocked Room Level " 
						+ player.getCurrentRoom().getLevel() + ".");
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * This method is used when a player unlocks the last room to escape and wins the game.
	 * This method checks if the current room is the last room and checks if it is unlocked.
	 * Displays winning message on console.
	 */
	public static boolean winGame() {
		
		Room lastRoom;
		
		if (player.getCurrentRoom().isLastRoom()) {
			lastRoom = player.getCurrentRoom();
			
			if (!lastRoom.getIsDoorLocked()) {
				System.out.println("\n==> Congrats, " + player.getName() 
									+ "! You escaped the final room and won the game. You completed " 
									+ player.getLevelsCompleted() + " level(s).\n");
				out.println("\n==> Congrats, " + player.getName() 
							+ "! You escaped the final room and won the game. You completed "
							+ player.getLevelsCompleted() + " level(s).\n");
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true; // Win game
			}
		} return false;
		
	}
	
	
}
