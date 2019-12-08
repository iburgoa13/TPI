package Command;

import Logic.Game.Game;

public class HelpCommand extends Command{
	private final static String shortcut = "h";
	private final static String name = "help";
	private final static String help = "Prints this help message.";
	private final static String details = "Help";
	public HelpCommand() {
		super(name,shortcut,details,help);
	}
	@Override
	public boolean execute(Game game) {
		CommandGenerator.commandHelp();
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
