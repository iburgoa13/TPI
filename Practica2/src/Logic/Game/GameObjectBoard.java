package Logic.Game;

import Logic.Object.AlienShip;
import Logic.Object.DestroyerShip;
import Logic.Object.RegularShip;
import Logic.Object.UCMShip;
import Logic.Weapon.shockWave;

public class GameObjectBoard {
	private GameObject[] objects;
	private boolean dir;
	private int currentObjects;
	public GameObjectBoard (int width, int height) {
		// TODO implement
		currentObjects = 0; 
		objects = new GameObject[width*height];
		dir = false;
	}
	
	private int getCurrentObjects() {
		return currentObjects;
	}
	
	public void add (GameObject object) {
		objects[currentObjects] = object;
		this.currentObjects++;
	}
	
	private GameObject getObjectInPosition (int x, int y) {
		GameObject object = null;
		int c = getIndex(x,y);
		if(c!=-1) {
			object = objects[c];
		}
		return object;
	}

	private int getIndex(int x, int y) {
		int i = 0;
		boolean objetoEncontrado = false;
		while(i < getCurrentObjects() && !objetoEncontrado) {
			if(objects[i].isOnPosition(x, y)) {
				return i;
			}
			 i++;
		}
		return -1;
	}

	private void remove (int i) {
		this.objects[i].onDelete();
		this.objects[this.currentObjects] = null;
		if(this.currentObjects!=0) {
			while(i < this.currentObjects) {
				this.objects[i] = this.objects[i+1];
				i++;
			}
			this.currentObjects--;
			
		}
	}

	private boolean heTocadoParedIzq(GameObject o) {
		return o.isOnPosition(o.x, 0);
	}
	private boolean heTocadoParedDer(GameObject o) {
		return o.isOnPosition(o.x, 8);
	}
	private void checkMove() {
		for(int i = 0; i < this.currentObjects; i++) {
			if(this.objects[i].getClass() == RegularShip.class ||
					this.objects[i].getClass() == DestroyerShip.class) {
				if(!dir) {
					if(heTocadoParedIzq(this.objects[i])) {
						AlienShip.EnemyShipDescent();
						dir = !dir;
					}
				}
				else {
					if(heTocadoParedDer(this.objects[i])) {
						AlienShip.EnemyShipDescent();
						dir = !dir;
					}
				}
			}
					
		}
	}

	/**
	 * dim x/2 - n/2 es la pos inicial
	 */
	public void update() {
		checkMove();
		for(int i = 0 ; i < this.currentObjects; i++) {		
			this.objects[i].move();			
			checkAttacks(this.objects[i]);			
		}
		this.removeDead();

	}
	
	



	private void checkAttacks(GameObject object) {
		for(int i = 0; i < this.currentObjects;i++) {
			if(this.objects[i].isOnPosition(object.x, object.y) && !object.equals(this.objects[i])) {
				object.performAttack(this.objects[i]);
			}
			if(object.getClass()==shockWave.class) {
				object.performAttack(this.objects[i]);
			}
		}
	}
	
	public GameObject position(int x, int y) {
		return this.getObjectInPosition(x, y);
	}
	public void computerAction() {
		for(int i = 0; i < this.currentObjects;i++) {
			this.objects[i].computerAction();
		}
	}
	
	public void removeDead() {
		int i = 0;
		while(i < this.currentObjects) {
			if(!this.objects[i].isAlive() && this.objects[i].getClass()!=UCMShip.class) {
				remove(i);
			}
			else i++;
		}
		
	}


}
