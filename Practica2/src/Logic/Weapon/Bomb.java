package Logic.Weapon;

import Logic.Game.Game;
import Logic.Game.GameObject;

public class Bomb extends Weapon{

	public Bomb(Game game, int x, int y, int dam) {
		super(game, x, y, dam);
		live = 1;
		// TODO Auto-generated constructor stub
	}

	public boolean performAttack(GameObject other) {
		other.receiveBombAttack(damage);
		onDelete();
		this.game.enableMissile();
		return true;
	}
	@Override
	public boolean receiveMissileAttack(int damage) {
		onDelete();
		return true;
	}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		live = 0;
		x = -888;
		y = -888;
	}

	@Override
	public void move() {
		x++;
		if(this.isOut()) {
			onDelete();
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ".";
	}

}
