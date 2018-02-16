package com.example.yahtzee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Straight Score Condition; must have consecutive dice values for a certain number of dice
public class StraightScoreCondition extends ScoreCondition {

    private int numberOfDiceNeeded;

    public StraightScoreCondition(int id, String desc, int numberOfDiceNeeded) {
        this.id = id;
        description = desc;
        this.numberOfDiceNeeded = numberOfDiceNeeded;
    }

    @Override
    public void score(ArrayList<Dice> allDice) {

        if (isValid(allDice)) {
            if (numberOfDiceNeeded >= 5) {
                this.points = 40;
            } else {
                this.points = 30;
            }
        } else{
            this.points = 0;
        }

        this.completed = true;
    }

    @Override
    public boolean isValid(ArrayList<Dice> allDice) {
        if(numberOfDiceNeeded == 4) {
            // Small Straight

            for(int i = 0; i < allDice.size(); i++){
                List<Dice> subList = new ArrayList<>(allDice);
                subList.remove(i);

                if(elementsConsecutive(subList)){
                    return true;
                }
            }

        } else {
            // Large Straight
            return elementsConsecutive(new ArrayList<>(allDice));
        }

        return false;

    }

    private boolean elementsConsecutive(List<Dice> list) {
        Collections.sort(list);

        for(int i = 0; i < list.size() - 1; i++){
            if(list.get(i).getValue() + 1 != list.get(i+1).getValue()){
                return false;
            }
        }

        return true;
    }
}
