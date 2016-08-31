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

		Scanner scn = new Scanner(System.in);
		int resp = 0; // int used to respond to the games questions

		Save saveState = new Save();
		Jedi[] masters = null;
		Player user = null; // creates user of type Player
		InvincibilityPowerUp invinc = null;
		RadarPowerUp radar = null;
		AddAmmoPowerUp addAmmo = null;
		Holocron h = null;

		FileInputStream fis;
		try {
			fis = new FileInputStream("saved.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			saveState = (Save) ois.readObject();
			System.out
					.println("Would you like to load your previous game?(1 yes, any number for no)");
			resp = scn.nextInt();
			if (resp == 1) {
				masters = saveState.getJedi();
				user = saveState.getPlayer(); // creates user of type Player
				invinc = (InvincibilityPowerUp) saveState.getItems()[1];
				radar = (RadarPowerUp) saveState.getItems()[3];
				addAmmo = (AddAmmoPowerUp) saveState.getItems()[2];
				h = (Holocron) saveState.getItems()[0];

			}
		} catch (ClassNotFoundException | IOException e1) {
			System.out.println("Did not load.");
		}

		Map gameMap = new Map(); // creates gameMap of type Map
		if (resp != 1) {
			user = new Player(); // creates user of type Player
		}
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
				gameMap.getArea(masters[i].getLocation()[0],
						masters[i].getLocation()[1]) // sets
						// location
						// of
						// masters[i]
						.setPerson(masters[i]);
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

		gameMap.setMaze(h.getLocation()[1] * 4 + 1, h.getLocation()[0] * 4 + 1,
				"H"); // sets
		// the
		// location
		// of
		// the
		// holocron
		// on
		// the
		// display
		// grid
		gameMap.setMaze(user.getLocation()[1] * 4 + 3,
				user.getLocation()[0] * 4 + 3, "P"); // sets
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
		gameMap.getArea(invinc.getLocation()[0], invinc.getLocation()[1])
				.setItem(invinc);

		gameMap.setMaze(invinc.getLocation()[1] * 4 + 1,
				invinc.getLocation()[0] * 4 + 1, "I");

		if (resp != 1) {
			do {
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			addAmmo = new AddAmmoPowerUp(x, y);
		}
		gameMap.getArea(addAmmo.getLocation()[0], addAmmo.getLocation()[1])
				.setItem(addAmmo);

		gameMap.setMaze(addAmmo.getLocation()[1] * 4 + 1,
				addAmmo.getLocation()[0] * 4 + 1, "A");

		if (resp != 1) {
			do {
				x = r.nextInt(9);
				y = r.nextInt(9);
			} while (((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
					|| (gameMap.getArea(x, y).getItem() != null)
					|| (x == 0 && y == 8));

			radar = new RadarPowerUp(x, y);
		}
		gameMap.getArea(radar.getLocation()[0], radar.getLocation()[1])
				.setItem(radar);

		gameMap.setMaze(radar.getLocation()[1] * 4 + 1,
				radar.getLocation()[0] * 4 + 1, "R");

		for (int i = 0; i < masters.length; i++) {
			gameMap.setMaze(masters[i].getLocation()[1] * 4 + 3,
					masters[i].getLocation()[0] * 4 + 3, "J"); // sets
			// location
			// of
			// masters[i]
			// on
			// the
			// display
			// grid
		}

		gameMap.printMaze(); // prints maze with current positions

		do {

			System.out
					.println("Do you want to move(1), look(2), or shoot(3)(-1 to quit, 0 to save): ");
			resp = scn.nextInt();
			if (resp < 0) { // allows user to quit the game
				System.out.println("you quit? you're a quitter!");
				System.exit(0);
			}
			if (resp == 0) {
				saveIt(saveState, user, masters, h, invinc, addAmmo, radar);

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
							|| gameMap.getArea(user.getLocation()[0],
									user.getLocation()[1] - 2).getPerson() != null) {
						System.out
								.println("You hear a noise out the door, stay here where it's safe for now");
						resp = 0;
					} else {
						resp = 1;
					}
				} else {
					System.out
							.println("up(1),right(2),down(3),left(4) or stay in place(0)?: ");
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
								user.getLocation()[1]).getIsRoom()) || resp < 0) { // input
					// stays
					// in
					// range
					if (resp > 4 || resp < 1) { // Keeps movement in range
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
				gameMap.setMaze(user.getLocation()[1] * 4 + 3,
						user.getLocation()[0] * 4 + 3, " "); // erases
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
						gameMap.setMaze(masters[i].getLocation()[1] * 4 + 3,
								masters[i].getLocation()[0] * 4 + 3, " ");
					}
				}
				if (resp > 0) {
					user.move(resp, gameMap); // moves user
					if (user.getLocation()[0] == invinc.getLocation()[0]
							&& user.getLocation()[1] == invinc.getLocation()[1]) {
						gameMap.getArea(invinc.getLocation()[0],
								invinc.getLocation()[1]).setItem(null);
						gameMap.setMaze(invinc.getLocation()[1] * 4 + 1,
								invinc.getLocation()[0] * 4 + 1, " ");
						invinc.activate(user);
						System.out.println("You are now invincible!");
					}
					if (user.getLocation()[0] == addAmmo.getLocation()[0]
							&& user.getLocation()[1] == addAmmo.getLocation()[1]) {

						gameMap.getArea(addAmmo.getLocation()[0],
								addAmmo.getLocation()[1]).setItem(null);
						gameMap.setMaze(addAmmo.getLocation()[1] * 4 + 1,
								addAmmo.getLocation()[0] * 4 + 1, " ");
						addAmmo.activate(user.getGun());
						System.out
								.println("You found an extra charge for your blaster! You have "
										+ user.getGun().getAmmo()
										+ " ammo left.");
					}
					if (user.getLocation()[0] == radar.getLocation()[0]
							&& user.getLocation()[1] == radar.getLocation()[1]) {
						gameMap.getArea(radar.getLocation()[0],
								radar.getLocation()[1]).setItem(null);
						gameMap.setMaze(radar.getLocation()[1] * 4 + 1, radar
								.getLocation()[0] * 4 + 1, " ");
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
				//maze[h.getLocation()[1] * 4 + 1][h.getLocation()[0] * 4 + 1] = "H"; // sets
																					// H
																					// to
																					// current
																					// location
																					// on
																					// the
																					// display
																					// grid
				gameMap.setMaze(user.getLocation()[1] * 4 + 3, user.getLocation()[0] * 4 + 3, "P"); // sets
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
						gameMap.setMaze(masters[i].getLocation()[1] * 4 + 3, masters[i]
								.getLocation()[0] * 4 + 3, "J"); // sets
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
				gameMap.printMaze(); // prints maze with current location
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
				while (resp > 4 || resp < 1) { // keeps input within range
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
				//maze[h.getLocation()[1] * 4 + 1][h.getLocation()[0] * 4 + 1] = "H"; // sets
																					// H
																					// to
																					// current
																					// location
																					// on
																					// the
																					// display
																					// grid
				gameMap.setMaze(user.getLocation()[1] * 4 + 3, user.getLocation()[0] * 4 + 3, "P"); // sets
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
						gameMap.setMaze(masters[i].getLocation()[1] * 4 + 3, masters[i]
								.getLocation()[0] * 4 + 3, "J");
					}
				}
				gameMap.printMaze(); // prints maze with all current locations
				resp = 0; // sets input back to zero
			}
			if (resp == 3) { // 3 means user wants to shoot
				System.out.println("up(1),right(2),down(3),left(4)?: ");
				resp = scn.nextInt();
				while (resp > 4 || resp <= 0) { // keeps running into input is
												// within range
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
						gameMap.setMaze(masters[i].getLocation()[1] * 4 + 3, masters[i]
								.getLocation()[0] * 4 + 3, " ");
					}
				}
				System.out.println("You heard " + user.shoot(resp, gameMap)
						+ " scream(s)"); // users shoots in a direction
				gameMap.setMaze(h.getLocation()[1] * 4 + 1, h.getLocation()[0] * 4 + 1, "H"); // sets
																					// location
																					// of
																					// the
																					// holocron
																					// on
																					// display
																					// grid
				gameMap.setMaze(user.getLocation()[1] * 4 + 3, user.getLocation()[0] * 4 + 3, "P"); // sets
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
						gameMap.setMaze(masters[i].getLocation()[1] * 4 + 3, masters[i]
								.getLocation()[0] * 4 + 3, "J");
					}
				}
				gameMap.printMaze(); // prints maze
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

	private static void saveIt(Save saveState, Player user, Jedi[] masters,
			Holocron h, InvincibilityPowerUp invinc, AddAmmoPowerUp addAmmo,
			RadarPowerUp radar) {

		Item[] i = new Item[4];
		i[0] = h;
		i[1] = invinc;
		i[2] = addAmmo;
		i[3] = radar;

		saveState.setItems(i);
		saveState.setPlayer(user);
		saveState.setEnemies(masters);

		FileOutputStream fos;

		try {
			fos = new FileOutputStream("saved.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveState);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Saved");

	}

}
