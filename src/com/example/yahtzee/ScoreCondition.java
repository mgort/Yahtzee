package com.example.yahtzee;

import java.util.ArrayList;

public abstract class ScoreCondition {

    protected int id;
    protected String description;
    protected int points;
    protected boolean completed;

    public int getId(){
        return id;
    }

    public int getPoints(){
        return points;
    }

    public boolean isCompeted() {
        return completed;
    }

    public void display() {
        String completedSection = "";

        if(completed){
            completedSection =  " | " + points + "pts. Completed!";
        }

        System.out.println(id + ") " + description + completedSection);
    }

    public abstract void score(ArrayList<Dice> allDice);

    public abstract boolean isValid(ArrayList<Dice> allDice);
}
