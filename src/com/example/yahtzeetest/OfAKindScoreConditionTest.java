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

    // Tests that score will be the sum of the dice if a valid set is given
    @org.junit.jupiter.api.Test
    public void scoreValid() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 3, 3, 3);
        condition.score(allDice);

        assertEquals(12, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests that score will be 50 if a yahtzee is given
    @org.junit.jupiter.api.Test
    public void scoreYahtzee() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "yahtzee test", 5);
        ArrayList<Dice> allDice = createFiveDice(3, 3, 3, 3, 3);
        condition.score(allDice);

        assertEquals(50, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests score will be 0 if the given dice are not valid
    @org.junit.jupiter.api.Test
    public void scoreInvalid() {
        OfAKindScoreCondition condition = new OfAKindScoreCondition(1, "3 of a kind test", 3);
        ArrayList<Dice> allDice = new ArrayList<Dice>();
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());
    }
}