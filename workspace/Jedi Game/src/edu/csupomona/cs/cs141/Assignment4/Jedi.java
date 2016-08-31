/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #4
 *
 * <description-of-assignment>
 *
 * Team Jedi
 *   Alec Shay
 *   Alejandro Diaz
 *   Alejandro Landeros
 *   David Scianni
 *   Luiz Romero
 */
package edu.csupomona.cs.cs141.Assignment4;

import Person;

/**
 * The Jedi object represents the enemy in the game. They will first stab in
 * each direction, trying to kill the player, and if they fail they will move
 * one space in a random direction. If they succeed in stabbing the player, then
 * the {@link Player#die()} method will be called. If the Jedi dies, then all
 * its fields will be set to null and the Jedi will cease being in the game.
 * 
 * @author David Scianni
 * 
 */
public class Jedi extends Person {

	/**
	 * The Jedi constructor will set the Jedi's {@link Person#location} to a
	 * random spot on the {@link Map}, as long as that location isn't already
	 * taken by another Person, or if the spot is within 2 spaces of the
	 * Player's original location.
	 */
	public Jedi() {

	}

	/**
	 * The die method will set all the Jedi's fields to null, so that the Jedi
	 * will be out of the game.
	 */
	public void die() {

	}

	/**
	 * The move method will generate a random number {@code 1 <= x <=4} and move
	 * the Jedi 1 space in that direction (1 up, 2 right, 3 down, 4 left).
	 */
	public void move() {

	}

	/**
	 * The stab method will check each space next to the Jedi to see if the
	 * player is inside, and if he or she is, it will call the
	 * {@link Player#die()} method. If the player is successfully stabbed, it
	 * will return a true, but if not, it will return a false.
	 * 
	 * @return true if the player was stabbed, false if the player was not
	 *         stabbed.
	 */
	public boolean stab() {

	}
}
