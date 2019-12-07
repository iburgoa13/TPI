package Command;

import Exceptions.CommandParseException;

public class CommandGenerator {
	private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ShootCommand(), 
			new UpdateCommand(), //interpreto Update como None
			new MoveCommand(),
			new ShockWaveCommand(),
			new BuyMissileCommand(),
			new StringifyCommand(),
			new ListPrintersCommand(),
			new SaveCommand(),
			new LoadCommand()
			};
	
	
	public static Command parse(String[] commandWords) throws CommandParseException {
		
		for(Command com : availableCommands) {
			Command c = com.parse(commandWords);
			if(c!=null) {
				return c;
			}			
		}
		throw new CommandParseException("Primer parametro del comando " + commandWords[0] + " no existe, por favor introduzca de nuevo");
	}
	public static String commandHelp() {
		String helpText = null;
		for (Command com : availableCommands) {
			helpText = com.helpText();
			System.out.println(helpText);
		}
		return helpText;
	}
}
