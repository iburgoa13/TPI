package Logic.Object;

import Logic.Game.Game;
import Logic.Game.GameObject;

public class ExplosiveShip extends AlienShip{
	public static int POINTS = 5;
	public ExplosiveShip(Game game, int x, int y, int live) {
		super(game, x, y, live, POINTS);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean performAttack(GameObject other) {
		if(other.isAlive())other.receiveExplosiveShipAttack(1);
		return true;
	}
	@Override
	public boolean receiveExplosiveShipAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		//onDelete();
		return true;
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		//onDelete();
		return true;
	}
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		//onDelete();
		return true;
	}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDelete() {
		game.receivePoints(POINTS);
		this.decNumEnemyShip();
		this.game.Explosive(this);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "E["+ live + "]";
	}

}
