package Logic.Game;

import Logic.IGame.IAttack;

public abstract class GameObject implements IAttack {
	protected int x;
	protected int y;
	protected int live;
	protected Game game;
	public GameObject( Game game, int x, int y, int live) {	
		this.x = x;
		this.y = y;
		this.game = game;
		this.live = live;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public void setX(int p) {
		x = p;
	}
	public void setY(int p) {
		y = p;
	}
	public void incY(int p) {
		y = y +p;
	}
	public void incX(int p) {
		x+=p;
	}
	public boolean isAlive() {
		return this.live > 0;
	}

	public int getLive() {
		return this.live;
	}
	
	public boolean isOnPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public void getDamage (int damage) {
		this.live = damage >= this.live ? 0 : this.live - damage;
	}
	
	public boolean isOut() {
		return !game.isOnBoard(x, y);
	}

	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();


	public boolean isPossible() {
		return this.game.getCycle();
	}


	
}

