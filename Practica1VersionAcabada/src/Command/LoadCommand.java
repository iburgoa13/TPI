package Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Exceptions.FileContentsException;
import Exceptions.InvalidGameException;
import Logic.Game.Game;

public class LoadCommand extends Command{
	private final static String shortcut = "lo";
	private final static String name = "Load";
	private final static String help = "Load the game.";
	private final static String details = "Load";
	private String text;
	public LoadCommand() {
		super(name, shortcut, details, help);
		text = null;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException, FileContentsException {
		BufferedReader br=null;

			try {
				br = new BufferedReader(new FileReader("C:/Users/iker_/OneDrive/Escritorio/ArcadePruebas/"+text+".dat"));
				
				game.read(br);
				System.out.println("Game successfully loaded from file "+text+".");
			} catch (IOException | FileContentsException | InvalidGameException e) {
				throw new FileContentsException(e.getMessage());
			}
			finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])){
			if(commandWords.length==2) {
				if(commandWords[1].contains(".dat")) {
					commandWords[1] = commandWords[1].substring(0, commandWords[1].length()-4);
				}
				else if(commandWords[1].contains("?")|| commandWords[1].contains("|") || commandWords[1].contains("/")
						||commandWords[1].contains("*")|| commandWords[1].contains("<") || commandWords[1].contains(">")
						||commandWords[1].contains(":")) {
					throw new CommandParseException("Los nombres de archivo no pueden contener caracteres especiales, consulte en internet");
				}//me falta \ y " que no se como ponerlo
				text = commandWords[1];
				return this;
			}
			else {
				throw new CommandParseException("Argumentos invalidos, por favor introduzca load + NombreDePartida");
			}
		}
		return null;
	}

}
