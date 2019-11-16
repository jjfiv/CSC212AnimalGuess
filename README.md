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

## Rubric (100)

The core adds to 90 points, and there are two 10-point challenges.

### (15) Reflection and Rubric Submitted

There's a [Google Form once again for submitting what you accomplished](https://forms.gle/znWbBi1YHBiG2hR56).

### (15) Code Compiles and Tests Run.

It is very important your code compiles and you do not change the directory structure. If you have issues importing the code into Eclipse, let me know and we can fix it together. I plan to grade these as automatically as possible.

## (15) Step 1: Playing!

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

## (30) Step 2: Updating!

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

## (15) Step 3: Saving the output.

Most of the hard work is done for you in the ``TreeIO`` class. Saving and loading a DecisionTree is a recursive algorithm.

1. Modify the code to use the provided ``knowledge`` variable if it cannot find the ``knowledge.dat`` file. To check if the file exists, create a new ``java.io.File`` object, and call ``exists`` on it; otherwise ``TreeIO.load`` the ``knowledge.dat`` file into the ``knowledge variable``.
2. Modify the code to save the data every time after the user plays the game.
3. Modify your playing to return a boolean; ``true`` if the computer wins, an ``false`` if the player wins. If the player wins, the data changes; only save the data to the ``knowledge.dat`` file if it has changed.

## (10) Challenge: do the opposite implementation

If you implemented it with loops, also implement it with recursion. If you implemented it first with recursion, also build the loop based version. You will be able to reuse most of the logic you've designed.

## (10) Challenge: Print the knowledge variable to a HTML file.

In HTML, you get to define lists as follows:

```html
<ul>
  <li>First</li>
  <li>Second</li>
  <li>Third</li>
</ul>
```

Make a new class that ``TreeIO.load`` the data from the game, and prints it out in the following style; indentation/spacing doesn't matter so much (but is worth bonus points if you get it nice).

```html
Is it a mammal?
<ul>
  <li>YES: Is it tiny?
    <ul>
      <li>YES: ANIMAL: Mouse</li>
      <li>NO: ANIMAL: Elephant</li>
    </ul>
  </li>
  <li>NO: Is it a bird?
    <ul>
      <li>YES: Is it nocturnal?
        <ul>
          <li>YES: ANIMAL: Owl</li>
          <li>NO: ANIMAL: Chicken</li>
        </ul>
      </li>
      <li>NO: ANIMAL: Snake</li>
    </ul>
  </li>
</ul>
```

You can then open and look at the knowledge the game has learned in your browser!