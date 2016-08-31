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

import Item;

/**
 * The InvincibilityPowerUp extends Item, so that it can have a location on the
 * map. This power-up will increase the player's invincibilityCounter by
 * {@link #INVINCIBILITY_AMOUNT}
 * 
 * @author David Scianni
 * 
 */
public class InvincibilityPowerUp extends Item {

	/**
	 * This is the amount of turns the player can be invincible to the stabs of
	 * the Jedi. It is a final int so that we can easily change the amount of
	 * turns if we ever needed to.
	 */
	private final int INVINCIBILITY_AMOUNT = 5;

	/**
	 * This constructor will call the {@link Item} constructor and will set up a
	 * random location for the InvincibilityPowerUp to be set to.
	 */
	public InvincibilityPowerUp() {

	}

	/**
	 * This will call the {@link Player#setInvincibilityCounter()} method and
	 * add {@link #INVINCIBILITY_AMOUNT} to the invincibilityCounter of the
	 * player.
	 */
	public void activate() {

	}
}
