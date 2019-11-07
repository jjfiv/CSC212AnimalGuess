package edu.smith.cs.csc212.guess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextInput {
	/**
	 * The source of data for text input.
	 */
	private BufferedReader input;

	/**
	 * For playing interactively, ask the user for input.
	 */
	public TextInput() {
		this.input = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * For my testing/grading usage, so you can provide me a copy of the winning
	 * script.
	 * 
	 * @param testFile - a script file that wins the game.
	 */
	public TextInput(File testFile) {
		try {
			this.input = new BufferedReader(new FileReader(testFile));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File does not exist or cannot be read: " + testFile, e);
		}
	}

	/**
	 * For testing, given an array of commands, execute them in order then quit.
	 * 
	 * @param testInput - the commands that the game will run in order.
	 */
	public TextInput(String[] testInput) {
		StringBuilder join = new StringBuilder();
		for (String line : testInput) {
			join.append(line).append('\n');
		}
		this.input = new BufferedReader(new StringReader(join.toString()));
	}

	/**
	 * Get the parsed-out words from the user.
	 * 
	 * @param prompt - what to print before asking the user.
	 * @return what the user typed.
	 */
	public List<String> getUserWords(String prompt) {
		while (true) {
			System.out.print(prompt);
			System.out.print(" ");
			System.out.flush();
			String resp;
			try {
				resp = input.readLine();
				if (resp == null) {
					return Collections.emptyList();
				}
			} catch (IOException e) {
				// Turn user error into a crash.
				throw new RuntimeException("User Input Error", e);
			}

			// Make sure they typed something.
			List<String> input = WordSplitter.splitTextToWords(resp);
			if (!input.isEmpty()) {
				return input;
			}
			// otherwise go around the loop again.
		}

	}

	/**
	 * Ask the user a yes/no question.
	 * 
	 * @param prompt - the question to ask.
	 * @return true if yes, false if no.
	 */
	public boolean confirm(String prompt) {
		while (true) {
			List<String> response = this.getUserWords(prompt + " (y/n): ");
			if (response.contains("yes") || response.contains("y")) {
				return true;
			} else if (response.contains("no") || response.contains("n")) {
				return false;
			}
			System.err.println("Couldn't understand: " + response + " try one of [yes, no]");
		}
	}

	/**
	 * Use command-line arguments to set up a TextInput.
	 * @param args - from main()
	 * @return a TextInput object.
	 */
	public static TextInput fromArgs(String[] args) {
		// No arguments, this is what you get from Eclipse.
		if (args.length == 0) {
			return new TextInput();
		} else if (args.length == 1) {
			// 1 argument -- I may automatically test your changes to SpookyMansion here.
			return new TextInput(new File(args[0]));
		} else {
			throw new RuntimeException("Not sure what to do with CLI Args: " + Arrays.toString(args));
		}

	}

	/**
	 * Convince Java to open the given file with a UTF-8 encoding; ignoring whatever your OS (e.g, Windows) tells it is the default.
	 * @param fileName - the path to the file, e.g., "src/main/resources/words"
	 * @return a buffered-reader object; kind of like a {@code Iterator<String>}
	 */
	public static BufferedReader readUTF8File(String fileName) {
		File path = new File(fileName);
		if (!path.exists() || !path.isFile() || !path.canRead()) {
			throw new RuntimeException("Couldn't read file: "+fileName+" are you sure you typed the path right?");
		}
		try {
			return new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException("Error opening file: "+fileName, e);
		}
	}
}
