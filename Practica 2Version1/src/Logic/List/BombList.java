package Logic.List;

import Logic.Game;
import Logic.Object.Bomb;
import Logic.Object.DestroyerShip;
import Logic.Object.UCMShip;
import Logic.Object.UCMShipLaser;
/**
 * 
 * @author iker_
 *Clase de array de los objetos bomba de los destroyer
 *funciones similares a los destroyer y regular
 */

public class BombList {
	private Bomb[] bomb;
	private int size;
	
	public BombList(int tam) {
		this.bomb = new Bomb[tam];
		size = 0;
		int id = 1;
		for(int i = 0; i < tam;i++) {
			bomb[i] = new Bomb(id);
			id++;
		}
	}

	public String dibuja(int i , int j) {
		int x = 0;
		boolean encontrado = false;
		while(!encontrado) {
			if(bomb[x].getPosition(i, j)) encontrado = true;
			else x++;
		}
		return bomb[x].toString();
	}
	public void addBomb(Bomb bomb2) {
		bomb[size] = bomb2;
		size++;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean getPosition(int i , int j) {
		boolean dibujo = false;
		for(int x = 0; x < size;x++) {
			if(bomb[x].getPosition(i, j))
				dibujo = true;
		}
		return dibujo;
	}
	public void eliminar(int pos) {
		Bomb aux;

		for(int i = pos; i < size-1; i++) {
			aux = bomb[i+1];
			bomb[i+1] = bomb[i];
			bomb[i] = aux;
		}
		size--;
	}
	/**
	 * 
	 * @param destroyer array de naves
	 * @param x posicion
	 * elimina la bala de la nave destroyer que haya disparado dicha bala
	 */
	public void eliminaShootDestroyer(DestroyerShipList destroyer, int x) {
		int i = 0;
		int j = x;
		boolean encontrado = false;
		while(i < destroyer.getSize() && !encontrado) {
			if(destroyer.getDestroyerPosition(i).getId() == bomb[j].getId()) {
				destroyer.getDestroyerPosition(i).setProjectile(false, -1, -1);
				encontrado = true;
				
			}
			i++;
		}
	}
	public void update(DestroyerShipList destroyer, int j) {
		int i = 0;
		while(i < size) {
			if(bomb[i].getId()== destroyer.getDestroyerPosition(j).getId()) {
				bomb[i].setRow(bomb[i].getRow()+1);
				if(bomb[i].getRow()==8) {
					eliminaShootDestroyer(destroyer, i);
					bomb[i] = null;
					eliminar(i);			
				}
			}i++;
			
		}
	}

	
	public void ucmshipAtackBomb(UCMShip ucmship, DestroyerShipList destroyerList, Game game) {
		for(int i = 0; i < size; i++) {
			if(ucmship.getPositionShoot(bomb[i].getRow(), bomb[i].getColumn())) {
				ucmship.setUCMShipLaser(new UCMShipLaser());
				eliminaShootDestroyer(destroyerList,i);
				bomb[i] = null;
				eliminar(i);
			}
			else if(ucmship.getPosition(bomb[i].getRow(), bomb[i].getColumn())) {
				ucmship.setUCMShipLaser(new UCMShipLaser());
				eliminaShootDestroyer(destroyerList,i);
				bomb[i] = null;
				eliminar(i);
				ucmship.setResistance(ucmship.getResistance()-DestroyerShip.DAMAGE);
				
			}

		}
		
	}
/**
 * 
 * @param destroyerShip el array de naves destroyer
 * @return devuelve si hay alguna bala donde su nave ya ha sido destruida
 */
	public boolean existShootWithOutDestroyer(DestroyerShip destroyerShip) {
		int i = 0;
		boolean encontrado = false;
		while(i < size && !encontrado) {
			if(destroyerShip.getId() == bomb[i].getId()) encontrado = true;
			i++;
		}
		return encontrado;
	}

	public void updateSpecial() {
		int i = 0;
		while(i < size) {
			
				bomb[i].setRow(bomb[i].getRow()+1);
				if(bomb[i].getRow()==8) {
					
					bomb[i] = null;
					eliminar(i);			
				}
			i++;
			
		}
	}

	

	
	
}
