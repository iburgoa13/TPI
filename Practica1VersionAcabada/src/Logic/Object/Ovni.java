package Logic.Object;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Logic.IGame.IAttack;
import Logic.IGame.IExecuteRandomActions;
import Util.FileContentsVerifier;

public class Ovni extends EnemyShip implements IExecuteRandomActions, IAttack{
	private static final int POINTS = 25;
	private boolean enable;
	public Ovni(Game game, int x, int y, int live, int points) {
		super(game, x, y, live,points);
		enable = false;
		// TODO Auto-generated constructor stub
	}
	public Ovni() {
		super(null,-8,-8,-8,POINTS);
		enable = false;
	}
	@Override
	public boolean receiveMissileAttack(int  damage) {
		this.getDamage(damage);
		if(!this.isAlive()) {
			onDelete();
			this.game.receivePoints(POINTS);
			this.game.enableShockWave();
		}
		return true;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		if(IExecuteRandomActions.canGenerateRandomOvni(game) && !enable) {
			x = 0;
			enable = true;
			y = Game.DIM_Y+1;
		}
	}

	@Override
	public void onDelete() {
		this.live = 1;
		x = -87;
		y = -87;
		enable = false;
		
	}

	@Override
	public void move() {
		if(enable) {
			y--;
			if(this.isOut()) {
				onDelete();
			}
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "O["+ this.live +"]";
	}
	@Override
	public String debug() {
		String deb ="";
		deb = "O;"+x+","+y+";"+
		live+"\r\n";
		return deb;
				
	}
	@Override
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='O'){
		//	String lineFromFile = stringFromFile.substring(2);
			if(verifier.verifyOvniString(stringFromFile, game2, this.live)) {
				String lineFromFile = stringFromFile.substring(2);
				String[] word = lineFromFile.split(";");
				String[] coord = word[0].split(",");
				x = Integer.parseInt(coord[0]);
				y = Integer.parseInt(coord[1]);
				live = Integer.parseInt(word[1]);
				game = game2;
				if(x!=-87) enable = true;
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
