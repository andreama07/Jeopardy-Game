package jeopardy;
import java.util.*;

//This Game class manages the largest bulk of game with user interaction and question bank.
public class Game {
	public static ArrayList<String> code = new ArrayList<String>();
	private ArrayList<String> unaskedQs  = new ArrayList<String>();
	private HashMap<String, String> questions = new HashMap<String, String>();
	private HashMap<String, String> answers = new HashMap<String, String>();
	private ArrayList<Double> unsortedArray = new ArrayList<Double>();
	private double[] sortedArray = {0.02856, 0.028556, 0.028357, 0.0028483, 0.028, 0.028238, 0.03};
	
	//passes the Scanner from main to a local Scanner in this Game class so that input can be read in both classes
	Scanner input;
	public Game(Scanner scan) {
		this.input = scan;
	}
 
	//asks user to choose a category and a point value, loops until user chooses an unasked question, returns the question key
	public String ChooseQ() {
		boolean newQuestion = false;
		String key= "";
		while (!newQuestion) {
			System.out.println("Which category would you like?");
			System.out.println("Type m for Math, c for Compsci, g for Geography, r for Riddles, and e for English:");
			input.nextLine();
			String category = "";
			while (!category.equals("m") && !category.equals("c") && !category.equals("g") && !category.equals("r") && !category.equals("e")) {
				category = input.next();
				if (!category.equals("m") && !category.equals("c") && !category.equals("g") && !category.equals("r") && !category.equals("e")) {
					System.out.println("Your answer is not a valid option. Try again:");
				}
			}

			System.out.println("What point value would you like?");
			String value = "0";
			while (!value.equals(Integer.toString(Display.numbers[0])) && !value.equals(Integer.toString(Display.numbers[1])) && !value.equals(Integer.toString(Display.numbers[2])) && !value.equals(Integer.toString(Display.numbers[3])) && !value.equals(Integer.toString(Display.numbers[4]))) {
				value = input.next();
				if (!value.equals(Integer.toString(Display.numbers[0])) && !value.equals(Integer.toString(Display.numbers[1])) && !value.equals(Integer.toString(Display.numbers[2])) && !value.equals(Integer.toString(Display.numbers[3])) && !value.equals(Integer.toString(Display.numbers[4]))) {
					System.out.println("Your answer is not a valid option. Try again:");
				}
			}
			
			key = category + value;
			for (int i = 0; i < unaskedQs.size(); i++) {
				if (unaskedQs.get(i).equals(key)) {
					newQuestion = true;
					unaskedQs.remove(i);
					break;
				}
			}
		}
		return key;
	}
	
	//prints the question associated with the key and checks user's answer with the answer associated with the key
	public int AskQ_Check(String key) {
		System.out.println(questions.get(key));
		input.nextLine();
		String guess = input.next().toLowerCase();
		String answer = answers.get(key);
		if (answer.substring(0, 1).equals("*")) {
			if (answer.substring(1).equals("1")) {
				return this.Insertion_Sort(guess);	
			}
			else if (answer.substring(1).equals("2")){
				return this.Mod(guess);
			}
			else if (answer.substring(1).equals("3")){
				int result = this.Dangling_Else(guess);
				if (result == 1) {
					System.out.println("Correct!");
					System.out.println();
					return 1;
				}
				else {
					System.out.println("Incorrect");
					System.out.println();
					return 0;
				}
			}
			else {
				return this.Traverse_Array(guess);
			}
		}
		else {
			if (guess.equals(answer)) {
				System.out.println("Correct!");
				System.out.println();
				return 1;
			}
			else {
				System.out.println("Incorrect");
				System.out.println();
				return 0;
			}
		}
	}
	
