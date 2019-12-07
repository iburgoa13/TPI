package Logic.Printer;

import Logic.Game.Game;

public enum PrinterType {
	BOARDPRINTER("boardprinter", "prints the game formatted as a board of dimension: ", new FormattedPrinter(Game.DIM_X,Game.DIM_Y)),
	SERIALIZER("serializer", "prints the game as plain text", new Stringified
			());
	
	private String printerName;
	private String helpText;
	private GamePrinter printerObject;
	
	private PrinterType(String name, String text, GamePrinter printer) {
		printerName = name;
		helpText = text;
		printerObject = printer;
	}
	
	public static String printerHelp(Game game) {
		String helpString = "";
		for (PrinterType printer : PrinterType.values())
			helpString += String.format("%s : %s%s%n", printer.printerName, printer.helpText,
						(printer == BOARDPRINTER ? Game.DIM_X + " x " + Game.DIM_Y : "") );
		return helpString;
	}
	
	// Assumes a max of one object of each printer type is needed (otherwise need to return a copy)
	public GamePrinter getObject(Game game) {
		//printerObject.setGame(game);
		return printerObject;
	}/*
	BOARDPRINTER("boardprinter", "prints the game formatted as a board of dimension: ",
			new FormattedPrinter(Game.DIM_X,Game.DIM_Y)),
	SERIALIZER("serializer", "prints the game as plain text", new Stringified
	());
	private String printerName;
	private String helpText;
	private GamePrinter printerObject;
	private PrinterType(String name, String text, GamePrinter printer) {
	printerName = name;
	helpText = text;
	printerObject = printer;
	}
	public static String printerHelp(Game game) {
	String helpString = "";
	for (PrinterType printer : PrinterType.values()){
		helpString += String.format(" %s : %s %s %n", printer.printerName, printer.helpText,
				(printer == BOARDPRINTER ? Game.DIM_X + " x " + Game.DIM_Y : ""
						) );
		//System.out.println(helpString);
		}
	System.out.println(helpString);
		return helpString;
	}
	
	public GamePrinter getObject() {
		return printerObject;
	}*/
}
