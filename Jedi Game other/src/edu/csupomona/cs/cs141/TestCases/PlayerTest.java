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
package edu.csupomona.cs.cs141.TestCases;

import edu.csupomona.cs.cs141.FinalProject.*;

/**
 * (@link main) is used to test our class Player
 * @author Team Jedi
 * 
 */
public class PlayerTest {

	/**
	 * (@link main) contains commands to test if our class Player runs properly.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map m = new Map(false); // class m of object Map
		Player p = new Player(); // class p of object Player
		m.getArea(p.getLocation()[0], p.getLocation()[1]).setPerson(p); //gets area and sets persons location
		System.out.println("starting location: " + p.getLocation()[0] + " " // gets starting location
				+ p.getLocation()[1]);
		System.out.println("lives: " + p.getLives()); // prints lives
		System.out.println("invincibilityCounter: " // number of invincibility counter 
				+ p.getInvincibilityCounter());
		p.move(1, m); // moves player's location up
		System.out.println("After moving up " + p.getLocation()[0] + " " // prints location after it moved up
				+ p.getLocation()[1]);
		p.move(2, m); // moves players location right
		System.out.println("After moving right " + p.getLocation()[0] + " " // prints location after movement
				+ p.getLocation()[1]);
		// will check for wall boundaries in main
		System.out.println("ammo: " + p.getGun().getAmmo()); // amount of ammo
		p.shoot(1, m, false); //shots up
		System.out.println("ammo: " + p.getGun().getAmmo()); // amount of ammo

		Jedi j = new Jedi(1, 6); // class j of object Jedi with location (1,6)
		m.getArea(j.getLocation()[0], j.getLocation()[1]).setPerson(j); //gets area and sets location of jedi

		//System.out.println("Jedi above you: " + p.look(1, m)); // looks upward

		InvincibilityPowerUp ipu = new InvincibilityPowerUp(2, 7); // creates class ipu of invincibilityPowerUp at location (2,7)
		m.getArea(ipu.getLocation()[0], ipu.getLocation()[1]).setItem(ipu);

		p.move(2, m); // moves to the right
		if (m.getArea(p.getLocation()[0], p.getLocation()[1]).getItem() != null) { //activates item to player 
			m.getArea(p.getLocation()[0], p.getLocation()[1]).getItem()
					.activate(p);
		}

		System.out.println("InvincibilityCounter after moving over powerup: "
				+ p.getInvincibilityCounter());
		System.out.println("InvincibilityCounter after one turn: "
				+ p.getInvincibilityCounter());
		System.out.println("InvincibilityCounter after one turn: "
				+ p.getInvincibilityCounter());
		System.out.println("InvincibilityCounter after one turn: "
				+ p.getInvincibilityCounter());
		System.out.println("InvincibilityCounter after one turn: "
				+ p.getInvincibilityCounter());
		System.out.println("InvincibilityCounter after one turn: "
				+ p.getInvincibilityCounter());
		System.out.println("InvincibilityCounter after one turn: "
				+ p.getInvincibilityCounter());

		Holocron h = new Holocron(3, 7); // class h of type Holocron at location of (3, 7)
		m.getArea(h.getLocation()[0], h.getLocation()[1]).setItem(h);

		p.move(2, m); // moves person right
		if (m.getArea(p.getLocation()[0], p.getLocation()[1]).getItem() != null) { //activates item for player ***** check this ******
			try {
				m.getArea(p.getLocation()[0], p.getLocation()[1]).getItem()
						.activate();
			} catch (GameOverException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}

		try {
			p.die();
		} catch (GameOverException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		System.out.println("lives after dying: " + p.getLives()); //lives after player dies
		System.out.println("Location after dying:  " + p.getLocation()[0] + " " // location after player dies
				+ p.getLocation()[1]);

		try {
			p.die();
		} catch (GameOverException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("lives after dying: " + p.getLives()); // lives after dying
		try {
			p.die();
		} catch (GameOverException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("lives after dying: " + p.getLives()); //lives after dying again
	}

}
