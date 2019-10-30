package Logic.Object;

import java.util.Random;

import Logic.Game;
/**
 * Clase del objeto ovni
 * @author iker_
 *
 */
public class Ovni {
	private int row, column;
	private int resistance;
	private int probabilidad;
	public static final int POINTS = 25;
	private int frecAparicion;
	private boolean esta;
	/**
	 * Constructora
	 * @param frec frecuencia con la que aparece el ovni
	 */
	public Ovni(int frec) {
		this.resistance = 1;
		this.row = -1;
		this.column= -1;
		this.frecAparicion = frec;
		this.esta = false;
	}
	
	public boolean getPosition(int i , int j) {
		return row == i && column == j;
	}
	

	public void setEsta(boolean f) {
		this.esta = f;
	}
	public void setColumn(int c) {
		this.column = c;
	}
	public void setFrec(int c) {
		this.frecAparicion = c;
	}
	public void setResistance(int r) {
		this.resistance = r;
	}
	public boolean getEsta() {
		return this.esta;
	}
	public int getFrecOvni() {
		return this.frecAparicion;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getRow() {
		return this.row;
	}
	public int getResistance() {
		return this.resistance;
	}
	
	public int getPoints() {
		return POINTS;
	}
	/**
	 * modifica la posicion del ovni en el tablero
	 */
	public void update() {
		column -= 1;
		if(column < 0) {
			column = -80;
			row = -80;
			esta = false;
		}
	}
	/**
	 * 
	 * @param ucmship el usuario
	 * @param game el juego actual
	 * la funcion consiste en comprobar si la bala del ucmship ha alcanzado al ovni, en este caso
	 * quitamos el ovni del tablero sumamos los puntos, le damos el disparo especial y quitamos la bala del jugador
	 */
	public void ucmshipAtackOvni(UCMShip ucmship, Game game) {
		if(ucmship.getPositionShoot(row, column)) {
			resistance = resistance - ucmship.getDamage();
			ucmship.setUCMShipLaser(new UCMShipLaser());
			game.incScore(POINTS);
			esta = false;
			ucmship.setSpecialShoot(true);
		}
	}
	/**
	 * 
	 * @param rand la semilla random
	 * Hace la probabilidad , segun el nivel de dificultad, de que aparezca un ovni o no en el tablero
	 */
	public void apareceOvni(Random rand) {
		probabilidad = rand.nextInt(10);
		if(probabilidad <= frecAparicion) {
			esta = true;
			row = 0 ;
			column = 8;
			resistance = 1;
		}
	}
	public String toString() {
		return "O["+ this.resistance +"]";
	}
}
