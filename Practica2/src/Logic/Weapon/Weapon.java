package Logic.Weapon;

import Logic.Game.Game;
import Logic.Game.GameObject;

public abstract class Weapon extends GameObject{
	protected int damage;
	//pongo las vidas siempre a uno
	public Weapon(Game game, int x, int y) {
		super(game, x, y, 1);
		damage = 0;
		// TODO Auto-generated constructor stub
	}
	public Weapon(Game game, int x, int y, int dam) {
		super(game,x,y,0);
		damage = dam;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int d) {
		this.damage = d;
	}
	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();

}