	//sorts a double array, determines answer to one of the math questions and checks user's guess
	public int Insertion_Sort(String guess) {
		
		for (int i = 0; i < sortedArray.length; i++) {
			unsortedArray.add(sortedArray[i]);
		}
		for (int j = 1; j < sortedArray.length; j++) {
			int index = j - 1;
			double key = sortedArray[j];
			while ((index > -1) && (sortedArray[index] > key)) {
				sortedArray[index + 1] = sortedArray[index];
				index --;
				sortedArray[index + 1] = key;
			}
		}
		String answer = "";
		for (int k = 0; k < sortedArray.length; k++) {
			answer = answer + (unsortedArray.indexOf(sortedArray[k]) + 1);
		}
		if (guess.equals(answer)) {
			System.out.println("Correct!");
			System.out.println();
			return 1;
		}
		else {
			System.out.println("Incorrect");
			System.out.println();
			return 0;
		}
		
	}
	
	//determines whether the user's answer to a math question is correct using the mod function
	public int Mod(String guess) {
		int intGuess = Integer.parseInt(guess);
		if ((intGuess % 4 == 1) && (intGuess % 10 == 3)) {
			System.out.println("Correct!");
			System.out.println();
			return 1;
		}
		else {
			System.out.println("Incorrect");
			System.out.println();
			return 0;
		}
	}
	
	//uses nested ifs and dangling else to check if user's guess is correct for a geography question
	public int Dangling_Else(String guess) {
		if (guess.length() == 5 || guess.length() == 15 || guess.length() == 17)
			if (guess.equals("yukon") || guess.equals("yukon territory") || guess.equals("yukon territories"))  
				return 1;
			else 
				return 0;
		return 0;
	}
	
	//uses for each to see if user's guess is in the array of correct answers
	public int Traverse_Array(String guess) {
		String[] iStates = {"idaho", "illinois", "indiana", "iowa"};
		for (String text: iStates) {
			if (guess.equals(text)) {
				System.out.println("Correct!");
				System.out.println();
				return 1;
			}
		}
		System.out.println("Incorrect");
		System.out.println();
		return 0;
	}
	
