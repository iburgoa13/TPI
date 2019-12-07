package Logic.Game;

import Logic.Level;
import Logic.Object.DestroyerShip;
import Logic.Object.Ovni;
import Logic.Object.RegularShip;

public class BoardInitializer {
	private Level level;
	private GameObjectBoard board;
	private Game game;
	int position;
	int numRow;
	public  GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
		numRow = 1;//level.getNumRowsOfRegularAliens();
		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return board;
	}
	
	private void initializeOvni () {
		Ovni ovni = new Ovni(game,-87,-87,1,25);
		board.add(ovni);
	}

	private void initializeRegularAliens () {
		// TODO implement
		position = (Game.DIM_X/2)-1;
		int total = level.getNumRegularAliens()/4;
		for(int i = 0; i < total;i++) {
			inserta(position);
			position = (Game.DIM_X/2)-1;
			numRow++;
		}
	}
	private void inserta(int p) {
		
		for(int i = 0; i < 4;i++) {
			RegularShip regular = new RegularShip(game,numRow,p,1);
			regular.incNumEnemyShip();
			p++;
			board.add(regular);
			
		}
	}
	private void initializeDestroyerAliens() {
		if(level.getNumDestroyerAliens()==2) {
			position = (Game.DIM_X/2);
		}
		else position = (Game.DIM_X/2)-1;
		insertaD(position);
		numRow++;
	
	}

	private void insertaD(int position2) {
		for(int i = 0; i < level.getNumDestroyerAliens();i++) {
			DestroyerShip destroyer = new DestroyerShip(game,numRow,position2,2);
			destroyer.incNumEnemyShip();
			position2++;
			board.add(destroyer);
			
		}
	}

}
