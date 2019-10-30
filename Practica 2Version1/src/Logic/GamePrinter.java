package Logic;

import Util.MyStringUtils;
/**
 * 
 * @author iker_
 *@version 1.0
 *
 */
public class GamePrinter {
	  Game game;
	  int numRows; 
	  int numCols;
	  String[][] board;
	  final String space = " ";
	  int regular;
	  int destroyer;
	  int bomb;
	  /**
	   * 
	   * @param game recibe el juego actual
	   * @param cols recibe las columnas del tablero
	   * @param rows recibe las filas del tablero
	   */
	  public GamePrinter (Game game, int cols, int rows) {
	    this.game = game;
	    this.numRows = rows;
	    this.numCols = cols;  
	    this.regular = 0;
	    this.bomb = 0;
	    this.destroyer=0;
	    board = new String[numRows][numCols];
	  }
	  /**
	   * Funcion encargada de guardar en el tablero todos los elementos
	   */
	  private void encodeGame() {
		  for(int i = 0; i < numRows;i++) {
		    	for(int j = 0; j < numCols; j++) {
		    		board[i][j] = game.getPositionObject(i, j);
		    	}
		  }
	
	  }
	  /**
	   * Funcion encargada de dibujar el tablero
	   */
	  public String toString() {
	    encodeGame();

	    int cellSize = 7;
	    int marginSize = 2;
	    String vDelimiter = "|";
	    String hDelimiter = "-";	    
	    String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
	    String margin = MyStringUtils.repeat(space, marginSize);
	    String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
	    
	    StringBuilder str = new StringBuilder();
	    
	    str.append(lineDelimiter);
	    
	    for(int i=0; i<numRows; i++) {
	        str.append(margin).append(vDelimiter);
	        for (int j=0; j<numCols; j++) {
	          str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
	        }
	        str.append(lineDelimiter);
	    }
	    return str.toString();
	  }
}
