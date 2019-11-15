package Logic.Object;

import Logic.Game.Game;
import Logic.Weapon.UCMShipLaser;
import Logic.Weapon.shockWave;



/**
 * 
 * @author iker_
 *
 */

public class UCMShip extends Ship{
	private int score;
	private boolean shoot;
	//private UCMShipLaser ucmShipLaser;
	private boolean specialShoot;
	/**
	 * Constructora de UCMShip
	 */
	public UCMShip(Game game,int x, int y) {
		super(game,y,x,3);
		//this.ucmShipLaser = new UCMShipLaser(game,-50,-50,1);
		this.specialShoot = false;
		this.score = 0;
		this.shoot = false;
	}

	
	//default boolean receiveMissileAttack(int damage) {return false;};
	@Override 
	public boolean receiveBombAttack(int damage)
	{
		this.getDamage(damage);
		return true;
		}
	//default boolean receiveShockWaveAttack(int damage) {return false;};
	//Metodos get
	
	public void incScore(int points) {
		score+= points;
	}
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return devuelve true si hay el tiro especial shockWave
	 */
	public boolean getSpecialShoot() {
		return this.specialShoot;
	}
	
	
	
	/**
	 * 
	 * @return devuelve la bala del ucmship
	 */

	/**
	 * 
	 * @return devuelve el simbolo del disparo
	 */

	//metodos set
	public void setSpecialShoot(boolean s) {
		this.specialShoot = s;
	}
	
	public boolean move(int celdas) {
		y += celdas;
		return true;
	}
	/*
	public boolean move(int dir, int celdas) {
		if(dir==1) {
			if(column+celdas <=8) {
				this.column+=celdas;
				return true;
			}
			return false;
		}
		else {
			if(column-celdas >=0) {
				this.column-=celdas;
				return true;
			}
			return false;
		}
	}
	*/
	/**
	 * Si existe un disparo en el tablero, llamo al metodo update del ucmshiplaser para modificar el disparo una posicion
	 */
	/*public void update() {
		if(ucmShipLaser.getShootUCM()) {
			ucmShipLaser.update();	
		}
	}*/
	/**
	 * 
	 * @return devuelve true si no habia disparo y te lo crea y false si ya existe una bala en el tablero
	 *//*
	public boolean shoot() {
		if(!this.ucmShipLaser.getShootUCM()) {
			this.ucmShipLaser.setShootUCM(true);
			this.ucmShipLaser.setColumn(this.column);
			this.ucmShipLaser.setRow(this.row);
			return true;
		}
		return false;
	}*/
	/*

	public boolean getPosition(int i , int j) {
		return (row == i && column == j);
	}
*/

	
	/**
	 * 
	 * @return devuelve true si hay bala en el tablero
	 */
	public boolean existShoot() {
		return shoot == true;
	}
	/**
	 * en caso de que la vida llegue a cero , cambiamos la imagen del ucmship
	 */
	/*
	public String toString() {
		if (this.resistance > 0) return "^_^";
		else return "!xx!";
	}*/
	/**
	 * 
	 * @return devuelve true si el jugador se ha quedado sin vidas
	 */

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		live = 0;
	}

	@Override
	public void move() {
		//this.incY(this.posicion);
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		if (this.isAlive()) return "^_^";
		else return "!xx!";
	}
	public String stateToString() {
		String sw = this.specialShoot ?  "SI" : "NO";
		return "Life: " + this.getLive() +"\r\n"
				+ "Points: " + this.score
				+ "\r\nshockWave: " + sw +"\r\n";
	}
	public void special() {
		shockWave wave = new shockWave(this.game,-250,-250,1);
		this.game.addObject(wave);
		specialShoot = false;
	}
	public void shoot() {
			UCMShipLaser laser = new UCMShipLaser(this.game,this.x,this.y,1);
			shoot = true;
			this.game.addObject(laser);
	}
	public void enableMissile() {
		shoot = false;
	}
	public void enableShockWave() {
		specialShoot = true;
	}


}
