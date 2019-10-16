package Logic.List;


import Logic.Game;
import Logic.Level;
import Logic.Object.RegularShip;
import Logic.Object.UCMShip;
import Logic.Object.UCMShipLaser;

public class RegularShipList {
	private RegularShip[] regular;
	private int size;
	private boolean dir;
	private boolean direction;
	/**
	 * 
	 * @param num numero de regular en tablero
	 * @param row fila
	 * @param column columna
	 * @param level nivel de dificultad
	 */
	public RegularShipList(int num, int row, int column, Level level){
		regular = new RegularShip[num];
		size = 0;
		dir = false;
		int row1 = row+1;
		int column1 = column;
		if(level.name().equals("EASY")) {
			for(int i = 0; i < num ; i++) {
				regular[i] = new RegularShip(row,column);
				column++;
				size++;
			}
		}
		else {
			for(int i = 0; i < num ; i++) {
				if(i < num/2) {
					regular[i] = new RegularShip(row,column);
					column++;
				}
				else {
					regular[i] = new RegularShip(row1,column1);
					column1++;
				}
				size++;
			}
		}
		direction = false;
	}
	public boolean getDir() {
		return dir;
	}
	/**
	 * 
	 * @param pos posicion del array donde hemos eliminado y actualizamos el array
	 */
	public void eliminar(int pos) {
		RegularShip aux;
		for(int i = pos; i < size-1; i++) {
			aux = regular[i+1];
			regular[i+1] = regular[i];
			regular[i] = aux;
		}
		size--;
	}
	public void setDir(boolean e) {
		dir = e;
	}
/**
 * 
 * @param ucmship el jugador
 * @param game el juego actual
 * Comprobamos si alguna de las naves del array ha sido alcanzado por la bala del ucmship
 * en caso de ser positivo , descontamos una vida a esa nave, si ha llegado a cero de vida, la eliminamos del tablero
 * sumamos los puntos al jugador, y quitamos la bala
 */
	public void ucmshipAtackRegular(UCMShip ucmship,  Game game) {
		int i = 0;
		while(i < size){
			if(ucmship.getPositionShoot(regular[i].getRow(), regular[i].getColumn())) {
				regular[i].quitaVida(ucmship.getDamage());
				ucmship.setUCMShipLaser(new UCMShipLaser());
				if(regular[i].getResistance()<=0) {
					game.setScore(game.getScore()+regular[i].getPoints());					
					game.setNumAliens(game.getNumAliens()-1);
					regular[i] = null;
					eliminar(i);
				}else i++;
			}else i++;
		}
	}
	/**
	 * mueve hacia abajo
	 */
	public void moveDown(int down) {
		for(int i = 0; i < size; i++) {
			regular[i].setRow(regular[i].getRow()+down);
		}
	}
	public void move(int mov) {
		for(int i = 0; i < size; i++) {
			regular[i].setColumn(regular[i].getColumn()+mov);
		}
	}
	
	
	public boolean getDirection() {
		return this.direction;
	}
	public RegularShip getRegular(int i) {
		return regular[i];
	}
	public boolean getRegularBordeDerecho() {
		int i = 0;
		while ( i < size) {
			if(regular[i].bordeDerecha()) return true;
			i++;
		}
		return false;
	}
	/**
	 * 
	 * @return devuelve si alguna nave esta en el suelo, a nivel del ucmship
	 */
	public boolean loseGround() {
		int i = 0;
		while (i < size) {
			if(regular[i].getRow()==7)return true;
			i++;
		}
		return false;
	}
	/**
	 * 
	 * @return devuelve si el jugador ha perdido por tocar el suelo las naves
	 */
	public boolean lose() {
		return size > 0 && loseGround();
	}
	/**
	 * 
	 * @param direction hacia donde se mueven las naves
	 * @param destroyerList la lista de destroyer
	 * @param ucm el jugador
	 * @param game el juego actual
	 * @return la direccion nueva
	 * Es una funcion generica donde solo la uso cuando en pista haya los dos tipos de naves
	 */
	public boolean moveGeneric(boolean direction, DestroyerShipList destroyerList, UCMShip ucm, Game game) {
		if(!direction) {
			if(destroyerList.getDestroyerBordeIzquierdo() || getRegularBordeIzquierdo()) {
				destroyerList.moveDown(1);
				moveDown(1);
				if(ucm.existShoot()) destroyerList.ucmshipAtackDestroyer(ucm, game);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return true;
			}
			else {
				move(-1);
				destroyerList.move(-1);
				if(ucm.existShoot())destroyerList.ucmshipAtackDestroyer(ucm, game);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return false;
			}
		}
		else {
			if(destroyerList.getDestroyerBordeDerecha() || 
					getRegularBordeDerecho()) {
				destroyerList.moveDown(1);
				moveDown(1);
				if(ucm.existShoot())destroyerList.ucmshipAtackDestroyer(ucm, game);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return false;
			}
			else {
				move(1);
				destroyerList.move(1);
				if(ucm.existShoot())destroyerList.ucmshipAtackDestroyer(ucm, game);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return true;
			}
		}
	}
	/**
	 * 
	 * @param direction direccion de las naves
	 * @param ucm jugador
	 * @param game juego actual
	 * @return la nueva direccion
	 */
	public boolean moveRegular(boolean direction, UCMShip ucm, Game game) {
		if(!direction) {
			if(getRegularBordeIzquierdo()) {
				moveDown(1);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return  true;
			}
			else {
				move(-1);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return false;
			}
		}
		else {
			if(getRegularBordeDerecho()) {
				moveDown(1);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return false;
			}
			else {
				move(1);
				if(ucm.existShoot())ucmshipAtackRegular(ucm, game);
				return true;
			}
		}
	}
	public boolean getRegularBordeIzquierdo() {
		int i = 0;
		while(i < size) {
			if(regular[i].bordeIzquierda()) return true;
			i++;
		}
		return false;
	}
	public int getSize() {
		return this.size;
	}
	public boolean isEmpty() {
		return this.size==0;
	}
	public String dibuja(int i , int j) {
		int x = 0;
		boolean encontrado = false;
		while(!encontrado) {
			if(regular[x].getPosition(i, j)) encontrado = true;
			else x++;
		}
		return regular[x].toString();
	}
	public boolean getPosition(int i , int j) {
		boolean dibujo = false;
		for(int x = 0; x < size;x++) {
			if(regular[x].getPosition(i, j))
				dibujo = true;
		}
		return dibujo;
	}
	public String toString(int pos) {
		return regular[pos].toString();
	}
	public void shockWave(Game game) {
		int i = 0;
		while(i < size) {
			regular[i].setResistance(regular[i].getResistance()-1);
			if(regular[i].getResistance()<=0) {
				game.setScore(game.getScore()+regular[i].getPoints());
				game.setNumAliens(game.getNumAliens()-1);
				regular[i] = null;
				eliminar(i);				
			}else i++;

		}
	}
	
}
