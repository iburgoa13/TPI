package Command;

import Logic.Game.Game;

public class ShockWaveCommand extends Command{
	private final static String shortcut = "w";
	private final static String name = "shockwave";
	private final static String help = "UCM-Ship releases a shock wave.";
	private final static String details = "shockWave";
	
	public ShockWaveCommand() {
		super(name,shortcut,details,help);
	}

	@Override
	public boolean execute(Game game) {
		if(game.shockWave()) {
			game.update();
			return true;
		}
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