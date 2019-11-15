package Logic.Weapon;

import Logic.Game.Game;
import Logic.Game.GameObject;

public class SuperMissile extends Weapon{
	public static int POINTS = 20;
	public SuperMissile(Game game, int x, int y, int dam) {
		super(game, x, y,dam);
		this.live=1;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean performAttack(GameObject other) {
		other.receiveMissileAttack(this.damage);
		onDelete();
		this.game.enableMissile();
		return true;
	}
	@Override public boolean receiveBombAttack(int damage) {
		onDelete();
		this.game.enableMissile();
		return true;
		}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		live = 0;
		x = -50;
		y = -50;
		this.game.enableMissile();
	}

	@Override
	public void move() {
		
		this.x--;
		if(isOut()) {
			onDelete();
		}
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString() {
		return "!oo!";
	}

}
