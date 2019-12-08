package Command;

import Logic.Game.Game;
import Logic.Printer.PrinterType;

public class ListPrintersCommand extends Command{
	private final static String shortcut = "lp";
	private final static String name = "listPrinters";
	private final static String help = "Show the type of the board.";
	private final static String details = "ListPrinters";
	public ListPrintersCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		PrinterType.printerHelp(game);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])){
			if(commandWords.length==1) {
				return this;
			}
			return null;
		}
		return null;
	}

}
