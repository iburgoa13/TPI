package Logic.Object;

import Logic.Game.Game;
import Logic.IGame.IAttack;
import Logic.IGame.IExecuteRandomActions;

public class Ovni extends EnemyShip implements IExecuteRandomActions, IAttack{
	private static final int POINTS = 25;
	private boolean enable;
	public Ovni(Game game, int x, int y, int live, int points) {
		super(game, x, y, live,points);
		enable = false;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		if(!this.isAlive()) {
			onDelete();
			this.game.receivePoints(POINTS);
			this.game.enableShockWave();
		}
		return true;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		if(IExecuteRandomActions.canGenerateRandomOvni(game) && !enable) {
			x = 0;
			enable = true;
			y = Game.DIM_Y+1;
		}
	}

	@Override
	public void onDelete() {
		this.live = 1;
		x = -87;
		y = -87;
		enable = false;
		
	}

	@Override
	public void move() {
		if(enable) {
			y--;
			if(this.isOut()) {
				onDelete();
			}
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "O["+ this.live +"]";
	}

}
