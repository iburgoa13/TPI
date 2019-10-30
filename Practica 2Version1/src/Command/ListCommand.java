package Command;

import Logic.Game;

public class ListCommand extends Command{
	private final static String shortcut = "l";
	private final static String name = "list";
	private final static String help = "Prints the list of available ships.";
	private final static String details = "List";
	public ListCommand() {
		super(name,shortcut,details,help);
	}
	@Override
	public boolean execute(Game game) {
		System.out.println(game.list());
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
