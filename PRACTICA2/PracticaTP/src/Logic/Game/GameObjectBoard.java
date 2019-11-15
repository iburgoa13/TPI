package Logic.Game;

import Logic.Direction;
import Logic.Object.AlienShip;
import Logic.Object.DestroyerShip;
import Logic.Object.ExplosiveShip;
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
		//this.objects[i].onDelete();
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
	/*
	private void check() {
		GameObject o = null;
		for(int i = 0; i < Game.DIM_X;i++) {
			if(!dir) {
				o = this.getObjectInPosition(i, 0);
				if(o !=null &&
						(o.getClass()==RegularShip.class || o.getClass()==DestroyerShip.class
						|| o.getClass() ==ExplosiveShip.class)) {
					AlienShip.EnemyShipDescent();
					dir = !dir;
				}
			}
			else {
				o = this.getObjectInPosition(i, 8);
				if(o != null &&
						(o.getClass()==RegularShip.class || o.getClass()==DestroyerShip.class
						|| o.getClass() ==ExplosiveShip.class)) {
					AlienShip.EnemyShipDescent();
					dir = !dir;
				}
			}
		}
	}*/
	private void checkMove() {
		for(int i = 0; i < this.currentObjects; i++) {
			if(this.objects[i].getClass() == RegularShip.class ||
					this.objects[i].getClass() == DestroyerShip.class ||
						this.objects[i].getClass() == ExplosiveShip.class) {
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
			if(this.objects[i].isOnPosition(object.x, object.y) && !object.equals(this.objects[i])
					&&object.getClass()!=ExplosiveShip.class) {
				object.performAttack(this.objects[i]);
			}
			if(object.getClass()==shockWave.class) {
				object.performAttack(this.objects[i]);
			}
			/*
			if(object.getClass()==ExplosiveShip.class && !object.isAlive()) {
				for(Direction dir : Direction.values()) {
					int x = object.getX() + dir.getDirX();
					int y = object.getY() + dir.getDirY();
					GameObject p = this.getObjectInPosition(x, y);
					object.performAttack(p);
				}
			}*/
		}
	}
	
	public GameObject position(int x, int y) {
		return this.getObjectInPosition(x, y);
	}
	private void change(int i) {
		this.objects[i] = this.objects[this.currentObjects-1];
		this.objects[this.currentObjects-1] = null;
		this.currentObjects--;
	}
	public void computerAction() {
		for(int i = 0; i < this.currentObjects;i++) {
			this.objects[i].computerAction();
			if(this.objects[i].getLive()==-888) {
				change(i);
			}
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

	public void explosive(GameObject e) {
		for(Direction dir : Direction.values()) {
			int x = e.getX() + dir.getDirX();
			int y = e.getY() + dir.getDirY();
			GameObject p = this.getObjectInPosition(x, y);
			if(p!=null)e.performAttack(p);
		}
	}


}
