package Logic;

public enum Level {
	EASY(4,2,1,3,5),HARD(8,2,3,2,2),INSANE(8,4,5,1,1);
	private int numRegShip,numDesShip, velCiclo;
	private int frecShootDes, frecOvni;
	
	private Level(int size,int sizeD, int fc, int vel,int fo) {
		this.numRegShip = size;
		this.numDesShip = sizeD;
		this.frecShootDes = fc;
		this.velCiclo = vel;
		this.frecOvni = fo;
	}
	public int getNumRegularShip() {
		return this.numRegShip;
	}
	public int getNumDesShip() {
		return this.numDesShip;
	}
	public int getVelCicle() {
		return this.velCiclo;
	}
	public int getFrecOvni() {
		return this.frecOvni;
	}
	public int getFrecShootDes() {
		return this.frecShootDes;
	}
	public static Level select(String level) {
		if(level.equals("EASY")) return Level.EASY;
		else if(level.equals("HARD")) return Level.HARD;
		else if(level.equals("INSANE")) return Level.INSANE;
		return Level.EASY;
	}
}
