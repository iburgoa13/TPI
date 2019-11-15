package Logic.Weapon;

import Logic.Game.Game;
import Logic.Game.GameObject;

public class shockWave extends Weapon{

	public shockWave(Game game, int x, int y, int dam) {
		super(game, x, y, dam);
		this.live=1;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean performAttack(GameObject other) {
		other.receiveShockWaveAttack(this.damage);
		onDelete();
		this.game.shockWave();
		return true;
	}
	@Override
	public void computerAction() {
	}

	@Override
	public void onDelete() {
		live = 0;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
