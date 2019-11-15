package Logic.Object;

import Logic.Game.Game;
import Logic.Game.GameObject;

public abstract class Ship extends GameObject{
	
	public Ship(Game game, int x, int y, int live) {
		super(game, x, y, live);

	}


	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();

}
