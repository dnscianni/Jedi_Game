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

import java.util.Random;
import java.util.Scanner;

/**
 * (@link Game) is the class that contains the entire program, where we combine
 * our work to create our game as intended. This class only contains two
 * methods: main and printMaze. (@link main) is the main method for the program,
 * where our maze is created, and executes our procedures to create our game
 * (@link printMaze) prints our maze.
 * 
 * @author Team Jedi
 * 
 */
public class Game {

	/**
	 * This is the main method for our program, where we create our maze, and
	 * execute procedures to make the game as we want.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String[] lineOne = { "_", "_", "_", "_", "_", "_",
				"_",
				"_",
				"_",
				"_", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				"_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_",
				"_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_",
				"_", "_", "_", };
		String[] lineTwo = { "|", " ", " ", " ", "|", " ",
				" ",
				" ",
				"|",
				" ", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				" ", " ", "|", " ", " ", " ", "|", " ", " ", " ", "|", " ",
				" ", " ", "|", " ", " ", " ", "|", " ", " ", " ", "|", " ",
				" ", " ", "|" };
		String[] lineThree = { "_", "_", "_", "_", "x",
				"x",
				"x",
				"x",
				"x", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				"_", "_", "_", "_", "_", "_", "_", "x", "x", "x", "x", "x",
				"_", "_", "_", "_", "_", "_", "_", "x", "x", "x", "x", "x",
				"_", "_", "_", "_", };
		String[] lineFour = { "|", " ", " ", " ", "x", " ",
				" ",
				" ",
				"x",
				" ", // Strings arrays lineOne, lineTwo, lineThree, lineFour are
						// used to create our display grid.
				" ", " ", "|", " ", " ", " ", "x", " ", " ", " ", "x", " ",
				" ", " ", "|", " ", " ", " ", "x", " ", " ", " ", "x", " ",
				" ", " ", "|" };
		String[][] maze = new String[37][37]; // 2d array that will hold our
												// maze display.
		for (int m = 1; m < lineOne.length; m++) { // this first step fills maze
													// with lineTwo
			for (int n = 0; n < lineOne.length; n++)
				maze[m][n] = lineTwo[n];
		}
		for (int j = 0; j < lineOne.length; j += 4) { // fills every fourth row
														// with underscores
														// since we want our
														// spaces to be 4 line
														// high.
			for (int i = 0; i < lineOne.length; i++)
				maze[j][i] = lineOne[i];
		}
		int[] row = { 4, 8, 16, 20, 28, 32 }; // This array contains the rows of
												// the maze which will have the
												// top or bottom of a room

		for (int y = 0; y < row.length; y++) { // Fills in the top and bottom
												// walls of the rooms
			for (int x = 0; x < lineThree.length; x++)
				maze[row[y]][x] = lineThree[x];
		}

		int[] rowTwo = { 5, 6, 7, 17, 18, 19, 29, 30, 31 }; // rows in which
															// contain a left
															// and right walls
															// of a room

		for (int t = 0; t < rowTwo.length; t++) { // fills the rows that need
													// left and right walls to
													// complete our rooms
			for (int u = 0; u < lineThree.length; u++)
				maze[rowTwo[t]][u] = lineFour[u];
		}
		/**
		 * [6][6] is center of room 1, [6][18] is center of square 2, [6][30] is
		 * center of square 3, [18][6] is center of square 4, [18][18] is center
		 * of square 5 [18][30] center of 6, [30][6] center of 7, [30][18]
		 * center of 8, [30][30] center of 9,
		 */
		maze[6][6] = "1"; // These are the centers of each room to help
							// visualize locations
		maze[6][18] = "2";
		maze[6][30] = "3";
		maze[18][6] = "4";
		maze[18][18] = "5";
		maze[18][30] = "6";
		maze[30][6] = "7";
		maze[30][18] = "8";
		maze[30][30] = "9";

		Map gameMap = new Map(); // creates gameMap of type Map
		Player user = new Player(); // creates user of type Player
		int temp;
		gameMap.getArea(user.getLocation()[0], user.getLocation()[1]) // sets
																		// location
																		// of
																		// the
																		// user
				.setPerson(user);

		Random r = new Random(); // random numbers are used to set locations of
									// our Holocron and Jedis
		int x = r.nextInt(9);
		int y = r.nextInt(9);
		Jedi[] masters = new Jedi[6];

