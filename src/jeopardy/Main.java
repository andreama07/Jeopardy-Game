package jeopardy;
import java.util.*;

// This Main class is where objects are created and functions from the other 3 classes are called. It's also where the game is ended.
public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Display.Intro();
		int difficulty = 0;
		Display Artist = null;
		
		while (difficulty != 1 && difficulty != 2 && difficulty != 3) {
			difficulty = input.nextInt();
			if (difficulty == 1 || difficulty == 2) {
				Artist = new Display(difficulty);
			}
			else if (difficulty == 3) {
				Artist = new Display();
			}
			else {
				System.out.println("Your answer is not a valid option. Try again:");
			}
		}
		
		System.out.println("What is your name?");
		String name = input.next();
		User Player = new User();
		Artist.Game_Rules(name);
		Artist.Set_Up();
		Game Host = new Game(input);
		Host.Begin();
		Player.Get_Stats();
		
		while (Player.lives > 0 && Player.points < User.goal) {
			String question = Host.ChooseQ();
			int check = Host.AskQ_Check(question);
			Player.Update_Stats(check, question);
			Artist.Update(question);	
			Player.Get_Stats();
		}
		if (Player.lives == 0) {
			System.out.println("You lost :(\nPlease try again!");
		}
		else {
			System.out.println("CONGRATS! You won Jeopardy :D");
		}
		
	}
}
