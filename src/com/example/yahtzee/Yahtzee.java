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

            System.out.println("Would you like to roll again? (y/n): ");
            String continueResponse = scanner.nextLine();

            while (numberOfRolls < 3 && !continueResponse.contains("n")){
                // Roll again
                System.out.println("Enter the ids of the dice you want to roll: ");
                String diceIds = scanner.nextLine();
                updateHand(diceIds);
                rollHand();
                numberOfRolls++;
                displayDice();

                if(numberOfRolls < 3) {
                    System.out.println("Would you like to roll again? (y/n): ");
                    continueResponse = scanner.nextLine();
                }
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

    public static void initialize() {
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
        scoreConditions.add(new SimpleScoreCondition(1, "Sum of all dice with a value of 1", 1));
        scoreConditions.add(new SimpleScoreCondition(2, "Sum of all dice with a value of 2", 2));
        scoreConditions.add(new SimpleScoreCondition(3, "Sum of all dice with a value of 3", 3));
        scoreConditions.add(new SimpleScoreCondition(4, "Sum of all dice with a value of 4", 4));
        scoreConditions.add(new SimpleScoreCondition(5, "Sum of all dice with a value of 5", 5));
        scoreConditions.add(new SimpleScoreCondition(6, "Sum of all dice with a value of 6", 6));

    }

    public static boolean allScoreConditionsCompleted() {
        for (ScoreCondition condition : scoreConditions) {
            if(!condition.completed) return false;
        }

        return true;
    }

    public static void displayScoreConditions() {
        System.out.println("Score Conditions:");

        for (ScoreCondition condition : scoreConditions) {
            condition.display();
        }

        System.out.println();
    }

    public static void displayDice() {
        System.out.println("Dice:");

        for (Dice dice : allDice) {
            dice.display();
        }

        System.out.println();
    }

    public static void displayTurnNumber(int turnNum) {
        System.out.println(line);
        System.out.println("Turn #" + turnNum);
        System.out.println(line);
    }

    public static void rollHand() {
        for (Dice dice : hand) {
            dice.roll();
        }

        System.out.println();
    }

    public static void updateHand(String diceIds) {
        hand = new ArrayList<Dice>();

        for (Dice dice : allDice) {
            if(diceIds.indexOf(dice.getId()) >=0 )
                hand.add(dice);
        }
    }

    public static boolean isValidId(String value) {
        // Checks if the given string value is a valid int and a score condition exists with that id
        try {
            int id = Integer.parseInt(value);

            for(ScoreCondition condition : scoreConditions){
                if(condition.getId() == id){
                    if(condition.isCompeted())
                        return false;
                    return true;
                }
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return false;
    }

    public static void scoreTurn(int scoreConditionId){
        scoreConditions.get(scoreConditionId - 1).score(allDice);
    }

    public static void displayFinalScore() {
        int finalScore = 0;

        for(ScoreCondition condition : scoreConditions){
            finalScore += condition.points;
        }

        System.out.println("Your final score is: " + finalScore);
    }
}
