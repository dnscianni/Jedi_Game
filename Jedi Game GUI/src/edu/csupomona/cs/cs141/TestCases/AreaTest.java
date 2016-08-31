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
 * (@link AreaTest) is used to test our class Area
 * @author Team Jedi
 *
 */
public class AreaTest {

	/**
	 * (@link main) is used to test our class Area.
	 * @param args
	 */
	public static void main(String[] args) {

		Area test = new Area(false); // class test of object type Area
		Person pTest = new Player(); // class pTest of object type Player
		Person jTest = new Jedi(5, 5); // class jTest of object type Jedi at location (5,5)
		Item hTest = new Holocron(6, 6); // class of hTest of object type Holocron at location (6,6)
		
		System.out.println("With  both null:"); // ****CHECK THIS****
 
		if (test.getPerson() != null) { // prints location of person
			System.out.println("Printing Area person location: "
					+ test.getPerson().getLocation()[0] + " "
					+ test.getPerson().getLocation()[1]);
		}

		if (test.getItem() != null) { // prints location of item *******CHECK THIS *********
			System.out.println("Printing Area item location: "
					+ test.getItem().getLocation()[0] + " "
					+ test.getItem().getLocation()[1]);
		}

		test.setItem(hTest); // *************CHECK THIS*********
		System.out.println("With  person  null:"); // *************CHECK THIS*********
		 
		if (test.getPerson() != null) { // prints location of person
			System.out.println("Printing Area person location: "
					+ test.getPerson().getLocation()[0] + " "
					+ test.getPerson().getLocation()[1]);
		}

		System.out.println("Printing Area item location: " //prints location of item
				+ test.getItem().getLocation()[0] + " "
				+ test.getItem().getLocation()[1]);

		test.setPerson(pTest); // *************CHECK THIS*********
		
		System.out.println("With  person and item:"); // *************CHECK THIS*********
		 
		System.out.println("Printing Area person location: " //prints location of person
				+ test.getPerson().getLocation()[0] + " "
				+ test.getPerson().getLocation()[1]);
		System.out.println("Printing Area item location: " //prints location of item
				+ test.getItem().getLocation()[0] + " "
				+ test.getItem().getLocation()[1]);
		
		test.setPerson(jTest);  // *************CHECK THIS*********
		
		System.out.println("With  jedi and item:"); // *************CHECK THIS*********
		 
		System.out.println("Printing Area person location: "// prints location of person
				+ test.getPerson().getLocation()[0] + " "
				+ test.getPerson().getLocation()[1]);
		System.out.println("Printing Area item location: " //prints location of item
				+ test.getItem().getLocation()[0] + " "
				+ test.getItem().getLocation()[1]);

	}

}
