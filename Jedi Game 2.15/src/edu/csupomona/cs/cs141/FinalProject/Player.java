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
 * The Player object represents the user who is playing the game. He or she will
 * be holding a blaster of type {@link Gun}, which they can shoot at a Jedi. The
 * player will have a set number of lives, and when that number reaches 0, the
 * game will end. During their turn, the player has the option to either move
 * one space, (up, down, left, or right), look ahead 2 spaces, or shoot their
 * blaster, hopefully hitting a Jedi in the process. The player may also pick up
 * a power-up item that is located on one of the regular spaces.
 * 
 * @author Team Jedi
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
		super(0, 8);
		blaster = new Gun();
		lives = 3;
		invincibilityCounter = 0;
	}

	/**
	 * When the die method is called, the player's lives will be reduced by 1,
	 * and their location will be reset to the beginning location. Also, if
	 * {@code lives = 0}, then it will call an exception that will end the game.
	 * @throws GameOverException 
	 */
	public void die(Map m) throws GameOverException {
		if (lives == 1) {
			lives--;
			throw new GameOverException();
			
		} else {
			m.getArea(this.location[0], this.location[1]).setPerson(null);
			lives--;
			this.location[0] = 0;
			this.location[1] = 8; // make location in person protected
			m.getArea(this.location[0], this.location[1]).setPerson(this);
		}
		System.out.println("You have " + this.lives + " lives left.");
	}

	/**
	 * This method will move the player's location to the next spot in one
	 * place. the integer d will determine the direction of the movement.
	 * 
	 * @param d
	 *            determines which way the player moves (1 for up, 2 for right,
	 *            3 for down, and 4 for left).
	 */
	public void move(int d, Map m) {
		m.getArea(this.location[0], this.location[1]).setPerson(null);
		switch (d) {
		case 1:
			this.location[1]--;
			break;
		case 2:
			this.location[0]++;
			break;
		case 3:
			this.location[1]++;
			break;
		default:
			this.location[0]--;
		}
		m.getArea(this.location[0], this.location[1]).setPerson(this);
	}

	/**
	 * The shoot method will check to see if there are any Jedi in the path of
	 * direction d, and then it will call the {@link Gun#shoot()} method. If
	 * there are Jedi in the path, it will call the {@link Jedi#die()} method of
	 * the jedi in the way. If there is not enough bullets, it will throw an
	 * exception.
	 * 
	 * @param d
	 *            The direction in which the player shoots his or her blaster.
	 */
	public void shoot(int d, Map m) {
		if (blaster.shoot()) {
			switch (d) {
			case 1:
				for (int i = this.location[1] - 1; i >= 0; i--) {
					if (m.getArea(this.location[0], i).getPerson() != null) {
						try {
							m.getArea(this.location[0], i).getPerson().die(m);
						} catch (GameOverException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 2:
				for (int i = this.location[0] + 1; i <= 8; i++) {
					if (m.getArea(i, this.location[1]).getPerson() != null) {
						try {
							m.getArea(i, this.location[1]).getPerson().die(m);
						} catch (GameOverException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 3:
				for (int i = this.location[1] + 1; i <= 8; i++) {
					if (m.getArea(this.location[0], i).getPerson() != null) {
						try {
							m.getArea(this.location[0], i).getPerson().die(m);
						} catch (GameOverException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			default:
				for (int i = this.location[0] - 1; i >= 0; i--) {
					if (m.getArea(i, this.location[1]).getPerson() != null) {
						try {
							m.getArea(i, this.location[1]).getPerson().die(m);
						} catch (GameOverException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
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
	public int look(int d, Map m) {
		int temp = 0;

		switch (d) {
		case 1:
			if (this.location[1] - 1 >= 0) {
				if (m.getArea(this.location[0], this.location[1] - 1)
						.getPerson() != null) {
					temp++;
				}
			}
			if (this.location[1] - 2 >= 0) {
				if (m.getArea(this.location[0], this.location[1] - 2)
						.getPerson() != null) {
					temp++;
				}
			}
			break;
		case 2:
			if (this.location[0] + 1 <= 8) {
				if (m.getArea(this.location[0] + 1, this.location[1])
						.getPerson() != null) {
					temp++;
				}
			}
			if (this.location[0] + 2 <= 8) {
				if (m.getArea(this.location[0] + 2, this.location[1])
						.getPerson() != null) {
					temp++;
				}
			}
			break;
		case 3:
			if (this.location[1] + 1 <= 8) {
				if (m.getArea(this.location[0], this.location[1] + 1)
						.getPerson() != null) {
					temp++;
				}
			}
			if (this.location[1] + 2 <= 8) {
				if (m.getArea(this.location[0], this.location[1] + 2)
						.getPerson() != null) {
					temp++;
				}
			}
			break;
		default:
			if (this.location[0] - 1 >= 0) {
				if (m.getArea(this.location[0] - 1, this.location[1])
						.getPerson() != null) {
					temp++;
				}
			}
			if (this.location[0] - 2 >= 0) {
				if (m.getArea(this.location[0] - 2, this.location[1])
						.getPerson() != null) {
					temp++;
				}
			}
		}
		return temp;
	}

	/**
	 * The pickUp method will take in the {@link Item} that the player has
	 * walked over and call its activate method.
	 * 
	 * @param i
	 *            The item that the player is picking up.
	 */
	public void pickUp(Item i) {
		try {
			i.activate();
		} catch (GameOverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to check how many lives the player has left.
	 * 
	 * @return The variable {@link #lives}.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * This returns the amount of turns the player has left while invincible,
	 * while decreasing that amount by 1.
	 * 
	 * @return The variable {@link #invincibilityCounter}--
	 */
	public int getInvincibilityCounter() {
		if (invincibilityCounter > 0) {
			return invincibilityCounter--;
		} else {
			return invincibilityCounter;
		}
	}

	/**
	 * This is called from the InvincibilityPowerUp object to add a amount of
	 * turns to the players invincibilityCounter.
	 * 
	 * @param a
	 *            The amount of turns the player can be invincible.
	 */
	public void setInvincibilityCounter(int a) {
		invincibilityCounter = a;
	}

	public Gun getGun() {
		// TODO Auto-generated method stub
		return blaster;
	}
}
