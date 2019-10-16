package Logic.Object;

/**
 * Clase del objeto bala del ucmship
 * @author iker_
 *
 */

public class UCMShipLaser {
	private int row;
	private int column;
	private boolean shootUCM;
	
	public UCMShipLaser() {
		this.row = -50;
		this.column = -50;
		this.shootUCM = false;
		}
	
	public void update() {
		this.row-=1;
		if(row < 0) {
			column = -1;
			row = -1;
			shootUCM = false;
		}		
	}

	public boolean getPosition(int i, int j) {
		return i == row && j == column;
	}
	//metodos get
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public boolean getShootUCM() {
		return this.shootUCM;
	}
	
	//metodo set
	public void setRow(int r) {
		this.row = r;
	}
	public void setColumn(int c) {
		this.column = c;
	}
	public void setShootUCM(boolean s) {
		this.shootUCM = s;
	}
	public String toString() {
		return "oo";
	}
}
