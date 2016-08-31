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
 * This class will generate a single object per game representing the player's
 * single gun. A Gun object will have an integer, {@code ammo}, used to
 * represent the amount of bullets currently in the Gun. The Gun object can
 * shoot - resulting in the amount of bullets it holds being lowered by 1 - and
 * it can also have the number of bullets inside of it increased by one (if the
 * player should "activate" a powerup that adds ammunition).
 * 
 * @author Team Jedi
 * 
 */
public class Gun  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@code ammo} is an integer representing the amount of bullets in the Gun,
	 * which will be used in determining whether the Gun can or cannot shoot.
	 */
	int ammo;

	/**
	 * This method will create a Gun object and set the value of the integer
	 * {@code ammo} to 1; this constructor will be used at the start of a new
	 * game.
	 */
	public Gun() {
		ammo = 1;
	}

	/**
	 * This special constructor will be used to create a Gun object after
	 * loading a game from saved data to set the value of the integer
	 * {@code ammo} to whatever value it was at when the game was saved.
	 * 
	 * @param a
	 *            determines the number of bullets the Gun object has in it.
	 */
	public Gun(int a) {
		ammo = a;
	}

	/**
	 * This method will first check to ensure that the value of {@code ammo} is
	 * greater than or equal to 1. If the number of bullets is high enough, then
	 * it will lower the number of bullets in it by 1 and return the boolean
	 * value, true (meaning the attempt to shoot was successful). If the number
	 * of bullets was not high enough, it simply returns a value of false
	 * (meaning the attempt to shoot was unsuccessful).
	 */
	public boolean shoot() {
		if (ammo >= 1) {
			ammo--;
			return true;
		}

		else
			return false;
	}

	/**
	 * This method will be called whenever the player picks up a powerup that
	 * adds a new bullet to the Gun. The method then simply raises the number of
	 * bullets in the gun by 1.
	 */
	public void gainAmmo() {
		ammo++;
	}

	/**
	 * This method will simply return the current value of the variable,
	 * {@code ammo}.
	 * 
	 * @return the value of {@code ammo}
	 */
	public int getAmmo() {
		return ammo;
	}
}
