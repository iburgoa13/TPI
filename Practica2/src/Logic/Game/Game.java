package Logic.Game;

import java.util.Random;

import Logic.Level;
import Logic.IGame.IPlayerController;
import Logic.Object.AlienShip;
import Logic.Object.UCMShip;

public class Game implements IPlayerController{
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;

	private int currentCycle;
	private Random rand;
	private Level level;

	GameObjectBoard board;
	private GamePrinter printer;

	private UCMShip player;
	
	private boolean doExit;
	private BoardInitializer initializer;
	
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		this.doExit= false;
		initializer = new BoardInitializer();
		printer = new GamePrinter(this, DIM_X, DIM_Y);
		initGame();
	}
	public void exit() {
		doExit = true;
	}
	public void initGame () {
		currentCycle = 0;	
		board = initializer.initialize(this, level);
		player = new UCMShip(this,  DIM_X / 2 ,DIM_Y-1);
		board.add(player);
	}

	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void reset() {
		AlienShip.resetEnemy();
		initializer = new BoardInitializer();
		printer = new GamePrinter(this, DIM_X, DIM_Y);
		initGame();
	}
	public boolean isOnBoard(int x, int y) {
		return x >= 0 && y >= 0 && x <= DIM_X && y <= DIM_Y;
	}
	public boolean aliensWin() {
		return !player.isAlive() || AlienShip.haveLanded();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}

	public boolean getCycle() {
		return (this.currentCycle % this.level.getNumCyclesToMoveOneCell()==0) && this.currentCycle!=0;
	}
	public String toString() {
		return this.infoToString() + this.printer.toString();
	}
	public boolean isFinished() {
		return  aliensWin() || doExit || playerWin();
		
	}
	public void update() {
		board.computerAction();
		currentCycle += 1;
		board.update();
		
	}
	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" +
			player.stateToString() +
			"Remaining aliens: " + (AlienShip.getRemainingAliens()) + "\n";
	}
	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}
	private boolean playerWin () {
		return AlienShip.allDead();
	}
	
/*
	
	
	public boolean aliensWin() {
		return !player.isAlive() || AlienShip.haveLanded();
	}
	
	private boolean playerWin () {
		return AlienShip.allDead();
	}
	
	public void update() {
		board.computerAction();
		board.update();
		currentCycle += 1;
	}
	
	
	
	
	
	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" +
			player.stateToString() +
			"Remaining aliens: " + (AlienShip.getRemainingAliens()) + "\n"; 
	}
	
	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}
	*/
	// TODO implementar los métodos del interfaz IPlayerController

	@Override
	public boolean move(int numCells) {
		if(this.isOnBoard(player.getX(),player.getY()+numCells)){
			player.move(numCells);
			//player.move();
			return true;
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shootLaser() {
		if(!player.existShoot()) {
			this.enableMissile();
			player.shoot();
			
			return true;
		}
		return false;
	}

	@Override
	public boolean shockWave() {
		if(player.getSpecialShoot()) {
			this.player.special();
			return true;
		}
		else player.setSpecialShoot(false);

		return false;
	}

	@Override
	public void receivePoints(int points) {
		player.incScore(points);
	}
	
	@Override
	public void enableShockWave() {
		if(!player.getSpecialShoot())player.enableShockWave();
	}

	@Override
	public void enableMissile() {
		player.enableMissile();		
	}
	public String positionToString(int i, int j) {
		if( board.position(i, j)==null) return " ";
		else return board.position(i, j).toString();

	}
}

