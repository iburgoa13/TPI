package Command;

import Logic.Game.Game;

public class BuyMissileCommand extends Command{
	private final static String shortcut = "b";
	private final static String name = "supermisil";
	private final static String help = "Buy a supermisil.";
	private final static String details = "supermisil";
	
	public BuyMissileCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean execute(Game game) {
		if(!game.buyMissile()) {
			System.out.println("Creditos no suficientes para comprar");
			return false;
		}
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
