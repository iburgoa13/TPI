package Logic.Game;

public class GameState {
	private Game g;
	private GameObjectBoard board;

	public GameState(Game copia, GameObjectBoard state) {
		g = copia;
		board = state;
	}

	public Game getFGame() {
		return g;
	}
	public void setGame(Game game) {
		g = game;
	}
	public GameObjectBoard getGOB() {
		return board;
	}
}
