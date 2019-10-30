package Command;

import Logic.Game;

public class ResetCommand extends Command{
	private final static String shortcut = "r";
	private final static String name = "reset";
	private final static String help = "Starts a new game.";
	private final static String details = "Reset";
	
	public ResetCommand() {
		super(name,shortcut,details,help);
	}
	
	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
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
