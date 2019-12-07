package Logic;

public enum Direction {
	ARRIBA(1,0),
	ABAJO(-1,0),
	IZQUIERDA(0,-1),
	DERECHA(0,1),
	ARRIBA_DER(1,1),
	ARRIBA_IZQ(1,-1),
	ABAJO_DER(-1,1),
	ABAJO_IZQ(-1,-1);
	private int x;
	private int y;
	
	private Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getDirX() {
		return x;
	}
	public int getDirY() {
		return y;
	}
}
