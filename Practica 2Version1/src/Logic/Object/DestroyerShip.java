package Logic.Object;


public class DestroyerShip {
	private int damage;
	private int row;
	private int column;
	private Bomb projectile;
	private int resistance;
	private int ProbShoot;
	public static final int POINTS = 10;
	public static final int DAMAGE = 1;
	private boolean shoot;
	private int id;
	public DestroyerShip(int r, int c, int id, int fr) {
		this.damage = 1;
		this.resistance = 1;
		this.row = r;
		this.column = c;
		this.id = id;
		this.projectile = new Bomb(this.id);
		this.ProbShoot = fr;
	
		this.shoot = false;
	}

	public int getProb() {
		return this.ProbShoot;
	}
	public int getId() {
		return id;
	}
	public void setId(int i) {
		id = i;
	}
	public boolean getShoot() {
		return this.shoot;
	}
	public void setShoot(boolean f) {
		this.shoot = f;
	}
	public boolean getProjectile() {
		if (this.projectile.getShootShip()) return true;
		return false;
	}
	public void setProjectile(boolean pro, int r, int c) {
		this.projectile.setShootShip(pro);
		this.projectile.setRow(r);
		this.projectile.setColumn(c);
	}
	public void quitaVida(int damage) {
		resistance -= damage;
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
	public boolean getPosition(int i , int j) {
		return row == i && column == j;
	}
	//metodos get

	public int getProbShoot() {
		return this.ProbShoot;
	}
	public int getPoints() {
		return POINTS;
	}
	public int getDamage() {
		return this.damage;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public int getResistance() {
		return this.resistance;
	}
	public Bomb getBomb() {
		return this.projectile;
	}
	
	//metodos set

	public void setBomb(Bomb j) {
		 this.projectile = j;
	}
	public void setProbShoot(int f) {
		this.ProbShoot = f;
	}
	public void setDamage(int c) {
		this.damage = c;
	}
	public void setRow(int c) {
		this.row = c;
	}
	public void setColumn(int c) {
		this.column = c;
	}
	public void setResistance(int c) {
		this.resistance = c;
	}
	public String toString() {
		return "D["+this.resistance+"]";
	}
}
