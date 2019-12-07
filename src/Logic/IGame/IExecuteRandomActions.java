package Logic.IGame;

import Logic.Game.Game;

public interface IExecuteRandomActions {
	
	static boolean canGenerateRandomOvni(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getOvniFrequency();
	}
	
	static boolean  canGenerateRandomBomb(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	static boolean canGenerateRandomExplosive(Game game) {
		return game.getRandom().nextInt(100) < game.getLevel().getTurnExplodeFreq();
	}
	
}
