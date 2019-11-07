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
