package com.example.yahtzee;

public class Dice {

    private char id;
    private int value;

    public Dice(char id) {
        this.id = id;
    }

    public Dice(int val) {
        value = val;
    }

    public void roll() {
        value = 1 + (int) (Math.random() * ((6 - 1) + 1));
    }

    public int getValue() {
        if (value == 0) {
            this.roll();
        }

        return value;
    }

    public char getId() {
        return id;
    }

    public void display() {
        System.out.print(id + ") " + value + "\t");

    }
}
