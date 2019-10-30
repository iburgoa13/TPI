package Logic.Object;
/**
 * 
 * @author iker_
 *
 */

public class UCMShip {
	private int row;
	private int column;
	private int resistance;
	private UCMShipLaser ucmShipLaser;
	private int damage;
	private boolean specialShoot;
	/**
	 * Constructora de UCMShip
	 */
	public UCMShip() {
		this.row = 7;
		this.column = 4;
		this.resistance = 3;
		this.damage = 1;
		this.ucmShipLaser = new UCMShipLaser();
		this.specialShoot = false;
	}
	//Metodos get
	/**
	 * 
	 * @return devuelve la fila
	 */
	public int getRow() {
		return this.row;
	}
	/**
	 * 
	 * @return devuelve la columna de la posicion del disparo del ucmship
	 */
	public int getUCMShipLaserColumn() {
		return this.ucmShipLaser.getColumn();
	}
	/**
	 * 
	 * @return devuelve la fila de la posicion del disparo del ucmship
	 */
	public int getUCMShipLaserRow() {
		return this.ucmShipLaser.getRow();
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
	 * @return devuelve la posicion de la columna del ucmship
	 */
	public int getColumn() {
		return this.column;
	}
	/**
	 * 
	 * @return devuelve la vida actual del ucmship
	 */
	public int getResistance() {
		return this.resistance;
	}
	/**
	 * 
	 * @return devuelve el daño que realiza el ucmship por cada bala
	 */
	public int getDamage() {
		return this.damage;
	}
	/**
	 * 
	 * @return devuelve la bala del ucmship
	 */
	public UCMShipLaser getUCMShipLaser() {
		return this.ucmShipLaser;
	}
	/**
	 * 
	 * @return devuelve el simbolo del disparo
	 */
	public String UCMShipLasertToString() {
		return ucmShipLaser.toString();
	}
	//metodos set
	public void setSpecialShoot(boolean s) {
		this.specialShoot = s;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
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
	/**
	 * Si existe un disparo en el tablero, llamo al metodo update del ucmshiplaser para modificar el disparo una posicion
	 */
	public void update() {
		if(ucmShipLaser.getShootUCM()) {
			ucmShipLaser.update();	
		}
	}
	/**
	 * 
	 * @return devuelve true si no habia disparo y te lo crea y false si ya existe una bala en el tablero
	 */
	public boolean shoot() {
		if(!this.ucmShipLaser.getShootUCM()) {
			this.ucmShipLaser.setShootUCM(true);
			this.ucmShipLaser.setColumn(this.column);
			this.ucmShipLaser.setRow(this.row);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param i fila
	 * @param j columna
	 * @return devuelve si el ucmship se encuentra en la posicion (i,j)
	 */
	public boolean getPosition(int i , int j) {
		return (row == i && column == j);
	}

	public void setUCMShipLaser(UCMShipLaser ucm) {
		this.ucmShipLaser = ucm;
	}
	/**
	 * 
	 * @param i fila
	 * @param j columna
	 * @return devuelve true si la bala del ucmship esta en la posicion (i,j)
	 */
	public boolean getPositionShoot(int i , int j) {
		return ucmShipLaser.getPosition(i,j);
	}
	/**
	 * 
	 * @return devuelve true si hay bala en el tablero
	 */
	public boolean existShoot() {
		return ucmShipLaser.getShootUCM();
	}
	/**
	 * en caso de que la vida llegue a cero , cambiamos la imagen del ucmship
	 */
	public String toString() {
		if (this.resistance > 0) return "^_^";
		else return "!xx!";
	}
	/**
	 * 
	 * @return devuelve true si el jugador se ha quedado sin vidas
	 */
	public boolean lose() {
		return resistance == 0;
	}
}
