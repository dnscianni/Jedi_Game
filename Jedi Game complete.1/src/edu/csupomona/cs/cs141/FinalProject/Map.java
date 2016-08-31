/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Final Assignment
 *
 * A long time ago, in a galaxy far far away, a battle was raging 
 * between the Sith and the Jedi.  In order to gain the upper hand, 
 * the Emperor, leader of the Sith, was busy constructing a Death Star 
 * that would have the power to destroy whole planets with a single shot.  
 * However, the Emperor discovered that there was a Jedi spy in his ranks 
 * who stole a holocron, which contained the plans and blueprints of this 
 * new weapon, and took it to the Jedi stronghold.  Darth Vader was given 
 * the task of retrieving the stolen holocron, but knowing that a young 
 * Boba Fett wanted revenge against the Jedi who killed his father, he sent 
 * this promising bounty hunter to infiltrate the Jedi compound.  As Boba 
 * Fett made his way to the Jedi headquarters, he was detected and was forced 
 * into crashing into the side of the building.  When he crashed, he shut 
 * down all the power in the building and destroyed all his weapons except 
 * a faulty blaster.  It is now up to you to help Boba Fett retrieve the 
 * holocron from the Jedi.  Be careful though, because you cannot see the 
 * Jedi who inhabit the area.  you must get the holocron from one of the 
 * nine rooms without being located.  During your turn you may either move 
 * one space, forward, backward, to the right or to the left, look ahead of 
 * you for Jedi, or shoot your blaster, which only has one shot left.  You 
 * have 3 lives, but if a Jedi catches you, you will lose one life and be 
 * transported back to the entrance.  You may also pick up some upgrades 
 * along the way.  The upgrades include a blaster clip, which will give you 
 * one extra shot, a force field, which will protect you from the Jedi for 
 * 5 turns, and radar, which will tell you where the holocron is located.  
 * Can you escape with the holocron in hand?
 *
 * Team Jedi
 *   Alec Shay
 *   Alejandro Diaz
 *   Alejandro Landeros
 *   David Scianni
 *   Luis Romero
 */
package edu.csupomona.cs.cs141.FinalProject;

/**
 * This class represents the Map in which the player will interact with. The Map
 * has an array of Areas that will be the spaces of the map that are available.
 * There will also be 9 rooms located in the map, where one of them would hold
 * the holocron.
 * 
 * @author Team Jedi
 * 
 */
public class Map {
	/**
	 * This array of Area is what the Map consists of, and will be of size 9x9.
	 * Each area will have the ability to hold a person and/or an item.
	 */
	private Area[][] map;

	/**
	 * This constructor will place an Area into each index of the map array, and
	 * will also set the characters, and items into their correct places on the
	 * map. For the player, he or she will always be placed at the starting
	 * zone, but the items and Jedi will be placed at random.
	 */

	/**
	 * (@link maze) is a 2-d string array that holds our maze.
	 */
	static String[][] maze;

	/**
	 * 
	 */
	public Map() {
		map = new Area[9][9];

		String[] lineOne = { "=", "=", "=", "=", "=", "=",
				"=",
				"=",
				"=",
				"=", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				"=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=",
				"=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=",
				"=", "=", "=", };
		String[] lineTwo = { "|", " ", "#", " ", "|", " ",
				"#",
				" ",
				"|",
				" ", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				"#", " ", "|", " ", "#", " ", "|", " ", "#", " ", "|", " ",
				"#", " ", "|", " ", "#", " ", "|", " ", "#", " ", "|", " ",
				"#", " ", "|" };
		String[] lineThree = { "=", "=", "=", "=", "*",
				"*",
				"*",
				"*",
				"*", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				"=", "=", "=", "=", "=", "=", "=", "*", "*", "*", "*", "*",
				"=", "=", "=", "=", "=", "=", "=", "*", "*", "*", "*", "*",
				"=", "=", "=", "=", };
		String[] lineFour = { "|", " ", "#", " ", "*", " ",
				"#",
				" ",
				"*",
				" ", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				"#", " ", "|", " ", "#", " ", "*", " ", "#", " ", "*", " ",
				"#", " ", "|", " ", "#", " ", "*", " ", "#", " ", "*", " ",
				"#", " ", "|" };
		maze = new String[19][37];

		for (int m = 0; m < maze.length; m++) { // this first step fills maze
			// with lineTwo
			for (int n = 0; n < maze[1].length; n++)
				maze[m][n] = lineTwo[n];
		}
		for (int j = 0; j < maze.length; j += 2) { // fills every fourth row
			// with underscores
			// since we want our
			// spaces to be 4 line
			// high.
			for (int i = 0; i < maze[1].length; i++)
				maze[j][i] = lineOne[i];
		}
		int[] row = {2, 4, 8, 10, 14, 16}; // This array contains the rows of
		// the maze which will have the
		// top or bottom of a room

		for (int y = 0; y < row.length; y++) { // Fills in the top and bottom
			// walls of the rooms
			for (int x = 0; x < maze[1].length; x++)
				maze[row[y]][x] = lineThree[x];
		}

		int[] rowTwo = { 3, 9, 15 }; // rows in which
		// contain a left
		// and right walls
		// of a room

		for (int t = 0; t < rowTwo.length; t++) { // fills the rows that need
			// left and right walls to
			// complete our rooms
			for (int u = 0; u < maze[1].length; u++)
				maze[rowTwo[t]][u] = lineFour[u];
		}
		/**
		 * [6][6] is center of room 1, [6][18] is center of square 2, [6][30] is
		 * center of square 3, [18][6] is center of square 4, [18][18] is center
		 * of square 5 [18][30] center of 6, [30][6] center of 7, [30][18]
		 * center of 8, [30][30] center of 9,
		 */
		maze[3][6] = "1"; // These are the centers of each room to help
		// visualize locations
		maze[3][18] = "2";
		maze[3][30] = "3";
		maze[9][6] = "4";
		maze[9][18] = "5";
		maze[9][30] = "6";
		maze[15][6] = "7";
		maze[15][18] = "8";
		maze[15][30] = "9";
		maze[2][6] = " ";
		maze[2][18] = " ";
		maze[2][30] = " ";
		maze[8][6] = " ";
		maze[8][18] = " ";
		maze[8][30] = " ";
		maze[14][6] = " ";
		maze[14][18] = " ";
		maze[14][30] = " ";

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				if ((i == 1 || i == 4 || i == 7)
						&& (j == 1 || j == 4 || j == 7)) {
					map[i][j] = new Area(true);
				} else {
					map[i][j] = new Area(false);
				}
		}

	}

	/**
	 * This method is used to be able to return an area in the map
	 * 
	 * @param i
	 *            The index of the first dimension of the array.
	 * @param j
	 *            The index of the second dimension of the array.
	 * @return map[i][j]
	 */
	public Area getArea(int i, int j) {
		return map[i][j];
	}

	public void setMaze(int x, int y, String s) {
		maze[x][y] = s;
	}

	/**
	 * (@link printMaze) is our method that prints our maze.
	 * 
	 * @param maze
	 *            is a 2 dimensional string array that stores our maze.
	 */
	public void printMaze() {
		for (int k = 0; k < maze.length; k++) { // procedure to print the maze
			for (int l = 0; l < maze[1].length; l++)
				System.out.print(maze[k][l]);
			System.out.println(); // after it prints a one dimensional array,
									// creates a new line to start the next
									// array to give the grid appearance
		}
	}

}