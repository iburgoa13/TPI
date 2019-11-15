package Logic.Object;

import Logic.Game.Game;

public abstract class EnemyShip extends Ship{
	
	protected int points;
	public EnemyShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
		points = 0;
		// TODO Auto-generated constructor stub
	}
	public EnemyShip(Game game, int x, int y, int live, int points) {
		super(game,x,y,live);
		this.points = points;
	}
	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();

}
