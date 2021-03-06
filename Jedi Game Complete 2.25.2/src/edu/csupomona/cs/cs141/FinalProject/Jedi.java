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
 * The Jedi object represents the enemy in the game. They will first stab in
 * each direction, trying to kill the player, and if they fail they will move
 * one space in a random direction. If they succeed in stabbing the player, then
 * the {@link Player#die()} method will be called. If the Jedi dies, then all
 * its fields will be set to null and the Jedi will cease being in the game.
 * 
 * @author Team Jedi
 * 
 */
public class Jedi extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Jedi constructor will set the Jedi's {@link Person#location} to a
	 * random spot on the {@link Map}, as long as that location isn't already
	 * taken by another Person, or if the spot is within 2 spaces of the
	 * Player's original location.
	 */
	public Jedi(int x, int y) {
		super(x, y);
	}

	/**
	 * The die method will set all the Jedi's fields to null, so that the Jedi
	 * will be out of the game.
	 */
	public void die(Map m, boolean b) {
		m.getArea(this.location[0], this.location[1]).setPerson(null);
		if (!b) {
			m.setMaze(this.location[1] * 2 + 1, this.location[0] * 4 + 2, "#");
		} else {
			m.setMaze(this.location[1] * 2 + 1, this.location[0] * 4 + 2, " ");
		}
		this.location[0] = -1;
		this.location[1] = -1;
	}

	/**
	 * The move method will generate a random number {@code 1 <= x <=4} and move
	 * the Jedi 1 space in that direction (1 up, 2 right, 3 down, 4 left).
	 */
	public void move(int d, Map m) {
		if (this.location[0] >= 0 && this.location[1] >= 0) {
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
	public boolean stab(Player p, Map m, boolean b) {
		if (this.location[0] < 8
				&& this.location[0] >= 0
				&& this.location[0] >= 0
				&& !m.getArea(this.location[0] + 1, this.location[1])
						.getIsRoom()
				&& m.getArea(this.location[0] + 1, this.location[1])
						.getPerson() == p) {
			try {
				p.die(m, b);
			} catch (GameOverException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			return true;
		} else if (this.location[0] > 0
				&& this.location[0] >= 0
				&& !m.getArea(this.location[0] - 1, this.location[1])
						.getIsRoom()
				&& m.getArea(this.location[0] - 1, this.location[1])
						.getPerson() == p) {
			try {
				p.die(m, b);
			} catch (GameOverException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			return true;
		} else if (this.location[1] < 8
				&& this.location[0] >= 0
				&& !m.getArea(this.location[0], this.location[1] + 1)
						.getIsRoom()
				&& m.getArea(this.location[0], this.location[1] + 1)
						.getPerson() == p) {
			try {
				p.die(m, b);
			} catch (GameOverException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			return true;
		} else if (this.location[1] > 0
				&& this.location[0] >= 0
				&& !m.getArea(this.location[0], this.location[1] - 1)
						.getIsRoom()
				&& m.getArea(this.location[0], this.location[1] - 1)
						.getPerson() == p) {
			try {
				p.die(m, b);
			} catch (GameOverException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			return true;
		} else {
			return false;
		}
	}
}
