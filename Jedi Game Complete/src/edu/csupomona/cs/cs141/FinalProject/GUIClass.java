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
 * This class will represent the GUI window that will appear if the user decides
 * to run the game in GUI mode. The window will manage many of the same things
 * that are also handled in main, because this class must be triggered by
 * private listener classes rather than a loop. It will also keep new
 * references for many of the same variables that are kept in the main
 * method, because the game must continue to keep track of things that are
 * also held locally by the main method - such as the {@link Player} and
 * {@link Map} objects. It holds several objects of various classes from the
 * "swing" package to be used for presenting the user with various buttons,
 * images, etc. It also keeps track of which action you chose if you clicked
 * an action button, which direction you are looking on your turn, if you
 * have shot during your turn, or whether the game has ended or you have died.
 * This class re-uses some of the static methods from the "Game" class - 
 * particularly the ones that manage the actions of the Jedi characters 
 * (their special methods for moving, stabbing, etc.).
 * 
 * @author Team Jedi
 * 
 */
public class GUIClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 800;
	/**
	 * This is an object that uses the image that will display at the top of the
	 * window, showing the game's title, "Rise of the Death Star."
	 */
	private ImageIcon titleImage;
	/**
	 * This is a two-dimensional array of JLabels, where each JLabel represents
	 * one square of the map grid for display.
	 */
	private JLabel[][] mapGrid;
	/**
	 * This is a JLabel that displays the titleImage at the top of the screen.
	 * 
	 */
	private JLabel title;
	/**
	 * This is a JLabel that display's the Player's name at the top of the
	 * player information area on the left side of the screen ("Boba Fett").
	 */
	private JLabel name;
	/**
	 * This is a JLabel that displays the first line of the sentence describing
	 * the Player's mission in the game.
	 */
	private JLabel missionLine1;
	/**
	 * This is a JLabel that displays the second line of the sentence describing
	 * the Player's mission in the game.
	 */
	private JLabel missionLine2;
	/**
	 * This is a JLabel that displays the value of the {@link Player} object's
	 * {@code lives} field in the information area on the left side of the
	 * screen.
	 */
	private JLabel livesDisplay;
	/**
	 * This is a JLabel that displays the value of the {@link Player} object's
	 * {@code invincibilityCounter} field in the information area on the left
	 * side of the screen.
	 */
	private JLabel invincDisplay;
	/**
	 * This is a JLabel that displays the value of the Player's {@link Gun}
	 * object's {@code ammo} field in the information area on the left side of
	 * the screen.
	 */
	private JLabel bulletsDisplay;
	/**
	 * This is a JLabel that is simply used to display a border above the
	 * map on the screen, to make it clearer where the map's display ends.
	 */
	private JLabel topB;
	/**
	 * This is a JLabel that is simply used to display a border to the right of
	 * the map on the screen, to make it clearer where the map's display ends.
	 */
	private JLabel rightB;
	/**
	 * This is a JLabel that is simply used to display a border to the left of
	 * the map on the screen, to make it clearer where the map's display ends.
	 */
	private JLabel leftB;
	/**
	 * This is a JLabel that is simply used to display a border below
	 * the map on the screen, to make it clearer where the map's display ends.
	 */
	private JLabel bottomB;
	/**
	 * This is an ImageIcon that uses an image for the top border for the
	 * display.
	 */
	private ImageIcon topBor;
	/**
	 * This is an ImageIcon that uses an image for the right border for the
	 * display.
	 */
	private ImageIcon rightBor;
	/**
	 * This is an ImageIcon that uses an image for the left border for the
	 * display.
	 */
	private ImageIcon leftBor;
	/**
	 * This is an ImageIcon that uses an image for the bottom border for the
	 * display.
	 */
	private ImageIcon bottomBor;
	/**
	 * This is the button that the user will press to move.
	 * It will hold a new {@code MoveListener} object.
	 * It will only appear when the user has the option to move.
	 */
	private JButton moveButton;
	/**
	 * This is the button that the user will press to look.
	 * It will hold a new {@code LookListener} object.
	 * It will only appear when the user has the option to look.
	 */
	private JButton lookButton;
	/**
	 * This is the button that the user will press to shoot.
	 * It will hold a new {@code ShootListener} object.
	 * It will only appear when the user has the option to shoot.
	 */
	private JButton shootButton;
	/**
	 * This is the button that the user will press to save the game.
	 * It will hold a new {@code SaveListener} object.
	 * It will only appear when the user has the option to save.
	 */
	private JButton saveButton;
	/**
	 * This is the button that the user will press to quit the game.
	 * It will hold a new {@code QuitListener} object.
	 * It will only appear when the user has the option to quit.
	 */
	private JButton quitButton;
	/**
	 * This is the button that the user will press to do an action upward.
	 * It will hold a new {@code DirectionListener} object.
	 * It will only appear when the user has chosen to move, look, or shoot.
	 */
	private JButton up;
	/**
	 * This is the button that the user will press to do an action downward.
	 * It will hold a new {@code DirectionListener} object.
	 * It will only appear when the user has chosen to move, look, or shoot.
	 */
	private JButton down;
	/**
	 * This is the button that the user will press to do an action left.
	 * It will hold a new {@code DirectionListener} object.
	 * It will only appear when the user has chosen to move, look, or shoot.
	 */
	private JButton left;
	/**
	 * This is the button that the user will press to do an action right.
	 * It will hold a new {@code DirectionListener} object.
	 * It will only appear when the user has chosen to move, look, or shoot.
	 */
	private JButton right;
	/**
	 * This is the button that the user will press to move in no direction
	 * at all, or to stay still for a turn.
	 * It will hold a new {@code DirectionListener} object.
	 * It will only appear when the user has chosen to move, because movement
	 * is the only action that can be done in no direction at all while choosing
	 * that option.
	 */
	private JButton none;
	/**
	 * This is the button that the user will press to save before quitting
	 * when the user has chosen to quit the game and is prompted with the
	 * options "Yes" or "No."
	 * It will hold a new {@code SaveBeforeQuitListener} object.
	 * It will only appear when the user has chosen to quit the game.
	 */
	private JButton yesButton;
	/**
	 * This is the button that the user will press to quit without saving
	 * when the user has chosen to quit the game and is prompted with the
	 * options "Yes" or "No."
	 * It will hold a new {@code SaveBeforeQuitListener} object.
	 * It will only appear when the user has chosen to quit the game.
	 */
	private JButton noButton;
	/**
	 * This is a text area that will display the text messages letting
	 * the user know what has just happened - such as when they have
	 * died or picked up a powerup item.
	 * It displays 5 lines at a time and will appear below the map
	 * (each new line displayed will print at the bottom, pushing 
	 * all of the old messages upward).
	 * It will be visible the entire time the GUI is active.
	 */
	private JTextArea mainText;
	/**
	 * This is the layout used for managing the positions of all of the
	 * visible objects used in the GUI window.
	 */
	private SpringLayout layout;
	/**
	 * This is the JPanel container used to hold all of the visible objects
	 * used in the GUI window.
	 */
	private JPanel mainPanel;
	/**
	 * This is the font used for display in the buttons and labels.
	 * Because these buttons and labels are needed more frequently - and
	 * because they appear in all different locations, unlike the more central
	 * {@code mainText} JTextArea - they should have a bigger, easier-to-read
	 * font.
	 */
	private Font biggerFont;

	/**
	 * This is the Serializable object used to either save or load the game.
	 * It will hold all of the parameters of the game that need to be
	 * maintained: the player, the enemies, the items, and whether or not the
	 * game is a debugging or non-debugging game (this choice cannot be changed,
	 * so it should be kept after saving/loading).
	 */
	private Save saveState;
	/**
	 * This is the Map used to represent all of the squares on the game grid.
	 */
	private Map gameMap;
	/**
	 * This is the Player representing the character controlled by the user.
	 */
	private Player user;
	/**
	 * This is an array of all of the enemies (the "Jedi") that antagonize
	 * the user throughout the game.
	 */
	private Jedi[] masters;
	/**
	 * This is the object representing the (@link Item} that the
	 * user may pick up if they walk onto giving the player temporary
	 * invincibility.
	 */
	private InvincibilityPowerUp invinc;
	/**
	 * This is the object representing the {@link Item} that the user may pick
	 * up if they walk onto that prints a message revealing the holocron's
	 * location on the grid.
	 */
	private RadarPowerUp radar;
	/**
	 * This is the object representing the {@link Item} that the user may pick
	 * up if they walk onto that adds one to the field of the Player's
	 * {@link Gun} object, {@code ammo}.
	 */
	private AddAmmoPowerUp addAmmo;
	/**
	 * This is the object representing the {@link Item} that represents the
	 * game's primary objective; the user may pick it up to win and end the
	 * game.
	 */
	private Holocron holo;
	/**
	 * This is a field keeping track of whether this is a game that the user
	 * chose to run in debug mode or not.
	 */
	private boolean isDebug;
	
	/**
	 * This is a field that registers which action the user chose to do
	 * when the pressed the corresponding buttons to either move, look,
	 * or shoot.
	 * It will be used in the {@link DirectionListener} class, so that
	 * the method within it knows both with direction and which action
	 * the user has chosen.
	 * A value of 1 represents a decision to move, a value of 2 represents
	 * a decision to look, and a value of 3 represents a decision to shoot.
	 */
	private int action;
	/**
	 * This is a field that registers which direction the user has
	 * looked on that turn.
	 * A value of 1 represents up, a value of 2 represents right, a value
	 * of 3 represents down, a value of 4 represents left, and a value of
	 * 5 represents "none" (i.e. the user has chosen to stay in place).
	 */
	private int lookDirection;
	/**
	 * This is a field that registers whether the user has shot already
	 * on their turn. It will reset itself to false at the start of each
	 * turn, and it will set itself to true just after the user shoots.
	 * This field is necessary to stop the user from shooting twice in
	 * one turn.
	 */
	private boolean hasShot;
	/**
	 * This is an array of Strings that are used to hold the information
	 * which is printed in the JTextArea, {@code mainText}.
	 */
	private String[] messageLines;
	/**
	 * This is a boolean field that keeps track of whether the game
	 * has ended.
	 * If the game has ended, there are no more options displayed, and
	 * so the game does not present the user with any more buttons.
	 */
	private boolean gameEnded;
	/**
	 * This is a boolean field that keeps track of whether the user has
	 * died. It is used by the static method in game, {@code mastersStab}.
	 */
	private boolean hasDied;

	/**
	 * This constructor 
	 * 
	 * @param p the {@link Player} object that is passed from main
	 * @param jed the array of {@link Jedi} objects, representing the enemies,
	 *            that is passed from main
	 * @param inv the {@link InvincibilityPowerUp} object that is passed
	 *            from main
	 * @param r the {@link RadarPowerUp} object that is passed from main
	 * @param a the {@link AddAmmoPowerUp} object that is passed from main
	 * @param h the {@link Holocron} object, representing the game's objective,
	 *          that is passed from main
	 * @param noFog a boolean value representing whether or not the game is
	 *              in debug mode (thus having no "fog")
	 * @param m the {@link Map} object representing the state of all of the
	 *          individual {@code Area} objects in the map
	 * @param s the Serializable {@link Save} object used for saving and loading
	 *          the state of the game
	 */
	public GUIClass(Player p, Jedi[] jed, InvincibilityPowerUp inv,
			RadarPowerUp r, AddAmmoPowerUp a, Holocron h, boolean noFog, Map m,
			Save s) {
		// Setting the default settings for the GUI JFrame object (title, size,
		// and closing operation)
		setTitle("Star Wars: Rise of the Death Star");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Setting all of the original states of the main game objects as well
		// as a few extra parameters such as whether the game has ended, and
		// the lines to be displayed in the game's main text area
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

		// Initializes all of the Strings for the game's messages
		for (int i = 0; i < 4; i++)
			messageLines[i] = "\n";

		messageLines[4] = "Locate and secure the holocron!\n";
		
		// Setting all of the images for the borders of the map's display
		topBor = new ImageIcon("Top_Bottom_Border.PNG");
		rightBor = new ImageIcon("Right_Border.PNG");
		leftBor = new ImageIcon("Left_Border.PNG");
		bottomBor = new ImageIcon("Top_Bottom_Border.PNG");
		
		// Using the border images to make corresponding labels to display them
		topB = new JLabel(topBor);
		rightB = new JLabel(rightBor);
		leftB = new JLabel(leftBor);
		bottomB = new JLabel(bottomBor);

		// Creating new buttons for each of the game's main options, as well as
		// corresponding action listeners for each of them
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

		// Creating new buttons for each of the game's directional options for
		// moving, looking, or shooting, and giving each a new DirectionListener
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

		// Creating new buttons for the options given when the user chooses
		// to quit the game, and giving each a new SaveBeforeQuitListener
		yesButton = new JButton("Yes");
		yesButton.addActionListener(new SaveBeforeQuitListener());
		noButton = new JButton("No");
		noButton.addActionListener(new SaveBeforeQuitListener());

		// Initiating most of the window's main JLabels, as well
		// as changing their default background and foreground colors
		// and font
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
		
		// Creating the JFrame's panel and layout
		mainPanel = new JPanel();
		layout = new SpringLayout();
		mainPanel.setLayout(layout);

		// Creating the JLabel for the title and adding it to the panel
		// with appropriate constraints for its position
		titleImage = new ImageIcon("Rise of the Death Star.PNG");
		title = new JLabel(titleImage);
		layout.putConstraint(SpringLayout.NORTH, title, 3, SpringLayout.NORTH,
				mainPanel);
		layout.putConstraint(SpringLayout.WEST, title, 150, SpringLayout.WEST,
				mainPanel);
		mainPanel.add(title);

		// Instantiating the JLabels that represent different spots on the map
		// grid
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				mapGrid[i][j] = new JLabel();
		}

		setUpDisplay();

		// puts constraints to set up the map grid JLabels and adds them
		// to the panel
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

		// Sets constraints and adds the JLabel for the Player's name,
		// "Boba Fett," to the panel
		layout.putConstraint(SpringLayout.WEST, name, 75, SpringLayout.WEST,
				mainPanel);
		layout.putConstraint(SpringLayout.NORTH, name, 3, SpringLayout.SOUTH,
				title);
		mainPanel.add(name);

		// Sets constraints and adds the JLabel for the Player's number of
		// lives to the panel
		layout.putConstraint(SpringLayout.NORTH, livesDisplay, 50,
				SpringLayout.SOUTH, name);
		layout.putConstraint(SpringLayout.WEST, livesDisplay, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(livesDisplay);
		
		// Sets constraints and adds the JLabel for the Player's number of
		// bullets to the panel
		layout.putConstraint(SpringLayout.NORTH, bulletsDisplay, 20,
				SpringLayout.SOUTH, livesDisplay);
		layout.putConstraint(SpringLayout.WEST, bulletsDisplay, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(bulletsDisplay);

		// Sets constraints and adds the JLabel for the Player's
		// invincibility counter to the panel
		layout.putConstraint(SpringLayout.NORTH, invincDisplay, 20,
				SpringLayout.SOUTH, bulletsDisplay);
		layout.putConstraint(SpringLayout.WEST, invincDisplay, 15,
				SpringLayout.WEST, mainPanel);
		mainPanel.add(invincDisplay);

		// Sets constraints and adds the two JLabels for the sentence
		// displaying the mission of the game to the panel
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

		// Sets constraints to the JTextArea used for the game's main
		// messages, sets up the messages, and then adds it to the panel
		layout.putConstraint(SpringLayout.NORTH, mainText, 15,
				SpringLayout.SOUTH, mapGrid[4][8]);
		layout.putConstraint(SpringLayout.WEST, mainText, -50,
				SpringLayout.WEST, mapGrid[0][8]);
		mainText.setText(messageLines[0] + messageLines[1] + messageLines[2]
				+ messageLines[3] + messageLines[4]);
		mainPanel.add(mainText);

		// Sets constraints and adds the button for moving to the panel
		layout.putConstraint(SpringLayout.EAST, moveButton, 5,
				SpringLayout.WEST, mainText);
		layout.putConstraint(SpringLayout.NORTH, moveButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(moveButton);

		// Sets constraints and adds the button for looking to the panel
		layout.putConstraint(SpringLayout.WEST, lookButton, 35,
				SpringLayout.EAST, moveButton);
		layout.putConstraint(SpringLayout.NORTH, lookButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(lookButton);

		// Sets constraints and adds the button for shooting to the panel
		layout.putConstraint(SpringLayout.WEST, shootButton, 35,
				SpringLayout.EAST, lookButton);
		layout.putConstraint(SpringLayout.NORTH, shootButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(shootButton);

		// Sets constraints and adds the button for saving to the panel
		layout.putConstraint(SpringLayout.WEST, saveButton, 35,
				SpringLayout.EAST, shootButton);
		layout.putConstraint(SpringLayout.NORTH, saveButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(saveButton);

		// Sets constraints and adds the button for quitting to the panel
		layout.putConstraint(SpringLayout.WEST, quitButton, 35,
				SpringLayout.EAST, saveButton);
		layout.putConstraint(SpringLayout.NORTH, quitButton, 5,
				SpringLayout.SOUTH, mainText);
		mainPanel.add(quitButton);
		
		// Sets constraints and adds the map border images to the panel
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
		
		// Sets the panel's background color and adds it to the JFrame
		mainPanel.setBackground(Color.BLACK);
		add(mainPanel);
		
		// Makes the JFrame non-resizable and finally makes it visible
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Resets all of the images for the JLabels that represent the squares of
	 * the map grid, and then changes the text displaying the user's current
	 * number of lives and ammunition to reflect any possible changes in these
	 * two fields of {@link Player}.
	 */
	public void setUpDisplay() {
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				mapGrid[i][j].setIcon(whichImage(i, j));
			}
		}

		livesDisplay.setText("Lives: " + user.getLives());
		bulletsDisplay.setText("Ammo: " + user.getGun().getAmmo());
	}

	/**
	 * This method returns an image to be used for the map's main grid's
	 * JLabels.
	 * 
	 * @param x the x-coordinate of the spot being checked for an image
	 * @param y the y-coordinate of the spot being checked for an image
	 * @return the image which will be used for the JLabel
	 */
	public ImageIcon whichImage(int x, int y) {
		ImageIcon theImage; // the image that will be returned

		// If the area is a room, then it will display either an image
		// to represent the Holocron, the Player, or an empty/invisible
		// room.
		if (gameMap.getArea(x, y).getIsRoom()) {
			if (((isDebug || gameMap.getArea(x, y).getPerson() == user))
					&& (gameMap.getArea(x, y).getItem() == holo))
				theImage = new ImageIcon("Room_Holocron.PNG");

			else if (gameMap.getArea(x, y).getPerson() == user)
				theImage = new ImageIcon("Room_Boba.PNG");

			else
				theImage = new ImageIcon("Room.PNG");
		}

		// If the area holds the Player in it, it will always display
		// an image representing the Player.
		else if (gameMap.getArea(x, y).getPerson() == user)
			theImage = new ImageIcon("Boba_Fett.PNG");

		else {
			
			// If the area does not fit one of these conditions, then it
			// must either be in debug mode or be "visible" (which is
			// checked by the "getIsVisible" method) to display what
			// is actually inside
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

			// If the area is invisible, it will display an image representing
			// an invisible square
			else
				theImage = new ImageIcon("Invisible_Black.PNG");
		}

		return theImage;
	}

	/**
	 * This method will check to see whether the square that is currently
	 * being checked is visible to the user, and thus whether the contents
	 * of it should be displayed.
	 * 
	 * @param x the x-coordinate of the spot being checked
	 * @param y the y-coordinate of the spot being checked
	 * @return a boolean value telling whether the spot should display
	 *         its actual contents or a black, invisible image
	 */
	public boolean getIsVisible(int x, int y) {
		boolean visible = false; // value representing whether the square is
		                         // visible

		// If the user is standing beside a space and it is not a room,
		// then the square should be visible
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

		// If the user is looking up, then the square might be visible,
		// depending on where the user is
		else if (lookDirection == 1) {
			for (int i = 1; i <= 3; i++) {
				if ((y + i) > 8 || gameMap.getArea(x, y + i).getIsRoom())
					break;

				if (gameMap.getArea(x, y + i).getPerson() == user)
					visible = true;
			}
		}

		// If the user is looking right, then the square might be visible,
		// depending on where the user is
		else if (lookDirection == 2) {
			for (int i = 1; i <= 3; i++) {
				if ((x - i) < 0 || gameMap.getArea(x - i, y).getIsRoom())
					break;

				if (gameMap.getArea(x - i, y).getPerson() == user)
					visible = true;
			}
		}

		// If the user is looking down, then the square might be visible,
		// depending on where the user is
		else if (lookDirection == 3) {
			for (int i = 1; i <= 3; i++) {
				if ((y - i) < 0 || gameMap.getArea(x, y - i).getIsRoom())
					break;

				if (gameMap.getArea(x, y - i).getPerson() == user)
					visible = true;
			}
		}

		// If the user is looking left, then the square might be visible,
		// depending on where the user is
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

	/**
	 * This is a method used to more conveniently add a new line to the
	 * JTextArea {@code mainText} with a single line. It adjusts all of
	 * the lines upward and then sets the very bottom line to be equal
	 * to the new line, which is the parameter. It then resets the
	 * JTextArea's text to reflect what its new, adjusted contents should be.
	 * 
	 * @param newLine the line being added to {@code mainText}
	 */
	public void addNewMessageLine(String newLine) {
		for (int i = 0; i <= 3; i++)
			messageLines[i] = messageLines[i + 1];

		messageLines[4] = newLine;
		mainText.setText(messageLines[0] + messageLines[1] + messageLines[2]
				+ messageLines[3] + messageLines[4]);
	}

	/**
	 * This is a method that will be used whenever the user wants to move.
	 * It will check to see if the direction they are trying to move is
	 * available, and will return a value reflecting whether the movement
	 * was successful.
	 * 
	 * @param direction the direction that the user wants to move
	 * @return whether the movement was successful
	 */
	public boolean tryToMove(int direction) {
		boolean moveSuccess; // value reflecting the success of the attempted
		                     // movement

		// If the user tries to walk out of the map, the movement fails.
		if ((direction == 1 && user.getLocation()[1] == 0)
				|| (direction == 2 && user.getLocation()[0] == 8)
				|| (direction == 3 && user.getLocation()[1] == 8)
				|| (direction == 4 && user.getLocation()[0] == 0)) {
			addNewMessageLine("You can't move that way - try again.\n");
			moveSuccess = false;
			
			// If the user tries to move into a room from the sides or
			// bottom, then the movement fails.
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
			
			// If the user tries to move into a space that has another Person
			// in it, then the movement fails.
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

			// If the movement was successful, then the movement will be
			// initiated with the Player's "move" method. However, if the
			// direction is equal to 5, then nothing needs to be done because
			// the user stayed still.
			if (direction != 5)
				user.move(direction, gameMap);
		}

		return moveSuccess;
	}

	/**
	 * This is a method that will be used whenever the user wishes to move while
	 * they are inside of a room. It will try to move them directly outside, or
	 * else (if a Jedi is nearby) it will keep them inside for a turn to avoid
	 * getting stuck or instant death.
	 */
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

	/**
	 * This is a method that will be used after the Player has successfully
	 * moved. It will check to see if the Player has moved onto an {@link Item}
	 * object, and if it has, then it will activate the {@code Item} and adjust
	 * the game accordingly.
	 */
	public void lookForItems() {
		String holocronRoom; // a String which will hold a value for which
		                     // room the Holocron is in, used when the Player
		                     // picks up the Radar

		if (gameMap.getArea(user.getLocation()[0], user.getLocation()[1])
				.getItem() != null) {
			try {
				// If the user landed on ammo, then the appropriate message
				// line will be added and it will activate the item
				if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getItem() == addAmmo) {
					addNewMessageLine("You found an extra charge for your "
							+ "blaster!\n");
					addAmmo.activate(user.getGun());
					
					// If the user landed on an InvincibilityPowerUp, then
					// the appropriate message line will be added, the
					// invincibility counter JLabel will be updated, and 
					// the powerup will activate.
				} else if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getItem() == invinc) {
					addNewMessageLine("You found an invincibility powerup! "
							+ "It will last for 5 turns!\n");
					invincDisplay.setText("Invincibility counter: 5");
					invinc.activate(user);
					
					// If the user landed on the Radar, then it will check
					// which room it is in and then display the message and
					// activate the Radar.
				} else if (gameMap.getArea(user.getLocation()[0],
						user.getLocation()[1]).getItem() == radar) {
					// The words "very center" are used for the middle room
					if (holo.getLocation()[0] == 4
							&& holo.getLocation()[1] == 4)
						holocronRoom = "very center";
					
					else {
						// The y-value of the room is used to determine
						// the first word of the location's description
						if (holo.getLocation()[1] == 1)
							holocronRoom = "upper";
						else if (holo.getLocation()[1] == 4)
							holocronRoom = "middle";
						else
							holocronRoom = "lower";

						// The x-value of the room is used to determine
						// the second word of the location's description
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
					
					// If the user lands on the Holocron, then it will activate.
					// This will throw the "GameOverException," so extra events
					// will be handled in the catch block.
				} else {
					holo.activate();
				}

				gameMap.getArea(user.getLocation()[0], user.getLocation()[1])
						.setItem(null);

				// If the game has ended, then the directional buttons are
				// removed but no buttons are added. The "You win" message is
				// added to the mainText JTextArea
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

	/**
	 * This method is used whenever the user has finished their turn (which
	 * would be whenever the user has moved). It updates the JLabel that
	 * displays the {@link Player} objects field, {@code InvincibilityCounter},
	 * and it allows the Jedi enemies to move. It also adds the appropriate
	 * message line and makes the Jedi stop chasing the user if the user died.
	 */
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

	/**
	 * This private listener class is used whenever the user has clicked
	 * the appropriate button to move.
	 * 
	 * @author Team Jedi
	 *
	 */
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

				// Setting new constraints and adding the directional
				// buttons for which way the player wants to move,
				// including one for "none" to stand still
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
				
				// If the Player is in a room, then it will try to move
				// out of the room and automatically end the Player's turn
				// while updating the map's display.
			} else {
				moveOutOfRoom();
				endTurn();
				setUpDisplay();
				mainPanel.updateUI();
			}
		}

	}

	/**
	 * This private listener class is used whenever the user has clicked
	 * the appropriate button to look.
	 * 
	 * @author Team Jedi
	 */
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

				// Puts constraints and adds buttons for all of the possible
				// directions to look
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

	/**
	 * This private listener class is used whenever the user has clicked
	 * the appropriate button to shoot.
	 * 
	 * @author Team Jedi
	 */
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

				// Puts constraints and adds buttons for all of the possible
				// directions to look
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

	/**
	 * This private listener class is used whenever the user has clicked
	 * the appropriate button to save.
	 * 
	 * @author Team Jedi
	 */
	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.saveIt(saveState, user, masters, holo, invinc, addAmmo, radar,
					isDebug, true);
			addNewMessageLine("Game saved.\n");
		}
	}

	/**
	 * This private listener class is used whenever the user has clicked
	 * the appropriate button to quit.
	 * 
	 * @author Team Jedi
	 */
	private class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addNewMessageLine("Would you like to save before quitting?\n");

			mainPanel.remove(quitButton);
			mainPanel.remove(saveButton);
			mainPanel.remove(shootButton);
			mainPanel.remove(lookButton);
			mainPanel.remove(moveButton);

			// Puts constraints and adds buttons for the options "Yes" and
			// "No," prompting the user for whether they wish to quit before
			// saving
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

	/**
	 * This private listener class is used whenever the user has chosen
	 * a direction to move, look, or shoot.
	 * 
	 * @author Team Jedi
	 *
	 */
	private class DirectionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String directionString; // This String represents the
			                        // direction chosen, which is used for
			                        // narration in the mainText box
			
			int directionChosen; // This integer also represents the direction
			                     // chosen in a way that is easier to use with
			                     // a switch-case block
			
			int screamsHeard; // This integer represents how many screams were
			                  // heard (implicating how many enemies were
			                  // killed) when the user has chosen to shoot
			
			boolean actionSuccess; // This value represents whether or not
			                       // the chosen action was successful

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
			// case 1 is used for when the user has tried to move
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
						// If the game has not ended, then the buttons to use
						// for the main options need to be re-added
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
				
				// case 2 is used for when the user has tried to look
			case 2:
				// If the user tried to look in a direction out of the map,
				// then it stops the user
				if ((directionChosen == 1 && user.getLocation()[1] == 0)
					 || (directionChosen == 2 && user.getLocation()[0] == 8)
					 || (directionChosen == 3 && user.getLocation()[1] == 8)
					 || (directionChosen == 4 && user.getLocation()[0] == 0)) {
					addNewMessageLine("You shouldn't waste time looking that "
							+ "way.\n");
				}
				
				// If the user tried to look in a direction of a room,
				// then it stops the user
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
				
					// new buttons for the game's main options need to be
					// re-added
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
				
				// case 3 is used for when the user has tried to shoot
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
			
				// new buttons for the game's main options need to be
				// re-added
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

	/**
	 * This private listener class is used whenever the user has decided
	 * to save before quitting.
	 * 
	 * @author Team Jedi
	 *
	 */
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
