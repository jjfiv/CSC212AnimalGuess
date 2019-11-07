package edu.smith.cs.csc212.guess;

/**
 * A class to store the knowledge learned by the animal guessing-game.
 */
public class DecisionTreeNode {
    /**
     * This is a question (in the middle) or an animal (at a leaf).
     */
    String text;
    /**
     * This is a reference to a node for a "yes" answer.
     */
    DecisionTreeNode yes;
    /**
     * This is a reference to a node for a "no" answer.
     */
    DecisionTreeNode no;

    /**
     * Create a leaf node -- probably just an animal.
     * @param value the name of the animal.
     */
    public DecisionTreeNode(String value) {
        this.text = value;
        this.yes = null;
        this.no = null;
    }

    /**
     * Create a node with children.
     * 
     * @param question the question to split the data.
     * @param yes the animal or next question if the answer is yes.
     * @param no the animal or next question if the answer is no.
     */
    public DecisionTreeNode(String question, DecisionTreeNode yes, DecisionTreeNode no) {
        this.text = question;
        this.yes = yes;
        this.no = no;
    }

    /**
     * Is this a leaf node?
     */
    public boolean isLeaf() {
        return this.yes == null && this.no == null;
    }

    /**
     * Turn this decision tree into a String.
     */
    public String toString() {
        return "(" + text + "\t" + this.yes + "\t" + this.no + ")";
    }
}