package Command;

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
			new BuyMissileCommand()
			};
	
	
	public static Command parse(String[ ] commandWords) {
		for(Command com : availableCommands) {
			Command c = com.parse(commandWords);
			if(c!=null) {
				return c;
			}			
		}
		return null;
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
