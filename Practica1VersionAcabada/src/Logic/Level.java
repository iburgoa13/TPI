package Logic;

public enum Level {
	 
	EASY(4, 2, 0.2, 3, 0.5, 1), 
	HARD(8, 4, 0.3, 2, 0.2, 2),
	INSANE(12, 4, 0.5, 1, 0.1, 3);

	private int numRegularAliens;
	private int numDestroyerAliens;
	private int numCyclesToMoveOneCell;
	private double ovniFrequency;
	private double shootFrequency;
	private int numRowsOfRegularAliens;
	private Level(
			int numRegularAliens, 
			int numDestroyerAliens, 
			double shootFrequency, 
			int numCyclesToMoveOneCell, 
			double ovniFrequency, 
			int numRowsOfRegularAliens)
	{
		this.numRegularAliens = numRegularAliens;
		this.numDestroyerAliens = numDestroyerAliens;
		this.shootFrequency = shootFrequency;
		this.numCyclesToMoveOneCell = numCyclesToMoveOneCell;
		this.ovniFrequency = ovniFrequency;
		this.numRowsOfRegularAliens = numRowsOfRegularAliens;
	}
	

	public int getNumRegularAliens() {
		return numRegularAliens;
	}
	
	public int getNumDestroyerAliens() {
		return numDestroyerAliens;
	}

	public Double getShootFrequency() {
		return shootFrequency;
	}
	
	public int getNumCyclesToMoveOneCell() {
		return numCyclesToMoveOneCell;
	}

	public Double getOvniFrequency() { 
		return ovniFrequency;
	}
	public int getNumRowsOfRegularAliens() {
		return numRowsOfRegularAliens;
	}
	
	public int getNumRegularAliensPerRow() {
		return numRegularAliens / numRowsOfRegularAliens;
	}
	
	public int  getNumDestroyerAliensPerRow() {
		return getNumDestroyerAliens();
	}
	
	public static Level fromParam(String param) {
		for (Level level : Level.values())
			if (level.name().equalsIgnoreCase(param)) return level;
	    return null;
	}

	public int getTurnExplodeFreq(){
		return 5;//return 0.05;
	}
}

