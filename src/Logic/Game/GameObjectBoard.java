package Logic.Game;

import java.io.BufferedReader;
import java.io.IOException;

import Exceptions.CommandExecuteException;
import Exceptions.FileContentsException;
import Logic.Direction;
import Logic.Object.AlienShip;
import Logic.Object.DestroyerShip;
import Logic.Object.ExplosiveShip;
import Logic.Object.GameObjectGenerator;
import Logic.Object.RegularShip;
import Logic.Object.UCMShip;
import Logic.Weapon.shockWave;
import Util.FileContentsVerifier;

public class GameObjectBoard {
	private GameObject[] objects;
	private boolean dir;
	private int currentObjects;
	private String list ="Command > list\r\n" + 
			"[R]egular ship: Points: 5 - Harm: 0 - Shield: 2\r\n" + 
			"[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1\r\n" + 
			"[O]vni: Points: 25 - Harm: 0 - Shield: 1\r\n" + 
			"[E]xplosive ship: Points: 5 - Harm: 1 - Shield: 2 \r\n"+
			"^__^: Harm: 1 - Shield: 3";
	public GameObjectBoard (int width, int height) {
		// TODO implement
		currentObjects = 0; 
		objects = new GameObject[width*height];
		dir = false;
	}
	public GameObject[] getObject() {
		return objects;
	}
	public GameObjectBoard guardaDatos() {
		GameObjectBoard g = new GameObjectBoard(Game.DIM_X,Game.DIM_Y);
		g.currentObjects = this.currentObjects;
		g.dir = this.dir ;
		g.objects = this.objects;
		return g;
	}
	public void recuperaDatos(GameObjectBoard g) {
		this.currentObjects = g.currentObjects;
		this.dir = g.dir;
		for(int i = 0; i < g.currentObjects; i++) {
			this.objects[i] = g.objects[i];
		}
	}
	public int getCurrentObjects() {
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
	 * @throws CommandExecuteException 
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
	public String list() {
		return list;
	}
	public String debug() {
		String debug ="";
		for(int i = 0; i < this.currentObjects;i++) {
			if(objects[i].getClass()!= shockWave.class) {
				debug+= objects[i].debug();
			}
		}
		return debug;
	}
	private void direccion() {
		if(currentObjects>=1) {
			if(this.objects[currentObjects-1].getClass() == RegularShip.class ||
					this.objects[currentObjects-1].getClass() == DestroyerShip.class ||
						this.objects[currentObjects-1].getClass() == ExplosiveShip.class) {
				if(this.objects[currentObjects-1].dimeDireccion()) dir = true;
				else dir = false;
			}
		}
	}
	public void read(BufferedReader br,Game game) throws IOException, FileContentsException {
		FileContentsVerifier verifier = new FileContentsVerifier();
		String words = br.readLine();
		this.currentObjects=0;
		while(words != null) {
			GameObject g = null;
			g = GameObjectGenerator.parse(words, game, verifier);
			add(g);
			direccion();
			words = br.readLine();
		}
	}
	public void borra() {
		this.currentObjects=0;
	}
	public GameObjectBoard getState() {
		GameObjectBoard gob = new GameObjectBoard(Game.DIM_X,Game.DIM_Y);
		for(int i = 0; i < this.currentObjects; i++) {
			gob.objects[i] = this.objects[i];
		}
		gob.currentObjects = this.currentObjects;
		gob.dir = this.dir;
		return gob;
	}
	public void recuperaJuego(GameObjectBoard gob) {
		AlienShip.resetEnemy();
		for(int i = 0; i < gob.currentObjects; i++) {
			this.objects[i] = gob.objects[i];
			if(this.objects[i].getClass() == RegularShip.class ||
					this.objects[i].getClass() == DestroyerShip.class ||
						this.objects[i].getClass() == ExplosiveShip.class) {
				AlienShip.sumaEnemy();
			}
		}
		this.currentObjects = gob.currentObjects;
		this.dir = gob.dir;
	}

}
