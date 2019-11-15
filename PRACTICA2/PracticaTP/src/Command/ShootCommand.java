package Command;

import Logic.Game.Game;

public class ShootCommand extends Command{
	private final static String shortcut = "s";
	private final static String name = "shoot";
	private final static String help = "UCM-Ship launches a missile or launches a supermisil.";
	private final static String details = "Shoot <supermisil>";
	private boolean shoot;
	public ShootCommand() {
		super(name,shortcut,details,help);
		shoot = false;
	}
	
	
	@Override
	public boolean execute(Game game) {
		if(!shoot) {
			if(!game.shootLaser()) {
				System.out.println("Shoot en pista");
				return false;
			}
			else{
				game.update();
				return true;
			}
		}
		else {
			if(!game.supermisil()) {
				System.out.println("Shoot en pista");
				return false;
			}
			else{
				game.update();
				return true;
			}
		}
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])){
			if(commandWords.length==1) {
				shoot = false;
				return this;

			}
			else if(commandWords.length==2) {
				if(commandWords[1].equals("supermisil")) {
					shoot = true;
					return this;
				}
				return null;
			}
		}
		return null;
	}
}
