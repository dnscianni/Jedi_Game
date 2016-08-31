package edu.csupomona.cs.cs141.TestCases;

import edu.csupomona.cs.cs141.FinalProject.*;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map m = new Map();
		Player user = new Player();
		m.setMaze(user.getLocation()[1] * 2 + 1,
				user.getLocation()[0] * 4 + 2, "P");
		m.printMaze();
	}

}
