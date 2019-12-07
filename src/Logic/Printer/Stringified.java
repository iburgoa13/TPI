package Logic.Printer;

import Logic.Game.Game;

public class Stringified extends GamePrinter{
	  String board;
	  public Stringified() {
		  board = "";
	  }
	private void encodeGame(Game game) {

		board = "---Space Invaders v2.0---\r\n"+
				"G ;" + game.getLevel().name()+ ";"+game.getCycleTotal()+"\r\n"+
				game.debug();
		//board += game.debug();
	}
	@Override
	public String toString(Game game) {
		encodeGame(game);
		return board;
	}
}
