package Command;

import Logic.Game.Game;
import Logic.Printer.PrinterType;

public class StringifyCommand extends Command{
	private final static String shortcut = "stringify";
	private final static String name = "stringify";
	private final static String help = "Prints the board serializable.";
	private final static String details = "Stringify ";
	
	public StringifyCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(PrinterType.SERIALIZER.getObject(game).toString(game));
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
