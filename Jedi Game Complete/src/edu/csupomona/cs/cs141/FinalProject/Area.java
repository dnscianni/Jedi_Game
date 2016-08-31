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
 * This class represents a space on the map. The Area can hold an Item, a
 * person, or both. It will also be able to return what is inside of it.
 * 
 * @author Team Jedi
 * 
 */
public class Area {
	
	/**
	 * This field represents an item that can be found in this Area. This can be
	 * a power-up or the holocron.
	 */
	private Item i;
	
	/**
	 * This field represents a person that may be occupying this Area, whether
	 * it be a Jedi or the Player.
	 */
	private Person p;
	
	/**
	 * This field represents wheter an Area acts as a room or not
	 */
	private boolean isRoom;

	/**
	 * This default constructor creates an empty area.
	 */
	public Area(boolean b) {
		i = null;
		p = null;
		isRoom = b;
	}

	/**
	 * This method is a getter that returns the Item in this Area
	 * 
	 * @return Item i
	 */
	public Item getItem() {
		return i;
	}

	/**
	 * This method is a getter that returns the Person in this Area
	 * 
	 * @return Person P
	 */
	public Person getPerson() {
		return p;
	}

	/**
	 * This method sets a new item to the Area
	 * 
	 * @param i
	 *            the new Item
	 */
	public void setItem(Item i) {
		this.i = i;
	}

	/**
	 * This method sets a new Person to the Area
	 * 
	 * @param p
	 *            the new Area
	 */
	public void setPerson(Person p) {
		this.p = p;
	}

	/**
	 * Returns the value of isRoom
	 * @return isRoom
	 */
	public boolean getIsRoom() {
		return isRoom;
	}

}
