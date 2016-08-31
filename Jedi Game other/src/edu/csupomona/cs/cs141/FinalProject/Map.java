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
	 * This will set up map as a two dimensional array of size 9 by 9, and fill
	 * the array with blank Areas. It will also create the maze, which is the
	 * visual representation of the map that we print out on screen so that the
	 * player can see what is happening in the game. Based on it it's a debug
	 * game or not the maze will look different. In the debug mode, all the
	 * middle spots will be empty spaces, but if debug mode is not set, then #'s
	 * // * will be used to represent the darkness in the rooms.
	 * 
	 * @param b
	 *            Will be true if it's in debug mode and false if it's not.
	 */
	public Map(boolean b) {
		map = new Area[9][9];

		/*
		 * Strings arrays lineOne, lineTwo, lineThree, lineFour are used to
		 * create our display grid.
		 */
		String[] lineOne = { "=", "=", "=", "=", "=", "=", "=", "=", "=", "=",
				"=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=",
				"=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=", "=",
				"=", "=", "=", };
		String[] lineTwo = { "|", " ", "#", " ", "|", " ", "#", " ", "|", " ",
				"#", " ", "|", " ", "#", " ", "|", " ", "#", " ", "|", " ",
				"#", " ", "|", " ", "#", " ", "|", " ", "#", " ", "|", " ",
				"#", " ", "|" };
		String[] lineThree = { "=", "=", "=", "=", "*", "*", "*", "*", "*",
				"=", "=", "=", "=", "=", "=", "=", "*", "*", "*", "*", "*",
				"=", "=", "=", "=", "=", "=", "=", "*", "*", "*", "*", "*",
				"=", "=", "=", "=", };
		String[] lineFour = { "|", " ", "#", " ", "*", " ", "#", " ", "*", " ",
				"#", " ", "|", " ", "#", " ", "*", " ", "#", " ", "*", " ",
				"#", " ", "|", " ", "#", " ", "*", " ", "#", " ", "*", " ",
				"#", " ", "|" };
		/*
		 * This will change all the #' into blank spaces if the game is in debug
		 * mode
		 */
		if (b) {
			lineTwo[2] = " ";
			lineTwo[6] = " ";
			lineTwo[10] = " ";
			lineTwo[14] = " ";
			lineTwo[18] = " ";
			lineTwo[22] = " ";
			lineTwo[26] = " ";
			lineTwo[30] = " ";
			lineTwo[34] = " ";

			lineFour[2] = " ";
			lineFour[6] = " ";
			lineFour[10] = " ";
			lineFour[14] = " ";
			lineFour[18] = " ";
			lineFour[22] = " ";
			lineFour[26] = " ";
			lineFour[30] = " ";
			lineFour[34] = " ";
		}
		maze = new String[19][37]; // Creates the string array for maze

		/* this first step fills maze with lineTwo */
		for (int m = 0; m < maze.length; m++) {
			for (int n = 0; n < maze[1].length; n++)
				maze[m][n] = lineTwo[n];
		}

		/*
		 * fills every fourth row with underscores since we want our spaces to
		 * be 4 line high.
		 */
		for (int j = 0; j < maze.length; j += 2) {
			for (int i = 0; i < maze[1].length; i++)
				maze[j][i] = lineOne[i];
		}

		/*
		 * This array contains the rows of the maze which will have the top or
		 * bottom of a room
		 */
		int[] row = { 2, 4, 8, 10, 14, 16 };

		/*
		 * Fills in the top and bottom walls of the rooms
		 */
		for (int y = 0; y < row.length; y++) {
			for (int x = 0; x < maze[1].length; x++)
				maze[row[y]][x] = lineThree[x];
		}

		/*
		 * rows in which contain a left and right walls of a room
		 */
		int[] rowTwo = { 3, 9, 15 };

		/*
		 * fills the rows that need left and right walls to complete our rooms
		 */
		for (int t = 0; t < rowTwo.length; t++) {
			for (int u = 0; u < maze[1].length; u++)
				maze[rowTwo[t]][u] = lineFour[u];
		}

		/*
		 * [3][6] is center of room 1, [3][18] is center of square 2, [3][30] is
		 * center of square 3, [9][6] is center of square 4, [9][18] is center
		 * of square 5 [9][30] center of 6, [15][6] center of 7, [15][18] center
		 * of 8, [15][30] center of 9. These are the centers of each room to
		 * help // visualize locations. The rest make it so that the rooms are
		 * open on the top.
		 */
		maze[3][6] = "1";
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

	/**
	 * This method is used to set an index in the maze array to a String s.
	 * 
	 * @param x
	 *            The x coordinate in the array maze.
	 * @param y
	 *            The y coordinate in the array maze.
	 * @param s
	 *            The String that we wish to set in the array maze at location
	 *            x, y.
	 */
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

		for (int i = 0; i < 36; i++) {
			System.out.println();
		}

		/*
		 * procedure to print the maze. after it prints a one dimensional array,
		 * creates a new line to start the next array to give the grid
		 * appearance
		 */
		for (int k = 0; k < maze.length; k++) {
			for (int l = 0; l < maze[1].length; l++)
				System.out.print(maze[k][l]);
			System.out.println();
		}
	}

}