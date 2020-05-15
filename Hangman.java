package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	private String wordToBeGuessed = "";
	private StringBuilder wordToBeGuessedSB;
	private ArrayList<Character> listOfGuessedLettersFromWord = new ArrayList<>();
	private ArrayList<String> listOfWords = new ArrayList<>();
	private static final int MAX_TRIES = 6;
	private int currentTries = 0;
	private static FileReader fr;
    private static BufferedReader br;
	
	private void readFromFile() throws IOException {
        try {
            File fileToBeRead = new File("C:\\Users\\OMG\\Desktop\\hangman.txt");
            fr = new FileReader(fileToBeRead);
            br = new BufferedReader(fr);
            String readLine = br.readLine();

            while(readLine != null) {
                listOfWords.add(readLine);
                readLine = br.readLine();
            }    
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	br.close();
            fr.close();
        }
    }
	
	public String randomWord() {
		Random randomizer = new Random();
		int wordIndex = Math.abs(randomizer.nextInt()) % listOfWords.size();
		
		return listOfWords.get(wordIndex);
	}
	
	public StringBuilder turnWordIntoStars() {
		StringBuilder wordIntoStars = new StringBuilder();
		for (int i = 0; i < wordToBeGuessed.length(); i++) {
			wordIntoStars.append("*");
		}
		
		return wordIntoStars;
	}
	
	public Hangman() throws IOException {
		readFromFile();
		wordToBeGuessed = randomWord();
		wordToBeGuessedSB = turnWordIntoStars();
	}
	
	public String getWordToBeGuessed() {
		String word = wordToBeGuessedSB.toString();
		return word;
	}
	
	public boolean win() {
		String word = getWordToBeGuessed();
		return word.equals(wordToBeGuessed);
	}
	
	public boolean loss() {
		return currentTries >= MAX_TRIES;
	}
	
	public boolean gameOver() {
		if (win()) {
			System.out.println("Congratulations, you guessed the word. You missed " + currentTries  + (currentTries == 1 ? " time." : " times."));
		} else if (loss()) {
			System.out.println("Unfortunately you couldn't make it. The word is: " + wordToBeGuessed + ".");
		}
		
		return win() || loss();
	}
	
    public boolean guessLetter(char letterFromWord) {
        boolean guessState = false;
        listOfGuessedLettersFromWord.add(letterFromWord);
        
        for (int i = 0; i < wordToBeGuessed.length(); i++) {
            if (wordToBeGuessed.charAt(i) == letterFromWord) {
                wordToBeGuessedSB.setCharAt(i, letterFromWord);
                guessState = true;
            }
        }

        if (!guessState) {
            currentTries++;
        }

        return guessState;
    }	
    
    public boolean isTheLetterAlreadyGuessed(char letterFromWord) {
		return listOfGuessedLettersFromWord.contains(letterFromWord);
	}
	
	public String gameState() {
		switch (currentTries) {
			case 0: return sixTriesLeft();
			case 1: return fiveTriesLeft();
			case 2: return fourTriesLeft();
			case 3: return threeTriesLeft();
			case 4: return twoTriesLeft();
			case 5: return oneTryLeft();
			default: return noTriesLeft();
		}
	}
	
	private String sixTriesLeft() {
		return "You are left with six tries";
	}
	
	private String fiveTriesLeft() {
		return "You are left with five tries";
	}
	
	private String fourTriesLeft() {
		return "You are left with four tries";
	}
	
	private String threeTriesLeft() {
		return "You are left with three tries";
	}
	
	private String twoTriesLeft() {
		return "You are left with two tries";
	}
	
	private String oneTryLeft() {
		return "You are left with one last try";
	}

	private String noTriesLeft() {
		return "You got hanged";
	}
}
