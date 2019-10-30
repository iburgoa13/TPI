package Logic.Object;
/**
 * 
 * @author iker_
 *Clase del objeto regularShip
 */
public class RegularShip {
	private int row;
	private int column;
	private int resistance;
	public static final int POINTS = 5;
	/**
	 * Constructora
	 * @param r fila
	 * @param c columna
	 */
	public RegularShip(int r, int c) {
		this.row = r;
		this.column = c;
		this.resistance = 2;
	}
	
	public void quitaVida(int damage) {
		resistance-=damage;
	}
	public boolean bordeIzquierda() {
		return column == 0 ;
	}
	public boolean bordeDerecha() {
		return column == 8 ;
	}
	public void move(int n) {
		column+=n;
	}
	public void moveDown(int n) {
		row+=n;
	}
	//metodos get
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public int getResistance() {
		return this.resistance;
	}
	public int getPoints() {
		return POINTS;
	}
	//metodos set
	public void setRow(int r) {
		this.row = r;
	}
	public void setColumn(int c) {
		this.column = c;
	}
	public void setResistance(int r) {
		this.resistance = r;
	}
	public boolean getPosition(int i , int j) {
		return (row == i && column == j);
	}
	public String toString() {
		return "R["+ this.resistance +"]";
	}
}
