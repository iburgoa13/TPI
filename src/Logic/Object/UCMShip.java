package Logic.Object;

import Exceptions.CommandExecuteException;
import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Logic.Weapon.SuperMissile;
import Logic.Weapon.UCMShipLaser;
import Logic.Weapon.shockWave;
import Util.FileContentsVerifier;



/**
 * 
 * @author iker_
 *
 */

public class UCMShip extends Ship{
	private int score;
	private boolean shoot;
	//private UCMShipLaser ucmShipLase
	private int numSuperMissile;
	private boolean specialShoot;
	/**
	 * Constructora de UCMShip
	 */
	public UCMShip(Game game,int x, int y) {
		super(game,y,x,3);
		this.specialShoot = false;
		this.numSuperMissile = 0;
		this.score = 0;
		this.shoot = false;
	}
	
	public UCMShip() {
		super(null,-1,-1,0);
		this.specialShoot = false;
		this.numSuperMissile = 0;
		this.score = 0;
		this.shoot = false;
	}

	public int getSuperMissile() {
		return this.numSuperMissile;
	}
	public void incSupMis() {
		this.numSuperMissile++;
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
	
	public boolean move(int celdas) throws CommandExecuteException {
		if(y+celdas < 0 || y + celdas >8) {
			throw new CommandExecuteException("Movimiento invalido por el tamaño del tablero");
		}
		y += celdas;
		return true;
	}

	
	/**
	 * 
	 * @return devuelve true si hay bala en el tablero
	 */
	public boolean existShoot() {
		return shoot == true;
	}

	@Override
	public void onDelete() {
		live = 0;
	}



	@Override
	public String toString() {
		if (this.isAlive()) return "^_^";
		else return "!xx!";
	}
	public String stateToString() {
		String sw = this.specialShoot ?  "SI" : "NO";
		return "Life: " + this.getLive() +"\r\n"
				+ "Points: " + this.score + "\r\n"
				+ "SuperMisiles:" + this.numSuperMissile 
				+ "\r\nshockWave: " + sw +"\r\n";
	}
	public void special() throws CommandExecuteException  {
		if(specialShoot) {
			shockWave wave = new shockWave(this.game,-250,-250,1);
			this.game.addObject(wave);
			specialShoot = false;
		}
		else {
			throw new CommandExecuteException("ShockWave no disponible, elimine el Ovni para poder usarlo");
		}
	}
	public void shoot() throws CommandExecuteException {
		if(!shoot) {
			UCMShipLaser laser = new UCMShipLaser(this.game,this.x,this.y,1);
			shoot = true;
			this.game.addObject(laser);
		}
		else {
			throw new CommandExecuteException("Misil o Supermisil en el tablero, no puedes disparar hasta que desaparezca");
		}
	}
	public void enableMissile() {
		shoot = false;
	}
	public void enableShockWave() {
		specialShoot = true;
	}


	public boolean payShoot() {
		return this.score - SuperMissile.POINTS >=0;
	}
	public void paySuperMissil() throws CommandExecuteException {
		if(payShoot()) {
			decScore();
			incSupMis();
		}
		else {
			throw new CommandExecuteException("No dispone de puntos suficientes para poder comprar un supermisil.");
		}
	}

	public void shootSuperMissil() throws CommandExecuteException {
		if(!shoot) {
			if(this.numSuperMissile>0) {
				shoot = true;
				SuperMissile misil = new SuperMissile(this.game,this.x,this.y,2);
				this.numSuperMissile--;
				this.game.addObject(misil);
			}
			else {
				throw new CommandExecuteException("No dispone de supermisiles en este momento, comprelos por "+ SuperMissile.POINTS+" puntos.");
			}
		}
		else {
			throw new CommandExecuteException("Misil o Supermisil en el tablero, no puedes disparar hasta que desaparezca");
		}
		
		
	}

	public void decScore() {
		score = score - SuperMissile.POINTS;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

//P;x,y;live;points;superpower;missiles

	@Override
	public String debug() {
		String sw;
		if(this.specialShoot)sw = "true";
		else sw = "false";
		return "P;"+x+","+y+";"+live+";"+this.score+";"+sw+";"+this.numSuperMissile+"\r\n";
	}

	@Override
	//P;7;4;2;false;0
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='P'){
			String lineFromFile = stringFromFile.substring(2);
			if(verifier.verifyPlayerString(lineFromFile, game2, live)) {
				String[] word = lineFromFile.split(";");
				String[] coords = word[0].split(",");
				x = Integer.parseInt(coords[0]);
				y = Integer.parseInt(coords[1]);
				live = Integer.parseInt(word[1]);
				score = Integer.parseInt(word[2]);
				if(word[3].equals("false")) this.specialShoot = false;
				else this.specialShoot = true;
				game = game2;
				this.numSuperMissile = Integer.parseInt(word[4]);
				game2.newPlayer(this);
				return this;
			}
			else {
				throw new FileContentsException("Juego corrompido, imposible cargar la partida");
			}
		}
		return null;
		
	}

	@Override
	public boolean getDireccion() {
		// TODO Auto-generated method stub
		return false;
	}


}
