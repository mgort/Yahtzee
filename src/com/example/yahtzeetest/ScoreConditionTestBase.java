package com.example.yahtzeetest;

import com.example.yahtzee.Dice;

import java.util.ArrayList;

class ScoreConditionTestBase {

    ArrayList<Dice> createFiveDice(int dice1, int dice2, int dice3, int dice4, int dice5) {
        ArrayList<Dice> allDice = new ArrayList<>();

        allDice.add(new Dice(dice1));
        allDice.add(new Dice(dice2));
        allDice.add(new Dice(dice3));
        allDice.add(new Dice(dice4));
        allDice.add(new Dice(dice5));

        return allDice;
    }
}
