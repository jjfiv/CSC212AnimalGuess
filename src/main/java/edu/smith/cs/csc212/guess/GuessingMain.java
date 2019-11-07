package edu.smith.cs.csc212.guess;

public class GuessingMain {

    public static void main(String[] args) {
        DecisionTreeNode knowledge = new DecisionTreeNode("Is it a mammal?", 
            new DecisionTreeNode("Mouse"), 
            new DecisionTreeNode("Snake"));

        System.out.println(knowledge);

        System.out.println("Welcome to the guessing game!");
        TextInput input = new TextInput();

        while(true) {
            System.out.println("To play, first think of an animal.");

            // TODO: Maybe something like this?
            // knowledge.play();

            System.out.println("...I guess there's no game here yet...");

            if (input.confirm("Do you want to play again?")) {
                continue;
            } else {
                break;
            }
        }
    }
}