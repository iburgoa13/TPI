package Logic;

import java.util.Random;


import Logic.List.BombList;
import Logic.List.DestroyerShipList;
import Logic.List.RegularShipList;
import Logic.Object.Ovni;
import Logic.Object.UCMShip;


public class Game {
	private GamePrinter gamePrinter;
	private Random rand;
	private Ovni ovni;
	private UCMShip ucmship;
	private RegularShipList regularList;
	private DestroyerShipList destroyerList;
	private BombList bombList;
	private Level level;
	private int ciclos;
	private int score;
	private boolean direction;
	private boolean win,lose;
	private int totalAliens;
	private boolean exit;
	/**
	 * 
	 * @param level Recibe el nivel por argumento, en caso de no recibirlo juega el modo EASY
	 * @param random Atributo de la clase Random de java
	 */
	public Game(Level level, Random random) {
		this.rand = random;
		this.level = level;
		reset();
	}
	/**
	 * Dependiendo del nivel seleccionado, crea una cantidad de naves u otras.
	 */
	public void selectLevel() {
		if(level.name().equals("EASY")) {
			destroyerList = new DestroyerShipList(level.getNumDesShip(),2,4,level.getFrecShootDes());
			regularList = new RegularShipList(level.getNumRegularShip(),1,3,level);
		}
		else if(level.name().equals("HARD")) {
			destroyerList = new DestroyerShipList(level.getNumDesShip(),3,4,level.getFrecShootDes());
			regularList = new RegularShipList(level.getNumRegularShip(),1,3,level);
		}
		else {
			destroyerList = new DestroyerShipList(level.getNumDesShip(),3,4,level.getFrecShootDes());
			regularList = new RegularShipList(level.getNumRegularShip(),1,4,level);
		}
	}
	/**
	 * Resetea el juego para inicializarlo desde cero.
	 */
	public void reset() {	
		selectLevel();
		bombList = new BombList(level.getNumDesShip());	
		ovni = new Ovni(level.getFrecOvni());
		ucmship = new UCMShip();
		gamePrinter = new GamePrinter(this,9,8);
		ciclos = 0;
		score = 0;
		direction = false;
		win = false;
		lose = false;
		exit = false;
		totalAliens = destroyerList.getSize() + regularList.getSize(); 
	}
	/**
	 * 
	 * @return El listado con los datos de las naves enemigas y del personaje.
	 */
	public String list() {
		return "[R]egular ship: Points: 5 - Harm: 0 - Shield: 2\r\n" + 
				"[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1\r\n" + 
				"[O]vni: Points: 25 - Harm: 0 - Shield: 1\r\n" + 
				"^__^: Harm: 1 - Shield: 3";
	}
	/**
	 * 
	 * @param i parametro de la fila
	 * @param j parametro de la columna
	 * @return devuelve el objeto dibujado que hay en la posicion (i,j) en caso de ser vacio devuelve ""
	 */
	public String getPositionObject(int i, int j) {	
		if(ucmship.getPosition(i, j)) return ucmship.toString();
		
		 if(!regularList.isEmpty() && regularList.getPosition(i, j)) {
			return regularList.dibuja(i,j);
		}
		 if(!destroyerList.isEmpty() && destroyerList.getPosition(i, j)) {
			 return destroyerList.dibuja(i, j);
		}	
		 if(!bombList.isEmpty() && bombList.getPosition(i, j)) {
			 return bombList.dibuja(i,j);
		 }
		 if(ovni.getPosition(i,j) && ovni.getEsta()) return ovni.toString();
		 if(ucmship.getPositionShoot(i, j)) return ucmship.UCMShipLasertToString();
		 return "";
	}
	/**
	 * Metodo que invoca al método ucmshipAtackDestroyer
	 */
	public void ucmshipAtackDestroyer() {
		destroyerList.ucmshipAtackDestroyer(ucmship,this);
	}
	/**
	 * 
	 * @param s el nuevo record
	 */
	public boolean isFinished() {
		return exit || win || lose;
	}
	public boolean getExit() {
		return exit;
	}
	public void setExit(boolean e) {
		exit = e;
	}
	
