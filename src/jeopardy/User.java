package jeopardy;

//This User class is responsible for keeping track of the user's lives and points.
public class User {
	public static int goal = 600;
	public int lives = 3;
	public int points = 0;
	public User() {
		
	}
	//prints out the # of lives left, # of point earned, and target # of points to win
	public void Get_Stats() {
		System.out.println("Lives: " + lives + "   Points: " + points + "   Goal: " + goal);
		System.out.println();
	}
	
	//updates the lives and points based on whether the answer was answered correctly or not
	public void Update_Stats(int check, String question) {
		if (check == 1) {
			points += Integer.parseInt(question.substring(1));
		}
		else {
			lives --;
		}
	}
	
}
