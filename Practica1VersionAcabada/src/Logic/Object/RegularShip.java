package Logic.Object;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Logic.IGame.IExecuteRandomActions;
import Util.FileContentsVerifier;
public class RegularShip extends AlienShip{
	private static final int POINTS = 5;
	public RegularShip(Game game, int x, int y, int live) {
		super(game, x, y, live, POINTS);
		// TODO Auto-generated constructor stub
	}
	public RegularShip() {
		super(null,AlienShip.NUM_ALIEN,-50,0,POINTS);
		AlienShip.aumenta();
	}

	public RegularShip(Game game2, int x, int y, int live, boolean dire) {
		super(game2, x, y, live, POINTS);
		this.dir = dire;
		this.direction = dire;
		this.direccion = dire;
	}
	@Override
	public boolean performAttack(GameObject other) {
		return false;
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
		if(IExecuteRandomActions.canGenerateRandomExplosive(game)) {
			ExplosiveShip e = new ExplosiveShip(game,x,y,live,this.dir,this.pared);
			e.dir = this.dir;
			this.game.addObject(e);
			this.live = -888;
		}
	}

	@Override
	public void onDelete() {
		this.decNumEnemyShip();
		this.game.receivePoints(POINTS);
	}

	//@Override
	//public void move() {
		/*if(game.getCycle() % game.getLevel().getNumCyclesToMoveOneCell() == 0 && game.getCycle()!=0) {
			if(!this.dir) {
				//tengo que bajar? no
				if(this.NUM_ENEMYSHIP_DESCENT==0) {
					y--;
					//estoy en un borde? si
					if(this.bordeIzquierdo()) {
						direction = true;
						dir = true;
						this.valorEnemyShipDescent();
						x++;
						this.decEnemyShipDescent();
					}
					else {
						y--;
					}
				}
				else {
					direction = true;
					dir = true;
					this.decEnemyShipDescent();
					x++;
				}
			}
			else {
				if(this.NUM_ENEMYSHIP_DESCENT==0) {
					y++;
				/*	//estoy en un borde? si
					if(this.bordeDerecho()) {
						direction = true;
						dir = false;
						this.valorEnemyShipDescent();
						x++;
						this.decEnemyShipDescent();
					}
					else {
						y++;
					}
				}
				else {
					direction = true;
					dir = false;
					this.decEnemyShipDescent();
					x++;
				}
			}
		}*/
		// TODO Auto-generated method stub
		
	//}

	@Override
	public String toString() {
		return "R["+this.live+"]";
	}
	@Override
	public String debug() {
		String mov;
		if(this.dir)mov ="right";
		else mov = "left";
		String deb ="";
		deb = "R;"+x+","+y+";"+
		live+";"+this.game.getLevel().getNumCyclesToMoveOneCell()+";"+
				mov+"\r\n";
		return deb;
				
	}
	@Override
	//R;x,y;live;cyclesNextAlienMove;dir
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='R'){
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
				return new RegularShip(game2,x,y,live,dir);
			}
			else {
				throw new FileContentsException("Juego corrompido, imposible cargar la partida");
			}
		}
		return null;
		
	}

}