		for (int i = 0; i < masters.length; i++) {

			while (x == 0 && y == 8 || x == 0 && y == 7 || x == 1 && y == 8
					|| gameMap.getArea(x, y).getIsRoom()
					|| gameMap.getArea(x, y).getPerson() != null) { // restrict
				// jedis
				// from
				// spawning
				// to
				// close
				// to
				// jedis
				x = r.nextInt(9);
				y = r.nextInt(9);
			}
			masters[i] = new Jedi(x, y); // creates masters[i] of type Jedi
			gameMap.getArea(masters[i].getLocation()[0],
					masters[i].getLocation()[1]) // sets
					// location
					// of
					// masters[i]
					.setPerson(masters[i]);
		}
		int z = r.nextInt(9); // random room number for the holocron
		Holocron h;
		switch (z) { // each number between 0-8 is associated with a room
		case 0:
			h = new Holocron(1, 1);
			break;
		case 1:
			h = new Holocron(4, 1);
			break;
		case 2:
			h = new Holocron(7, 1);
			break;
		case 3:
			h = new Holocron(1, 4);
			break;
		case 4:
			h = new Holocron(1, 7);
			break;
		case 5:
			h = new Holocron(4, 4);
			break;
		case 6:
			h = new Holocron(4, 7);
			break;
		case 7:
			h = new Holocron(7, 7);
			break;
		default:
			h = new Holocron(7, 4);
		}
		gameMap.getArea(h.getLocation()[0], h.getLocation()[1]).setItem(h); // sets
																			// location
																			// of
																			// the
																			// holocron
																			// to
																			// an
																			// area

