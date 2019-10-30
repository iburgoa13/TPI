package Logic.List;

import java.util.Random;

import Logic.Game;
import Logic.Object.DestroyerShip;
import Logic.Object.UCMShip;
import Logic.Object.UCMShipLaser;
/**
 * 
 * @author iker_
 *Clase de los objetos destroyer en un array
 *las funciones son semejantes en regularShip y algunas importantes vienen explicadas alli
 */
public class DestroyerShipList {
	private DestroyerShip[] destroyer;
	private int size;
	private boolean direction;
	private int id;
	/**
	 * 
	 * @param size numero de destroyer en tablero
	 * @param i fila
	 * @param j columna
	 * @param frec frecuencia de disparo
	 */
	public DestroyerShipList(int size, int i , int j,int frec) {
		this.size = 0;
		 id = 1;
		destroyer = new DestroyerShip[size];
		for(int x = 0; x < size; x++) {
			destroyer[x] = new DestroyerShip(i,j,id,frec);
			id++;
			j++;
			this.size++;
		}
		direction = false;
	}
	public void setDir(boolean e) {
		direction = e;
	}
	public boolean getDir() {return direction;}
	
	public DestroyerShip getDestroyerPosition(int pos) {
		return destroyer[pos];
	}
	public void eliminar(int pos) {
		DestroyerShip aux;
		for(int i = pos; i < size-1; i++) {
			aux = destroyer[i+1];
			destroyer[i+1] = destroyer[i];
			destroyer[i] = aux;
		}
		size--;
	}
	public void ucmshipAtackDestroyer(UCMShip ucmship, Game game) {
		for(int i = 0; i < size; i++) {
			if(ucmship.getPositionShoot(destroyer[i].getRow(),destroyer[i].getColumn())) {
				destroyer[i].quitaVida(ucmship.getDamage());
				ucmship.setUCMShipLaser(new UCMShipLaser());
				if(destroyer[i].getResistance()<=0) {
					game.incScore(destroyer[i].getPoints());
					game.decNumAliens();
					destroyer[i] = null;
					eliminar(i);
				}
			}
		}
		
	}
	public boolean existShootWithOutDestroyer(BombList bomblist) {
		int i = 0;
		boolean encontrado = false;
		while(i < size && !encontrado) {
			encontrado = bomblist.existShootWithOutDestroyer(destroyer[i]);
			i++;
		}
		return encontrado;
	}
	public void existShoot(Random rand,BombList bomblist) {
		int p;
		for(int i = 0; i < size;i++) {
			p = rand.nextInt(10);
			if(!destroyer[i].getProjectile() && destroyer[i].getProb() >= p) {
				shootDestroyer(i, bomblist);
			}
			else if(destroyer[i].getProjectile()) bomblist.update(this, i);
			
		}
		if(!existShootWithOutDestroyer(bomblist)) bomblist.updateSpecial();
	}
	public void shootDestroyer(int i,BombList bombList) {
		destroyer[i].setProjectile(true,destroyer[i].getRow()+1,destroyer[i].getColumn());
		destroyer[i].setShoot(true);
		bombList.addBomb(destroyer[i].getBomb());
	}
	
	
	public void moveDown(int down) {
		for(int i = 0; i < size; i++) {
			destroyer[i].setRow(destroyer[i].getRow()+down);
		}
	}
	public void move(int mov) {
		for(int i = 0; i < size; i++) {
			destroyer[i].setColumn(destroyer[i].getColumn()+mov);
		}
	}
	public boolean getDirection() {
		return this.direction;
	}
	public boolean getDestroyerBordeIzquierdo() {
		int i = 0;
		while(i < size) {
			if( destroyer[i].bordeIzquierda()) return true;
			i++;
		}
		return false;
	}
	public boolean loseGround() {
		int i = 0;
		while(i < size) {
			if(destroyer[i].getRow()==7)return true;
			i++;
		}
		return false;
	}
	public boolean lose() {
		return size > 0 && loseGround();
	}
	public boolean moveDestroyer(boolean direction, UCMShip ucmship, Game game) {
		if(!direction) {
			if(getDestroyerBordeIzquierdo()) {
				moveDown(1);
				if(ucmship.existShoot())ucmshipAtackDestroyer(ucmship, game);
				return true;
			}
			else {
				move(-1);
				if(ucmship.existShoot())ucmshipAtackDestroyer(ucmship, game);
				return false;
			}
		}
		else {
			if(getDestroyerBordeDerecha()) {
				moveDown(1);
				if(ucmship.existShoot())ucmshipAtackDestroyer(ucmship, game);
				return false;
			}
			else {
				move(1);
				if(ucmship.existShoot())ucmshipAtackDestroyer(ucmship, game);
				return true;
			}
		}
	}
	public boolean getDestroyerBordeDerecha() {
		int i = 0;
		while(i < size) {
			if(destroyer[i].bordeDerecha()) return true;
			i++;
		//else if(destroyer[3]!= null && destroyer[3].bordeDerecha())return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}
	public int getSize() {
		return size;
	}
	public String dibuja(int i , int j) {
		int x = 0;
		boolean encontrado = false;
		while(!encontrado) {
			if(destroyer[x].getPosition(i, j)) encontrado = true;
			else x++;
		}
		return destroyer[x].toString();
	}
	public boolean getPosition(int i , int j) {
		boolean dibujo = false;
		for(int x = 0; x < size;x++) {
			if(destroyer[x].getPosition(i, j))
				dibujo = true;
		}
		return dibujo;
	}
	public String toString(int pos) {
		return destroyer[pos].toString();
	}

	public void shockWave(Game game) {
		int i = 0;
		while(i < size) {
			destroyer[i].setResistance(destroyer[i].getResistance()-1);
			if(destroyer[i].getResistance()<=0) {
				game.incScore(destroyer[i].getPoints());
				game.decNumAliens();
				destroyer[i] = null;
				eliminar(i);
			}else i++;
		}
	}
}
