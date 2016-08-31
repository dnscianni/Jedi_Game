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
 * (@link JediTest) is a class to test Jedi
 * @author Team Jedi
 * 
 */
public class JediTest {

	/**
	 * (@link main) contains commands to test if our class Jedi runs properly.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map m = new Map(); // class m of object Map
		Jedi j = new Jedi(0, 6); //sets location

		m.getArea(j.getLocation()[0], j.getLocation()[1]).setPerson(j); //sets location of jedi and the prints its current location to check if it is running correctly.
		System.out.println("starting location: " + j.getLocation()[0] + " "
				+ j.getLocation()[1]);

		j.move(3, m); // tests the movement of the jedi
		System.out.println("location after moving: " + j.getLocation()[0] + " "
				+ j.getLocation()[1]);

		Player p = new Player(); // class P of object Player
		m.getArea(p.getLocation()[0], p.getLocation()[1]).setPerson(p); //sets location of player 

		System.out.println("player starting location: " + p.getLocation()[0] + " " // prints location of player
				+ p.getLocation()[1]);
		System.out.println("player lives before stab: " + p.getLives()); // prints lives of player before he is stabbed
		j.stab(p, m);
		System.out.println("player lives after stab: " + p.getLives()); //prints lives after player is stabbed 

		j.die(m); //jedi dies

		System.out.println("player lives before trying to stab while dead: "//prints lives after player is dead
				+ p.getLives());
		j.stab(p, m); //jedi stabs
		System.out.println("player lives after stab: " + p.getLives()); //prints lives after stab

		
		System.out.println("location after dying: " + j.getLocation()[0] + " " // location after jedi dies 
				+ j.getLocation()[1]);
		j.move(2, m);
		System.out.println("location after trying to move while dead: " + j.getLocation()[0] + " " // location after trying to move while jedi is dead
				+ j.getLocation()[1]);
	}
}
