package Main;

import java.util.Random;
import java.util.Scanner;

import Controller.Controller;
import Exceptions.ArgumentsException;
import Logic.Level;
import Logic.Game.Game;


/*
 * Juego de los marcianitos de la asignatura de Tecnología de la Programación
 * @author  Iker Burgoa Muñoz
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		try {
			Level level = Level.fromParam(args[0]);
			if(args.length==1) {
				Game game = new Game(level,new Random(123));
				Controller controller = new Controller(game,in);
				controller.run();
			}
			else if(args.length==2){
				int seed = Integer.parseInt(args[1]);
				Random random = new Random(seed);
				Game game = new Game(level,random);
				Controller controller = new Controller(game,in);
				controller.run();
			}
			else {
				in.close();
				throw new ArgumentsException("Usage: Main <EASY|HARD|INSANE> [seed]");
			}
			in.close();
		}
		catch(NumberFormatException number) {
			System.err.println("Usage: Main <EASY|HARD|INSANE> [seed]: the seed must be a number");
		}
		catch(NullPointerException nul) {
			System.err.println("Usage: Main <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
		} catch (ArgumentsException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
}

