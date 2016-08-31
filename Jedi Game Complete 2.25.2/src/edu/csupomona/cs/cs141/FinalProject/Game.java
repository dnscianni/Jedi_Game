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

import java.io.*;
import java.util.InputMismatchException;
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

		int resp = 0; // int used to respond to the games questions

		Save saveState = new Save();
		Jedi[] masters = null;
		Player user = null; // creates user of type Player
		InvincibilityPowerUp invinc = null;
		RadarPowerUp radar = null;
		AddAmmoPowerUp addAmmo = null;
		Holocron h = null;
		boolean hasLooked = false;
		boolean hasShot = false;
		boolean isDebug = false;
		boolean hasDied = false;
		boolean inRoom = false;
		String tString;

		System.out
				.println("====================================================================================================");
		System.out
				.println("||                           Star Wars: Rise of the Death Star                                    ||");
		System.out
				.println("====================================================================================================");

		System.out
				.println("====================================================================================================");
		System.out
				.println("||                                      The Story                                                 ||");
		System.out
				.println("====================================================================================================");
		System.out
				.println("||A long time ago, in a galaxy far far away, a battle was raging "
						+ "between the Sith and the Jedi.   ||\n||In order to gain the upper hand,"
						+ " the Emperor, leader of the Sith, was busy constructing a       ||\n||Death Star "
						+ "that would have the power to destroy whole planets with a single shot."
						+ " However,      ||\n||the Emperor discovered that there was a Jedi spy in his ranks"
						+ " who stole a holocron, which       ||\n||contained the plans and blueprints of this"
						+ " new weapon, and took it to the Jedi stronghold.      ||\n||Darth Vader was given"
						+ " the task of retrieving the stolen holocron, but knowing that a young      ||\n||"
						+ "Boba Fett wanted revenge against the Jedi who killed his father, he sent"
						+ " this promising         ||\n||bounty hunter to infiltrate the Jedi compound. As Boba "
						+ "Fett made his way to the Jedi            ||\n||headquarters, he was detected and was forced "
						+ "into crashing into the side of the building.       ||\n||When he crashed,"
						+ "the power in the building went out and destroyed all of his weapons except      ||\n||"
						+ "a faulty blaster. It is now up to you to help Boba Fett retrieve the"
						+ " holocron from the Jedi...  ||");
		System.out
				.println("====================================================================================================\n");

		System.out
				.println("====================================================================================================");
		System.out
				.println("||                                     Instructions                                               ||");
		System.out
				.println("====================================================================================================");
		System.out
				.println("||Be careful for you cannot see the"
						+ " Jedi who inhabit the area.  You must get the holocron from    ||\n||one of the"
						+ " nine rooms without being located.  During your turn you may either move"
						+ " one          ||\n||space(up, down, right or left), look an extra square in a direction for "
						+ "a Jedi, or shoot your   ||\n||blaster, which only has one shot. You "
						+ "have 3 lives, but if a Jedi catches you, you will lose    ||\n||one life and be "
						+ "transported back to the entrance. You may also pick up some upgrades "
						+ "along the  ||\n||way. The upgrades include a blaster clip, which will give you"
						+ " one extra shot, a force field,    ||\n||which will protect you from the Jedi for"
						+ " 5 turns, and a radar, which will tell you where the    ||\n||holocron is located."
						+ " Can you escape with the holocron in hand?                                  ||");
		System.out
				.println("====================================================================================================\n");

		FileInputStream fis;

		System.out.println("==========================");
		System.out.println("||      Main Menu       ||");
		System.out.println("==========================");
		System.out.println("|| 0. Start a new Game  ||");
		System.out.println("|| 1. Load Game         ||");
		System.out.println("|| 2. Start in debug    ||");
		System.out.println("||-1. Quit              ||");
		System.out.println("==========================");
		do {
			resp = getInput(resp);

			if (resp == 0) {
				System.out.println("Starting a new game...");
				break;
			}

			else if (resp == 1) {
				try {
					fis = new FileInputStream("saved.dat");
					ObjectInputStream ois = new ObjectInputStream(fis);
					saveState = (Save) ois.readObject();
					masters = saveState.getJedi();
					user = saveState.getPlayer(); // creates user of type Player
					invinc = (InvincibilityPowerUp) saveState.getItems()[1];
					radar = (RadarPowerUp) saveState.getItems()[3];
					addAmmo = (AddAmmoPowerUp) saveState.getItems()[2];
					h = (Holocron) saveState.getItems()[0];
					isDebug = saveState.getDebug();
					fis.close();
					ois.close();
					break;

				} catch (ClassNotFoundException | IOException e1) {
					System.out.println("There is no saved game");
				}
			} else if (resp == 2) {
				System.out.println("Starting a new game in debug...");
				isDebug = true;
				break;
			} else if (resp == -1) {
				System.out.println("Thank you for playing. \nQuitting game...");
				System.exit(0);
			} else
				System.out.println("Please enter a correct value.");

		} while (true);

		Map gameMap = new Map(isDebug); // creates gameMap of type Map
		if (resp != 1) {
			user = new Player(); // creates user of type Player
		}
		int temp = 0;
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
		if (resp != 1) {
			masters = new Jedi[6];

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
		} else {
			for (int i = 0; i < masters.length; i++) {
				if (masters[i].getLocation()[0] >= 0) {
					gameMap.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1]) // sets
							// location
							// of
							// masters[i]
							.setPerson(masters[i]);
				}
			}
		}
		int z = r.nextInt(9); // random room number for the holocron

		if (resp != 1) {
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
		}
		gameMap.getArea(h.getLocation()[0], h.getLocation()[1]).setItem(h); // sets
																			// location
																			// of
																			// the
																			// holocron
																			// to
																			// an
																			// area

		gameMap.setMaze(user.getLocation()[1] * 2 + 1,
				user.getLocation()[0] * 4 + 2, "P"); // sets
														// location
														// of
														// users
														// on
														// the
														// display
														// grid
		if (resp != 1) {
			do {
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			invinc = new InvincibilityPowerUp(x, y);
		}
		if (invinc.getLocation()[0] >= 0) {
			gameMap.getArea(invinc.getLocation()[0], invinc.getLocation()[1])
					.setItem(invinc);
		}

		if (resp != 1) {
			do {
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			addAmmo = new AddAmmoPowerUp(x, y);
		}
		if (addAmmo.getLocation()[0] >= 0) {
			gameMap.getArea(addAmmo.getLocation()[0], addAmmo.getLocation()[1])
					.setItem(addAmmo);
		}

		if (resp != 1) {
			do {
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			radar = new RadarPowerUp(x, y);
		}
		if (radar.getLocation()[0] >= 0) {
			gameMap.getArea(radar.getLocation()[0], radar.getLocation()[1])
					.setItem(radar);
		}

		if (user.getLocation()[1] * 2 - 1 > 0
				&& !gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1] - 1).getIsRoom()) {
			gameMap.setMaze(user.getLocation()[1] * 2 - 1,
					user.getLocation()[0] * 4 + 2, " ");
		}
		if (user.getLocation()[0] * 4 + 6 < 37
				&& !gameMap.getArea(user.getLocation()[0] + 1,
						user.getLocation()[1]).getIsRoom()) {
			gameMap.setMaze(user.getLocation()[1] * 2 + 1,
					user.getLocation()[0] * 4 + 6, " ");
		}
		if (user.getLocation()[1] * 2 + 3 < 19
				&& !gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1] + 1).getIsRoom()) {
			gameMap.setMaze(user.getLocation()[1] * 2 + 3,
					user.getLocation()[0] * 4 + 2, " ");
		}
		if (user.getLocation()[0] * 4 - 2 > 0
				&& !gameMap.getArea(user.getLocation()[0] - 1,
						user.getLocation()[1]).getIsRoom()) {
			gameMap.setMaze(user.getLocation()[1] * 2 + 1,
					user.getLocation()[0] * 4 - 2, " ");
		}

		if ((addAmmo.getLocation()[0] == user.getLocation()[0] && addAmmo
				.getLocation()[1] == user.getLocation()[1] - 1)
				|| (addAmmo.getLocation()[0] == user.getLocation()[0] + 1 && addAmmo
						.getLocation()[1] == user.getLocation()[1])
				|| (addAmmo.getLocation()[0] == user.getLocation()[0] && addAmmo
						.getLocation()[1] == user.getLocation()[1] + 1)
				|| (addAmmo.getLocation()[0] == user.getLocation()[0] - 1 && addAmmo
						.getLocation()[1] == user.getLocation()[1])) {
			if (addAmmo.getLocation()[0] >= 0) {
				gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
						addAmmo.getLocation()[0] * 4 + 2, "A");
			}
		}
		if ((radar.getLocation()[0] == user.getLocation()[0] && radar
				.getLocation()[1] == user.getLocation()[1] - 1)
				|| (radar.getLocation()[0] == user.getLocation()[0] + 1 && radar
						.getLocation()[1] == user.getLocation()[1])
				|| (radar.getLocation()[0] == user.getLocation()[0] && radar
						.getLocation()[1] == user.getLocation()[1] + 1)
				|| (radar.getLocation()[0] == user.getLocation()[0] - 1 && radar
						.getLocation()[1] == user.getLocation()[1])) {
			if (radar.getLocation()[0] >= 0) {
				gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
						radar.getLocation()[0] * 4 + 2, "R");
			}
		}
		if ((invinc.getLocation()[0] == user.getLocation()[0] && invinc
				.getLocation()[1] == user.getLocation()[1] - 1)
				|| (invinc.getLocation()[0] == user.getLocation()[0] + 1 && invinc
						.getLocation()[1] == user.getLocation()[1])
				|| (invinc.getLocation()[0] == user.getLocation()[0] && invinc
						.getLocation()[1] == user.getLocation()[1] + 1)
				|| (invinc.getLocation()[0] == user.getLocation()[0] - 1 && invinc
						.getLocation()[1] == user.getLocation()[1])) {
			if (invinc.getLocation()[0] >= 0) {
				gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
						invinc.getLocation()[0] * 4 + 2, "I");
			}
		}
		for (int i = 0; i < masters.length; i++) {
			if ((masters[i].getLocation()[0] == user.getLocation()[0] && masters[i]
					.getLocation()[1] == user.getLocation()[1] - 1)
					|| (masters[i].getLocation()[0] == user.getLocation()[0] + 1 && masters[i]
							.getLocation()[1] == user.getLocation()[1])
					|| (masters[i].getLocation()[0] == user.getLocation()[0] && masters[i]
							.getLocation()[1] == user.getLocation()[1] + 1)
					|| (masters[i].getLocation()[0] == user.getLocation()[0] - 1 && masters[i]
							.getLocation()[1] == user.getLocation()[1])) {
				if (masters[i].getLocation()[0] >= 0) {
					gameMap.setMaze(masters[i].getLocation()[1] * 2 + 1,
							masters[i].getLocation()[0] * 4 + 2, "J");
				}
			}
		}

		if (isDebug) {
			if (addAmmo.getLocation()[0] >= 0) {
				gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
						addAmmo.getLocation()[0] * 4 + 2, "A");
			}
			if (radar.getLocation()[0] >= 0) {
				gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
						radar.getLocation()[0] * 4 + 2, "R");
			}
			if (invinc.getLocation()[0] >= 0) {
				gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
						invinc.getLocation()[0] * 4 + 2, "I");
			}
			if (h.getLocation()[0] >= 0) {
				gameMap.setMaze(h.getLocation()[1] * 2 + 1,
						h.getLocation()[0] * 4 + 2, "H");
			}
			for (int i = 0; i < masters.length; i++) {
				if (masters[i].getLocation()[0] >= 0) {
					gameMap.setMaze(masters[i].getLocation()[1] * 2 + 1,
							masters[i].getLocation()[0] * 4 + 2, "J");
				}
			}
		}

		gameMap.setMaze(user.getLocation()[1] * 2 + 1,
				user.getLocation()[0] * 4 + 2, "P");// sets
		// P
		// to
		// current
		// location
		// on
		// the
		// display
		// grid
		gameMap.printMaze(); // prints maze with current positions

		if (isDebug) {
			if (addAmmo.getLocation()[0] >= 0) {
				gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
						addAmmo.getLocation()[0] * 4 + 2, " ");
			}
			if (radar.getLocation()[0] >= 0) {
				gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
						radar.getLocation()[0] * 4 + 2, " ");
			}
			if (invinc.getLocation()[0] >= 0) {
				gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
						invinc.getLocation()[0] * 4 + 2, " ");
			}
			for (int i = 0; i < masters.length; i++) {
				if (masters[i].getLocation()[0] >= 0) {
					gameMap.setMaze(masters[i].getLocation()[1] * 2 + 1,
							masters[i].getLocation()[0] * 4 + 2, " ");
				}
			}
		}

		do {

			System.out.println("==============================");
			System.out.println("||  Lives left: " + user.getLives()
					+ "           ||");
			System.out.println("==============================");
			System.out.println("||           Menu           ||");
			System.out.println("==============================");
			System.out.println("|| 1. Move                  || ");
			System.out.println("|| 2. Look                  ||");
			System.out.println("|| 3. Shoot (" + user.getGun().getAmmo()
					+ " shot(s) left)||");
			System.out.println("|| 0. Save                  ||");
			System.out.println("||-1. Quit                  ||");
			System.out.println("==============================");
			resp = getInput(resp);

			while (resp > 3) { // keeps inputs between 1-3
				System.out.println("That is an incorrect value, try again: ");
				resp = getInput(resp);
			}

			if (resp < 0) { // allows user to quit the game
				System.out
						.println("Would you like to save before you quit? 1 for yes");
				resp = getInput(resp);
				if (resp == 1)
					saveIt(saveState, user, masters, h, invinc, addAmmo, radar,
							isDebug);
				System.out.print("Thank you for playing.");
				System.exit(0);
			}
			if (resp == 0) {
				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "A");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "R");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "I");
					}
					if (h.getLocation()[0] >= 0) {
						gameMap.setMaze(h.getLocation()[1] * 2 + 1,
								h.getLocation()[0] * 4 + 2, "H");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "J");
						}
					}
				}
				gameMap.printMaze();

				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, " ");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, " ");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, " ");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, " ");
						}
					}
				}

				saveIt(saveState, user, masters, h, invinc, addAmmo, radar,
						isDebug);

			}
			if (resp == 1) { // when resp equals 1 means user wants to move
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					if (gameMap.getArea(user.getLocation()[0],// checks to see
																// if a jedi is
																// outside the
																// room
							user.getLocation()[1] - 1).getPerson() != null
							|| gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1] - 1).getPerson() != null
							|| gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1] - 1).getPerson() != null
							|| (user.getLocation()[1] - 2 > 0 && gameMap
									.getArea(user.getLocation()[0],
											user.getLocation()[1] - 2)
									.getPerson() != null)) {
						gameMap.printMaze();
						System.out
								.println("You hear a noise out the door, stay here where it's safe for now");
						inRoom = true;
						resp = 0;
					} else {
						resp = 1;
					}
				} else {
					System.out.println("==============="); // In game menu
					System.out.println("|| Direction ||");
					System.out.println("===============");
					System.out.println("|| 0. Stay   ||");
					System.out.println("|| 1. Up     || ");
					System.out.println("|| 2. Right  ||");
					System.out.println("|| 3. Down   ||");
					System.out.println("|| 4. Left   ||");
					System.out.println("===============");
					hasLooked = false;
					hasShot = false;

					resp = getInput(resp);
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
								user.getLocation()[1]).getIsRoom()) || resp < 0) { // input
					// stays
					// in
					// range
					if (resp > 4 || resp < 1) { // Keeps movement in range
						System.out
								.println("That is an incorrect value, try again: ");
						resp = getInput(resp);
					} else if ((user.getLocation()[0] == 0 && resp == 4)
							|| (user.getLocation()[0] == 8 && resp == 2)
							|| (user.getLocation()[1] == 0 && resp == 1)
							|| (user.getLocation()[1] == 8 && resp == 3)) {
						System.out
								.println("You're going out of bounds. try again: ");
						resp = getInput(resp);
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
								.println("You are walking into a wall...try again: ");
						resp = getInput(resp);
					} else {
						System.out
								.println("Someone's already in that spot. Try again: ");
						resp = getInput(resp);
					}
				}

				if (!isDebug) {
					if (user.getLocation()[1] * 2 - 1 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 1,
								user.getLocation()[0] * 4 + 2, "#");
					}
					if (user.getLocation()[0] * 4 + 6 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 6, "#");
					}
					if (user.getLocation()[1] * 2 + 3 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 3,
								user.getLocation()[0] * 4 + 2, "#");
					}
					if (user.getLocation()[0] * 4 - 2 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 2, "#");
					}

					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, "#"); // erases
																	// old
																	// position
																	// of
																	// the
																	// display
				} else {
					if (user.getLocation()[1] * 2 - 1 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 1,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 + 6 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 6, " ");
					}
					if (user.getLocation()[1] * 2 + 3 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 3,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 - 2 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 2, " ");
					}

					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, " ");
				}

				if (resp > 0) {
					if (gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {
						switch (getRoomNumber(user.getLocation()[0],
								user.getLocation()[1])) {
						case 1:
							tString = "1";
							break;
						case 2:
							tString = "2";
							break;
						case 3:
							tString = "3";
							break;
						case 4:
							tString = "4";
							break;
						case 5:
							tString = "5";
							break;
						case 6:
							tString = "6";
							break;
						case 7:
							tString = "7";
							break;
						case 8:
							tString = "8";
							break;
						default:
							tString = "9";
							break;
						}

						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 2, tString);
					}
					user.move(resp, gameMap); // moves user
				}

				if (!gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					if (user.getLocation()[1] * 2 - 1 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 1,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 + 6 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 6, " ");
					}
					if (user.getLocation()[1] * 2 + 3 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 3,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 - 2 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 2, " ");
					}
				}

				gameMap.setMaze(user.getLocation()[1] * 2 + 1,
						user.getLocation()[0] * 4 + 2, "P");// sets
				// P
				// to
				// current
				// location
				// on
				// the
				// display
				// grid
				temp = user.getInvincibilityCounter();
				if (temp == 0) {
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] < 0) {
							continue;
						}
						hasDied = masters[i].stab(user, gameMap, isDebug); // jedi
																			// stabs
						if (hasDied) {
							break;
						}
					}
				}
				for (int i = 0; i < masters.length; i++) {
					if (masters[i].getLocation()[1] > 0) {
						if (!isDebug) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "#");
						} else {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, " ");
						}
					}
				}
				mastersMove(gameMap, masters);

				if (user.getLives() == 0) {
					System.exit(0);
				}

				if (!isDebug) {
					if (addAmmo.getLocation()[1] > 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "#");
					}
					if (radar.getLocation()[1] > 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "#");
					}
					if (invinc.getLocation()[1] > 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "#");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[1] > 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "#");
						}
					}
				} else {
					if (addAmmo.getLocation()[1] > 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, " ");
					}
					if (radar.getLocation()[1] > 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, " ");
					}
					if (invinc.getLocation()[1] > 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, " ");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[1] > 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, " ");
						}
					}
				}

				if (!isDebug) {
					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, "#");
				} else {
					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, " ");
				}
				if (!gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					if ((addAmmo.getLocation()[0] == user.getLocation()[0] && addAmmo
							.getLocation()[1] == user.getLocation()[1] - 1)
							|| (addAmmo.getLocation()[0] == user.getLocation()[0] + 1 && addAmmo
									.getLocation()[1] == user.getLocation()[1])
							|| (addAmmo.getLocation()[0] == user.getLocation()[0] - 1 && addAmmo
									.getLocation()[1] == user.getLocation()[1])
							|| (addAmmo.getLocation()[0] == user.getLocation()[0] && addAmmo
									.getLocation()[1] == user.getLocation()[1] + 1)) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "A");
					}
					if ((radar.getLocation()[0] == user.getLocation()[0] && radar
							.getLocation()[1] == user.getLocation()[1] - 1)
							|| (radar.getLocation()[0] == user.getLocation()[0] + 1 && radar
									.getLocation()[1] == user.getLocation()[1])
							|| (radar.getLocation()[0] == user.getLocation()[0] - 1 && radar
									.getLocation()[1] == user.getLocation()[1])
							|| (radar.getLocation()[0] == user.getLocation()[0] && radar
									.getLocation()[1] == user.getLocation()[1] + 1)) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "R");
					}
					if ((invinc.getLocation()[0] == user.getLocation()[0] && invinc
							.getLocation()[1] == user.getLocation()[1] - 1)
							|| (invinc.getLocation()[0] == user.getLocation()[0] + 1 && invinc
									.getLocation()[1] == user.getLocation()[1])
							|| (invinc.getLocation()[0] == user.getLocation()[0] - 1 && invinc
									.getLocation()[1] == user.getLocation()[1])
							|| (invinc.getLocation()[0] == user.getLocation()[0] && invinc
									.getLocation()[1] == user.getLocation()[1] + 1)) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "I");
					}
					for (int i = 0; i < masters.length; i++) {

						if ((masters[i].getLocation()[0] == user.getLocation()[0] && masters[i]
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (masters[i].getLocation()[0] == user
										.getLocation()[0] + 1 && masters[i]
										.getLocation()[1] == user.getLocation()[1])
								|| (masters[i].getLocation()[0] == user
										.getLocation()[0] - 1 && masters[i]
										.getLocation()[1] == user.getLocation()[1])
								|| (masters[i].getLocation()[0] == user
										.getLocation()[0] && masters[i]
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "J");
						}
					}
				}

				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "A");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "R");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "I");
					}
					if (h.getLocation()[0] >= 0) {
						gameMap.setMaze(h.getLocation()[1] * 2 + 1,
								h.getLocation()[0] * 4 + 2, "H");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "J");
						}
					}
				}
				gameMap.setMaze(user.getLocation()[1] * 2 + 1,
						user.getLocation()[0] * 4 + 2, "P");
				if (!inRoom) {
					gameMap.printMaze(); // prints maze with current location
				}

				if (hasDied) {
					System.out.println("You Died!");
					hasDied = false;
				}

				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, " ");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, " ");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, " ");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, " ");
						}
					}
				}

				if (temp > 0) {
					System.out.println("you have " + temp
							+ " turns left being invincible");
				}
				if (user.getLocation()[0] == invinc.getLocation()[0]
						&& user.getLocation()[1] == invinc.getLocation()[1]) {
					gameMap.getArea(invinc.getLocation()[0],
							invinc.getLocation()[1]).setItem(null);
					if (!isDebug) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "#");
					} else {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, " ");
					}
					invinc.activate(user);
					System.out.println("You are now invincible!");
				}
				if (user.getLocation()[0] == addAmmo.getLocation()[0]
						&& user.getLocation()[1] == addAmmo.getLocation()[1]) {

					gameMap.getArea(addAmmo.getLocation()[0],
							addAmmo.getLocation()[1]).setItem(null);
					if (!isDebug) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "#");
					} else {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, " ");
					}
					addAmmo.activate(user.getGun());
					System.out
							.println("You found an extra charge for your blaster! You have "
									+ user.getGun().getAmmo() + " ammo left.");
				}
				if (user.getLocation()[0] == radar.getLocation()[0]
						&& user.getLocation()[1] == radar.getLocation()[1]) {
					gameMap.getArea(radar.getLocation()[0],
							radar.getLocation()[1]).setItem(null);
					if (!isDebug) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "#");
					} else {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, " ");
					}

					System.out
							.println("You found the radar! The holocron is in room "
									+ getRoomNumber(radar.activate(h)[0],
											radar.activate(h)[1]));

				}
				if (gameMap.getArea(user.getLocation()[0], // checks if the user
															// and the holocron
															// are on the same
															// location, if so
															// user wins
						user.getLocation()[1]) == gameMap.getArea(
						h.getLocation()[0], h.getLocation()[1])) {
					try {
						if (isDebug) {
							if (addAmmo.getLocation()[0] >= 0) {
								gameMap.setMaze(
										addAmmo.getLocation()[1] * 2 + 1,
										addAmmo.getLocation()[0] * 4 + 2, "A");
							}
							if (radar.getLocation()[0] >= 0) {
								gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
										radar.getLocation()[0] * 4 + 2, "R");
							}
							if (invinc.getLocation()[0] >= 0) {
								gameMap.setMaze(
										invinc.getLocation()[1] * 2 + 1,
										invinc.getLocation()[0] * 4 + 2, "I");
							}
							if (h.getLocation()[0] >= 0) {
								gameMap.setMaze(h.getLocation()[1] * 2 + 1,
										h.getLocation()[0] * 4 + 2, "H");
							}
							for (int i = 0; i < masters.length; i++) {
								if (masters[i].getLocation()[0] >= 0) {
									gameMap.setMaze(
											masters[i].getLocation()[1] * 2 + 1,
											masters[i].getLocation()[0] * 4 + 2,
											"J");
								}
							}
						}
						gameMap.printMaze();

						if (isDebug) {
							if (addAmmo.getLocation()[0] >= 0) {
								gameMap.setMaze(
										addAmmo.getLocation()[1] * 2 + 1,
										addAmmo.getLocation()[0] * 4 + 2, " ");
							}
							if (radar.getLocation()[0] >= 0) {
								gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
										radar.getLocation()[0] * 4 + 2, " ");
							}
							if (invinc.getLocation()[0] >= 0) {
								gameMap.setMaze(
										invinc.getLocation()[1] * 2 + 1,
										invinc.getLocation()[0] * 4 + 2, " ");
							}
							for (int i = 0; i < masters.length; i++) {
								if (masters[i].getLocation()[0] >= 0) {
									gameMap.setMaze(
											masters[i].getLocation()[1] * 2 + 1,
											masters[i].getLocation()[0] * 4 + 2,
											" ");
								}
							}
						}

						h.activate();
					} catch (GameOverException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					System.exit(0); // exits program
				}
				resp = 0; // sets input to zero
				inRoom = false;
			}
			if (resp == 2) { // if resp equals 2 means user wants to look in a
				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "A");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "R");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "I");
					}
					if (h.getLocation()[0] >= 0) {
						gameMap.setMaze(h.getLocation()[1] * 2 + 1,
								h.getLocation()[0] * 4 + 2, "H");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "J");
						}

					}
					gameMap.printMaze();
					System.out
							.println("There's no need to look in debug mode.");

					if (isDebug) {
						if (addAmmo.getLocation()[0] >= 0) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, " ");
						}
						if (radar.getLocation()[0] >= 0) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, " ");
						}
						if (invinc.getLocation()[0] >= 0) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, " ");
						}
						for (int i = 0; i < masters.length; i++) {
							if (masters[i].getLocation()[0] >= 0) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										" ");
							}
						}
					}

					continue;
				}
				// certain direction
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					gameMap.printMaze();
					System.out.println("You cant look through walls.");
					continue;
				}
				if (hasLooked) {
					System.out.println("You have already looked this turn.");
					continue;
				}
				System.out.println("===============");
				System.out.println("|| Direction ||");
				System.out.println("===============");
				System.out.println("|| 1. Up     || ");
				System.out.println("|| 2. Right  ||");
				System.out.println("|| 3. Down   ||");
				System.out.println("|| 4. Left   ||");
				System.out.println("===============");
				resp = getInput(resp);
				hasLooked = true;

				while (resp > 4 || resp < 1) { // keeps input within range
					System.out
							.println("That is an incorrect value, try again: ");
					resp = getInput(resp);
				}

				user.look(resp, gameMap, masters, addAmmo, invinc, radar);

				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "A");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "R");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "I");
					}
					if (h.getLocation()[0] >= 0) {
						gameMap.setMaze(h.getLocation()[1] * 2 + 1,
								h.getLocation()[0] * 4 + 2, "H");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, "J");
						}
					}
				}
				gameMap.setMaze(user.getLocation()[1] * 2 + 1,
						user.getLocation()[0] * 4 + 2, "P");// sets
				// P
				// to
				// current
				// location
				// on
				// the
				// display
				// grid

				gameMap.printMaze(); // prints maze with current location

				if (isDebug) {
					if (addAmmo.getLocation()[0] >= 0) {
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, " ");
					}
					if (radar.getLocation()[0] >= 0) {
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, " ");
					}
					if (invinc.getLocation()[0] >= 0) {
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, " ");
					}
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] >= 0) {
							gameMap.setMaze(
									masters[i].getLocation()[1] * 2 + 1,
									masters[i].getLocation()[0] * 4 + 2, " ");
						}
					}
				}

				if (!isDebug) {
					if (user.getLocation()[1] * 2 - 3 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 3,
								user.getLocation()[0] * 4 + 2, "#");
					}
					if (user.getLocation()[1] * 2 - 5 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 2).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 5,
								user.getLocation()[0] * 4 + 2, "#");
					}
					if (user.getLocation()[0] * 4 + 10 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 10, "#");
					}
					if (user.getLocation()[0] * 4 + 14 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 2,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 14, "#");
					}
					if (user.getLocation()[1] * 2 + 5 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 5,
								user.getLocation()[0] * 4 + 2, "#");
					}
					if (user.getLocation()[1] * 2 + 7 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 2).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 7,
								user.getLocation()[0] * 4 + 2, "#");
					}
					if (user.getLocation()[0] * 4 - 6 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 6, "#");
					}
					if (user.getLocation()[0] * 4 - 10 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 2,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 10, "#");
					}
				} else {
					if (user.getLocation()[1] * 2 - 3 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 3,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[1] * 2 - 5 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 2).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 5,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 + 10 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 10, " ");
					}
					if (user.getLocation()[0] * 4 + 14 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 2,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 14, " ");
					}
					if (user.getLocation()[1] * 2 + 5 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 5,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[1] * 2 + 7 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 2).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 7,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 - 6 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 6, " ");
					}
					if (user.getLocation()[0] * 4 - 10 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 2,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 10, " ");
					}
				}

				resp = 0; // sets input back to zero
			}
			if (resp == 3) { // 3 means user wants to shoot
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					gameMap.printMaze();
					System.out
							.println("You are safe in here, no need to shoot.");
					continue;
				}
				if (hasShot) {
					System.out.println("You have already shot this turn.");
					continue;
				}
				System.out.println("===============");
				System.out.println("|| Direction ||");
				System.out.println("===============");
				System.out.println("|| 1. Up     || ");
				System.out.println("|| 2. Right  ||");
				System.out.println("|| 3. Down   ||");
				System.out.println("|| 4. Left   ||");
				System.out.println("===============");
				resp = getInput(resp);
				hasShot = true;

				while (resp > 4 || resp <= 0) { // keeps running into input is
												// within range
					System.out
							.println("That is an incorrect value, try again: ");
					resp = getInput(resp);
				}
				if (user.getGun().getAmmo() > 0) {
					temp = user.shoot(resp, gameMap, isDebug);

					if (user.getLocation()[1] * 2 - 1 > 0
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 - 1,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 + 6 < 37
							&& !gameMap.getArea(user.getLocation()[0] + 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 + 6, " ");
					}
					if (user.getLocation()[1] * 2 + 3 < 19
							&& !gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] + 1).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 3,
								user.getLocation()[0] * 4 + 2, " ");
					}
					if (user.getLocation()[0] * 4 - 2 > 0
							&& !gameMap.getArea(user.getLocation()[0] - 1,
									user.getLocation()[1]).getIsRoom()) {
						gameMap.setMaze(user.getLocation()[1] * 2 + 1,
								user.getLocation()[0] * 4 - 2, " ");
					}

					if (isDebug) {
						if (addAmmo.getLocation()[0] >= 0) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, "A");
						}
						if (radar.getLocation()[0] >= 0) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, "R");
						}
						if (invinc.getLocation()[0] >= 0) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, "I");
						}
						if (h.getLocation()[0] >= 0) {
							gameMap.setMaze(h.getLocation()[1] * 2 + 1,
									h.getLocation()[0] * 4 + 2, "H");
						}
						for (int i = 0; i < masters.length; i++) {
							if (masters[i].getLocation()[0] >= 0) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										"J");
							}
						}
					}

					if (!gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {
						if ((addAmmo.getLocation()[0] == user.getLocation()[0] && addAmmo
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (addAmmo.getLocation()[0] == user
										.getLocation()[0] + 1 && addAmmo
										.getLocation()[1] == user.getLocation()[1])
								|| (addAmmo.getLocation()[0] == user
										.getLocation()[0] - 1 && addAmmo
										.getLocation()[1] == user.getLocation()[1])
								|| (addAmmo.getLocation()[0] == user
										.getLocation()[0] && addAmmo
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, "A");
						}
						if ((radar.getLocation()[0] == user.getLocation()[0] && radar
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (radar.getLocation()[0] == user
										.getLocation()[0] + 1 && radar
										.getLocation()[1] == user.getLocation()[1])
								|| (radar.getLocation()[0] == user
										.getLocation()[0] - 1 && radar
										.getLocation()[1] == user.getLocation()[1])
								|| (radar.getLocation()[0] == user
										.getLocation()[0] && radar
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, "R");
						}
						if ((invinc.getLocation()[0] == user.getLocation()[0] && invinc
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (invinc.getLocation()[0] == user
										.getLocation()[0] + 1 && invinc
										.getLocation()[1] == user.getLocation()[1])
								|| (invinc.getLocation()[0] == user
										.getLocation()[0] - 1 && invinc
										.getLocation()[1] == user.getLocation()[1])
								|| (invinc.getLocation()[0] == user
										.getLocation()[0] && invinc
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, "I");
						}
						for (int i = 0; i < masters.length; i++) {

							if ((masters[i].getLocation()[0] == user
									.getLocation()[0] && masters[i]
									.getLocation()[1] == user.getLocation()[1] - 1)
									|| (masters[i].getLocation()[0] == user
											.getLocation()[0] + 1 && masters[i]
											.getLocation()[1] == user
											.getLocation()[1])
									|| (masters[i].getLocation()[0] == user
											.getLocation()[0] - 1 && masters[i]
											.getLocation()[1] == user
											.getLocation()[1])
									|| (masters[i].getLocation()[0] == user
											.getLocation()[0] && masters[i]
											.getLocation()[1] == user
											.getLocation()[1] + 1)) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										"J");
							}
						}
					}

					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, "P");

					gameMap.printMaze(); // prints maze with current location

					if (isDebug) {
						if (addAmmo.getLocation()[0] >= 0) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, " ");
						}
						if (radar.getLocation()[0] >= 0) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, " ");
						}
						if (invinc.getLocation()[0] >= 0) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, " ");
						}
						for (int i = 0; i < masters.length; i++) {
							if (masters[i].getLocation()[0] >= 0) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										" ");
							}
						}
					}
					System.out.println("You heard " + temp + " scream(s)."); // users
																				// shoots
																				// in
																				// a
																				// direction
					System.out.println("You have " + user.getGun().getAmmo() // displays
																				// amount
																				// of
																				// ammo
																				// left
							+ " ammo left.");
				} else {
					if (isDebug) {
						if (addAmmo.getLocation()[0] >= 0) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, "A");
						}
						if (radar.getLocation()[0] >= 0) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, "R");
						}
						if (invinc.getLocation()[0] >= 0) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, "I");
						}
						if (h.getLocation()[0] >= 0) {
							gameMap.setMaze(h.getLocation()[1] * 2 + 1,
									h.getLocation()[0] * 4 + 2, "H");
						}
						for (int i = 0; i < masters.length; i++) {
							if (masters[i].getLocation()[0] >= 0) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										"J");
							}
						}
					}

					if (!gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {
						if ((addAmmo.getLocation()[0] == user.getLocation()[0] && addAmmo
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (addAmmo.getLocation()[0] == user
										.getLocation()[0] + 1 && addAmmo
										.getLocation()[1] == user.getLocation()[1])
								|| (addAmmo.getLocation()[0] == user
										.getLocation()[0] - 1 && addAmmo
										.getLocation()[1] == user.getLocation()[1])
								|| (addAmmo.getLocation()[0] == user
										.getLocation()[0] && addAmmo
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, "A");
						}
						if ((radar.getLocation()[0] == user.getLocation()[0] && radar
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (radar.getLocation()[0] == user
										.getLocation()[0] + 1 && radar
										.getLocation()[1] == user.getLocation()[1])
								|| (radar.getLocation()[0] == user
										.getLocation()[0] - 1 && radar
										.getLocation()[1] == user.getLocation()[1])
								|| (radar.getLocation()[0] == user
										.getLocation()[0] && radar
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, "R");
						}
						if ((invinc.getLocation()[0] == user.getLocation()[0] && invinc
								.getLocation()[1] == user.getLocation()[1] - 1)
								|| (invinc.getLocation()[0] == user
										.getLocation()[0] + 1 && invinc
										.getLocation()[1] == user.getLocation()[1])
								|| (invinc.getLocation()[0] == user
										.getLocation()[0] - 1 && invinc
										.getLocation()[1] == user.getLocation()[1])
								|| (invinc.getLocation()[0] == user
										.getLocation()[0] && invinc
										.getLocation()[1] == user.getLocation()[1] + 1)) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, "I");
						}
						for (int i = 0; i < masters.length; i++) {

							if ((masters[i].getLocation()[0] == user
									.getLocation()[0] && masters[i]
									.getLocation()[1] == user.getLocation()[1] - 1)
									|| (masters[i].getLocation()[0] == user
											.getLocation()[0] + 1 && masters[i]
											.getLocation()[1] == user
											.getLocation()[1])
									|| (masters[i].getLocation()[0] == user
											.getLocation()[0] - 1 && masters[i]
											.getLocation()[1] == user
											.getLocation()[1])
									|| (masters[i].getLocation()[0] == user
											.getLocation()[0] && masters[i]
											.getLocation()[1] == user
											.getLocation()[1] + 1)) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										"J");
							}
						}
					}

					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, "P");
					gameMap.printMaze(); // prints maze with current location

					if (isDebug) {
						if (addAmmo.getLocation()[0] >= 0) {
							gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
									addAmmo.getLocation()[0] * 4 + 2, " ");
						}
						if (radar.getLocation()[0] >= 0) {
							gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
									radar.getLocation()[0] * 4 + 2, " ");
						}
						if (invinc.getLocation()[0] >= 0) {
							gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
									invinc.getLocation()[0] * 4 + 2, " ");
						}
						for (int i = 0; i < masters.length; i++) {
							if (masters[i].getLocation()[0] >= 0) {
								gameMap.setMaze(
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										" ");
							}
						}
					}

					System.out.println("You have no ammo to shoot.");

				}

				resp = 0; // sets input back to zero
			}
		} while (true);

	}

	private static void saveIt(Save saveState, Player user, Jedi[] masters,
			Holocron h, InvincibilityPowerUp invinc, AddAmmoPowerUp addAmmo,
			RadarPowerUp radar, boolean isDebug) {

		Item[] i = new Item[4];
		i[0] = h;
		i[1] = invinc;
		i[2] = addAmmo;
		i[3] = radar;

		saveState.setItems(i);
		saveState.setPlayer(user);
		saveState.setEnemies(masters);
		saveState.setDebug(isDebug);

		FileOutputStream fos;

		try {
			fos = new FileOutputStream("saved.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveState);
			fos.close();
			oos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Saved");
	}

	/**
	 * (@link getInput) is a method to catch mismatch exceptions such as letters
	 * and symbols instead of integers.
	 * 
	 * @param resp
	 *            is our integer that holds the input of the user.
	 * @return
	 */
	public static int getInput(int resp) {
		System.out.print("Input: ");
		Scanner scn = new Scanner(System.in);
		try {
			resp = scn.nextInt();
		} catch (InputMismatchException e1) {
			resp = 10;
			scn.next();
		}
		return resp;
	}

	public static void mastersMove(Map gameMap, Jedi[] masters) {
		int z;
		Random r = new Random();

		for (int i = 0; i < masters.length; i++) {
			// if they're dead it does nothing
			if (masters[i].getLocation()[0] < 0) {
				continue;
			}

			// generating the number for which way they will move
			z = r.nextInt(4) + 1;

			while ((z == 1 && masters[i].getLocation()[1] - 1 >= 0 && gameMap
					.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] - 1).getIsRoom()) // this
																			// and
																			// the
																			// next
																			// four
																			// conditions
																			// check
																			// to
																			// see
																			// whether
																			// it
																			// is
																			// moving
																			// into
																			// a
																			// room...
																			// rather
																			// scarily
																			// enough
					|| (z == 2 && masters[i].getLocation()[0] + 1 <= 8 && gameMap
							.getArea(masters[i].getLocation()[0] + 1,
									masters[i].getLocation()[1]).getIsRoom())
					|| (z == 3 && masters[i].getLocation()[1] + 1 <= 8 && gameMap
							.getArea(masters[i].getLocation()[0],
									masters[i].getLocation()[1] + 1)
							.getIsRoom())
					|| (z == 4 && masters[i].getLocation()[0] - 1 >= 0 && gameMap
							.getArea(masters[i].getLocation()[0] - 1,
									masters[i].getLocation()[1]).getIsRoom())
					|| (z == 1 && masters[i].getLocation()[1] - 1 < 0) // this
																		// and
																		// the
																		// next
																		// four
																		// conditions
																		// check
																		// to
																		// see
																		// if
																		// they're
																		// moving
																		// out
																		// of
																		// bounds
					|| (z == 2 && masters[i].getLocation()[0] + 1 > 8)
					|| (z == 3 && masters[i].getLocation()[1] + 1 > 8)
					|| (z == 4 && masters[i].getLocation()[0] - 1 < 0)
					|| (z == 3 && gameMap.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] + 1) == gameMap
							.getArea(0, 7)) // this and the next couple of
											// conditions check to make sure
											// they do not move too close to the
											// spawn point
					|| (z == 4 && gameMap.getArea(
							masters[i].getLocation()[0] - 1,
							masters[i].getLocation()[1]) == gameMap.getArea(1,
							8))
					|| (z == 1 && gameMap.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] - 1).getPerson() != null) // this
																					// and
																					// the
																					// next
																					// few
																					// conditions
																					// check
																					// to
																					// ensure
																					// that
																					// they
																					// do
																					// not
																					// move
																					// into
																					// an
																					// Area
																					// with
																					// a
																					// Person
					|| (z == 2 && gameMap.getArea(
							masters[i].getLocation()[0] + 1,
							masters[i].getLocation()[1]).getPerson() != null)
					|| (z == 3 && gameMap.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] + 1).getPerson() != null)
					|| (z == 4 && gameMap.getArea(
							masters[i].getLocation()[0] - 1,
							masters[i].getLocation()[1]).getPerson() != null)) {

				if ((masters[i].getLocation()[1] - 1 > 0 && gameMap.getArea(
						masters[i].getLocation()[0],
						masters[i].getLocation()[1] - 1).getPerson() != null)
						&& (masters[i].getLocation()[0] + 1 < 8 && gameMap
								.getArea(masters[i].getLocation()[0] + 1,
										masters[i].getLocation()[1])
								.getPerson() != null)
						&& (masters[i].getLocation()[1] + 1 < 8 && gameMap
								.getArea(masters[i].getLocation()[0],
										masters[i].getLocation()[1] + 1)
								.getPerson() != null)
						&& (masters[i].getLocation()[0] - 1 > 0 && gameMap
								.getArea(masters[i].getLocation()[0] - 1,
										masters[i].getLocation()[1])
								.getPerson() != null)) {
					z = 5;
				}
				z = r.nextInt(4) + 1; // if any of the above conditions are met
										// it will try again
			}
			if (z < 5) {
				masters[i].move(z, gameMap);
			}// masters[i]
				// moves to
				// a random direction
		}
	}

	public static int getRoomNumber(int x, int y) {
		if (x == 1 && y == 1) {
			return 1;
		} else if (x == 4 && y == 1) {
			return 2;
		} else if (x == 7 && y == 1) {
			return 3;
		} else if (x == 1 && y == 4) {
			return 4;
		} else if (x == 4 && y == 4) {
			return 5;
		} else if (x == 7 && y == 4) {
			return 6;
		} else if (x == 1 && y == 7) {
			return 7;
		} else if (x == 4 && y == 7) {
			return 8;
		} else {
			return 9;
		}
	}
}
