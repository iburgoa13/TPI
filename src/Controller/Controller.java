package Controller;

import java.util.Scanner;

import Logic.Game;

public class Controller {
	private Game game;
	private Scanner in;
	private boolean exit;
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
		int direccion;
		int celdas;
		System.out.println(game.toString());
		String[] action;
		do {
			System.out.print("Command > ");
			action = in.nextLine().toLowerCase().trim().split("\\s");
			if(action.length==3) {
				if(action[0].equals("move") || action[0].equals("m")) {
					if(action[1].equals("right") && (action[2].equals("1") || action[2].equals("2"))) {
						celdas = Integer.parseInt(action[2]);
						direccion = 1;
						if(game.moveUCM(direccion, celdas)) {
							game.update();
							System.out.println(game.toString());
						}
					}
					else if(action[1].equals("left") && (action[2].equals("1") || action[2].equals("2"))) {
						celdas = Integer.parseInt(action[2]);
						direccion = -1;
						if(game.moveUCM(direccion,celdas)) {
							game.update();
							System.out.println(game.toString());
						}
					}
					else System.out.println("Movimiento imposible, máximo dos casillas de movimiento");
				}
				else System.out.println("Comando incorrecto, en caso de duda inserte help para consultar comandos");
			}
			else if(action.length==1) {
				switch(action[0]) {
				case "list":
				case "l":
					System.out.println(game.list());
					break;
				case "shockwave":
				case"w":
					if(game.shockWave()) {
						game.update();
						System.out.println(game.toString());
					}
					else System.out.println("No dispone del disparo especial");
					break;
				case "reset":
				case "r":
					game.reset();
					System.out.println(game.toString());
					break;
				case "shoot":
				case "s":
					if(!game.shootUCM()) System.out.println("ERROR");
					else {
						game.update();
						System.out.println(game.toString());
					}
					break;
					
				case "help":
				case "h":
					System.out.println(game.helpText());
					break;
				
				case "exit":
				case "e":
					exit = true;
					break;
				
				case "none":
				case "":
					game.update();
					System.out.println(game.toString());
					break;
					default: System.out.println("Comando incorrecto, en caso de duda inserte help para consultar comandos");
				}
			} else System.out.println("Comando incorrecto, en caso de duda inserte help para consultar comandos");
		}while(!exit && !game.getWin() && !game.getLose()) ;
		if(exit)System.out.println("GAME OVER");
		else if(game.getWin()) System.out.println("PLAYER WINS");
		else if(game.getLose()) System.out.println("ALIENS WINS");
	}
}
