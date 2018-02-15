package com.example.yahtzeetest;

import com.example.yahtzee.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class OfAKindScoreConditionTest extends ScoreConditionTestBase {

    // Tests that the condition is only valid if the set of dice has the right number of matching dice
    @org.junit.jupiter.api.Test
    public void isValid() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);

        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 5, 6))); // one of a kind
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 3, 6))); // two of a kind
        assertTrue(condition.isValid(createFiveDice(1, 2, 3, 3, 3))); // three of a kind
        assertTrue(condition.isValid(createFiveDice(3, 3, 3, 3, 6))); // four of a kind
        assertTrue(condition.isValid(createFiveDice(3, 3, 3, 3, 3))); // five of a kind
    }

    // Tests scoring a normal, valid set of dice that contains some number of 5s
    @org.junit.jupiter.api.Test
    public void scoreNormal() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 5, 5, 5);
        condition.score(allDice);

        assertEquals(18, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests that score will always return the sum of the dice
    @org.junit.jupiter.api.Test
    public void scoreAlwaysSums() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 3, 4, 5);
        condition.score(allDice);

        assertEquals(15, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests scoring an empty list of dice
    @org.junit.jupiter.api.Test
    public void scoreNoDice() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);
        ArrayList<Dice> allDice = new ArrayList<Dice>();
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests scoring a set of dice with negative numbers
    @org.junit.jupiter.api.Test
    public void scoreNegativeDice() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 3, -3, -6);
        condition.score(allDice);

        assertEquals(-3, condition.getPoints());
        assertTrue(condition.isCompeted());
    }
}