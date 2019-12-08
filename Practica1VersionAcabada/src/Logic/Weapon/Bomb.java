package Logic.Weapon;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Util.FileContentsVerifier;

public class Bomb extends Weapon{

	public Bomb(Game game, int x, int y, int dam) {
		super(game, x, y, dam);
		live = 1;
		// TODO Auto-generated constructor stub
	}

	public Bomb() {
		super(null,-50,Weapon.aumenta,1);
		Weapon.sube();
	}

	public boolean performAttack(GameObject other) {
		other.receiveBombAttack(damage);
		onDelete();
		this.game.enableMissile();
		return true;
	}
	@Override
	public boolean receiveMissileAttack(int damage) {
		onDelete();
		return true;
	}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		live = 0;
		x = -888;
		y = -888;
	}

	@Override
	public void move() {
		x++;
		if(x==8)onDelete();
		/*if(this.isOut()) {
			onDelete();
		}*/
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ".";
	}
	@Override
	public String debug() {
		String deb ="";
		deb = "B;"+x+","+y+"\r\n";
		return deb;
				
	}

	@Override
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='B'){
			//String lineFromFile = stringFromFile.substring(2);
			if(verifier.verifyWeaponString(stringFromFile, game2)) {
				String lineFromFile = stringFromFile.substring(2);
				String[] word = lineFromFile.split(";");
				String[] coords = word[0].split(",");
				x = Integer.parseInt(coords[0]);
				y = Integer.parseInt(coords[1]);
				live = 1;
				
				game = game2;
			//	this.incNumEnemyShip();
				return new Bomb(game2,x,y,1);
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
