package com.example.yahtzeetest;

import com.example.yahtzee.*;
import static org.junit.jupiter.api.Assertions.*;

public class FullHouseScoreConditionTest extends ScoreConditionTestBase {

    // Tests that the condition is only valid if the set of dice has the right number of matching dice
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
}
