package Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Logic.Game.Game;

public class SaveCommand extends Command{
	private final static String shortcut = "sa";
	private final static String name = "save";
	private final static String help = "Save the game.";
	private final static String details = "Save";
//	private String ruta = "C:/Users/iker_/OneDrive/Escritorio/ArcadePruebas/";
	private  String text;
	
	public SaveCommand() {
		super(name,shortcut,details,help);
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("C:/Users/iker_/OneDrive/Escritorio/ArcadePruebas/"+text+".dat");
			bw = new BufferedWriter(fw);
			game.save(bw);
			System.out.println("Game successfully saved in file "+ text+".dat.\r\n" + 
					"Use the load command to reload it.");
			
		}
		catch(IOException io) {
			io.getMessage();
		}
		finally{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])){
			if(commandWords.length==2) {
				if(commandWords[1].contains(".dat")) {
					commandWords[1] = commandWords[1].substring(0, commandWords[1].length()-4);
				}
				else if(commandWords[1].contains("?") || commandWords[1].contains("|") || commandWords[1].contains("/")
						||commandWords[1].contains("*")|| commandWords[1].contains("<") || commandWords[1].contains(">")
						||commandWords[1].contains(":")) {
					throw new CommandParseException("Los nombres de archivo no pueden contener caracteres especiales, consulte en internet");
				}//me falta \ y " que no se como ponerlo
				text = commandWords[1];
				return this;
			}
			else {
				throw new CommandParseException("Argumentos invalidos, por favor introduzca save + NombreDePartida");
			}
		}
		return null;
	}
}
