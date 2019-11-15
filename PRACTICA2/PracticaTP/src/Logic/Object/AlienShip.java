package Logic.Object;

import Logic.Game.Game;

public abstract class AlienShip extends EnemyShip{
	protected static boolean SUELO = false;
	protected static int NUM_ENEMYSHIP = 0;
	protected static int NUM_ENEMYSHIP_DESCENT = 0;
	protected static boolean direction;
	protected boolean dir;
	
	public AlienShip(Game game, int x, int y, int live,int points) {
		super(game, x, y, live,points);
		direction = false;
		dir = false;
		// TODO Auto-generated constructor stub
	}
	
	public boolean getDirection() {
		return direction;
	}
	public void setDir(boolean dir) {
		direction = dir;
	}
	public void incNumEnemyShip() {
		NUM_ENEMYSHIP++;
	}
	public void decNumEnemyShip() {
		NUM_ENEMYSHIP--;
	}
	public void valorEnemyShipDescent() {
		NUM_ENEMYSHIP_DESCENT = NUM_ENEMYSHIP;
	}
	public  static void EnemyShipDescent() {
		NUM_ENEMYSHIP_DESCENT = NUM_ENEMYSHIP;
	}
	public void decEnemyShipDescent() {
		NUM_ENEMYSHIP_DESCENT--;
	}
	public abstract void computerAction();
	public abstract void onDelete();
	public static void resetEnemy() {
		NUM_ENEMYSHIP_DESCENT=0;
		NUM_ENEMYSHIP = 0;
	}
	@Override
	public  void move() {
		if(game.getCycle()) {
			if(NUM_ENEMYSHIP_DESCENT==0) {
				if(!this.dir) {
					y--;
				}
				else {
					y++;
				}
			}
			else {
				dir = !dir;
				direction = !direction;
				x++;
				if(suelo()) {
					this.changeSuelo();
				}
				this.decEnemyShipDescent();
			}
		}

	}
	public abstract String toString();

	public static int getRemainingAliens() {
		return NUM_ENEMYSHIP;
	}
	public static int getTotalAliensDes() {
		return NUM_ENEMYSHIP_DESCENT;
	}
	public boolean bordeIzquierdo() {
		return y == 0;
	}
	public boolean bordeDerecho() {
		return y == 8;
	}
	public static boolean hanBajado() {
		if(direction)return true;
		return false;
	}
	public static void cambiaEstado() {
		direction = false;
	}

	public static boolean allDead() {
		return NUM_ENEMYSHIP==0;
	}
	public void changeSuelo() {
		SUELO = true;
	}
	public static boolean haveLanded() {
		return SUELO == true;
	}
	public boolean suelo() {
		return( x == Game.DIM_X-2) ;
	}
}
