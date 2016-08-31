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

import java.io.Serializable;

/**
 * This is the top class, Item. In our game, there will be different objects
 * scatter in the map. These objects we have called "Item", which have common
 * attributes such as a location on the map. From this Item class, we will
 * extend it to include the Holocron and various powerups that will help the
 * player along.
 * 
 * @author Team Jedi
 */
public abstract class Item implements Serializable {
	/**
	 * This is the default serial ID of the Item class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This field is for setting the location of each Item object within the
	 * boundaries of the map generated by the game.
	 */
	protected int[] location;

	/**
	 * This is the constructor that will set the location of the item based on x
	 * and y.
	 * 
	 * @param x
	 *            The x coordinate of the location
	 * @param y
	 *            The y coordinate of the location
	 */
	public Item(int x, int y) {
		location = new int[2];
		location[0] = x;
		location[1] = y;
	}

	/**
	 * This is the place holder for the other activate methods of the subclasses
	 * of Item.
	 * 
	 * @throws GameOverException
	 */
	public void activate() throws GameOverException {
	}

	/**
	 * Returns the location of item
	 * 
	 * @return location
	 */
	public int[] getLocation() {
		return location;
	}

	/**
	 * This is the place holder for the other activate methods of the subclasses
	 * of Item.
	 * 
	 * @param p
	 *            The player that needs to be sent to activate some items.
	 */
	public void activate(Player p) {

	}

}