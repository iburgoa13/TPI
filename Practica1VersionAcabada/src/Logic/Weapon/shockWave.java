package Logic.Weapon;


import Logic.Game.Game;
import Logic.Game.GameObject;
import Util.FileContentsVerifier;

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
	@Override
	protected String debug() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getDireccion() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
