package Command;


import Logic.Game.Game;

public class UpdateCommand extends Command{
	//CAMBIAR ESTO
	private final static String shortcut = "";
	private final static String name = "nose";
	private final static String help = "Skips one cycle.";
	private final static String details = "[none]";
	
	public UpdateCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
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