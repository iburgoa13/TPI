package Command;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
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
	public boolean execute(Game game) throws CommandExecuteException {
		if(!shoot) {
			game.shootLaser();
			game.update();
			return true;
			
		}
		else {
			game.supermisil();
			game.update();
			return true;
		}
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length==1) {
				shoot = false;
				return this;
			}
			else if(commandWords.length==2) {
				if(commandWords[1].equals("supermisil")) {
					shoot = true;
					return this;
				}
				else {
					throw new CommandParseException("Parametro "+ commandWords[1] + " no valido, introduzca shoot supermisil");

				}
			}
			else {
				throw new CommandParseException("Numero de parametros invalidos, introduzca shoot <supermisil>");
			}
		}
		return null;
	}
}
