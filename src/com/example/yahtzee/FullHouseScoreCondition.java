package com.example.yahtzee;

import java.util.ArrayList;

// Full House Score Condition; 2 of a kind and 3 of a kind (of a different value)
public class FullHouseScoreCondition extends ScoreCondition {

    public FullHouseScoreCondition(int id, String desc){
        this.id = id;
        description = desc;
    }

    @Override
    public void score(ArrayList<Dice> allDice) {
        if(isValid(allDice)) {
            this.points = 25;
        } else {
            this.points = 0;
        }

        this.completed = true;
    }

    @Override
    public boolean isValid(ArrayList<Dice> allDice) {
        String valueCounts = "";
        int count = 0;

        // Iterate through each dice value
        for (int i = 1; i <= 6; i++) {

            for (Dice dice : allDice) {
                if (dice.getValue() == i){
                    count++;
                }
            }

            valueCounts = valueCounts + String.valueOf(count);

            count = 0;
        }

        return valueCounts.contains("2") && valueCounts.contains("3");
    }

}
