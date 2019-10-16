package Main;

import java.util.Random;
import java.util.Scanner;

import Controller.Controller;
import Logic.Game;
import Logic.Level;
/*
 * Juego de los marcianitos de la asignatura de Tecnología de la Programación
 * @author  Iker Burgoa Muñoz
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);		
		Level level = Level.select(args[0]);
		Random random = new Random();
		
		
		Game game = new Game(level,random);
		Controller controller = new Controller(game,in);
		controller.run();
		}
}


