package com.example.yahtzeetest;

import com.example.yahtzee.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FullHouseScoreConditionTest extends ScoreConditionTestBase {

    // Tests that the condition is only valid if the set of dice has 2 of a kind AND 3 of a kind of a different value
    @org.junit.jupiter.api.Test
    public void isValid() {
        FullHouseScoreCondition condition = new FullHouseScoreCondition(1, "full house test");

        assertTrue(condition.isValid(createFiveDice(1, 1, 2, 2, 2))); // normal full house
        assertTrue(condition.isValid(createFiveDice(1, 2, 2, 1, 2))); // shuffled full house
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 4, 6))); // one of a kind
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 3, 6))); // just two of a kind
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 3, 3))); // just three of a kind
        assertFalse(condition.isValid(createFiveDice(3, 3, 3, 3, 3))); // five of a kind
    }

    // Tests that full houses score 25 points
    @org.junit.jupiter.api.Test
    public void scoreValid() {
        FullHouseScoreCondition condition = new FullHouseScoreCondition(1, "full house test");
        ArrayList<Dice> allDice = createFiveDice(1, 1, 2, 2, 2);
        condition.score(allDice);

        assertEquals(25, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests that dice that aren't full houses get a score of 0
    @org.junit.jupiter.api.Test
    public void scoreInvalid() {
        FullHouseScoreCondition condition = new FullHouseScoreCondition(1, "full house test");
        ArrayList<Dice> allDice = createFiveDice(1, 1, 1, 1, 1);
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());
    }
}
