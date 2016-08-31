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
 * This class will be used to generate objects that represent specific powerup
 * in the game that adds a single bullet to the player's Gun. It extends the
 * class {@link Item}, and thus it inherits Item's {@code location} field (an
 * array of 2 integers, holding values for the item's x-coordinate and
 * y-coordinate on the map grid) and its method (which is abstract in the
 * superclass), {@code activate()}.
 * 
 * @author Team Jedi
 * 
 */
public class AddAmmoPowerUp extends Item {
	/**
	 * This constructor will call the {@link Item} constructor and will set the
	 * location for the AddAmmoPowerUp to be set to (which would have been
	 * randomly generated). It calls the method's superclass's constructor with
	 * two parameters to be used to set the powerup's location on the map for
	 * later reference.
	 * 
	 * @param x
	 *            the integer representing the item's x-coordinate on the grid
	 * @param y
	 *            the integer representing the item's y-coordinate on the grid
	 */
	public AddAmmoPowerUp(int x, int y) {
		super(x, y);
	}

	/**
	 * This method will be called whenever the player moves over (and picks up)
	 * an ammunition powerup. The method will call the gainAmmo() method of the
	 * {@link Gun} g (which represents the player's gun) to raise the number of
	 * bullets it contains by 1.
	 * 
	 * 
	 * @param g
	 *            is the Gun object that represents the player's gun, which will
	 *            have its ammunition count raised by 1
	 */
	public void activate(Gun g) {
		g.gainAmmo();
	}
}