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
 * (@link radarPowerUp) class will activate the radar for the user that picks up
 * the power up. (@link activate) is the method that activates the users radar.
 * 
 * @author Team Jedi
 */
public class RadarPowerUp extends Item {

	/**
	 * The default Serial ID of the RadarPowerUp class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor will call the {@link Item} constructor and will set up a
	 * the location at x and y for the RadarPowerUp to be set to.
	 * 
	 * @param x
	 *            the x coordinate of the location
	 * @param y
	 *            the y coordinate of the location
	 */
	public RadarPowerUp(int x, int y) {
		super(x, y);
	}

	/**
	 * (@link activate) is the method that implements the activation of the
	 * radar on the users screen. When called the method will give the
	 * coordinate of the Holocron through a getter method of the (@link
	 * Holocron) class.
	 * 
	 * @param holo
	 *            is the holocron the game uses.
	 * @return the location of the holocron
	 */
	public int[] activate(Holocron holo) {
		this.location[0] = -1;
		this.location[1] = -1;
		return holo.location;
	}

}