		maze[h.getLocation()[1] * 4 + 1][h.getLocation()[0] * 4 + 1] = "H"; // sets
																			// the
																			// location
																			// of
																			// the
																			// holocron
																			// on
																			// the
																			// display
																			// grid
		maze[user.getLocation()[1] * 4 + 2][user.getLocation()[0] * 4 + 2] = "P"; // sets
																					// location
																					// of
																					// users
																					// on
																					// the
																					// display
																					// grid
		do {
			x = r.nextInt(9);
			y = r.nextInt(9);
		} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
				|| (gameMap.getArea(x, y).getItem() != null));

		InvincibilityPowerUp invinc = new InvincibilityPowerUp(x, y);
		gameMap.getArea(invinc.getLocation()[0], invinc.getLocation()[1])
				.setItem(invinc);

		maze[invinc.getLocation()[1] * 4 + 1][invinc.getLocation()[0] * 4 + 1] = "I";

		do {
			x = r.nextInt(9);
			y = r.nextInt(9);
		} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
				|| (gameMap.getArea(x, y).getItem() != null));

		AddAmmoPowerUp addAmmo = new AddAmmoPowerUp(x, y);
		gameMap.getArea(addAmmo.getLocation()[0], addAmmo.getLocation()[1])
				.setItem(addAmmo);

		maze[addAmmo.getLocation()[1] * 4 + 1][addAmmo.getLocation()[0] * 4 + 1] = "A";

		do {
			x = r.nextInt(9);
			y = r.nextInt(9);
		} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
				|| (gameMap.getArea(x, y).getItem() != null));

		RadarPowerUp radar = new RadarPowerUp(x, y);
		gameMap.getArea(radar.getLocation()[0], radar.getLocation()[1])
				.setItem(radar);

		maze[radar.getLocation()[1] * 4 + 1][radar.getLocation()[0] * 4 + 1] = "R";

		for (int i = 0; i < masters.length; i++) {
			maze[masters[i].getLocation()[1] * 4 + 2][masters[i].getLocation()[0] * 4 + 2] = "J"; // sets
			// location
			// of
			// masters[i]
			// on
			// the
			// display
			// grid
		}

		printMaze(maze); // prints maze with current positions
		Scanner scn = new Scanner(System.in);
		int resp; // int used to respond to the games questions
		do {

			System.out
					.println("Do you want to move(1), look(2), or shoot(3)(-1 to quit): ");
			resp = scn.nextInt();
			if (resp < 0) { // allows user to quit the game
				System.out.println("you quit? you're a quitter!");
				System.exit(0);
			}
			while (resp > 3) { // keeps inputs between 1-3
				System.out
						.println("What's wrong with you? 1, 2, or 3! try again!: ");
				resp = scn.nextInt();
			}
			if (resp == 1) { // when resp equals 1 means user wants to move
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					if (gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1] - 1).getPerson() != null
							|| gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1] - 1).getPerson() != null
							|| gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1] - 1).getPerson() != null
							|| gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1] - 2).getPerson() != null) {
						System.out
								.println("You hear a noise out the door, stay here where it's safe for now");
						resp = 0;
					} else {
						resp = 1;
					}
				} else {
					System.out.println("up(1),right(2),down(3),left(4)?: ");
					resp = scn.nextInt();
				}

				while (resp > 4
						|| (user.getLocation()[0] == 0 && resp == 4)
						|| (user.getLocation()[0] == 8 && resp == 2)
						|| (user.getLocation()[1] == 0 && resp == 1)
						|| (user.getLocation()[1] == 8 && resp == 3)
						|| (resp == 1 && gameMap.getArea(user.getLocation()[0],
								user.getLocation()[1] - 1).getPerson() != null)
						|| (resp == 1 && gameMap.getArea(user.getLocation()[0],
								user.getLocation()[1] - 1).getIsRoom())
						|| (resp == 2 && gameMap.getArea(
								user.getLocation()[0] + 1,
								user.getLocation()[1]).getPerson() != null)
						|| (resp == 2 && gameMap.getArea(
								user.getLocation()[0] + 1,
								user.getLocation()[1]).getIsRoom())
						|| (resp == 3 && gameMap.getArea(user.getLocation()[0],
								user.getLocation()[1] + 1).getPerson() != null)
						|| (resp == 4 && gameMap.getArea(
								user.getLocation()[0] - 1,
								user.getLocation()[1]).getPerson() != null)
						|| (resp == 4 && gameMap.getArea(
								user.getLocation()[0] - 1,
								user.getLocation()[1]).getIsRoom())) { // input
					// stays
					// in
					// range
					if (resp > 4) {
						System.out
								.println("What's wrong with you? 1, 2, 3, or 4! try again!: ");
						resp = scn.nextInt();
					} else if ((user.getLocation()[0] == 0 && resp == 4)
							|| (user.getLocation()[0] == 8 && resp == 2)
							|| (user.getLocation()[1] == 0 && resp == 1)
							|| (user.getLocation()[1] == 8 && resp == 3)) {
						System.out
								.println("You're going out of bounds. try again: ");
						resp = scn.nextInt();
					} else if (resp == 1
							&& gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()
							|| resp == 2
							&& gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()
							|| resp == 4
							&& gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						System.out
								.println("You are walking into a wall >_>...try again: ");
						resp = scn.nextInt();
					} else {
						System.out
								.println("Someone's already in that spot. Try again: ");
						resp = scn.nextInt();
					}

				}
				maze[user.getLocation()[1] * 4 + 2][user.getLocation()[0] * 4 + 2] = " "; // erases
																							// old
																							// position
																							// of
																							// the
																							// display
				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[0] < 0) {
						continue;
					}
					if (masters[i].getLocation()[1] >= 0) { // h
						maze[masters[i].getLocation()[1] * 4 + 2][masters[i]
								.getLocation()[0] * 4 + 2] = " ";
					}
				}
				if (resp > 0) {
					user.move(resp, gameMap); // moves user
					if (user.getLocation()[0] == invinc.getLocation()[0]
							&& user.getLocation()[1] == invinc.getLocation()[1]) {
						invinc.activate(user);
						gameMap.getArea(invinc.getLocation()[0],
								invinc.getLocation()[1]).setItem(null);
						maze[invinc.getLocation()[1] * 4 + 1][invinc
								.getLocation()[0] * 4 + 1] = " ";
						System.out.println("You are now invincible!");
					}
					if (user.getLocation()[0] == addAmmo.getLocation()[0]
							&& user.getLocation()[1] == addAmmo.getLocation()[1]) {
						addAmmo.activate(user.getGun());
						gameMap.getArea(addAmmo.getLocation()[0],
								addAmmo.getLocation()[1]).setItem(null);
						maze[addAmmo.getLocation()[1] * 4 + 1][addAmmo
								.getLocation()[0] * 4 + 1] = " ";
						System.out
								.println("You found an extra charge for your blaster! You have "
										+ user.getGun().getAmmo()
										+ " ammo left.");
					}
					if (user.getLocation()[0] == radar.getLocation()[0]
							&& user.getLocation()[1] == radar.getLocation()[1]) {
						gameMap.getArea(radar.getLocation()[0],
								radar.getLocation()[1]).setItem(null);
						maze[radar.getLocation()[1] * 4 + 1][radar
								.getLocation()[0] * 4 + 1] = " ";
						System.out
								.println("You found the radar! The holocron is at "
										+ radar.activate(h)[0]
										+ " ,"
										+ radar.activate(h)[1]);
					}
				}

				temp = user.getInvincibilityCounter();
				if (temp == 0) {
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] < 0) {
							continue;
						}
						masters[i].stab(user, gameMap); // jedi stabs
					}
				} else {
					System.out.println("you have " + temp
							+ " turns left being invincible");
				}

				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[0] < 0) {
						continue;
					}
					z = r.nextInt(4) + 1;
					while ((z == 1 && masters[i].getLocation()[1] - 1 >= 0 && gameMap
							.getArea(masters[i].getLocation()[0],
									masters[i].getLocation()[1] - 1)
							.getIsRoom())
							|| (z == 2 && masters[i].getLocation()[0] + 1 <= 8 && gameMap
									.getArea(masters[i].getLocation()[0] + 1,
											masters[i].getLocation()[1])
									.getIsRoom())
							|| (z == 3 && masters[i].getLocation()[1] + 1 <= 8 && gameMap
									.getArea(masters[i].getLocation()[0],
											masters[i].getLocation()[1] + 1)
									.getIsRoom())
							|| (z == 4 && masters[i].getLocation()[0] - 1 >= 0 && gameMap
									.getArea(masters[i].getLocation()[0] - 1,
											masters[i].getLocation()[1])
									.getIsRoom())
							|| (z == 1 && masters[i].getLocation()[1] - 1 < 0)
							|| (z == 2 && masters[i].getLocation()[0] + 1 > 8)
							|| (z == 3 && masters[i].getLocation()[1] + 1 > 8)
							|| (z == 4 && masters[i].getLocation()[0] - 1 < 0)
							|| (z == 3 && gameMap.getArea(
									masters[i].getLocation()[0],
									masters[i].getLocation()[1] + 1) == gameMap
									.getArea(0, 7))
							|| (z == 4 && gameMap.getArea(
									masters[i].getLocation()[0] - 1,
									masters[i].getLocation()[1]) == gameMap
									.getArea(1, 8))
							|| (z == 1 && gameMap.getArea(
									masters[i].getLocation()[0],
									masters[i].getLocation()[1] - 1)
									.getPerson() != null)
							|| (z == 2 && gameMap.getArea(
									masters[i].getLocation()[0] + 1,
									masters[i].getLocation()[1]).getPerson() != null)
							|| (z == 3 && gameMap.getArea(
									masters[i].getLocation()[0],
									masters[i].getLocation()[1] + 1)
									.getPerson() != null)
							|| (z == 4 && gameMap.getArea(
									masters[i].getLocation()[0] - 1,
									masters[i].getLocation()[1]).getPerson() != null)) {
						z = r.nextInt(4) + 1;
					}
					masters[i].move(z, gameMap); // masters[i]
													// moves to
													// a
					// random
					// direction
				}
				if (user.getLives() == 0) {
					System.exit(0);
				}
				maze[h.getLocation()[1] * 4 + 1][h.getLocation()[0] * 4 + 1] = "H"; // sets
																					// H
																					// to
																					// current
																					// location
																					// on
																					// the
																					// display
																					// grid
				maze[user.getLocation()[1] * 4 + 2][user.getLocation()[0] * 4 + 2] = "P"; // sets
																							// P
																							// to
																							// current
																							// location
																							// on
																							// the
																							// display
																							// grid
				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[0] < 0) {
						continue;
					}
					if (masters[i].getLocation()[1] >= 0) {
						maze[masters[i].getLocation()[1] * 4 + 2][masters[i]
								.getLocation()[0] * 4 + 2] = "J"; // sets
						// J
						// to
						// current
						// location
						// on
						// the
						// display
						// grid
					}
				}
				printMaze(maze); // prints maze with current location
				if (gameMap.getArea(user.getLocation()[0], // checks if the user
															// and the holocron
															// are on the same
															// location, if so
															// user wins
						user.getLocation()[1]) == gameMap.getArea(
						h.getLocation()[0], h.getLocation()[1])) {
					try {
						h.activate();
					} catch (GameOverException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					System.exit(0); // exits program
				}
				resp = 0; // sets input to zero
			}
			if (resp == 2) { // if resp equals 2 means user wants to look in a
								// certain direction
				System.out.println("up(1),right(2),down(3),left(4)?: ");
				resp = scn.nextInt();
				while (resp > 4) { // keeps input within range
					System.out
							.println("What's wrong with you? 1, 2, 3, or 4! try again!: ");
					resp = scn.nextInt();
				}
				System.out.println("There are " + user.look(resp, gameMap) // if
																			// a
																			// jedi
																			// is
																			// present,
																			// it
																			// will
																			// say
																			// how
																			// many
						+ " Jedi(s) next to you");
				maze[h.getLocation()[1] * 4 + 1][h.getLocation()[0] * 4 + 1] = "H"; // sets
																					// H
																					// to
																					// current
																					// location
																					// on
																					// the
																					// display
																					// grid
				maze[user.getLocation()[1] * 4 + 2][user.getLocation()[0] * 4 + 2] = "P"; // sets
																							// P
																							// to
																							// current
																							// location
																							// on
																							// the
																							// display
																							// grid
				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[0] < 0) {
						continue;
					}
					if (masters[i].getLocation()[1] >= 0) { // sets J to current
						// location
						// on the display grid
						maze[masters[i].getLocation()[1] * 4 + 2][masters[i]
								.getLocation()[0] * 4 + 2] = "J";
					}
				}
				printMaze(maze); // prints maze with all current locations
				resp = 0; // sets input back to zero
			}
			if (resp == 3) { // 3 means user wants to shoot
				System.out.println("up(1),right(2),down(3),left(4)?: ");
				resp = scn.nextInt();
				while (resp > 4) { // keeps running into input is within range
					System.out
							.println("What's wrong with you? 1, 2, 3, or 4! try again!: ");
					resp = scn.nextInt();
				}
				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[0] < 0) {
						continue;
					}
					if (masters[i].getLocation()[1] >= 0) { // clears old
															// position of
						// masters[i]
						// if masters[i] meets
						// condition
						maze[masters[i].getLocation()[1] * 4 + 2][masters[i]
								.getLocation()[0] * 4 + 2] = " ";
					}
				}
				user.shoot(resp, gameMap); // users shoots in a direction
				maze[h.getLocation()[1] * 4 + 1][h.getLocation()[0] * 4 + 1] = "H"; // sets
																					// location
																					// of
																					// the
																					// holocron
																					// on
																					// display
																					// grid
				maze[user.getLocation()[1] * 4 + 2][user.getLocation()[0] * 4 + 2] = "P"; // sets
																							// location
																							// of
																							// the
																							// User
																							// on
																							// display
																							// grid
				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[0] < 0) {
						continue;
					}
					if (masters[i].getLocation()[1] >= 0) { // sets location of
															// the
						// masters[i]
						// on display grid
						maze[masters[i].getLocation()[1] * 4 + 2][masters[i]
								.getLocation()[0] * 4 + 2] = "J";
					}
				}
				printMaze(maze); // prints maze
				System.out.println("you have " + user.getGun().getAmmo() // displays
																			// amount
																			// of
																			// ammo
																			// left
						+ " ammo left");
				resp = 0; // sets input back to zero
			}
		} while (true);

		// maze[user.getLocation()[1]*4 +2][user.getLocation()[0]*4 +2] = "P";
		// printMaze(maze);
		// maze[user.getLocation()[1]*4 +2][user.getLocation()[0]*4 +2] = " ";
		// user.move(1, gameMap);
		// maze[user.getLocation()[1]*4 +2][user.getLocation()[0]*4 +2] = "P";
		// printMaze(maze);
		// maze[user.getLocation()[1]*4 +2][user.getLocation()[0]*4 +2] = " ";
		// user.move(2, gameMap);
		// maze[user.getLocation()[1]*4 +2][user.getLocation()[0]*4 +2] = "P";
		// printMaze(maze);

	}

	/**
	 * (@link printMaze) is our method that prints our maze.
	 * 
	 * @param maze
	 *            is a 2 dimensional string array that stores our maze.
	 */
	public static void printMaze(String[][] maze) {
		for (int k = 0; k < 37; k++) { // procedure to print the maze
			for (int l = 0; l < 37; l++)
				System.out.print(maze[k][l]);
			System.out.println(); // after it prints a one dimensional array,
									// creates a new line to start the next
									// array to give the grid appearance
		}
	}
}
