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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Team Jedi
 * 
 */
public class GUIClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 800;
	private ImageIcon titleImage;
	private JLabel[][] mapGrid;
	private JLabel title, name, missionLine1, missionLine2, livesDisplay,
			invincDisplay, bulletsDisplay;
	private JLabel topB, rightB, leftB, bottomB;
	private ImageIcon topBor, rightBor, leftBor, bottomBor;
	private JButton moveButton, lookButton, shootButton, saveButton,
			quitButton;
	private JButton up, down, left, right, none;
	private JButton yesButton, noButton;
	private JTextArea mainText;
	private SpringLayout layout;
	private JPanel mainPanel;
	private Font biggerFont;

	private Save saveState;
	private Map gameMap;
	private Player user;
	private Jedi[] masters;
	private InvincibilityPowerUp invinc;
	private RadarPowerUp radar;
	private AddAmmoPowerUp addAmmo;
	private Holocron holo;
	private boolean isDebug;
	private int action;
	private int lookDirection;
	private boolean hasShot;
	private String[] messageLines;
	private boolean gameEnded;
	private boolean hasDied;

	public GUIClass(Player p, Jedi[] jed, InvincibilityPowerUp inv,
			RadarPowerUp r, AddAmmoPowerUp a, Holocron h, boolean noFog, Map m,
			Save s) {
		setTitle("Star Wars: Rise of the Death Star");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		biggerFont = new Font("Verdana", Font.BOLD, 15);
		gameMap = m;
		user = p;
		masters = jed;
		invinc = inv;
		radar = r;
		addAmmo = a;
		holo = h;
		saveState = s;
		isDebug = noFog;
		lookDirection = 0;
		gameEnded = false;
		hasDied = false;
		messageLines = new String[5];

		for (int i = 0; i < 4; i++)
			messageLines[i] = "\n";

		messageLines[4] = "Locate and secure the holocron!\n";
		
		topBor = new ImageIcon("Top_Bottom_Border.PNG");
		rightBor = new ImageIcon("Right_Border.PNG");
		leftBor = new ImageIcon("Left_Border.PNG");
		bottomBor = new ImageIcon("Top_Bottom_Border.PNG");
		
		topB = new JLabel(topBor);
		rightB = new JLabel(rightBor);
		leftB = new JLabel(leftBor);
		bottomB = new JLabel(bottomBor);

		moveButton = new JButton("Move");
		moveButton.addActionListener(new MoveListener());
		lookButton = new JButton("Look");
		lookButton.addActionListener(new LookListener());
		shootButton = new JButton("Shoot");
		shootButton.addActionListener(new ShootListener());
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(new SaveListener());
		quitButton = new JButton("Quit Game");
		quitButton.addActionListener(new QuitListener());

		up = new JButton("Up");
		up.addActionListener(new DirectionListener());
		down = new JButton("Down");
		down.addActionListener(new DirectionListener());
		left = new JButton("Left");
		left.addActionListener(new DirectionListener());
		right = new JButton("Right");
		right.addActionListener(new DirectionListener());
		none = new JButton("None (wait)");
		none.addActionListener(new DirectionListener());

		yesButton = new JButton("Yes");
		yesButton.addActionListener(new SaveBeforeQuitListener());
		noButton = new JButton("No");
		noButton.addActionListener(new SaveBeforeQuitListener());

		mapGrid = new JLabel[9][9];
		livesDisplay = new JLabel("Lives: " + user.getLives());
		livesDisplay.setBackground(Color.BLACK);
		livesDisplay.setForeground(Color.WHITE);
		livesDisplay.setFont(biggerFont);
		bulletsDisplay = new JLabel("Ammo: " + user.getGun().getAmmo());
		bulletsDisplay.setBackground(Color.BLACK);
		bulletsDisplay.setForeground(Color.WHITE);
		bulletsDisplay.setFont(biggerFont);
		invincDisplay = new JLabel("Invincibility counter: "
				+ user.getInvincibilityCounter());
		invincDisplay.setBackground(Color.BLACK);
		invincDisplay.setForeground(Color.WHITE);
		invincDisplay.setFont(biggerFont);
		name = new JLabel("Boba Fett");
		name.setBackground(Color.BLACK);
		name.setForeground(Color.WHITE);
		name.setFont(biggerFont);
		missionLine1 = new JLabel("Mission: Foil the Jedi");
		missionLine1.setBackground(Color.BLACK);
		missionLine1.setForeground(Color.WHITE);
		missionLine1.setFont(biggerFont);
		missionLine2 = new JLabel("by stealing the holocron.");
		missionLine2.setBackground(Color.BLACK);
		missionLine2.setForeground(Color.WHITE);
		missionLine2.setFont(biggerFont);
		mainText = new JTextArea(5, 50);
		mainText.setBackground(Color.BLACK);
		mainText.setForeground(Color.WHITE);
		mainText.setEditable(false);
		mainPanel = new JPanel();
		layout = new SpringLayout();
		mainPanel.setLayout(layout);

		titleImage = new ImageIcon("Rise of the Death Star.PNG");
		title = new JLabel(titleImage);
		layout.putConstraint(SpringLayout.NORTH, title, 3, SpringLayout.NORTH,
				mainPanel);
		layout.putConstraint(SpringLayout.WEST, title, 150, SpringLayout.WEST,
				mainPanel);
		mainPanel.add(title);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				mapGrid[i][j] = new JLabel();
		}

		setUpDisplay();

		layout.putConstraint(SpringLayout.NORTH, mapGrid[0][0], 7,
				SpringLayout.SOUTH, title);
		layout.putConstraint(SpringLayout.WEST, mapGrid[0][0], 300,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(mapGrid[0][0]);

		for (int i = 1; i < 9; i++) {
			layout.putConstraint(SpringLayout.WEST, mapGrid[i][0], 1,
					SpringLayout.EAST, mapGrid[i - 1][0]);
			layout.putConstraint(SpringLayout.NORTH, mapGrid[i][0], 3,
					SpringLayout.SOUTH, title);
			mainPanel.add(mapGrid[i][0]);
		}

		for (int i = 1; i < 9; i++) {
			layout.putConstraint(SpringLayout.NORTH, mapGrid[0][i], 1,
					SpringLayout.SOUTH, mapGrid[0][i - 1]);
			layout.putConstraint(SpringLayout.WEST, mapGrid[0][i], 300,
					SpringLayout.WEST, mainPanel);
			mainPanel.add(mapGrid[0][i]);

			for (int j = 1; j < 9; j++) {
				layout.putConstraint(SpringLayout.WEST, mapGrid[j][i], 1,
						SpringLayout.EAST, mapGrid[j - 1][i]);
				layout.putConstraint(SpringLayout.NORTH, mapGrid[j][i], 1,
						SpringLayout.SOUTH, mapGrid[j][i - 1]);
				mainPanel.add(mapGrid[j][i]);
			}
		}

		layout.putConstraint(SpringLayout.WEST, name, 75, SpringLayout.WEST,
				mainPanel);
		layout.putConstraint(SpringLayout.NORTH, name, 3, SpringLayout.SOUTH,
				title);
		mainPanel.add(name);

		layout.putConstraint(SpringLayout.NORTH, livesDisplay, 50,
				SpringLayout.SOUTH, name);
		layout.putConstraint(SpringLayout.WEST, livesDisplay, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(livesDisplay);

		layout.putConstraint(SpringLayout.NORTH, bulletsDisplay, 20,
				SpringLayout.SOUTH, livesDisplay);
		layout.putConstraint(SpringLayout.WEST, bulletsDisplay, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(bulletsDisplay);

		layout.putConstraint(SpringLayout.NORTH, invincDisplay, 20,
				SpringLayout.SOUTH, bulletsDisplay);
		layout.putConstraint(SpringLayout.WEST, invincDisplay, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(invincDisplay);

		layout.putConstraint(SpringLayout.NORTH, missionLine1, 75,
				SpringLayout.SOUTH, bulletsDisplay);
		layout.putConstraint(SpringLayout.WEST, missionLine1, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(missionLine1);
		layout.putConstraint(SpringLayout.NORTH, missionLine2, 5,
				SpringLayout.SOUTH, missionLine1);
		layout.putConstraint(SpringLayout.WEST, missionLine2, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(missionLine2);

		layout.putConstraint(SpringLayout.NORTH, mainText, 15,
				SpringLayout.SOUTH, mapGrid[4][8]);
		layout.putConstraint(SpringLayout.WEST, mainText, -50,
				SpringLayout.WEST, mapGrid[0][8]);

		mainText.setText(messageLines[0] + messageLines[1] + messageLines[2]
				+ messageLines[3] + messageLines[4]);
		mainPanel.add(mainText);

		layout.putConstraint(SpringLayout.EAST, moveButton, 5,
				SpringLayout.WEST, mainText);
		layout.putConstraint(SpringLayout.NORTH, moveButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(moveButton);

		layout.putConstraint(SpringLayout.WEST, lookButton, 35,
				SpringLayout.EAST, moveButton);
		layout.putConstraint(SpringLayout.NORTH, lookButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(lookButton);

		layout.putConstraint(SpringLayout.WEST, shootButton, 35,
				SpringLayout.EAST, lookButton);
		layout.putConstraint(SpringLayout.NORTH, shootButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(shootButton);

		layout.putConstraint(SpringLayout.WEST, saveButton, 35,
				SpringLayout.EAST, shootButton);
		layout.putConstraint(SpringLayout.NORTH, saveButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(saveButton);

		layout.putConstraint(SpringLayout.WEST, quitButton, 35,
				SpringLayout.EAST, saveButton);
		layout.putConstraint(SpringLayout.NORTH, quitButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(quitButton);
		
		layout.putConstraint(SpringLayout.EAST, leftB, -1,
				SpringLayout.WEST, mapGrid[0][4]);
		layout.putConstraint(SpringLayout.NORTH, leftB, -5,
				SpringLayout.NORTH, mapGrid[0][0]);
		mainPanel.add(leftB);
		
		layout.putConstraint(SpringLayout.NORTH, topB, 1,
				SpringLayout.SOUTH, title);
		mainPanel.add(topB);
		
		layout.putConstraint(SpringLayout.WEST, rightB, 1,
				SpringLayout.EAST, mapGrid[8][4]);
		layout.putConstraint(SpringLayout.NORTH, rightB, 50,
				SpringLayout.NORTH, mainPanel);
		mainPanel.add(rightB);
		
		layout.putConstraint(SpringLayout.NORTH, bottomB, -1,
				SpringLayout.NORTH, mainText);
		mainPanel.add(bottomB);
		

		mainPanel.setBackground(Color.BLACK);
		add(mainPanel);
		setResizable(false);
		setVisible(true);
	}

	public void setUpDisplay() {
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				mapGrid[i][j].setIcon(whichImage(i, j));
			}
		}

		livesDisplay.setText("Lives: " + user.getLives());
		bulletsDisplay.setText("Ammo: " + user.getGun().getAmmo());
	}

	public ImageIcon whichImage(int x, int y) {
		ImageIcon theImage;

		if (gameMap.getArea(x, y).getIsRoom()) {
			if (((isDebug || gameMap.getArea(x, y).getPerson() == user))
					&& (gameMap.getArea(x, y).getItem() == holo))
				theImage = new ImageIcon("Room_Holocron.PNG");

			else if (gameMap.getArea(x, y).getPerson() == user)
				theImage = new ImageIcon("Room_Boba.PNG");

			else
				theImage = new ImageIcon("Room.PNG");
		}

		else if (gameMap.getArea(x, y).getPerson() == user)
			theImage = new ImageIcon("Boba_Fett.PNG");

		else {
			if (isDebug || getIsVisible(x, y)) {
				if (gameMap.getArea(x, y).getPerson() == masters[0])
					theImage = new ImageIcon("Yoda.PNG");

				else if (gameMap.getArea(x, y).getPerson() == masters[1])
					theImage = new ImageIcon("Kit_Fisto.PNG");

				else if (gameMap.getArea(x, y).getPerson() == masters[2])
					theImage = new ImageIcon("Luke_Skywalker.PNG");

				else if (gameMap.getArea(x, y).getPerson() == masters[3])
					theImage = new ImageIcon("Mace_Windu.PNG");

				else if (gameMap.getArea(x, y).getPerson() == masters[4])
					theImage = new ImageIcon("Obi_Wan.PNG");

				else if (gameMap.getArea(x, y).getPerson() == masters[5])
					theImage = new ImageIcon("Qui_Gonn.PNG");

				else if (gameMap.getArea(x, y).getItem() == addAmmo)
					theImage = new ImageIcon("Ammo.PNG");

				else if (gameMap.getArea(x, y).getItem() == radar)
					theImage = new ImageIcon("Radar.PNG");

				else if (gameMap.getArea(x, y).getItem() == invinc)
					theImage = new ImageIcon("Invincibility.PNG");

				else
					theImage = new ImageIcon("Visible_Nothing.PNG");
			}

			else
				theImage = new ImageIcon("Invisible_Black.PNG");
		}

		return theImage;
	}

	public boolean getIsVisible(int x, int y) {
		boolean visible = false;

		if (x > 0 && gameMap.getArea(x - 1, y).getPerson() == user
				&& !gameMap.getArea(x - 1, y).getIsRoom())
			visible = true;

		else if (x < 8 && gameMap.getArea(x + 1, y).getPerson() == user
				&& !gameMap.getArea(x + 1, y).getIsRoom())
			visible = true;

		else if (y > 0 && gameMap.getArea(x, y - 1).getPerson() == user
				&& !gameMap.getArea(x, y - 1).getIsRoom())
			visible = true;

		else if (y < 8 && gameMap.getArea(x, y + 1).getPerson() == user
				&& !gameMap.getArea(x, y + 1).getIsRoom())
			visible = true;

		else if (lookDirection == 1) {
			for (int i = 1; i <= 3; i++) {
				if ((y + i) > 8 || gameMap.getArea(x, y + i).getIsRoom())
					break;

				if (gameMap.getArea(x, y + i).getPerson() == user)
					visible = true;
			}
		}

		else if (lookDirection == 2) {
			for (int i = 1; i <= 3; i++) {
				if ((x - i) < 0 || gameMap.getArea(x - i, y).getIsRoom())
					break;

				if (gameMap.getArea(x - i, y).getPerson() == user)
					visible = true;
			}
		}

		else if (lookDirection == 3) {
			for (int i = 1; i <= 3; i++) {
				if ((y - i) < 0 || gameMap.getArea(x, y - i).getIsRoom())
					break;

				if (gameMap.getArea(x, y - i).getPerson() == user)
					visible = true;
			}
		}

		else if (lookDirection == 4) {
			for (int i = 1; i <= 3; i++) {
				if ((x + i) > 8 || gameMap.getArea(x + i, y).getIsRoom())
					break;

				if (gameMap.getArea(x + i, y).getPerson() == user)
					visible = true;
			}
		}

		return visible;
	}

	public void addNewMessageLine(String newLine) {
		for (int i = 0; i <= 3; i++)
			messageLines[i] = messageLines[i + 1];

		messageLines[4] = newLine;
		mainText.setText(messageLines[0] + messageLines[1] + messageLines[2]
				+ messageLines[3] + messageLines[4]);
	}

	public boolean tryToMove(int direction) {
		boolean moveSuccess;

		if ((direction == 1 && user.getLocation()[1] == 0)
				|| (direction == 2 && user.getLocation()[0] == 8)
				|| (direction == 3 && user.getLocation()[1] == 8)
				|| (direction == 4 && user.getLocation()[0] == 0)) {
			addNewMessageLine("You can't move that way - try again.\n");
			moveSuccess = false;
		} else if ((direction == 1 && gameMap.getArea(user.getLocation()[0],
				user.getLocation()[1] - 1).getIsRoom())
				|| (direction == 2 && gameMap.getArea(
						user.getLocation()[0] + 1, user.getLocation()[1])
						.getIsRoom())
				|| (direction == 4 && gameMap.getArea(
						user.getLocation()[0] - 1, user.getLocation()[1])
						.getIsRoom())) {
			addNewMessageLine("You are walking into a wall... try again.\n");
			moveSuccess = false;
		} else if ((direction == 1 && gameMap.getArea(user.getLocation()[0],
				user.getLocation()[1] - 1).getPerson() != null)
				|| (direction == 2 && gameMap.getArea(
						user.getLocation()[0] + 1, user.getLocation()[1])
						.getPerson() != null)
				|| (direction == 3 && gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1] + 1).getPerson() != null)
				|| (direction == 4 && gameMap.getArea(
						user.getLocation()[0] - 1, user.getLocation()[1])
						.getPerson() != null)) {
			addNewMessageLine("Someone is already there! Try again!\n");
			moveSuccess = false;
		} else {
			moveSuccess = true;

			if (direction != 5)
				user.move(direction, gameMap);
		}

		return moveSuccess;
	}

	public void moveOutOfRoom() {

		if ((user.getLocation()[1] != 1 && gameMap.getArea(
				user.getLocation()[0], user.getLocation()[1] - 2).getPerson() != null)
				|| (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1] - 1).getPerson() != null)
				|| (gameMap.getArea(user.getLocation()[0] - 1,
						user.getLocation()[1] - 1).getPerson() != null)
				|| (gameMap.getArea(user.getLocation()[0] + 1,
						user.getLocation()[1] - 1).getPerson() != null)) {
			addNewMessageLine("You hear a noise out the door; "
					+ "stay here where it's safe for now.\n");
		} else {
			user.move(1, gameMap);
		}
	}

	public void lookForItems() {
		String holocronRoom;

		if (gameMap.getArea(user.getLocation()[0], user.getLocation()[1])
				.getItem() != null) {
			try {
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getItem() == addAmmo) {
					addNewMessageLine("You found an extra charge for your "
							+ "blaster!\n");
					addAmmo.activate(user.getGun());
				} else if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getItem() == invinc) {
					addNewMessageLine("You found an invincibility powerup! "
							+ "It will last for 5 turns!\n");
					invincDisplay.setText("Invincibility counter: 5");
					invinc.activate(user);
				} else if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getItem() == radar) {
					if (holo.getLocation()[0] == 4
							&& holo.getLocation()[1] == 4)
						holocronRoom = "very center";
					else {
						if (holo.getLocation()[1] == 1)
							holocronRoom = "upper";
						else if (holo.getLocation()[1] == 4)
							holocronRoom = "middle";
						else
							holocronRoom = "lower";

						if (holo.getLocation()[0] == 1)
							holocronRoom = holocronRoom + " left";
						else if (holo.getLocation()[0] == 4)
							holocronRoom = holocronRoom + " middle";
						else
							holocronRoom = holocronRoom + " right";
					}

					addNewMessageLine("You found the radar! The holocron is "
							+ "in the " + holocronRoom + " room.\n");
					radar.activate(holo);
				} else {
					holo.activate();
				}

				gameMap.getArea(user.getLocation()[0], user.getLocation()[1])
						.setItem(null);

			} catch (GameOverException e) {
				addNewMessageLine(e.getMessage());
				remove(none);
				remove(right);
				remove(down);
				remove(up);
				remove(left);
				gameEnded = true;
			}
		}

	}

	public void endTurn() {
		if (user.getInvincibilityCounter() == 0)
			hasDied = Game.mastersStab(masters, isDebug, user, gameMap,
					hasDied, true);

		if (hasDied) {
			addNewMessageLine("You were stabbed by a Jedi and died!\n");
			Game.mastersStopChasing(masters);

			if (user.getLives() == 0) {
				addNewMessageLine("You lost all your lives! Game over!\n");
				gameEnded = true;
			}
		}

		invincDisplay.setText("Invincibility counter: " + 
		                      user.getInvincibilityCounter(true));
		Game.mastersMove(gameMap, masters, user);
	}

	private class MoveListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!gameMap.getArea(user.getLocation()[0], user.getLocation()[1])
					.getIsRoom()) {
				action = 1;
				addNewMessageLine("Which way do you want to move?\n");
				mainPanel.remove(quitButton);
				mainPanel.remove(saveButton);
				mainPanel.remove(shootButton);
				mainPanel.remove(lookButton);
				mainPanel.remove(moveButton);

				layout.putConstraint(SpringLayout.EAST, left, 5,
						SpringLayout.WEST, mainText);
				layout.putConstraint(SpringLayout.NORTH, left, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(left);

				layout.putConstraint(SpringLayout.WEST, up, 35,
						SpringLayout.EAST, left);
				layout.putConstraint(SpringLayout.NORTH, up, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(up);

				layout.putConstraint(SpringLayout.WEST, down, 35,
						SpringLayout.EAST, up);
				layout.putConstraint(SpringLayout.NORTH, down, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(down);

				layout.putConstraint(SpringLayout.WEST, right, 35,
						SpringLayout.EAST, down);
				layout.putConstraint(SpringLayout.NORTH, right, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(right);

				layout.putConstraint(SpringLayout.WEST, none, 35,
						SpringLayout.EAST, right);
				layout.putConstraint(SpringLayout.NORTH, none, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(none);

				mainPanel.updateUI();
			} else {
				moveOutOfRoom();
				endTurn();
				setUpDisplay();
				mainPanel.updateUI();
			}
		}

	}

	private class LookListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isDebug) {
				addNewMessageLine("You do not need to look in debug mode.\n");
			} else if (lookDirection != 0) {
				addNewMessageLine("You have already looked on this turn.\n");
			} else if (gameMap.getArea(user.getLocation()[0],
					user.getLocation()[1]).getIsRoom()) {
				addNewMessageLine("You cannot see outside the room unless you "
						+ "leave.\n");
			} else {
				action = 2;
				addNewMessageLine("Which way do you want to look?\n");
				mainPanel.remove(quitButton);
				mainPanel.remove(saveButton);
				mainPanel.remove(shootButton);
				mainPanel.remove(lookButton);
				mainPanel.remove(moveButton);

				layout.putConstraint(SpringLayout.EAST, left, 5,
						SpringLayout.WEST, mainText);
				layout.putConstraint(SpringLayout.NORTH, left, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(left);

				layout.putConstraint(SpringLayout.WEST, up, 35,
						SpringLayout.EAST, left);
				layout.putConstraint(SpringLayout.NORTH, up, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(up);

				layout.putConstraint(SpringLayout.WEST, down, 35,
						SpringLayout.EAST, up);
				layout.putConstraint(SpringLayout.NORTH, down, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(down);

				layout.putConstraint(SpringLayout.WEST, right, 35,
						SpringLayout.EAST, down);
				layout.putConstraint(SpringLayout.NORTH, right, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(right);

				mainPanel.updateUI();
			}
		}

	}

	private class ShootListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (hasShot) {
				addNewMessageLine("You have already shot on this turn.\n");
			} else if (gameMap.getArea(user.getLocation()[0],
					user.getLocation()[1]).getIsRoom()) {
				addNewMessageLine("You are safe in here; no need to shoot.\n");
			} else if (user.getGun().getAmmo() == 0) {
				addNewMessageLine("You are out of charges for your blaster.\n");
			} else {
				action = 3;
				addNewMessageLine("Which way do you want to shoot?\n");
				mainPanel.remove(quitButton);
				mainPanel.remove(saveButton);
				mainPanel.remove(shootButton);
				mainPanel.remove(lookButton);
				mainPanel.remove(moveButton);

				layout.putConstraint(SpringLayout.EAST, left, 5,
						SpringLayout.WEST, mainText);
				layout.putConstraint(SpringLayout.NORTH, left, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(left);

				layout.putConstraint(SpringLayout.WEST, up, 35,
						SpringLayout.EAST, left);
				layout.putConstraint(SpringLayout.NORTH, up, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(up);

				layout.putConstraint(SpringLayout.WEST, down, 35,
						SpringLayout.EAST, up);
				layout.putConstraint(SpringLayout.NORTH, down, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(down);

				layout.putConstraint(SpringLayout.WEST, right, 35,
						SpringLayout.EAST, down);
				layout.putConstraint(SpringLayout.NORTH, right, 5,
						SpringLayout.SOUTH, mainText);
				mainPanel.add(right);

				mainPanel.updateUI();
			}
		}

	}

	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.saveIt(saveState, user, masters, holo, invinc, addAmmo, radar,
					isDebug, true);
			addNewMessageLine("Game saved.\n");
		}
	}

	private class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addNewMessageLine("Would you like to save before quitting?\n");

			mainPanel.remove(quitButton);
			mainPanel.remove(saveButton);
			mainPanel.remove(shootButton);
			mainPanel.remove(lookButton);
			mainPanel.remove(moveButton);

			layout.putConstraint(SpringLayout.WEST, yesButton, 25,
					SpringLayout.EAST, mapGrid[0][8]);
			layout.putConstraint(SpringLayout.NORTH, yesButton, 5,
					SpringLayout.SOUTH, mainText);
			mainPanel.add(yesButton);

			layout.putConstraint(SpringLayout.EAST, noButton, 25,
					SpringLayout.WEST, mapGrid[4][8]);
			layout.putConstraint(SpringLayout.NORTH, noButton, 5,
					SpringLayout.SOUTH, mainText);
			mainPanel.add(noButton);
			mainPanel.updateUI();
		}

	}

	private class DirectionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String directionString;
			int directionChosen;
			int screamsHeard;
			boolean actionSuccess;

			if (e.getSource() == up) {
				directionChosen = 1;
				directionString = "up";
			} else if (e.getSource() == right) {
				directionChosen = 2;
				directionString = "right";
			} else if (e.getSource() == down) {
				directionChosen = 3;
				directionString = "down";
			} else if (e.getSource() == left) {
				directionChosen = 4;
				directionString = "left";
			} else {
				directionChosen = 5;
				directionString = "still and silent for a turn.\n";
			}

			switch (action) {
			case 1:
				actionSuccess = tryToMove(directionChosen);
				if (actionSuccess) {
					lookDirection = 0;
					hasShot = false;
					
					if (directionChosen != 5) {
						addNewMessageLine("You moved " + directionString 
								+ ".\n");
					} else {
						addNewMessageLine("You stayed " + directionString);
					}
					
					endTurn();
					lookForItems();
					mainPanel.remove(none);
					mainPanel.remove(right);
					mainPanel.remove(down);
					mainPanel.remove(up);
					mainPanel.remove(left);

					if (!gameEnded) {
						layout.putConstraint(SpringLayout.EAST, moveButton, 5,
								SpringLayout.WEST, mainText);
						layout.putConstraint(SpringLayout.NORTH, moveButton, 5,
								SpringLayout.SOUTH, mainText);
						mainPanel.add(moveButton);

						layout.putConstraint(SpringLayout.WEST, lookButton, 35,
								SpringLayout.EAST, moveButton);
						layout.putConstraint(SpringLayout.NORTH, lookButton, 5,
								SpringLayout.SOUTH, mainText);
						mainPanel.add(lookButton);

						layout.putConstraint(SpringLayout.WEST, shootButton,
								35, SpringLayout.EAST, lookButton);
						layout.putConstraint(SpringLayout.NORTH, shootButton,
								5, SpringLayout.SOUTH, mainText);
						mainPanel.add(shootButton);

						layout.putConstraint(SpringLayout.WEST, saveButton, 35,
								SpringLayout.EAST, shootButton);
						layout.putConstraint(SpringLayout.NORTH, saveButton, 5,
								SpringLayout.SOUTH, mainText);
						mainPanel.add(saveButton);

						layout.putConstraint(SpringLayout.WEST, quitButton, 35,
								SpringLayout.EAST, saveButton);
						layout.putConstraint(SpringLayout.NORTH, quitButton, 5,
								SpringLayout.SOUTH, mainText);
						mainPanel.add(quitButton);
					}

					setUpDisplay();
					mainPanel.updateUI();
				}
				break;
				
			case 2:
				if ((directionChosen == 1 && user.getLocation()[1] == 0)
					 || (directionChosen == 2 && user.getLocation()[0] == 8)
					 || (directionChosen == 3 && user.getLocation()[1] == 8)
					 || (directionChosen == 4 && user.getLocation()[0] == 0)) {
					addNewMessageLine("You shouldn't waste time looking that "
							+ "way.\n");
				}
				
				else if ((directionChosen == 1 && gameMap.getArea(
						user.getLocation()[0], user.getLocation()[1] - 1)
						.getIsRoom())
						|| (directionChosen == 2 && gameMap.getArea(
							user.getLocation()[0] + 1, user.getLocation()[1])
							.getIsRoom())
						|| (directionChosen == 3 && gameMap.getArea(
							user.getLocation()[0], user.getLocation()[1] + 1)
							.getIsRoom())
						|| (directionChosen == 4 && gameMap.getArea(
							user.getLocation()[0] - 1, user.getLocation()[1])
							.getIsRoom())) {
					addNewMessageLine("You can't see into the room from "
							+ "here.\n");
				} else {
					lookDirection = directionChosen;
					mainPanel.remove(none);
					mainPanel.remove(right);
					mainPanel.remove(down);
					mainPanel.remove(up);
					mainPanel.remove(left);
				
					layout.putConstraint(SpringLayout.EAST, moveButton, 5,
						SpringLayout.WEST, mainText);
					layout.putConstraint(SpringLayout.NORTH, moveButton, 5,
						SpringLayout.SOUTH, mainText);
					mainPanel.add(moveButton);

					layout.putConstraint(SpringLayout.WEST, lookButton, 35,
						SpringLayout.EAST, moveButton);
					layout.putConstraint(SpringLayout.NORTH, lookButton, 5,
						SpringLayout.SOUTH, mainText);
					mainPanel.add(lookButton);

					layout.putConstraint(SpringLayout.WEST, shootButton,
						35, SpringLayout.EAST, lookButton);
					layout.putConstraint(SpringLayout.NORTH, shootButton,
						5, SpringLayout.SOUTH, mainText);
					mainPanel.add(shootButton);

					layout.putConstraint(SpringLayout.WEST, saveButton, 35,
						SpringLayout.EAST, shootButton);
					layout.putConstraint(SpringLayout.NORTH, saveButton, 5,
						SpringLayout.SOUTH, mainText);
					mainPanel.add(saveButton);

					layout.putConstraint(SpringLayout.WEST, quitButton, 35,
						SpringLayout.EAST, saveButton);
					layout.putConstraint(SpringLayout.NORTH, quitButton, 5,
						SpringLayout.SOUTH, mainText);
					mainPanel.add(quitButton);
					
					addNewMessageLine("You peered carefully " + directionString
							+ " before acting.\n");
					setUpDisplay();
					mainPanel.updateUI();
				}
				break;
				
			case 3:
				hasShot = true;
				screamsHeard = user.shoot(directionChosen, gameMap, isDebug);
				addNewMessageLine("You shot your blaster " + directionString
						+ ".\n");
				addNewMessageLine("You heard " + screamsHeard + " scream(s).\n");
				
				mainPanel.remove(none);
				mainPanel.remove(right);
				mainPanel.remove(down);
				mainPanel.remove(up);
				mainPanel.remove(left);
			
				layout.putConstraint(SpringLayout.EAST, moveButton, 5,
					SpringLayout.WEST, mainText);
				layout.putConstraint(SpringLayout.NORTH, moveButton, 5,
					SpringLayout.SOUTH, mainText);
				mainPanel.add(moveButton);

				layout.putConstraint(SpringLayout.WEST, lookButton, 35,
					SpringLayout.EAST, moveButton);
				layout.putConstraint(SpringLayout.NORTH, lookButton, 5,
					SpringLayout.SOUTH, mainText);
				mainPanel.add(lookButton);

				layout.putConstraint(SpringLayout.WEST, shootButton,
					35, SpringLayout.EAST, lookButton);
				layout.putConstraint(SpringLayout.NORTH, shootButton,
					5, SpringLayout.SOUTH, mainText);
				mainPanel.add(shootButton);

				layout.putConstraint(SpringLayout.WEST, saveButton, 35,
					SpringLayout.EAST, shootButton);
				layout.putConstraint(SpringLayout.NORTH, saveButton, 5,
					SpringLayout.SOUTH, mainText);
				mainPanel.add(saveButton);

				layout.putConstraint(SpringLayout.WEST, quitButton, 35,
					SpringLayout.EAST, saveButton);
				layout.putConstraint(SpringLayout.NORTH, quitButton, 5,
					SpringLayout.SOUTH, mainText);
				mainPanel.add(quitButton);
				
				setUpDisplay();
				mainPanel.updateUI();
				break;
			}
		}
	}

	private class SaveBeforeQuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == yesButton) {
				Game.saveIt(saveState, user, masters, holo, invinc, addAmmo,
						radar, isDebug, true);
			}

			System.exit(0);
		}
	}
}
