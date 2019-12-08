package Controller;

import java.util.Scanner;

import Command.Command;
import Command.CommandGenerator;
import Exceptions.CommandExecuteException;
import Exceptions.CommandParseException;
import Exceptions.FileContentsException;
import Logic.Game.Game;
import Logic.Printer.GamePrinter;
import Logic.Printer.PrinterType;



public class Controller {
	private Game game;
	private GamePrinter printer;
	private Scanner in;
	private PrinterType print;
	/**
	 * Constructora de la clase Controller
	 * @param game Recibe el juego del Main por argumento, si no tiene argumento
	 * recibe el juego EASY por defecto
	 * @param in Scanner para poder escribir por teclado
	 */

	public Controller(Game game, Scanner in) {
		this.game = game;
		print = PrinterType.BOARDPRINTER;
		this.in = in;
		printer = print.getObject(game);
	}

	
	public void printGame() {
		System.out.println(printer.toString(game));//(printer.toString(game));
	}
	
	public void run()  {
		printGame();
		while(!game.isFinished()) {
			String[] action;
			System.out.print("Command >");
			action = in.nextLine().toLowerCase().trim().split("\\s");
			try {
				Command command = CommandGenerator.parse(action);
				if(command.execute(game)) printGame();				
			}
			catch(FileContentsException | CommandParseException | CommandExecuteException ex) {
				System.out.format("Causa de la excepcion: " + ex + "%n%n");
			}
		}
		System.out.print(game.getWinnerMessage());
	}
	
}
