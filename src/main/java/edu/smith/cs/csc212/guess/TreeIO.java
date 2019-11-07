package edu.smith.cs.csc212.guess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Save and load a DecisionTreeNode to a file.
 */
public class TreeIO {
    /**
     * Save a {@link DecisionTreeNode} to a file: fileName.
     * The opposite of {@link #load}.
     * @param fileName the file to create/save-over.
     * @param tree the data to save.
     */
    public static void save(String fileName, DecisionTreeNode tree) {
        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            saveTree(tree, writer);
        } catch (IOException ioe) {
            throw new RuntimeException("save error", ioe);
        }
    }

    /**
     * Load a {@link DecisionTreeNode} from a file: fileName.
     * The opposite of {@link #save}.
     * @param fileName the file to open.
     * @return the decision tree.
     */
    public static DecisionTreeNode load(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = TextInput.readUTF8File(fileName)) {
            reader.lines().forEach((line) -> {
                sb.append(line).append('\n');
            });
        } catch (IOException ioe) {
            throw new RuntimeException("load error", ioe);
        }
        Parser p = new Parser(sb.toString().toCharArray());
        return readTree(p);
    }

    /**
     * Recursiely save a DecisionTreeNode to a file (calls itself on children).
     * @param tree the current node.
     * @param writer the file to save to.
     */
    private static void saveTree(DecisionTreeNode tree, PrintWriter writer) {
        if (tree == null) {
            writer.print("null");
            return;
        }
        writer.print("(");
        writer.print(tree.text.replace('\t', ' '));
        writer.print('\t');
        saveTree(tree.yes, writer);
        writer.print('\t');
        saveTree(tree.no, writer);
        writer.print(")");
    }

    /**
     * Recursively read a decision tree from a file, using the {@link Parser} class.
     * @param p the parser object.
     * @return the decision tree node.
     */
    public static DecisionTreeNode readTree(Parser p) {
        if (p.consume("null")) {
            return null;
        } else {
            p.expect("(");
            String text = p.takeUntil('\t');
            p.expect("\t");
            DecisionTreeNode yes = readTree(p);
            p.expect("\t");
            DecisionTreeNode no = readTree(p);
            p.expect(")");
            return new DecisionTreeNode(text, yes, no);
        }
    }

    /**
     * This is a secret class that lets us walk slowly over the data we read from a file.
     * It has some loops turned into verbs as different methods.
     */
    private static class Parser {
        /**
         * This is the data read from a file as an array rather than a String.
         */
        char[] data;
        /**
         * How far have we gotten in data?
         */
        int position = 0;
        /**
         * Create a new Parser from an array.
         * @param data the text to read from.
         */
        public Parser(char[] data) {
            this.data = data;
        }
        /**
         * Read the next character.
         * @return -1 if we're done, and the character if we found it.
         */
        public int getc() {
            if (position >= data.length) {
                return -1;
            }
            return data[position++];
        }
        /**
         * Get the rest of the data as a String.
         * @return data[position..] as a string.
         */
        public String rest() {
            return new String(data, position, data.length - position);
        }
        /**
         * Read an exact word or crash.
         * @param literal the word to read.
         */
        public void expect(String literal) {
            if (!consume(literal)) {
                throw new RuntimeException("Loading error, expected: "+literal+" but found: "+this.rest());
            }
        }
        /**
         * Try to read something, if we can; going back if we can't.
         * @param literal the word to try to read.
         * @return true if it was there, false if it was not next.
         */
        public boolean consume(String literal) {
            int start = this.position;
            for (char c : literal.toCharArray()) {
                if (getc() != c) {
                    // reset and quit.
                    this.position = start;
                    return false;
                }
            }
            return true;
        }
        /**
         * Read until a specific character.
         * @param delimiter a special character that marks the end.
         * @return all the characters in data from position until we see that special "delimiter".
         */
        public String takeUntil(char delimiter) {
            StringBuilder sb = new StringBuilder();
            while (position < data.length) {
                if (data[position] == delimiter) {
                    return sb.toString();
                } else {
                    sb.append(data[position]);
                }
                position++;
            }
            throw new RuntimeException("Looking for: "+delimiter+" but found end of file first...");
        }
        
    }
    
}