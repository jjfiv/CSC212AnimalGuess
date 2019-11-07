package edu.smith.cs.csc212.guess;

import org.junit.Test;
import org.junit.Assert;

public class TreeIOTest {
    @Test
    public void testSimpleTree() {
        DecisionTreeNode knowledge = new DecisionTreeNode("Is it a mammal?", 
            new DecisionTreeNode("Mouse"), 
            new DecisionTreeNode("Snake"));

        TreeIO.save("TreeIOTest.dat", knowledge);
        DecisionTreeNode tree = TreeIO.load("TreeIOTest.dat");

        Assert.assertEquals(knowledge.text, tree.text);
        Assert.assertEquals(knowledge.yes.text, tree.yes.text);
        Assert.assertEquals(knowledge.no.text, tree.no.text);
        
        Assert.assertNull(tree.yes.yes);
        Assert.assertNull(tree.yes.no);
        Assert.assertTrue(tree.yes.isLeaf());

        Assert.assertNull(tree.no.yes);
        Assert.assertNull(tree.no.no);
        Assert.assertTrue(tree.no.isLeaf());
    }

}