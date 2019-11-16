# CSC212AnimalGuess
Animal Guessing Game based on Decision Trees.

In this assignment you will implement a decision tree that learns while playing. This assignment is based on [N. Howe's Decision Tree assignment](http://www.cs.smith.edu/~nhowe/teaching/csc212/Assignments/dtree.php).

## What does it do?

Your output will look something like this; with the ``?>`` sequence indicating where the user was asked to type.

```
Think of an animal.
I'll try to guess it.
Is your animal a Mouse?
?> no
I got it wrong.
Please help me to learn.
What was your animal?
?> Snake
Type a yes or no question that would distinguish between a Crocodile and a Mouse
?> Is it a mammal?
Would you answer yes to this question for the Crocodile?
?> no
Play again?
?> yes
Think of an animal.
I''ll try to guess it.
Is it a mammal?
?> no
Is your animal a Snake?
?> yes
I guessed it!
Play again?
?> no
```

## Objective: Learn by playing

Your program should try to guess the animal by asking the questions in a stored decision tree, 
and following the appropriate branch for the answer given by the user. 
The leaves will contain an animal to guess. 
If the system guesses wrong, it will ask the user to add a question distinguishing the wrong guess from the correct answer. 
This question will be used to extend the decision tree. 

## Useful Classes

Using the provided ``TreeIO`` class, you can ``load`` and ``save`` your decision tree to a file, 
so that questions added during one session can be saved for later.

Using the provided ``TextInput`` class, you can ask a yes/no question with ``confirm`` and get a line of text from the user with ``readLine``. 

## Step 1: Playing!

This is a guided tree-traversal. The ``TextInput`` object, e.g., ``input.confirm("question?")`` will be of great help to you.

```
Welcome to the guessing game!
To play, first think of an animal.
Is it a mammal? (y/n):  y
Is it a Mouse? (y/n):  n
You win!
Do you want to play again? (y/n):  y
To play, first think of an animal.
Is it a mammal? (y/n):  n
Is it a bird? (y/n):  n
Is it a Snake? (y/n):  y
I win!
Do you want to play again? (y/n): 
```

This can be done with a loop, OR with recursion.

## Step 2: Updating!

```
Welcome to the guessing game!
To play, first think of an animal.
Is it a mammal? (y/n):  y
Is it a Mouse? (y/n):  n
You win!
...
What animal were you thinking of?
Elephant
What is a question that would be YES for a Mouse but not a Elephant?
Is it really small?
Do you want to play again? (y/n):  y
To play, first think of an animal.
Is it a mammal? (y/n):  y
Is it really small? (y/n):  y
Is it a Mouse? (y/n):  y
I win!
Do you want to play again? (y/n):  y
To play, first think of an animal.
Is it a mammal? (y/n):  y
Is it really small? (y/n):  n
Is it a Elephant? (y/n):  y
I win!
Do you want to play again? (y/n):  
```

- In order to update the tree, you will need to ask for a new question, and a new animal.
- Add a method with the signature ``public String getNotEmptyInput(String question)`` to the ``TextInput`` class. It will call the ``readLine`` method, but make sure they typed something and didn't just accidentally press ENTER.
- Once you find the ``current`` node that the game thought was the answer; you can use two strategies to expand the tree:
    1. You can keep track of the ``parent`` of that node so you can insert a new node in either ``yes`` or ``no`` where ``current`` used to be. (Harder in the recursive solution.
    2. You can move the animal in ``current`` to a new node and then turn the original into a question.

## Step 3: Saving the output.

Most of the hard work is done for you in the ``TreeIO`` class. Saving and loading a DecisionTree is a recursive algorithm.

1. Modify the code to use the provided ``knowledge`` variable if it cannot find the ``knowledge.dat`` file. To check if the file exists, create a new ``java.io.File`` object, and call ``exists`` on it; otherwise ``TreeIO.load`` the ``knowledge.dat`` file into the ``knowledge variable``.
2. Modify the code to save the data every time after the user plays the game.
3. Modify your playing to return a boolean; ``true`` if the computer wins, an ``false`` if the player wins. If the player wins, the data changes; only save the data to the ``knowledge.dat`` file if it has changed.

## Challenge: Print the knowledge variable to a HTML file.