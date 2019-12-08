package Logic.Object;

import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Game.GameObject;
import Logic.Weapon.Bomb;
import Logic.Weapon.SuperMissile;
import Logic.Weapon.UCMShipLaser;
import Util.FileContentsVerifier;

public class GameObjectGenerator {
	private static GameObject[] availableGameObjects = {
		new UCMShip(),
		new Ovni(),
		new RegularShip(),
		new DestroyerShip(),
		new ExplosiveShip(),
		//new shockWave(null, 0, 0, 0),
		new Bomb(),
		new UCMShipLaser(),
		new SuperMissile()
		};
	public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)
			throws FileContentsException {		
			GameObject gameObject = null;
				for (GameObject go: availableGameObjects) {
						gameObject = go.parse(stringFromFile, game, verifier);
						if (gameObject != null) break;
				}
				return gameObject;
			}

}
