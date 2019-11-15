package Command;

import Logic.Game.Game;

public class ExitCommand extends Command {
	private final static String shortcut = "e";
	private final static String name = "exit";
	private final static String help = "Prints this help message.";
	private final static String details = "Exit ";
	
	public ExitCommand() {
		super(name,shortcut,details,help);
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
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