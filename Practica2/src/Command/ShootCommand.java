package Command;

import Logic.Game.Game;

public class ShootCommand extends Command{
	private final static String shortcut = "s";
	private final static String name = "shoot";
	private final static String help = "UCM-Ship launches a missile.";
	private final static String details = "Shoot";
	
	public ShootCommand() {
		super(name,shortcut,details,help);
	}
	
	
	@Override
	public boolean execute(Game game) {
		if(!game.shootLaser()) {
			System.out.println("Shoot en pista");
			return false;
		}
		else{
			game.update();
			return true;
		}
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
