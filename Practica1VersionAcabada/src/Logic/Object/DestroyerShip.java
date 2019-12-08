package Logic.Object;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Logic.IGame.IExecuteRandomActions;
import Logic.Weapon.Bomb;
import Util.FileContentsVerifier;

public class DestroyerShip extends AlienShip{
	private static final int POINTS = 10;
	private Bomb bomb;
	private boolean shoot;
	public DestroyerShip(Game game, int x, int y, int live) {
		super(game, x, y, live, POINTS);
		bomb = null;
		shoot = false;
		// TODO Auto-generated constructor stub
	}
	public DestroyerShip(Game game2, int x, int y, int live, boolean dire) {
		super(game2, x, y, live, POINTS);
		this.dir = dire;
		this.direction = dire;
		this.direccion = dire;
	}
	public DestroyerShip() {
		super(null,AlienShip.NUM_ALIEN,-50,0,POINTS);
		AlienShip.aumenta();
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		return true;
	}
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		return true;
	}
	@Override
	public boolean receiveExplosiveShipAttack(int damage) {
		this.getDamage(damage);
		if(!this.isAlive()) onDelete();
		return true;
	}
	
	@Override
	public void computerAction() {
		if(IExecuteRandomActions.canGenerateRandomBomb(game) && !shoot) {
			bomb = new Bomb(game, x, y,1);
			game.addObject(bomb);
			shoot = true;
		}
		if(shoot) {
			if(!this.bomb.isAlive()) {
				shoot = false;
				bomb = null;
			}
		}
	}
	
	@Override
	public void onDelete() {
		this.decNumEnemyShip();
		this.game.receivePoints(POINTS);
	}


	@Override
	public String toString() {
		return "D["+this.live+"]";
	}
	/*@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}*/
	@Override
	public String debug() {
		String mov;
		if(this.dir)mov ="right";
		else mov = "left";
		String deb ="";
		deb = "D;"+x+","+y+";"+
		live+";"+this.game.getLevel().getNumCyclesToMoveOneCell()+";"+
				mov+"\r\n";
		return deb;
				
	}
	@Override
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='D'){
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
				return new DestroyerShip(game2,x,y,live,dir);
			}
			else {
				throw new FileContentsException("Juego corrompido, imposible cargar la partida");
			}
		}
		return null;
		
	}


}
