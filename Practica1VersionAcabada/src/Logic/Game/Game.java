package Logic.Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import Exceptions.CommandExecuteException;
import Exceptions.FileContentsException;
import Exceptions.InvalidGameException;
import Logic.Level;
import Logic.IGame.IPlayerController;
import Logic.Object.AlienShip;
import Logic.Object.UCMShip;
import Logic.Printer.PrinterType;
import Util.FileContentsVerifier;

public class Game implements IPlayerController{
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;
	private int currentCycle;
	private Random rand;
	private Level level;
	private GameState state;
	GameObjectBoard board;

	private UCMShip player;
	
	private boolean doExit;
	private BoardInitializer initializer;
	
	public Game (Level level, Random random){
		state = null;	
		this.rand = random;
		this.level = level;
		this.doExit= false;
		initializer = new BoardInitializer();
//		printer = new GamePrinter(this, DIM_X, DIM_Y);
		initGame();
		//state = new GameState(this, board.getObject());
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
	public void newPlayer(UCMShip u) {
		player = u;
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
//		printer = new GamePrinter(this, DIM_X, DIM_Y);
		initGame();
	}
	public boolean isOnBoard(int x, int y) {
		//return x >= 0 && y >= 0 && x < DIM_X && y < DIM_Y;
		return x >= 0 && y >= 0 && x <= DIM_X && y <= DIM_Y;
	}
	public boolean aliensWin() {
		return !player.isAlive() || AlienShip.haveLanded();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}
	public int getCycleTotal() {
		return this.currentCycle;
	}
	public boolean getCycle() {
		return (this.currentCycle % this.level.getNumCyclesToMoveOneCell()==0) && this.currentCycle!=0;
	}
	public String toString() {
		return this.infoToString();// + this.printer.toString();
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
	public boolean move(int numCells) throws CommandExecuteException {
		player.move(numCells);		
		return true;
	}

	@Override
	public boolean shootLaser() throws CommandExecuteException {
		player.shoot();
		return true;
	}
	/*public boolean shootLaser() {
		if(!player.existShoot()) {
			this.enableMissile();
			player.shoot();
			
			return true;
		}
		return false;
	}*/

	@Override
	public boolean shockWave() throws CommandExecuteException  {
		player.special();
		return true;
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
	public boolean supermisil() throws CommandExecuteException {
		player.shootSuperMissil();
		return true;
	}
	public boolean buyMissile() throws CommandExecuteException {
		player.paySuperMissil();
		return true;
	}
	public void Explosive(GameObject e)  {
		this.board.explosive(e);
	}
	public String list() {
		return board.list();
	}

	public String debug() {
		return this.board.debug();
	}
	public void save(BufferedWriter bw) throws IOException{
		String s = PrinterType.SERIALIZER.getObject(this).toString(this);//getObject().toString(this);
		/*for(int i = 0; i < s.length(); i++) {
			bw.write(s.charAt(i));
		}*/
		bw.write(s);
		bw.close();
	}
	public Game guardaCopia() {
		Game g = new Game(this.level,this.rand);
		g.board = this.board.guardaDatos();
		g.currentCycle = this.currentCycle;
		g.doExit = this.doExit;
		g.initializer = this.initializer;
		g.level = this.level;
		g.player = this.player;
		g.rand = this.rand;
		return g;
	}
	public void recuperaDatos(Game g) {
		//this.board.recuperaDatos(g.board);
		this.board = g.board;
		this.currentCycle = g.currentCycle;
		this.doExit = g.doExit;
		this.initializer = g.initializer;
		this.level = g.level;
		this.player = g.player;
		this.rand = g.rand;
	}
	public GameState getState() {
		Game copia = new Game(this.level,this.rand);
		copia = guardaCopia();
		return new GameState(copia,board.getState());
	}
	public void setState(GameState st) {
		recuperaDatos(st.getFGame());
		this.board.recuperaJuego(st.getGOB());
	}
	public boolean miraNumero(char x) {
		return x =='1' || x =='2' || x == '3' || x == '4' || x== '5'||
				x == '6' || x == '7' || x == '8' || x == '9' || x =='0';
	}
	public void comprueba(String lin) throws InvalidGameException {
		String[] p = lin.split("---");
		if(p.length!=2) {
			throw new InvalidGameException("Juego no valido");
		}
		String[] p1 = p[1].split("Space Invaders v");
		if(p1.length !=2) {
			throw new InvalidGameException("El juego no existe, juego corrompido");
		}
		try {
			String x = p1[1];
			if(!miraNumero(x.charAt(0)) || !miraNumero(x.charAt(2))) {
				throw new InvalidGameException("Juego no valido");
			}
			if(p1[1].charAt(1)!='.') {
				throw new InvalidGameException("Juego no valido");
			}
		}
		catch(NumberFormatException e) {
			throw new InvalidGameException("Versiones invalidas del juego");
		}
	}
	public void read(BufferedReader br) throws IOException, FileContentsException, InvalidGameException {
		try {
			String lin = br.readLine();
			comprueba(lin);
			this.state = getState();
			String[] words = br.readLine().trim().split(";");
			if(FileContentsVerifier.verifyCurrentCycle(Integer.parseInt(words[2]))) {
				this.currentCycle = Integer.parseInt(words[2]);
			}
			
			if(FileContentsVerifier.verifyLevel(Level.fromParam(words[1]))) {
				this.level = Level.fromParam(words[1]);
			}
			
			AlienShip.resetEnemy();
			this.board.read(br,this);
			//AlienShip.resetEnemy();
		}
		catch(IOException | FileContentsException|NumberFormatException  e) {
			setState(state);
			//throw new FileContentsException(e.getMessage());
			throw new FileContentsException("Juego corrompido, imposible cargar la partida");
			
		}
		// TODO Auto-generated method stub
		
	}
}

