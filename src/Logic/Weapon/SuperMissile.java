package Logic.Weapon;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Util.FileContentsVerifier;

public class SuperMissile extends Weapon{
	public static int POINTS = 20;
	public SuperMissile(Game game, int x, int y, int dam) {
		super(game, x, y,dam);
		this.live=1;
		// TODO Auto-generated constructor stub
	}
	public SuperMissile() {
		super(null,-50,Weapon.aumenta,1);
		Weapon.sube();
	}
	@Override
	public boolean performAttack(GameObject other) {
		other.receiveMissileAttack(this.damage);
		onDelete();
		this.game.enableMissile();
		return true;
	}
	@Override public boolean receiveBombAttack(int damage) {
		onDelete();
		this.game.enableMissile();
		return true;
		}
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		live = 0;
		x = -50;
		y = -50;
		this.game.enableMissile();
	}

	@Override
	public void move() {
		
		this.x--;
		if(isOut()) {
			onDelete();
		}
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString() {
		return "!oo!";
	}
	@Override
	public String debug() {
		String deb ="";
		deb = "X;"+x+","+y+"\r\n";
		return deb;
				
	}
	@Override
	public GameObject parse(String stringFromFile, Game game2, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0)=='X'){
			//String lineFromFile = stringFromFile.substring(2);
			if(verifier.verifyWeaponString(stringFromFile, game2)) {
				String lineFromFile = stringFromFile.substring(2);
				String[] word = lineFromFile.split(";");
				String[] coords = word[0].split(",");
				x = Integer.parseInt(coords[0]);
				y = Integer.parseInt(coords[1]);
				live = 1;
				game = game2;
				return new SuperMissile(game2,x,y,1);
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
