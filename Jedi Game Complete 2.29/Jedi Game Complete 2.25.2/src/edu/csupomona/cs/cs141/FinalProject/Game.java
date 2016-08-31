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
		boolean hasLooked = false; // a switch the would prevent looking again
									// in same turn
		boolean hasShot = false;// a switch the would prevent shooting again in
								// same turn
		boolean isDebug = false;// a value that would be used to determine if
								// the
								// game is in Debug
		boolean hasDied = false;//
		boolean inRoom = false;// value representing whether the player is in a
								// room
		boolean GUI = false;// a value that is used to check if game is running
							// in GUI
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
		do {// runs till user enters a valid value
			resp = getInput();

			if (resp == 0) {// line that is used to start a new game
				System.out.println("Starting a new game...");
				break;
			}

			else if (resp == 1) {// the following will set the saved values from
									// the file to the current game
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
					fis.close(); // closes saved file
					ois.close(); // closes saved file
					break;

				} catch (ClassNotFoundException | IOException e1) {
					System.out.println("There is no saved game");
				}
			} else if (resp == 2) {// runs game in debug
				System.out.println("Starting a new game in debug...");
				isDebug = true;
				break;
			} else if (resp == -1) {
				System.out.println("Thank you for playing. \nQuitting game...");
				System.exit(0);
			} else
				// should user enter a wrong value
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
		if (resp != 1) {// will skip if user has loaded a game
			masters = new Jedi[6];

			for (int i = 0; i < masters.length; i++) {

				// restricts jedis from spawning too close to other jedis

				while (x == 0 && y == 8 || x == 0 && y == 7 || x == 1 && y == 8
						|| gameMap.getArea(x, y).getIsRoom()
						|| gameMap.getArea(x, y).getPerson() != null) {

					x = r.nextInt(9);
					y = r.nextInt(9);
				}
				masters[i] = new Jedi(x, y, false); // creates masters[i] of
													// type Jedi
				gameMap.getArea(masters[i].getLocation()[0],
						masters[i].getLocation()[1]).setPerson(masters[i]);
				// sets location of master[i] to an Area
			}
		} else {// if user loaded game will place Jedi back to Areas in Map
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

		if (resp != 1) {// instantiates the holocron; skips if user loads a game
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
		// sets location of the holocron to an area
		gameMap.getArea(h.getLocation()[0], h.getLocation()[1]).setItem(h);

		// sets location of user on the display grid
		gameMap.setMaze(user.getLocation()[1] * 2 + 1,
				user.getLocation()[0] * 4 + 2, "P");

		if (resp != 1) {// instantiates the InvinvibilityPowerUP; will skip if
						// user loaded a game
			do {// will get random numbers until the two values represent a
				// normal space with no items
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			invinc = new InvincibilityPowerUp(x, y);
		}
		if (invinc.getLocation()[0] >= 0) {// sets invic to an area
			gameMap.getArea(invinc.getLocation()[0], invinc.getLocation()[1])
					.setItem(invinc);
		}

		if (resp != 1) {// instantiates the AddAmmoPowerUP;will skip if user
						// loaded a game
			do {// will get random numbers until the two values represent a
				// normal room with no items
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			addAmmo = new AddAmmoPowerUp(x, y);
		}
		if (addAmmo.getLocation()[0] >= 0) {// sets addAmmo to an area
			gameMap.getArea(addAmmo.getLocation()[0], addAmmo.getLocation()[1])
					.setItem(addAmmo);
		}

		if (resp != 1) {// instantiates the RadarPowerUP;will skip if user
						// loaded a game
			do {// will get random numbers until the two values represent a
				// normal room with no items
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			radar = new RadarPowerUp(x, y);
		}
		if (radar.getLocation()[0] >= 0) {// sets radar to an area
			gameMap.getArea(radar.getLocation()[0], radar.getLocation()[1])
					.setItem(radar);
		}

		// the following if statements will reveal the spaces adjacent to the
		// player
		if (!gameMap.getArea(user.getLocation()[0], user.getLocation()[1])
				.getIsRoom()) {
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

			// prints A on the visual map if addAmmo is adjacent to the player
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
			// prints R on the visual map if radar is adjacent to the player
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
			// prints I on the visual map if invic is adjacent to the player
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
			// prints J on the visual map if a Jedi is adjacent to the player
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
		}

		if (isDebug) {// will skip is user is running a normal game
			/*
			 * the folloiwng is the setting of the intial locations of the items
			 * and the Jedi to the visual map if the user ran the game in debug
			 */
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

		// sets P to current location on the display grid
		gameMap.setMaze(user.getLocation()[1] * 2 + 1,
				user.getLocation()[0] * 4 + 2, "P");

		System.out.println("\n\n\n=================================");
		System.out.println("|| Select a style of game play ||");
		System.out.println("=================================");
		System.out.println("|| 0. Normal text mode         ||");
		System.out.println("|| 1. GUI mode                 ||");
		System.out.println("=================================\n");

		resp = -1;

		while (resp != 0 && resp != 1)
			resp = getInput();

		if (resp == 0) // game runs in console
			GUI = false;

		else
			// game runs in GUI
			GUI = true;

		if (!GUI)// prints text maze to console only if game is not running in
					// GUI
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
		if (GUI)// runs game in GUI
			new GUIClass(user, masters, invinc, radar, addAmmo, h, isDebug,
					gameMap, saveState);

		while (!GUI) {// runs game in text should it not be running in GIU

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
			resp = getInput();

			while (resp > 3) { // keeps inputs between 1-3
				System.out.println("That is an incorrect value, try again: ");
				resp = getInput();
			}

			if (resp < 0) { // allows user to quit the game
				System.out
						.println("Would you like to save before you quit? 1 for yes");
				resp = getInput();
				if (resp == 1)// saves the game
					saveIt(saveState, user, masters, h, invinc, addAmmo, radar,
							isDebug, false);
				System.out.print("Thank you for playing.");
				System.exit(0);
			}
			if (resp == 0) {// if user wants to save the game
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
				gameMap.printMaze(); // prints maze if not in GUI

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
						isDebug, false); // saves the game

			}
			if (resp == 1) { // when resp equals 1 means user wants to move
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getIsRoom()) {
					// checks to see if a jedi is outside of the room
					if (gameMap.getArea(user.getLocation()[0],
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
					} else {// if player wants to move while in a room,
							// automatically moves playerout of room
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

					resp = getInput(); // gets input from user
				}

				// runs until input is valid based on wheter player trys to move
				// out of range or when the player
				// attemps to walk into an occupied space
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
								user.getLocation()[1]).getIsRoom()) || resp < 0) {

					if (resp > 4 || resp < 1) { // Keeps movement in range
						System.out
								.println("That is an incorrect value, try again: ");

						resp = getInput();// gets input from user

						/* keeps running if user tries to go out of bounds */
					} else if ((user.getLocation()[0] == 0 && resp == 4)
							|| (user.getLocation()[0] == 8 && resp == 2)
							|| (user.getLocation()[1] == 0 && resp == 1)
							|| (user.getLocation()[1] == 8 && resp == 3)) {

						System.out
								.println("You're going out of bounds. try again: ");

						resp = getInput();// gets input from user

					} else if (resp == 1 // checks if user runs into a wall
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
						resp = getInput(); // gets input from user
					} else {
						System.out
								.println("Someone's already in that spot. Try again: ");
						resp = getInput(); // gets input from user
					}
				}
				/*
				 * the following if statements will make the original view
				 * spaces turn to dark when the player moves doesnt run if
				 * player is running in debug
				 */
				if (!isDebug) { // if game is running in console
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
							user.getLocation()[0] * 4 + 2, "#");
				} else { // sets fogged spaces to viewable spaces
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

				if (resp > 0) {// reprints room numbers if player leaves room
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
				// sets P to the current location on the display grid
				gameMap.setMaze(user.getLocation()[1] * 2 + 1,
						user.getLocation()[0] * 4 + 2, "P");
				temp = user.getInvincibilityCounter();
				if (temp == 0) {// keeps user in same spot
					for (int i = 0; i < masters.length; i++) {
						if (masters[i].getLocation()[0] < 0) {
							continue;
						}
						hasDied = mastersStab(masters, isDebug, user, gameMap,
								hasDied, false); // jedi stabs
						if (hasDied) {
							break;
						}
					}
				}
				// prints a dark space where the Jedi orignally was or an open
				// space if in debug
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
				mastersMove(gameMap, masters, user);// moves the Jedi

				if (user.getLives() == 0) {
					System.exit(0);
				}

				if (!isDebug) {// darkens position of items should player move
								// away; only in console mode
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

				if (!isDebug) { // not in debug
					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, "#");
				} else {
					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, " ");
				}
				// prints A if addAmmo is adjacent to player
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
					// prints R if radar is adjacent to player
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
					// prints I if invincibility is adjacent to player
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
					// prints J if a Jedi adjacent to player
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

				if (isDebug) {// prints items/player when in debug mode

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
				gameMap.setMaze(user.getLocation()[1] * 2 + 1, // prints player
						user.getLocation()[0] * 4 + 2, "P");
				if (!inRoom) {
					gameMap.printMaze(); // prints maze with current location
				}

				if (hasDied) {
					mastersStopChasing(masters); // stops chasing the player
					System.out.println("You Died!");
					hasDied = false; // resets the player to alive
				}

				if (isDebug) { // clears items/players of the maze
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
				// activates invic if player is standing on it
				if (user.getLocation()[0] == invinc.getLocation()[0]
						&& user.getLocation()[1] == invinc.getLocation()[1]) {

					gameMap.getArea(invinc.getLocation()[0], // clears invinc
							invinc.getLocation()[1]).setItem(null);

					if (!isDebug) // sets fog of war
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, "#");
					else
						// if it is debug sets it to blank
						gameMap.setMaze(invinc.getLocation()[1] * 2 + 1,
								invinc.getLocation()[0] * 4 + 2, " ");

					invinc.activate(user); // activates the invic

					System.out.println("You are now invincible!");
				}
				// activates addAmmo if player is standing on it
				if (user.getLocation()[0] == addAmmo.getLocation()[0]
						&& user.getLocation()[1] == addAmmo.getLocation()[1]) {

					gameMap.getArea(addAmmo.getLocation()[0], // clears item
							addAmmo.getLocation()[1]).setItem(null);

					if (!isDebug)
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, "#");

					else
						gameMap.setMaze(addAmmo.getLocation()[1] * 2 + 1,
								addAmmo.getLocation()[0] * 4 + 2, " ");

					addAmmo.activate(user.getGun()); // activates Ammo

					System.out
							.println("You found an extra charge for your blaster! You have "
									+ user.getGun().getAmmo() + " ammo left.");
				}
				// activates radar if player is standing on it
				if (user.getLocation()[0] == radar.getLocation()[0]
						&& user.getLocation()[1] == radar.getLocation()[1]) {

					gameMap.getArea(radar.getLocation()[0],
							radar.getLocation()[1]).setItem(null);// clears item

					if (!isDebug)
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, "#");
					else
						gameMap.setMaze(radar.getLocation()[1] * 2 + 1,
								radar.getLocation()[0] * 4 + 2, " ");

					System.out
							.println("You found the radar! The holocron is in room "
									+ getRoomNumber(radar.activate(h)[0],
											radar.activate(h)[1]));

				}
				// activates holocron if player is standing on it
				if (gameMap.getArea(user.getLocation()[0],
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

						gameMap.printMaze(); // prints maze

						if (isDebug) { // clears maze
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

						h.activate(); // activates holocron
					} catch (GameOverException e) {

						System.out.println(e.getMessage());
					}
					System.exit(0); // exits program
				}
				resp = 0; // sets input to zero
				inRoom = false; // user is no longer in a room
			}
			if (resp == 2) { // if resp equals 2 means user wants to look

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
					gameMap.printMaze(); // prints maze
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

					gameMap.printMaze(); // prints maze

					System.out.println("You cant look through walls.");

					continue;
				}
				if (hasLooked) {// prevents user from looking twice in a turn
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
				resp = getInput();
				hasLooked = true;

				while (resp > 4 || resp < 1) { // keeps input within range
					System.out
							.println("That is an incorrect value, try again: ");
					resp = getInput(); // gets input of the user
				}

				user.look(resp, gameMap, masters, addAmmo, invinc, radar);

				if (isDebug) {// prints out location of Jedi and items if in
								// debug
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
				// sets P to current location on the display grid
				gameMap.setMaze(user.getLocation()[1] * 2 + 1,
						user.getLocation()[0] * 4 + 2, "P");
				gameMap.printMaze(); // prints maze with current location

				if (isDebug) {// reverts spaces to blank after print
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

				if (!isDebug) { // sets fog of war
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

					gameMap.printMaze(); // prints maze

					System.out
							.println("You are safe in here, no need to shoot.");
					continue;
				}
				if (hasShot) {// prevents user from shooting twice per turn
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
				resp = getInput();
				hasShot = true;

				while (resp > 4 || resp <= 0) { // keeps running into input is
												// within range
					System.out
							.println("That is an incorrect value, try again: ");
					resp = getInput(); // gets input from user
				}
				if (user.getGun().getAmmo() > 0) {// shoots only if gun has ammo
					temp = user.shoot(resp, gameMap, isDebug); // jedis killed

					/* clear area around player */
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

					if (isDebug) {// prints location of items and Jedi to map;
									// only in debug
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
					/* dosn't print run if player is in a room */
					if (!gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {

						// prints A if addAmmo is adjacent to player
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

						// prints R if radar is adjacent to player
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

						// prints I if invic is adjacent to player
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

						// prints J if a Jedi is adjacent to player
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
					// mentions how many were killed
					System.out.println("You heard " + temp + " scream(s).");

					// displays remaining ammo
					System.out.println("You have " + user.getGun().getAmmo()
							+ " ammo left.");
				} else {
					if (isDebug) {// prints items and jedis if in debug mode

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
					/* wont run if player is in a room */
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

							// prints location of ammo
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

							// prints location of radar
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

							// prints location of invincibility
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
										// prints location of Jedis
										masters[i].getLocation()[1] * 2 + 1,
										masters[i].getLocation()[0] * 4 + 2,
										"J");
							}
						}
					}

					// prints location of user
					gameMap.setMaze(user.getLocation()[1] * 2 + 1,
							user.getLocation()[0] * 4 + 2, "P");

					gameMap.printMaze(); // prints maze with current location

					// clears map display
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
		}

	}

	/**
	 * Function that saves the game
	 * 
	 * @param saveState
	 *            the Save object that will hold the other parameters
	 * @param user
	 *            the player
	 * @param masters
	 *            the array of jedi
	 * @param h
	 *            the holocron
	 * @param invinc
	 *            the Invincibility powerup
	 * @param addAmmo
	 *            the addammo powerup
	 * @param radar
	 *            the radar powerup
	 * @param isDebug
	 *            the value if game is running in debug
	 * @param GUI
	 *            the value if game is running in GUI
	 */
	public static void saveIt(Save saveState, Player user, Jedi[] masters,
			Holocron h, InvincibilityPowerUp invinc, AddAmmoPowerUp addAmmo,
			RadarPowerUp radar, boolean isDebug, boolean GUI) {

		Item[] i = new Item[4]; // array of Items
		i[0] = h; // holocron
		i[1] = invinc; // invincibility
		i[2] = addAmmo; // ammo
		i[3] = radar; // radar

		saveState.setItems(i); // saves items
		saveState.setPlayer(user); // saves user
		saveState.setEnemies(masters); // saves jedis
		saveState.setDebug(isDebug); // saves if debug mode or not

		FileOutputStream fos;

		try {
			fos = new FileOutputStream("saved.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveState);
			fos.close(); // closes file
			oos.close(); // closes file

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (!GUI)
			System.out.println("Saved");
	}

	/**
	 * (@link getInput) is a method to catch mismatch exceptions such as letters
	 * and symbols instead of integers.
	 * 
	 * @param resp
	 *            is our integer that holds the input of the user.
	 * @return resp
	 */
	public static int getInput() {

		int resp; // input of the user

		System.out.print("Input: ");
		Scanner scn = new Scanner(System.in); // object to take input
		try {
			resp = scn.nextInt();
		} catch (InputMismatchException e1) { // catches if the input is not an
												// integer
			resp = 10;
			scn.next();
		}
		return resp; // returns input of the user
	}

	/**
	 * Function used that runs the code to make the jedi move
	 * 
	 * @param gameMap
	 *            the Map
	 * @param masters
	 *            the Jedi
	 * @param user
	 *            the player
	 */
	public static void mastersMove(Map gameMap, Jedi[] masters, Player user) {
		int z;
		Random r = new Random(); // object of type Random

		for (int i = 0; i < masters.length; i++) {
			// if they're dead it does nothing
			if (masters[i].getLocation()[0] < 0) {
				continue;
			}

			// generating the number for which way they will move
			if (!masters[i].getChasing()) // part of the AI
				z = r.nextInt(4) + 1;

			else
				z = getChaseDirection(masters[i], user, gameMap);

			/*
			 * The following while loop will keep repeating itself the until the
			 * Jedi no longer attempts to move into a room, trys to go out of
			 * bounds, move to the two squares surrounding the starting point,
			 * or trys to enter a space where another Person exists
			 */
			while ((z == 1 && masters[i].getLocation()[1] - 1 >= 0 && gameMap
					.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] - 1).getIsRoom())

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
					|| (z == 1 && masters[i].getLocation()[1] - 1 < 0)
					|| (z == 2 && masters[i].getLocation()[0] + 1 > 8)
					|| (z == 3 && masters[i].getLocation()[1] + 1 > 8)
					|| (z == 4 && masters[i].getLocation()[0] - 1 < 0)
					|| (z == 3 && gameMap.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] + 1) == gameMap
							.getArea(0, 7))
					|| (z == 4 && gameMap.getArea(
							masters[i].getLocation()[0] - 1,
							masters[i].getLocation()[1]) == gameMap.getArea(1,
							8))
					|| (z == 1 && gameMap.getArea(masters[i].getLocation()[0],
							masters[i].getLocation()[1] - 1).getPerson() != null)
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
				masters[i].setChasing(false); // part of the AI
			}
			if (z < 5) {
				masters[i].move(z, gameMap);
				masterLookAround(gameMap, masters, masters[i]); // looks for
																// user

			}// masters[i] moves to a random direction
		}
	}

	/**
	 * (@link mastersStab) is a method that gets all jedis to stab.
	 * 
	 * @param masters
	 *            object of type (@link Jedi).
	 * @param isDebug
	 *            keeps track if game is running in debug mode.
	 * @param user
	 *            object of type (@link Player).
	 * @param gameMap
	 *            object of type (@link Map).
	 * @param hasDied
	 *            boolean field to determine is user has died.
	 * @param GUI
	 *            keeps track of GUI or text version to print on the console or
	 *            not.
	 * @return
	 */
	public static boolean mastersStab(Jedi[] masters, boolean isDebug,
			Player user, Map gameMap, boolean hasDied, boolean GUI) {

		for (int i = 0; i < masters.length; i++) {

			if (masters[i].getLocation()[0] < 0)
				continue;

			hasDied = masters[i].stab(user, gameMap, isDebug, GUI); // stab

			if (hasDied) // if player dies stops all stabbings
				break;

		}

		return hasDied; // player dies or not
	}

	/**
	 * (@link masterLookAround) is a method that looks around to see if the
	 * player near by to chase.
	 * 
	 * @param gameMap
	 *            is an object of type (@link Map).
	 * @param masters
	 *            array of all jedi objects.
	 * @param whichJedi
	 *            object of type (@link Jedi)
	 */
	public static void masterLookAround(Map gameMap, Jedi[] masters,
			Jedi whichJedi) {

		boolean isJedi = true; // Jedi or not

		// if the left of the jedi is not null and not a room
		if ((whichJedi.getLocation()[0] > 0
				&& gameMap.getArea(whichJedi.getLocation()[0] - 1,
						whichJedi.getLocation()[1]).getPerson() != null && !gameMap
				.getArea(whichJedi.getLocation()[0] - 1,
						whichJedi.getLocation()[1]).getIsRoom())) {

			for (int j = 0; j < masters.length; j++) {

				// checks if location to the left is a jedi
				if (gameMap.getArea(whichJedi.getLocation()[0] - 1,
						whichJedi.getLocation()[1]).getPerson() == masters[j]) {

					isJedi = true;

					break;
				}

				else
					isJedi = false;
				//
			}

			if (!isJedi)
				whichJedi.setChasing(true); // makes jedi chase
		}

		// checks upward if not a room and not null
		else if (whichJedi.getLocation()[1] > 0
				&& gameMap.getArea(whichJedi.getLocation()[0],
						whichJedi.getLocation()[1] - 1).getPerson() != null
				&& !gameMap.getArea(whichJedi.getLocation()[0],
						whichJedi.getLocation()[1] - 1).getIsRoom()) {

			for (int j = 0; j < masters.length; j++) {

				// checks if location above jedi is another jedi
				if (gameMap.getArea(whichJedi.getLocation()[0],
						whichJedi.getLocation()[1] - 1).getPerson() == masters[j]) {

					isJedi = true;

					break;
				}

				else
					isJedi = false;
			}

			if (!isJedi)
				whichJedi.setChasing(true); // makes jedi chase
		}

		// checks if location to the right of jedi is not null and not a room
		else if (whichJedi.getLocation()[0] < 8
				&& gameMap.getArea(whichJedi.getLocation()[0] + 1,
						whichJedi.getLocation()[1]).getPerson() != null
				&& !gameMap.getArea(whichJedi.getLocation()[0] + 1,
						whichJedi.getLocation()[1]).getIsRoom()) {

			for (int j = 0; j < masters.length; j++) {

				// checks to the right if there is a jedi
				if (gameMap.getArea(whichJedi.getLocation()[0] + 1,
						whichJedi.getLocation()[1]).getPerson() == masters[j]) {

					isJedi = true;

					break;
				}

				else
					isJedi = false;
			}

			if (!isJedi)
				whichJedi.setChasing(true); // makes jedi chase
		}

		// checks towards the bottom if it is not null and not a room
		else if (whichJedi.getLocation()[1] < 8
				&& gameMap.getArea(whichJedi.getLocation()[0],
						whichJedi.getLocation()[1] + 1).getPerson() != null
				&& !gameMap.getArea(whichJedi.getLocation()[0],
						whichJedi.getLocation()[1] + 1).getIsRoom()) {

			for (int j = 0; j < masters.length; j++) {

				// checks if there is a jedi to the bottom
				if (gameMap.getArea(whichJedi.getLocation()[0],
						whichJedi.getLocation()[1] + 1).getPerson() == masters[j]) {

					isJedi = true;

					break;
				}

				else
					isJedi = false;
			}

			if (!isJedi)
				whichJedi.setChasing(true); // makes jedi chase
		}
	}

	/**
	 * (@link getChaseDirection) checks which direction the player moves to
	 * chase in that direction
	 * 
	 * @param master
	 *            object of type (@link Jedi).
	 * @param user
	 *            object of type (@link Player).
	 * @param gameMap
	 *            object of type (@link Map).
	 * @return the return is an integer which is used to move the jedi.
	 */
	public static int getChaseDirection(Jedi master, Player user, Map gameMap) {
		Random rand = new Random();

		if (master.getLocation()[1] > 1
				&& user.getLocation()[1] == (master.getLocation()[1] - 2))
			return 1; // because the user is 2 spaces up

		else if (master.getLocation()[0] < 7
				&& user.getLocation()[0] == (master.getLocation()[0] + 2))
			return 2; // because the user is 2 spaces right

		else if (master.getLocation()[1] < 7
				&& user.getLocation()[1] == (master.getLocation()[1] + 2))
			return 3; // because the user is 2 spaces down

		else if (master.getLocation()[0] > 1
				&& user.getLocation()[0] == (master.getLocation()[0] - 2))
			return 4; // because the user is 2 spaces left

		else if ((user.getLocation()[1] < master.getLocation()[1])// top left
				&& (user.getLocation()[0] < master.getLocation()[0])) {

			if (gameMap.getArea(master.getLocation()[0], // checks for corner
					master.getLocation()[1] - 1).getIsRoom()
					|| gameMap.getArea(master.getLocation()[0] - 1,
							master.getLocation()[1]).getIsRoom()
					|| gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {

				master.setChasing(false); // player turned a corner

				return rand.nextInt(4) + 1; // moves jedi randomly
			}

			else {
				if (gameMap.getArea(master.getLocation()[0] - 1,
						master.getLocation()[1]).getPerson() != null)
					return 1; // jedi is to the left so moves up

				else if (gameMap.getArea(master.getLocation()[0],
						master.getLocation()[1] - 1).getPerson() != null)
					return 4; // jedi to the top so moves left

				else
					return (rand.nextInt(2)) * 3 + 1; // moves left or up
			}
		}

		else if ((user.getLocation()[1] < master.getLocation()[1]) // top right
				&& (user.getLocation()[0] > master.getLocation()[0])) {

			if (gameMap.getArea(master.getLocation()[0], // checks for corner
					master.getLocation()[1] - 1).getIsRoom()
					|| gameMap.getArea(master.getLocation()[0] + 1,
							master.getLocation()[1]).getIsRoom()
					|| gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {

				master.setChasing(false); // player turned a corner

				return rand.nextInt(4) + 1; // moves jedi randomly
			}

			else {
				if (gameMap.getArea(master.getLocation()[0] + 1,
						master.getLocation()[1]).getPerson() != null)
					return 1; // jedi to the right so moves up

				else if (gameMap.getArea(master.getLocation()[0],
						master.getLocation()[1] - 1).getPerson() != null)
					return 2; // jedi to the top so moves right

				else
					return rand.nextInt(2) + 1; // moves up or right
			}
		}

		else if ((user.getLocation()[1] > master.getLocation()[1]) // down right
				&& (user.getLocation()[0] > master.getLocation()[0])) {

			if (gameMap.getArea(master.getLocation()[0], // checks for corner
					master.getLocation()[1] + 1).getIsRoom()
					|| gameMap.getArea(master.getLocation()[0] + 1,
							master.getLocation()[1]).getIsRoom()
					|| gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {

				master.setChasing(false); // player turned a corner

				return rand.nextInt(4) + 1; // jedi moves randomly
			}

			else {
				if (gameMap.getArea(master.getLocation()[0] + 1,
						master.getLocation()[1]).getPerson() != null)
					return 3; // another jedi to the right moves down

				else if (gameMap.getArea(master.getLocation()[0],
						master.getLocation()[1] + 1).getPerson() != null)
					return 2; // another jedi to the bottom so moves right

				else
					return rand.nextInt(2) + 2; // moves down or right
			}
		}

		else { // bottom left corner
			if (gameMap.getArea(master.getLocation()[0], // checks corner
					master.getLocation()[1] + 1).getIsRoom()
					|| gameMap.getArea(master.getLocation()[0] - 1,
							master.getLocation()[1]).getIsRoom()
					|| gameMap.getArea(user.getLocation()[0],
							user.getLocation()[1]).getIsRoom()) {

				master.setChasing(false); // stops chasing

				return rand.nextInt(4) + 1; // jedi randomly moves
			}

			else {
				if (gameMap.getArea(master.getLocation()[0] - 1,
						master.getLocation()[1]).getPerson() != null)
					return 3; // another jedi to the left moves down

				else if (gameMap.getArea(master.getLocation()[0],
						master.getLocation()[1] + 1).getPerson() != null)
					return 4; // another jedi to the bottom so moves left

				else
					return rand.nextInt(2) + 3; // moves down or left
			}
		}
	}

	/**
	 * (@link mastersStopChasing) is a method that is called when a player dies
	 * resulting in all jedis to stop chasing the player.
	 * 
	 * @param masters
	 */
	public static void mastersStopChasing(Jedi[] masters) {
		for (int i = 0; i < masters.length; i++)

			masters[i].setChasing(false); // makes all jedis stop chasing
	}

	/**
	 * (@link getRoomNumber) is a method that determines the room number
	 * 
	 * @param x
	 *            is the x coordinate of the array
	 * @param y
	 *            is the y coordinate of the array
	 * @return returns an integer 1-9 depending on which room it is
	 */
	public static int getRoomNumber(int x, int y) {
		if (x == 1 && y == 1) { // location of room 1
			return 1;
		} else if (x == 4 && y == 1) { // location of room 2
			return 2;
		} else if (x == 7 && y == 1) { // location of room 3
			return 3;
		} else if (x == 1 && y == 4) { // location of room 4
			return 4;
		} else if (x == 4 && y == 4) { // location of room 5
			return 5;
		} else if (x == 7 && y == 4) { // location of room 6
			return 6;
		} else if (x == 1 && y == 7) { // location of room 7
			return 7;
		} else if (x == 4 && y == 7) { // location of room 8
			return 8;
		} else { // room 9 because no other rooms left
			return 9;
		}
	}
}
