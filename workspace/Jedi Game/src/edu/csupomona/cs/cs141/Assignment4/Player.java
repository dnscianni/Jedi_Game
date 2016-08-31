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
 * The Player object represents the user who is playing the game. He or she will
 * be holding a blaster of type {@link Gun}, which they can shoot at a Jedi. The
 * player will have a set number of lives, and when that number reaches 0, the
 * game will end. During their turn, the player has the option to either move
 * one space, (up, down, left, or right), look ahead 2 spaces, or shoot their
 * blaster, hopefully hitting a Jedi in the process. The player may also pick up
 * a power-up item that is located on one of the regular spaces.
 * 
 * @author David Scianni
 * 
 */
public class Player extends Person {
	/**
	 * This is the gun that the player will use to try to stop the Jedi from
	 * attacking. The blaster will start with only one shot, so the player must
	 * use the shot wisely.
	 */
	private Gun blaster;
	/**
	 * This determines how many lives the player has left. It will be from
	 * {@code 0 <= x <= 4}. When it reaches 0, the game will end.
	 */
	private int lives;
	/**
	 * This will keep track of how many turns the player is invincible to Jedi
	 * attacks.
	 */
	private int invincibilityCounter;

	/**
	 * The player constructor will call the constructor of the super class, and
	 * will set the player's location, construct the player's gun, their
	 * beginning amount of lives, and beginning invincibilityCounter.
	 */
	public Player() {
	}

	/**
	 * When the die method is called, the player's lives will be reduced by 1,
	 * and their location will be reset to the beginning location. Also, if
	 * {@code lives = 0}, then it will call an exception that will end the game.
	 */
	public void die() {
	}

	/**
	 * This method will move the player's location to the next spot in one
	 * place. the integer d will determine the direction of the movement.
	 * 
	 * @param d
	 *            determines which way the player moves (1 for up, 2 for right,
	 *            3 for down, and 4 for left).
	 */
	public void move(int d) {
	}

	/**
	 * The shoot method will first check to see if the player has enough ammo to
	 * shoot, and if not, it will throw an exception. If the player does have
	 * enough ammo, then it will check the direction that the player shot at,
	 * and it will activate the {@link Jedi#die()} method of any {@link Jedi}
	 * along the path of the ammo. Then it will decrease the ammo in the blaster
	 * by 1.
	 * 
	 * @param d
	 *            The direction in which the player shoots his or her blaster.
	 */
	public void shoot(int d) {
	}

	/**
	 * The look method will look two spaces ahead of the player in the direction
	 * given. It will return the amount of Jedi that are in those two spaces. It
	 * will return 0 if there are no Jedi in those spaces.
	 * 
	 * @param d
	 *            The direction in which the player looks
	 * @return The amount of Jedi in the two spaces the player is looking at,
	 *         and it must be {@code 0 <= x <= 2}
	 */
	public int look(int d) {
	}

	/**
	 * The pickUp method will take in the {@link Item} that the player has
	 * walked over and call its activate method.
	 * 
	 * @param i
	 *            The item that the player is picking up.
	 */
	public void pickUp(Item i) {
	}

	/**
	 * This method is used to check how many lives the player has left.
	 * 
	 * @return The variable {@link #lives}.
	 */
	public int getLives() {
	}

	/**
	 * This returns the amount of turns the player has left while invincible,
	 * while decreasing that amount by 1.
	 * 
	 * @return The variable {@link #invincibilityCounter}--
	 */
	public int getInvincibilityCounter() {
	}

	/**
	 * This is called from the InvincibilityPowerUp object to add {@link a}
	 * amount of turns to the players {@link invincibilityCounter}.
	 * 
	 * @param a
	 *            The amount of turns the player can be invincible.
	 */
	public void setInvincibilityCounter(int a) {
	}
}
