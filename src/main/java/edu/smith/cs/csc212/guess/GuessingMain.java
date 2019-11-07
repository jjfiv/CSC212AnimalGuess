package edu.smith.cs.csc212.guess;

public class GuessingMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the guessing game!");
        TextInput input = new TextInput();

        while(true) {
            System.out.println("To play, first think of an animal.");

            System.out.println("...I guess there's no game here yet...");

            if (input.confirm("Do you want to play again?")) {
                continue;
            } else {
                break;
            }
        }
    }
}