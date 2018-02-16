package com.example.yahtzeetest;

import com.example.yahtzee.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StraightScoreConditionTest extends ScoreConditionTestBase {

    // Tests that the condition is valid for small straights
    @org.junit.jupiter.api.Test
    public void isValidSmallStraight() {
        StraightScoreCondition condition = new StraightScoreCondition(1, "small straight test", 4);

        assertTrue(condition.isValid(createFiveDice(1, 2, 3, 4, 2)));
        assertTrue(condition.isValid(createFiveDice(2, 4, 3, 2, 1)));
        assertTrue(condition.isValid(createFiveDice(3, 5, 3, 4, 2)));
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 3, 6)));
        assertFalse(condition.isValid(createFiveDice(5, 2, 3, 6, 1)));
        assertFalse(condition.isValid(createFiveDice(3, 4, 4, 5, 3)));
    }

    // Tests that the condition is valid for large straights
    @org.junit.jupiter.api.Test
    public void isValidLargeStraight() {
        StraightScoreCondition condition = new StraightScoreCondition(1, "large straight test", 5);

        assertTrue(condition.isValid(createFiveDice(1, 2, 3, 4, 5)));
        assertTrue(condition.isValid(createFiveDice(6, 5, 4, 3, 2)));
        assertTrue(condition.isValid(createFiveDice(3, 5, 1, 2, 4)));
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 4, 2)));
        assertFalse(condition.isValid(createFiveDice(2, 4, 3, 2, 1)));
        assertFalse(condition.isValid(createFiveDice(3, 5, 3, 4, 2)));
        assertFalse(condition.isValid(createFiveDice(1, 2, 3, 3, 6)));
        assertFalse(condition.isValid(createFiveDice(5, 2, 3, 6, 1)));
        assertFalse(condition.isValid(createFiveDice(3, 4, 4, 5, 3)));
    }

    // Tests that small straights score 30 points and large straights score 40 points
    @org.junit.jupiter.api.Test
    public void scoreValid() {
        // Small straight
        StraightScoreCondition condition = new StraightScoreCondition(1, "small straight test", 4);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 3, 4, 5);
        condition.score(allDice);

        assertEquals(30, condition.getPoints());
        assertTrue(condition.isCompeted());

        // Large straight
        condition = new StraightScoreCondition(1, "large straight test", 5);
        condition.score(allDice);

        assertEquals(40, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests that dice that aren't straights get a score of 0
    @org.junit.jupiter.api.Test
    public void scoreInvalid() {
        // Small straight
        StraightScoreCondition condition = new StraightScoreCondition(1, "small straight test", 4);
        ArrayList<Dice> allDice = createFiveDice(1, 1, 1, 1, 1);
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());

        // Large straight
        condition = new StraightScoreCondition(1, "large straight test", 5);
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());
    }
}
