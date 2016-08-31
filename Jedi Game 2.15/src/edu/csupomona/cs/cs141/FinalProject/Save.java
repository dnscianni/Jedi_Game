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
 * This class will hold all of the objects and data that will need to be
 * preserved if the data is saved in a single (serializable) object that can be
 * saved and then read again later with the use of
 * ObjectInputStream/ObjectOutputStream objects. This class will hold a single
 * {@link Map} object (saving the state of the map and each of its individual
 * {@code Area} objects), one {@link Player} object (to save the status of the
 * player), and six {@link Jedi} objects (to keep track of the six {@code Jedi}
 * enemies) so that the game can maintain the status of each of these objects by
 * saving the data to a file which can be loaded at another time.
 * 
 * @author Team Jedi
 * 
 */
public class Save implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The single {@link Map} object that will store all of the data for each
	 * individual {@code Area} on the map's grid. This will include The
	 * {@code Map} class will implement {@code Serializable}.
	 */
	Map theMap;

	/**
	 * The single necessary {@code Player} object (which is also of type
	 * {@code Character} that will store all of the data for the player once the
	 * game is saved. This will include whether each {@code Area} contains an
	 * {@link Item} object or {@link Person} object in it.
	 */
	Player thePlayer;

	/**
	 * {@code enemies} will be an array of size 6 of {@link Jedi} objects. This
	 * will be necessary to keep track of the location of each of these enemies.
	 */
	Jedi[] enemies;

	/**
	 * This constructor will make the only {@code Save} object necessary for any
	 * time the program is run. It takes no parameters, and serves mostly just
	 * to set the size of the array {@code enemies} to 6.
	 */
	public Save() {
	}

	/**
	 * This method will set the field {@code theMap} to the parameter m for
	 * whenever the game is saved.
	 * 
	 * @param m
	 *            the state of the single {@link Map} when the game is saved.
	 */
	public void setMap(Map m) {
	}

	/**
	 * This method will set the field {@code thePlayer} to the parameter p for
	 * whenever the game is saved.
	 * 
	 * @param p
	 *            the state of the single {@link Player} when the game is saved.
	 */
	public void setPlayer(Player p) {
	}

	/**
	 * This method will set all elements of the array {@code enemies} to be the
	 * same as each element of the array j whenever the game is saved.
	 * 
	 * @param j
	 *            the state of each {@link Jedi} when the game is saved.
	 */
	public void setEnemies(Jedi[] j) {
	}

	/**
	 * This method will return this object's {@link Map} object whenever the
	 * game is loaded. This object will represent the state of the game's Map
	 * when the game was previously saved.
	 * 
	 * @return the Map object that was loaded from a file
	 */
	public Map getMap() {
		return null;
	}

	/**
	 * This method will return this object's {@link Player} object whenever the
	 * game is loaded. This object will represent the state of the game's Player
	 * when the game was previously saved.
	 * 
	 * @return the Player object that was loaded from a file
	 */
	public Player getPlayer() {
		return null;
	}

	/**
	 * This method will return the array {@code enemies} whenever the game is
	 * loaded. Each of this array's elements will represent the state of each of
	 * the game's {@link Jedi} when the game was previously saved.
	 * 
	 * @return an array where each element holds the status of one of the 6
	 *         {@code Jedi} enemies, which was loaded from a file
	 */
	public Jedi[] getJedi() {
		return null;
	}
}
