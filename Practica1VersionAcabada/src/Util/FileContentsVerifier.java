package Util;

import Logic.Level;
import Logic.Game.Game;

public class FileContentsVerifier {
	public static final String separator1 = ";";
	public static final String separator2 = ",";
	public static final String labelRefSeparator = " - ";

	private String foundInFileString = "";
	
	private void appendToFoundInFileString(String linePrefix) {
		foundInFileString += linePrefix;
	}

	// Don't catch NumberFormatException.
	public boolean verifyCycleString(String cycleString) {
		String[] words = cycleString.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 2
				|| !verifyCurrentCycle(Integer.parseInt(words[1])))
			return false;
		return true;
	}
	
	public boolean verifyLevelString(String levelString) {
		String[] words = levelString.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 2
				|| !verifyLevel(Level.fromParam(words[1])))
			return false;
		return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyOvniString(String lineFromFile, Game game, int armour) {
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 3) return false;
		String[] coords = words[1].split(separator2);
		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLivesOvni(Integer.parseInt(words[2])) ) 
			return false;
		return true;
	}

	private boolean verifyLivesOvni(int px) {
		return px >=0;
	}

	// Don't catch NumberFormatException.
	public boolean verifyPlayerString(String lineFromFile, Game game, int armour) {
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 5) return false;

		String[] coords = words[0].split(separator2);
		
		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[1]), armour)
				|| !verifyPoints(Integer.parseInt(words[2]))
				|| !verifyBool(words[3])
				|| !verifyMissile(words[4]) )
			return false;
		return true;
	}
	
	public boolean verifyMissile(String string) {
		return Integer.parseInt(string)>=0;
	}

	// Don't catch NumberFormatException.
	public boolean verifyAlienShipString(String lineFromFile, Game game, int armour) {
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 5) return false;

		String[] coords = words[1].split(separator2);
		
		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
				|| !verifyDirection(words[4]) ) {//Move.parse(words[4])
			return false;
		}
		return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyWeaponString(String lineFromFile, Game game) {
		String[] words = lineFromFile.split(separator1);
		if (words.length != 2) return false;
		
		appendToFoundInFileString(words[0]);
		String[] coords = words[1].split(separator2);

		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game) )
			return false;
		return true;
	}
	
	public boolean verifyRefString(String lineFromFile) {
		String[] words = lineFromFile.split(labelRefSeparator);
		if (words.length != 2 || !verifyLabel(words[1])) return false;
		return true;
	}

	public static boolean verifyLabel(String label) {
		return Integer.parseInt(label) > 0;
	}
	
	public static boolean verifyCoords(int x, int y, Game game) {
		return game.isOnBoard(x, y) || !game.isOnBoard(x, y);
	}
	
	public static boolean verifyCurrentCycle(int currentCycle) {
		return currentCycle >= 0;
	}
	
	public static boolean verifyLevel(Level level) {
		return level != null;
	}
	/*
	public static boolean verifyDir(Move dir) {
		return dir != null;
	}*/
	
	public static boolean verifyLives(int live, int armour) {
	//	return 0 < live && live <= armour;
		return live > 0;
	}
	
	public static boolean verifyPoints(int points) {
		return points >= 0;
	}
	
	public static boolean verifyCycleToNextAlienMove(int cycle, Level level) {
		return cycle >=0 && cycle <= level.getNumCyclesToMoveOneCell();//return 0 <= cycle && cycle <= level.getNumCyclesToMoveOneCell();
	}
	
	// parseBoolean converts any string different from "true" to false.
	public static boolean verifyBool(String boolString) {
		return boolString.equals("true") || boolString.equals("false");
	}
	public static boolean verifyDirection(String boolString) {
		return boolString.equals("left") || boolString.equals("right");
	}

	public boolean isMissileOnLoadedBoard() {
		return foundInFileString.toUpperCase().contains("M");
	}
	
	// Use a regular expression to verify the string of concatenated prefixes found
	public boolean verifyLines() {
		// TO DO: compare foundInFileString with a regular expression
		return true; 
	}

	// text explaining allowed concatenated prefixes
	public String toString() {
		// TO DO
		return "";
	}

}
