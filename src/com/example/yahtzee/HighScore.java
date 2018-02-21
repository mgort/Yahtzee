package com.example.yahtzee;

public class HighScore implements Comparable<HighScore> {

    private String name;
    private int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }


    @Override
    public int compareTo(HighScore o) {
        if(o.score > this.score) return 1;
        if(o.score < this.score) return -1;
        return 0;
    }
}
