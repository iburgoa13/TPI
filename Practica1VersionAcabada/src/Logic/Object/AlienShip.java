package Logic.Object;

import Logic.Game.Game;

public abstract class AlienShip extends EnemyShip{
	protected static boolean SUELO = false;
	protected static int NUM_ENEMYSHIP = 0;
	protected static int NUM_ALIEN = 0;
	protected static int NUM_ENEMYSHIP_DESCENT = 0;
	protected static boolean HAY_PARED = false;
	protected  boolean direction;
	protected boolean  dir;
	protected boolean pared;
	
	public AlienShip(Game game, int x, int y, int live,int points) {
		super(game, x, y, live,points);
		direction = false;
		dir = false;
		pared = false;
		// TODO Auto-generated constructor stub
	}
	/*public static boolean dimeDireecion() 
	{return direction;}*/
	public boolean getDirection() {
		return direction;
	}
/*	public static void cambiaDireccion(boolean x) {
		direction = x;
	}*/
	public static void aumenta() {
		NUM_ALIEN++;
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
	public static void sumaEnemy() {
		NUM_ENEMYSHIP = NUM_ENEMYSHIP+1;
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
	public boolean  getDireccion() {
		return direction;
	}
	public static void chancePared() {
		HAY_PARED = !HAY_PARED;
	}

	
	@Override
	public  void move() {
		if(game.getCycle()) {
			if(NUM_ENEMYSHIP_DESCENT==0) {
				if(!dir) {
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

	public abstract String debug();
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
	/*public static boolean hanBajado() {
		if(direction)return true;
		return false;
	}
	public static void cambiaEstado() {
		direction = false;
	}*/

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
