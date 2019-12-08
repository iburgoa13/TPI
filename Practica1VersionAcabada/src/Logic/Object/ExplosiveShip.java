package Logic.Object;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Util.FileContentsVerifier;

public class ExplosiveShip extends AlienShip{
	public static int POINTS = 5;
	public ExplosiveShip(Game game, int x, int y, int live) {
		super(game, x, y, live, POINTS);
		// TODO Auto-generated constructor stub
	}
	public ExplosiveShip(Game game2, int x, int y, int live, boolean dire, boolean paredes) {
		super(game2, x, y, live, POINTS);
		this.dir = dire;
		this.direction = dire;
		this.direccion = dire;
		pared = paredes;
	}
	public ExplosiveShip() {
		super(null,AlienShip.NUM_ALIEN,-50,0,POINTS);
		AlienShip.aumenta();
	}
	@Override
	public boolean performAttack(GameObject other) {
		if(other.isAlive())other.receiveExplosiveShipAttack(1);
		return true;
	}
	@Override
	public boolean receiveExplosiveShipAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		//onDelete();
		return true;
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		//onDelete();
		return true;
	}
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		//onDelete();
		return true;
	}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDelete() {
		game.receivePoints(POINTS);
		this.decNumEnemyShip();
		this.game.Explosive(this);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "E["+ live + "]";
	}
	@Override
	public String debug() {
		String mov;
		if(this.dir)mov ="right";
		else mov = "left";
		String deb ="";
		deb = "E;"+x+","+y+";"+
		live+";"+this.game.getLevel().getNumCyclesToMoveOneCell()+";"+
			mov+"\r\n";
		return deb;
				
	}
	@Override
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='E'){
			//String lineFromFile = stringFromFile.substring(2);
			if(verifier.verifyAlienShipString(stringFromFile, game2, 0)) {
				String lineFromFile = stringFromFile.substring(2);
				String[] word = lineFromFile.split(";");
				String[] coords = word[0].split(",");
				x = Integer.parseInt(coords[0]);
				y = Integer.parseInt(coords[1]);
				live = Integer.parseInt(word[1]);
				if(word[3].equals("left"))dir= false;
				else dir = true;
				
				game = game2;
				this.incNumEnemyShip();
				return new ExplosiveShip(game2,x,y,live,dir,pared);
			}
			else {
				throw new FileContentsException("Juego corrompido, imposible cargar la partida");
			}
		}
		return null;
		
	}


}
