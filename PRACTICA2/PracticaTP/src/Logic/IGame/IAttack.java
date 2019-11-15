package Logic.IGame;

import Logic.Game.GameObject;

public interface IAttack {
	/*
	 * devuelve false lo que quiere decir que el objeto de juego no
hace da�o (s�lo hacen da�o el misil, las bombas y el shockwave)
	 */
	default boolean performAttack(GameObject other) {return false;};
/*
 * puede recivir da�o del misil que son los ship enemys y las bombas
 */
	default boolean receiveMissileAttack(int  damage) {return false;};

	/*
	 * recive da�o de la bomba que son misil y ucmship
	 */
	default boolean receiveBombAttack(int damage) {return false;};
	/*
	 * naves enemigas todas
	 */
	default boolean receiveShockWaveAttack(int damage) {return false;};
	default boolean receiveExplosiveShipAttack(int damage) {return false;};
}

