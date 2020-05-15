package hangman;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		char anotherGame;

		do {
			Hangman hangman = new Hangman();
			
			do {
                System.out.println("The word you have to guess is: " + hangman.getWordToBeGuessed());
                System.out.print("Enter a letter: ");
                char letter = scanner.next().toLowerCase().charAt(0);

                if (hangman.isTheLetterAlreadyGuessed(letter)) {
                    System.out.print("\nThis letter has already been guessed. Try with another one: ");
                    letter = scanner.next().toLowerCase().charAt(0);
                }

                if (hangman.guessLetter(letter)) {
                    System.out.println("\nYour guess was correct. Keep up!\n");
                } else {
                    System.out.println("\nYour guess was incorrect. Try again.\n");
                    System.out.println(hangman.gameState());
                }
            } while (!hangman.gameOver());		
			
            System.out.print("\nDo you want to guess for another word? Enter y or n: \n");
			anotherGame = scanner.next().toLowerCase().charAt(0);
			
        } while (anotherGame == 'y');
       
		System.out.println("See you again!");
    }
}
