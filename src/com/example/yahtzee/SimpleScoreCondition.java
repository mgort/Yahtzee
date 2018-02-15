package com.example.yahtzee;

import java.util.ArrayList;

// Simple Score Condition; all of the score conditions that are just the sums of certain values of dice
public class SimpleScoreCondition extends ScoreCondition {

    private int diceValue;

    public SimpleScoreCondition(int id, String desc, int diceVal){
        this.id = id;
        description = desc;
        diceValue = diceVal;
    }

    @Override
    public void score(ArrayList<Dice> allDice) {
        int total = 0;

        for(Dice dice : allDice){
            if(dice.getValue() == diceValue)
                total += diceValue;
        }

        this.points = total;
        this.completed = true;
    }

    @Override
    public boolean isValid(ArrayList<Dice> allDice) {
        // All dice combinations can be scored
        return true;
    }


}
