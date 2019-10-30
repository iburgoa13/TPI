package Command;

import Logic.Game;

public class MoveCommand extends Command{
	//CAMBIAR ESTO
	private final static String shortcut = "m";
	private final static String name = "move";
	private final static String help = "Moves UCM-Ship to the indicated direction.";
	private final static String details = "move <left|right><1|2>";
	private int direction;
	private int celdas;
	public MoveCommand() {
		super(name, shortcut, details, help);
	}
	public MoveCommand(int dir, int celdas) {
		super(name, shortcut, details, help);
		this.direction = dir;
		this.celdas = celdas;
	}
	@Override
	public boolean execute(Game game) {
		if(game.moveUCM(direction,celdas)) {
			game.update();
			return true;
		} 
		else {
			System.out.println("Movimiento imposible por tamaño");
			return false;
		}
	}

	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		if(matchCommandName(commandWords[0])){
			if(commandWords.length==3) {
				if(commandWords[1].equals("left") || commandWords[1].equals("right")) {
					int celdas = Integer.parseInt(commandWords[2]);
					if(celdas == 1 || celdas == 2) {
						if(commandWords[1].equals("left")) {
							command = new MoveCommand(-1,celdas);
						}
						else command = new MoveCommand(1,celdas);
						return command;
					} else return command;
				}
				else return command;
			}
			else return command;
		}
		else return null;
	}

}
