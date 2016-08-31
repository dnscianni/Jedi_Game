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
import java.util.Scanner;

/**
 * This class will serve to test the behaviors of a {@link Gun} object to ensure it is
 * working smoothly before it is used altogether with the rest of the game.
 * It will test its ability to properly shoot and add an extra bullet to it.
 * 
 * @author Team Jedi
 *
 */
public class GunTest {
	
	/**
	 * Stand-alone main method to test a {@code Gun} object.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Gun g = new Gun(); //Gun object to be tested
		Scanner kb = new Scanner(System.in); //Will allow input to be used to
		                                     //test adding bullets to the Gun
		int input;
		
		//The following code will simply ensure that the Gun object adds bullets
		//correctly
		System.out.println("Current bullets: " + g.getAmmo());
		System.out.print("Ammo to add: ");
		input = kb.nextInt();
		
		for (int count = 0; count < input; count++)
			g.gainAmmo();
		
		System.out.println("New amount of bullets: " + g.getAmmo());
		
		//The following loop will ensure the Gun fires the appropriate amount
		//of times before returning a value of false
		while (g.shoot())
			System.out.println("The gun shot! It has " + g.getAmmo()
					           + " left.");
		
		System.out.println("The gun did not shoot again because it ran out of "
				           + "ammo.");
		
		

	}

}
