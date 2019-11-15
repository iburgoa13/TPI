package Controller;

import java.util.Scanner;

import Command.Command;
import Command.CommandGenerator;
import Logic.Game.Game;


public class Controller {
	private Game game;
	private Scanner in;
	/**
	 * Constructora de la clase Controller
	 * @param game Recibe el juego del Main por argumento, si no tiene argumento
	 * recibe el juego EASY por defecto
	 * @param in Scanner para poder escribir por teclado
	 */
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}
	/**
	 * Desde este metodo el jugador da los comandos para jugar.
	 * Sale de la partida en caso de dar a la e o escribir exit o ganar o perder.
	 */
	public void run() {
		System.out.println(game.toString());
		while(!game.isFinished()) {//while(!game.isFinished()) {
			String[] action;
			System.out.print("Command >");
			action = in.nextLine().toLowerCase().trim().split("\\s");
			Command command = CommandGenerator.parse(action);
			if(command!=null) {
				if(command.execute(game)) {
					System.out.format(game.toString());
				}
			}
			else System.out.println("ERROR");
		}
		System.out.print(game.getWinnerMessage());
		
	}
}
