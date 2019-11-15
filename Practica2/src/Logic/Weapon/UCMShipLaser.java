package Logic.Weapon;

import Logic.Game.Game;
import Logic.Game.GameObject;

public class UCMShipLaser extends Weapon{
	private boolean shootUCM;
	
	
	public UCMShipLaser(Game game, int x , int y, int d) {
		super(game,x,y,d);
		shootUCM = false;
		this.live=1;
	}

/*	
	public void update() {
		this.row-=1;
		if(row < 0) {
			column = -1;
			row = -1;
			shootUCM = false;
		}		
	}


*/
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
	
	
	public boolean getShootUCM() {
		return this.shootUCM;
	}
	

	public void setShootUCM(boolean s) {
		this.shootUCM = s;
	}
	@Override
	public String toString() {
		return "oo";
	}

	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		live = 0;
		x = -50;
		y = -50;
		this.game.enableMissile();
		shootUCM = false;
	}

	@Override
	public void move() {
		
		this.x--;
		if(isOut()) {
			onDelete();
		}
		// TODO Auto-generated method stub
		
	}




	public void setLive(int i) {
		live = i;
	}
}