	//creates the ArrayList of unasked questions at the beginning, adds the questions and answers to separate HashMaps using the keys
	public void Begin() {
		
		code.add("m");
		code.add("c");
		code.add("g");
		code.add("r");
		code.add("e");
		for (int i = 0; i < 5; i++) {
			String category = code.get(i);
			for (int j = 0; j < 5; j++) {
				String value = Integer.toString(Display.numbers[j]);
				unaskedQs.add(category + value);
			}
		}
	//math questions
				
		questions.put(unaskedQs.get(0), "How many prime numbers are there from 1 to 100?");
		answers.put(unaskedQs.get(0), "25");
		
		questions.put(unaskedQs.get(1), "Which of the following is not a Roman Numeral:\na)M\nb)N\nc)D\nd)L\nAnswer with one letter");
		answers.put(unaskedQs.get(1), "b");
		
		int dividend = (int)(Math.random()*500 + 500);
		int divisor = (int)(Math.random()*7 + 2);
		questions.put(unaskedQs.get(2), "Compute: " + dividend + "/" + divisor + "  Round to the nearest integer.");
		int quotient = (int)Math.round((double)dividend / divisor);
		answers.put(unaskedQs.get(2), Integer.toString(quotient));
		
		//*special answer 1
		questions.put(unaskedQs.get(3), "Sort the following decimal numbers from smallest to largest:\n1) " + sortedArray[0]
				+ "\n2) " + sortedArray[1] + "\n3) " + sortedArray[2] + "\n4) " + sortedArray[3] + "\n5) " + sortedArray[4] + "\n6) " + sortedArray[5]
						+ "\n7) " + sortedArray[6] + "\nAnswer with one 7-digit number with ordered numbers. (eg. 1526374)");
		answers.put(unaskedQs.get(3), "*1");
		
		//*special answer 2
		questions.put(unaskedQs.get(4), "What is a number that is both 1(mod 4) and 3(mod 10)?");
		answers.put(unaskedQs.get(4), "*2");
		
	//compsci questions
		questions.put(unaskedQs.get(5), "What does GUI stand for: \na) General User Interaction\nb)Guided User Interface"
				+ "\nc) Graphical User Interface\nd) Graphical User Interaction\nAnswer with one letter");
		answers.put(unaskedQs.get(5), "c");
		
		questions.put(unaskedQs.get(6), "A for loop has three components: _________, condition, iteration."
				+ "\nWhat is the first component called? (hint: it ends in -tion)");
		answers.put(unaskedQs.get(6), "initialization");
		
		questions.put(unaskedQs.get(7), "How many times does this for-loop loop?:\nfor(int i = 1; i < 20; i+=2){}\nAnswer with a number");
		answers.put(unaskedQs.get(7), "10");
		
		questions.put(unaskedQs.get(8), "How many bits are in a Java integer?");
		answers.put(unaskedQs.get(8), "32");
		
		questions.put(unaskedQs.get(9), "Which of the following is not a programming language: \na) A\nb) B\nc) C\nd) D"
				+ "\nAnswer with one letter");
		answers.put(unaskedQs.get(9), "a");
		
	//geography questions
		questions.put(unaskedQs.get(10), "Which of the following is the tallest mountain in the world: \na) Machu Picchu\nb) Mount Everest"
				+ "\nc) Mount Fuji\nd) Kilimanjaro\nAnswer with one letter");
		answers.put(unaskedQs.get(10), "b");
		
		//*special answer 3
		questions.put(unaskedQs.get(11), "Which Canadian province’s capital is Whitehorse?");
		answers.put(unaskedQs.get(11), "*3");
		
		//*special answer 4
		questions.put(unaskedQs.get(12), "Name one U.S. state that starts with the letter I?");
		answers.put(unaskedQs.get(12), "*4");
		
		questions.put(unaskedQs.get(13), "Which U.S. state has the most active volcanoes: \na) Washington\nb) Hawaii\nc) California"
				+ "\nd) Alaska\nAnswer with one letter");
		answers.put(unaskedQs.get(13), "d");
		
		questions.put(unaskedQs.get(14), "What is the one country in the world that starts with the letter Y?");
		answers.put(unaskedQs.get(14), "yemen");
		
	//riddles
		questions.put(unaskedQs.get(15), "I am not alive, but I grow; I don't have lungs, but I need air; I don't have a mouth, "
				+ "but water kills me. What am I?");
		answers.put(unaskedQs.get(15), "fire");
		
		questions.put(unaskedQs.get(16), "It stalks the countryside with ears that can’t hear. What is it?");
		answers.put(unaskedQs.get(16), "corn");
		
		questions.put(unaskedQs.get(17), "Mary has four daughters, and each of her daughters has a brother."
				+ "\nHow many children does Mary have?\nAnswer with a number");
		answers.put(unaskedQs.get(17), "5");
		
		questions.put(unaskedQs.get(18), "What can’t be burned in a fire nor drowned in the water?");
		answers.put(unaskedQs.get(18), "ice");
		
		questions.put(unaskedQs.get(19), "What is 3/7 chicken, 2/3 cat and 2/4 goat?");
		answers.put(unaskedQs.get(19), "chicago");
		
	//english questions
		questions.put(unaskedQs.get(20), "Who wrote the classic novel, ‘Emma’?\nType their first and last name separated by a space");
		answers.put(unaskedQs.get(20), "jane eyre");
		
		questions.put(unaskedQs.get(21), "What is the rhetorical device that means 'the use of words that sound good together'?");
		answers.put(unaskedQs.get(21), "euphony");
		
		questions.put(unaskedQs.get(22), "Which of the following is not a Shakespeare play: \na) Love and Lust\nb) As You Like It"
				+ "\nc) Comedy of Errors\nd) All’s Well That Ends Well\nAnswer with one letter");
		answers.put(unaskedQs.get(22), "a");
		
		questions.put(unaskedQs.get(23), "How do you spell the really long word that starts with supercal…?\\n(Hint: it's 34 letters long)");
		answers.put(unaskedQs.get(23), "supercalifragilisticexpialidocious");
		
		questions.put(unaskedQs.get(24), "Ethos appeals to credibility, Logos appeals to logic, Pathos appeals to emotion."
				+ "\nWhat is the fourth rhetorical device that appeals to time?");
		answers.put(unaskedQs.get(24), "kairos");
		
	}


}
