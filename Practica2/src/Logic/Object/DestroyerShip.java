package Logic.Object;

import Logic.Game.Game;
import Logic.IGame.IExecuteRandomActions;
import Logic.Weapon.Bomb;

public class DestroyerShip extends AlienShip{
	private static final int POINTS = 10;
	private Bomb bomb;
	private boolean shoot;
	public DestroyerShip(Game game, int x, int y, int live) {
		super(game, x, y, live, POINTS);
		bomb = null;
		shoot = false;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		return true;
	}
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		return true;
	}
	
	
	@Override
	public void computerAction() {
		if(IExecuteRandomActions.canGenerateRandomBomb(game) && !shoot) {
			bomb = new Bomb(game, x, y,1);
			game.addObject(bomb);
			shoot = true;
		}
		if(shoot) {
			if(!this.bomb.isAlive()) {
				shoot = false;
				bomb = null;
			}
		}
	}
	
	@Override
	public void onDelete() {
		this.decNumEnemyShip();
		this.game.receivePoints(POINTS);
	}


	@Override
	public String toString() {
		return "D["+this.live+"]";
	}
	/*@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}*/



}
