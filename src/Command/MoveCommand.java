package Command;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Logic.Game.Game;

public class MoveCommand extends Command{
	//CAMBIAR ESTO
	private final static String shortcut = "m";
	private final static String name = "move";
	private final static String help = "Moves UCM-Ship to the indicated direction.";
	private final static String details = "move <left|right><1|2>";
	private int celdas;
	public MoveCommand() {
		super(name, shortcut, details, help);
	}
	public MoveCommand(int dir, int celdas) {
		super(name, shortcut, details, help);
		this.celdas = celdas;
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.move(celdas);
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length==3) {
				if(commandWords[1].equals("left") || commandWords[1].equals("right")) {
					try {
						int celdas = Integer.parseInt(commandWords[2]);
						if(celdas ==2 || celdas == 1) {
							if(commandWords[1].equals("left")) {
								command = new MoveCommand(-1,-celdas);
							}
							else command = new MoveCommand(1,celdas);
						}
						else {
							throw new CommandParseException("Imposible mover "+ celdas+ " veces. Por favor introduzca 1 o 2");
						}
					}
					catch(NumberFormatException e) {
						throw new CommandParseException(commandWords[2] + " no es un numero. Introduzca 1 o 2");
					}
				}
				else {
					throw new CommandParseException("Direccion " + commandWords[1] + " no existe. Introduzca left o right");
				}
			}
			else {
				throw new CommandParseException("El comando " + name +" tiene argumentos incorrectos, por favor: move + <left/rigth> + <1/2>");
			}
		}
		return command;
	}

}
