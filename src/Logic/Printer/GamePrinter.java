package Logic.Printer;

import Logic.Game.Game;

public abstract class GamePrinter {
	Game game;
	public void setGame(Game game) {
		this.game = game;
	}
	public abstract String toString(Game game);
	//public GamePrinter() {}
	//public abstract String toString(Game game);
	//public abstract GamePrinter parse (String name);
	//public abstract String helpText();
}
/**
 * package Logic.Printer;

import Logic.Game.Game;

public interface GamePrinter {
	String toString(Game game);
	public GamePrinter parse (String name);
	public String helpText();
}

 */
