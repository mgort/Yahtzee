package com.example.yahtzee;

import java.util.ArrayList;

// Of-a-kind Score Condition; 3 of a kind, 4 of a kind, Chance
public class OfAKindScoreCondition extends ScoreCondition {

    private int ofAKindAmount;

    public OfAKindScoreCondition(int id, String desc, int ofAKindAmount) {
        this.id = id;
        description = desc;
        this.ofAKindAmount = ofAKindAmount;
    }

    @Override
    public void score(ArrayList<Dice> allDice) {
        if(isValid(allDice)) {
            // Points = the sum of all dice values
            int total = 0;

            for (Dice dice : allDice) {
                total += dice.getValue();
            }

            this.points = total;
        } else {
            this.points = 0;
        }

        this.completed = true;
    }

    @Override
    public boolean isValid(ArrayList<Dice> allDice) {
        // Only dice with the minimum amount of identical dice can be scored

        int count = 0;

        // Iterate through each dice value
        for (int i = 1; i <= 6; i++) {

            for (Dice dice : allDice) {
                if (dice.getValue() == i){
                    count++;
                }
            }

            if (count >= ofAKindAmount)
                return true;

            count = 0;
        }

        return false;
    }
}
