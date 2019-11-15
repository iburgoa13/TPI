package Logic.Object;

import Logic.Game.Game;
import Logic.Game.GameObject;
import Logic.IGame.IExecuteRandomActions;
public class RegularShip extends AlienShip{
	private static final int POINTS = 5;
	public RegularShip(Game game, int x, int y, int live) {
		super(game, x, y, live, POINTS);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean performAttack(GameObject other) {
		return false;
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		return true;
	}
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		return true;
	}
	@Override
	public boolean receiveExplosiveShipAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		return true;
	}
	@Override
	public void computerAction() {
		if(IExecuteRandomActions.canGenerateRandomExplosive(game)) {
			ExplosiveShip e = new ExplosiveShip(game,x,y,live);
			e.dir = this.dir;
			this.game.addObject(e);
			this.live = -888;
		}
	}

	@Override
	public void onDelete() {
		this.decNumEnemyShip();
		this.game.receivePoints(POINTS);
	}

	//@Override
	//public void move() {
		/*if(game.getCycle() % game.getLevel().getNumCyclesToMoveOneCell() == 0 && game.getCycle()!=0) {
			if(!this.dir) {
				//tengo que bajar? no
				if(this.NUM_ENEMYSHIP_DESCENT==0) {
					y--;
					//estoy en un borde? si
					if(this.bordeIzquierdo()) {
						direction = true;
						dir = true;
						this.valorEnemyShipDescent();
						x++;
						this.decEnemyShipDescent();
					}
					else {
						y--;
					}
				}
				else {
					direction = true;
					dir = true;
					this.decEnemyShipDescent();
					x++;
				}
			}
			else {
				if(this.NUM_ENEMYSHIP_DESCENT==0) {
					y++;
				/*	//estoy en un borde? si
					if(this.bordeDerecho()) {
						direction = true;
						dir = false;
						this.valorEnemyShipDescent();
						x++;
						this.decEnemyShipDescent();
					}
					else {
						y++;
					}
				}
				else {
					direction = true;
					dir = false;
					this.decEnemyShipDescent();
					x++;
				}
			}
		}*/
		// TODO Auto-generated method stub
		
	//}

	@Override
	public String toString() {
		return "R["+this.live+"]";
	}

}
