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
	public Map() {
		map = new Area[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				if ((i == 1 || i == 4 || 1 == 7)
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

}