package Logic.Object;
/**
 * 
 * @author iker_
 *Clase del objeto bomba, la bala del destroyer
 */
public class Bomb {
	private int row;
	private int column;
	private boolean shootShip;
	private int id;
	public Bomb(int id) {
		this.row = -1;
		this.column = -1;
		this.shootShip= false;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		id = i;
	}
	//metodos get
	public boolean getPosition(int i , int j) {
		return row == i && column == j;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public boolean getShootShip() {
		return this.shootShip;
	}
	
	
	public void setRow(int r) {
		this.row = r;
	}
	public void setColumn(int c) {
		this.column = c;
	}
	public void setShootShip(boolean s) {
		this.shootShip = s;
	}
	public String toString() {
		return ".";
	}
}