	public void setScore(int s) {
		score = s;
	}
	public void incScore(int sc) {
		this.score+= sc;
	}
	/**
	 * 
	 * @return Devuelve el record actual
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * 
	 * @param a el numero de aliens
	 */
	/*
	public void setNumAliens(int a) {
		this.totalAliens = a;
	}*/
	public void decNumAliens(){
		totalAliens = totalAliens - 1;
	}
	public int getNumAliens() {
		return this.totalAliens;
	}
	/**
	 * llama al metodo ucmshipAtackRegular
	 */
	public void ucmshipAtackRegular() {
		regularList.ucmshipAtackRegular(ucmship,this);	
	}
	/**
	 * Llama al metodo ucmshipAtackOvni
	 */
	public void ucmshipAtackOvni() {
		ovni.ucmshipAtackOvni(ucmship,this);
	}
	/**
	 * Llama al metodo ucmshipAtackBomb
	 */
	public void ucmshipAtackBomb() {
		bombList.ucmshipAtackBomb(ucmship,destroyerList,this);
	}
	/**
	 * Metodo que se encarga de todas las actualizaciones tras cada comando que modifique el juego
	 * Sigue los pasos dados en la práctica del update.
	 */
	public void atack() {
		if(!regularList.isEmpty()) {
			ucmshipAtackRegular();
		}
		if(!destroyerList.isEmpty()) {
			ucmshipAtackDestroyer();
		}
		if(!bombList.isEmpty()) {
			ucmshipAtackBomb();
		}
		if(ovni !=null) ucmshipAtackOvni();
	}
	/**
	 * Funcion que modifica el tablero tras cada movimiento del usuario
	 */
	public void update() {
		setCiclos(getCiclos()+1);

		if(ucmship.existShoot()) {
			ucmship.update();
			atack();
		}	
		destroyerList.existShoot(this.rand, this.bombList);
		ucmshipAtackBomb();
	
		if(!ovni.getEsta()) {
			ovni.apareceOvni(rand);
		}else {
			ovni.update();
			ucmshipAtackOvni();
		}
		moveShips();
		if(winPlayer()) {
			win = true;
		}
		if(losePlayer()) {
			lose = true;
		}
	}
	/**
	 * 
	 * @return devuelve si el jugador ha perdido
	 */
	public boolean getLose() {
		return lose;
	}
	/**
	 * D
	 * @return Devuelve si el jugador ha ganado la partida
	 */
	public boolean getWin() {
		return win;
	}
/**
 * 
 * @return devuelve si la lista de RegularList es vacia
 */
	public boolean isEmptyRegular() {
		if( this.regularList.isEmpty()) return true;
		return false;
	}
	/**
	 * 
	 * @return Devuelve si la lista de DestroyerList es vacia
	 */
	public boolean isEmptyDestroyer() {
		if(this.destroyerList.isEmpty()) return true;
		return false;
	}
/**
 * Funcion encargada de si cumple la condicion de los ciclos, mover cada nave
 */
	public void moveShips() {
		if(ciclos % level.getVelCicle()==0) {
			if(!regularList.isEmpty() && !destroyerList.isEmpty()) {
				moveGeneric();
			}
			else if(!destroyerList.isEmpty()) moveDestroyer();
			else if(!regularList.isEmpty())moveRegular();
		}
	}
	/**
	 * Funcion que llama a mover del destroyer
	 */
	public void moveDestroyer() {
		direction = destroyerList.moveDestroyer(direction, ucmship, this);
		
	}
	/**
	 * Funcion que llama a mover del regular
	 */
	public void moveRegular() {
		direction = regularList.moveRegular(direction, ucmship, this);
	}
	/**
	 * funcion que llama al generico en el caso que haya ambas naves
	 */
	public void moveGeneric() {
		direction = regularList.moveGeneric(direction, destroyerList,ucmship,this);
	}
	
	public void setCiclos(int c) {
		this.ciclos = c;
	}
	public int getCiclos() {
		return this.ciclos;
	}
	/**
	 * 
	 * @param dir direccion introducida por el usuario 1- derecha -1 - izquierda
	 * @param celdas el numero de celdas introducido por el usuario
	 * @return devuelve si es posible realizar dicho movimiento
	 */
	public boolean moveUCM(int dir,int celdas) {
		return ucmship.move(dir,celdas);
	}
	/**
	 * 
	 * @return devuelve si el usuario ha lanzado un disparo
	 */
	public boolean shootUCM() {
		return ucmship.shoot();
	}
	/**
	 * 
	 * @return devuelve true en caso de que en la partida no haya aliens en el mapa
	 */
	public boolean winPlayer() {
		return totalAliens == 0;
	}
	/**
	 * 
	 * @return devuelve cualquier caso de derrota del jugador
	 */
	public boolean losePlayer() {
		return (regularList.lose() || destroyerList.lose() || ucmship.lose());
	}
	/**
	 * 
	 * @return devuelve si es posible realizar el disparo especial shockWave
	 */
	public boolean shockWave() {
		if(ucmship.getSpecialShoot()) {
			destroyerList.shockWave(this);
			regularList.shockWave(this);
			ucmship.setSpecialShoot(false);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return devuelve la informacion que sale por pantalla del jugador todo el rato y se 
	 * va actualizando
	 */
	public String informationPlayer() {
		String sw = (ucmship.getSpecialShoot()) ?  "SI" : "NO";
		return "Life: " + ucmship.getResistance() +"\r\nNumber of cycles: " 
				+ ciclos +"\r\nPoints: " + score +"\r\nRemaining aliens: " + totalAliens 
				+ "\r\nshockWave: " + sw +"\r\n";
		
	}
	public String toString() {
		return informationPlayer() + gamePrinter.toString();
	}
	
	public void finalMessage() {
		if(exit) {
			System.out.println("*****GAME OVER*****");
		}
		else if(lose) {
			System.out.println("*****ALIENS WINS*****");
		}
		else if(win) {
			System.out.println("*****PLAYER WINS*****");
		}
	}
	
}
