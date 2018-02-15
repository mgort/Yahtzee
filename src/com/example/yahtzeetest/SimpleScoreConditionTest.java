package com.example.yahtzeetest;

import com.example.yahtzee.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleScoreConditionTest extends ScoreConditionTestBase {

    // Tests scoring a normal, valid set of dice that contains some number of 3s
    @org.junit.jupiter.api.Test
    public void scoreNormal() {
        SimpleScoreCondition condition = new SimpleScoreCondition(1, "Test summing all 3s", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 3, 3, 4);
        condition.score(allDice);

        assertEquals(6, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests scoring a normal, valid set of dice that contains no 3s
    @org.junit.jupiter.api.Test
    public void scoreNoThrees() {
        SimpleScoreCondition condition = new SimpleScoreCondition(1, "Test summing all 3s", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 4, 5, 6);
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests scoring an empty list of dice
    @org.junit.jupiter.api.Test
    public void scoreNoDice() {
        SimpleScoreCondition condition = new SimpleScoreCondition(1, "Test summing all 3s", 3);
        ArrayList<Dice> allDice = new ArrayList<Dice>();
        condition.score(allDice);

        assertEquals(0, condition.getPoints());
        assertTrue(condition.isCompeted());
    }

    // Tests scoring a set of dice with negative numbers
    @org.junit.jupiter.api.Test
    public void scoreNegativeDice() {
        SimpleScoreCondition condition = new SimpleScoreCondition(1, "Test summing all 3s", 3);
        ArrayList<Dice> allDice = createFiveDice(1, 2, 3, -3, -6);
        condition.score(allDice);

        assertEquals(3, condition.getPoints());
        assertTrue(condition.isCompeted());
    }
}