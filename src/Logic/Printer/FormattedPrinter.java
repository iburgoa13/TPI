package Logic.Printer;

import Logic.Game.Game;
import Logic.Game.GameObjectBoard;
import Util.MyStringUtils;

public class FormattedPrinter extends GamePrinter{
	  Game game;
	  int numRows; 
	  int numCols;
	  String[][] board;
	  final String space = " ";
	  GameObjectBoard gob;
	  /**
	   * 
	   * @param game recibe el juego actual
	   * @param cols recibe las columnas del tablero
	   * @param rows recibe las filas del tablero
	   */
	  public FormattedPrinter (int cols, int rows) {		
	    this.numRows = rows;
	    this.numCols = cols;  
	    board = new String[numRows][numCols];
	    gob = new GameObjectBoard(cols, rows);
	  }
	  private void encodeGame(Game game) {
		  for(int i = 0; i < numRows;i++) {
		    	for(int j = 0; j < numCols; j++) {
		    			board[i][j] = game.positionToString(i, j);
		    	}
		  }
	
	  }
	@Override
	public String toString(Game game) {
		   encodeGame(game);

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
		    return game.infoToString() + str.toString();
	}

	

}
