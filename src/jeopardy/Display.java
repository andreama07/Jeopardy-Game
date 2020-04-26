package jeopardy;

//This Display class produces the 'graphics' shown in the console.
public class Display {
	public static int pointIncrement = 0;	
	public static int[] numbers = null;
	public static String[][] board = null;
	public Display() {
		pointIncrement = (int)(Math.random()*10) * 2 + 10;	
	}
	
	public Display(int level) {
		if (level == 1) {
			pointIncrement = 30;
		}
		else {
			pointIncrement = 10;
		}
	}
	
	//prints intro to game
	public static void Intro() {
		System.out.println("Welcome to Jeopardy!");
		System.out.println("There are 5 categories and 5 questions of varying diffuculty in each category.");
		System.out.println("Type 1 for easy, 2 for hard, and 3 for a random difficulty:");
	}
	
	//prints game rules, wishes user good luck :D
	public void Game_Rules(String player) {
		System.out.println("You have 3 lives.\nEach round, you get to choose a new category and point value.");
		System.out.println("If you answer correctly, you earn the points. If not, you lose a life.");
		System.out.println("The game ends when you reach 600 points or lose all 3 lives.");
		System.out.println("Good luck, " + player + "!");
		System.out.println();
	}
	
	//Creates the initial board layout, call the Draw function to display in the console
	public void Set_Up() {
		numbers = new int[5];
		board = new String[5][5];
		for (int i = 0; i < 5; i++) {
			numbers[i] = pointIncrement*(i + 1);
		}
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				board[j][k] = "         |";
			}
		}
		this.Draw();
	}
	
	//prints out the jeopardy board in the console
	public void Draw() {
		System.out.println("      Math    Compsci  Geography  Riddles   English  ");
		System.out.println("-----------------------------------------------------");
		for (int k = 0; k < 5; k++) {
			System.out.print(numbers[k] + "|");
			for (int l = 0; l < 4; l++) {
				System.out.print(board[k][l]);
			}
			System.out.println(board[k][4]);
		}
		System.out.println();
	}
	
	//adds an X to the jeopardy board based on which question was just asked
	public void Update(String key) {
		int column = Game.code.indexOf(key.substring(0,1));
		int value = Integer.parseInt(key.substring(1));
		int row = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (value == numbers[i]) {
				row = i;
			}
		}
		board[row][column] = "    X    |";
		this.Draw();
	}
}
