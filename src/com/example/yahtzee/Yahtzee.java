package com.example.yahtzee;

import java.util.ArrayList;
import java.util.Scanner;

/** This is Yahtzee
 *
 */
public class Yahtzee {

    private static ArrayList<Dice> allDice;
    private static ArrayList<Dice> hand;
    private static ArrayList<ScoreCondition> scoreConditions;
    private static String line = "---------------------------------------------------------------\n";

    public static void main(String[] args) {
        int turnNumber = 1;
        Scanner scanner = new Scanner( System.in );
        System.out.println("LET'S PLAY SOME YAHTZEE!");

        initialize();

        while(!allScoreConditionsCompleted()) {
            displayTurnNumber(turnNumber);

            hand = allDice;
            rollHand();
            displayScoreConditions();
            displayDice();

            int numberOfRolls = 1;

            while (numberOfRolls < 3){
                // Roll again
                System.out.println("Enter the ids of the dice you want to roll: ");
                String diceIds = scanner.nextLine();
                if(diceIds.equals("")) {
                    break;
                }
                updateHand(diceIds);
                rollHand();
                numberOfRolls++;
                displayDice();
            }

            // The player is finished rolling, they need to score their dice
            displayScoreConditions();

            System.out.println("Enter the id of the condition you would like to score: ");
            String scoreConditionId = scanner.nextLine();

            while(!isValidId(scoreConditionId)){
                System.out.println("You did not enter a valid id.");
                System.out.println("Enter the id of the condition you would like to score: ");
                scoreConditionId = scanner.nextLine();
            }

            scoreTurn(Integer.parseInt(scoreConditionId));

            // End of turn
            turnNumber++;
        }

        // The game is over, display final score
        displayScoreConditions();
        displayFinalScore();
    }

    private static void initialize() {
        // Create 5 dice
        allDice = new ArrayList<Dice>();
        allDice.add(new Dice('a'));
        allDice.add(new Dice('b'));
        allDice.add(new Dice('c'));
        allDice.add(new Dice('d'));
        allDice.add(new Dice('e'));

        // The player starts by rolling all of the dice
        hand = allDice;
        rollHand();

        // Create the score conditions. For now, we only support summing dice of a certain value
        scoreConditions = new ArrayList<ScoreCondition>();
        scoreConditions.add(new SimpleScoreCondition(1, "Aces", 1));
        scoreConditions.add(new SimpleScoreCondition(2, "Twos", 2));
        scoreConditions.add(new SimpleScoreCondition(3, "Threes", 3));
        scoreConditions.add(new SimpleScoreCondition(4, "Fours", 4));
        scoreConditions.add(new SimpleScoreCondition(5, "Fives", 5));
        scoreConditions.add(new SimpleScoreCondition(6, "Sixes", 6));
        scoreConditions.add(new OfAKindScoreCondition(7, "3 of a Kind", 3));
        scoreConditions.add(new OfAKindScoreCondition(8, "4 of a Kind", 4));
        scoreConditions.add(new FullHouseScoreCondition(9, "Full House"));
        scoreConditions.add(new StraightScoreCondition(10, "Small Straight", 4));
        scoreConditions.add(new StraightScoreCondition(11, "Large Straight", 5));
        scoreConditions.add(new OfAKindScoreCondition(12, "Yahtzee", 5));
        scoreConditions.add(new OfAKindScoreCondition(13, "Chance", 0));
    }

    private static boolean allScoreConditionsCompleted() {
        for (ScoreCondition condition : scoreConditions) {
            if(!condition.completed) {
                return false;
            }
        }

        return true;
    }

    private static void displayScoreConditions() {
        System.out.println("Score Conditions:");

        for (ScoreCondition condition : scoreConditions) {
            condition.display();
        }

        System.out.println();
    }

    private static void displayDice() {
        System.out.println("Dice:");

        for (Dice dice : allDice) {
            dice.display();
        }

        System.out.println();
    }

    private static void displayTurnNumber(int turnNum) {
        System.out.println(line);
        System.out.println("Turn #" + turnNum);
        System.out.println(line);
    }

    private static void rollHand() {
        for (Dice dice : hand) {
            dice.roll();
        }

        System.out.println();
    }

    private static void updateHand(String diceIds) {
        hand = new ArrayList<Dice>();

        for (Dice dice : allDice) {
            if(diceIds.indexOf(dice.getId()) >=0 )
                hand.add(dice);
        }
    }

    private static boolean isValidId(String value) {
        // Checks if the given string value is a valid int and a score condition exists with that id
        try {
            int id = Integer.parseInt(value);

            for(ScoreCondition condition : scoreConditions){
                if(condition.getId() == id){
                    return !condition.isCompeted();
                }
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return false;
    }

    private static void scoreTurn(int scoreConditionId){
        scoreConditions.get(scoreConditionId - 1).score(allDice);
    }

    private static void displayFinalScore() {
        int finalScore = 0;

        for(ScoreCondition condition : scoreConditions){
            finalScore += condition.getPoints();
        }

        System.out.println("Your final score is: " + finalScore);
    }
}
