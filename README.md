# SimScape Game

>*SimScape: A Text-Based Simulation of an Escape Room Game*

SimScape is a project that concerns a text-based simulation of an escape room game. The player will have to search the room for a key to unlock the door to escape, then enter the next room and progress that way until they successfully escape all rooms to win the game. This game will be called SimScape and will start with 3 different rooms where each room will be slightly more difficult than the previous room. 
SimScape will load the first room, then all subsequent rooms, and then connect all the rooms to have a fully loaded game. SimScape will then display to the Player how many items there are of each type and how many items are added to each room level. The Player will then enter his/her name to start the game. When a player enters a room, there will be various furniture or puzzle items to choose from that may contain a tool or a clue. Each tool or clue found will be saved in the player’s inventory, which can be accessed whenever the player needs to use a tool or view a clue for a puzzle. After solving the puzzles, the player will eventually find a key. The door will then unlock and the player will have successfully escaped the current room. After escaping the room, the player will enter another room and this progresses until the player has reached the last (3rd) room. When the player successfully escapes the last room, they will win the entire game.


## Overview
This program contains a test example run for `SimScapeGame`.


## Instructions
Compile the program on command line:

```
javac SimScapeGame.java
```
Execute the program on command line:

```
java SimScapeGame
```


## Requirements
The requirements are the postconditions of `SimScapeGame.main()`.


## Design
The SimScape RUML diagram can be found 
[here](https://docs.google.com/spreadsheets/d/1lDjROftObbnvJgsMUkCD66fIMmq4Q5Cs8szzr_P0nHA/edit?usp=sharing).


## Testing
- Run `ItemContainerTest.java` in IDE to test non-trivial methods of `ItemContainer.java` class.
- Run `PlayerTest.java` in IDE to test non-trivial methods of `Player.java` class.
- Run `RoomTest.java` in IDE to test non-trivial methods of `Room.java` class.
- Run `ClueItemTest.java` in IDE to test non-trivial methods of `ClueItem.java` class.
- Run `FurnitureItemTest.java` in IDE to test non-trivial methods of `FurnitureItem.java` class.
- Run `ItemTest.java` in IDE to test non-trivial methods of `Item.java` class.
- Run `PuzzleItemTest.java` in IDE to test non-trivial methods of `PuzzleItem.java` class.
- The `ToolItem.java` class only has trivial methods. No JUnit testing.


## Authors
- **Linda Fung** - Assignment for CS 622

