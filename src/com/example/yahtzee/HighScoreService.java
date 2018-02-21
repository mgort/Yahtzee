package com.example.yahtzee;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

// This class is responsible for file i/o for storing high scores
public class HighScoreService {

    private static final int MAX_NUMBER_OF_HIGH_SCORES = 5;
    private Path filePath = Paths.get(".\\src\\com\\example\\yahtzee\\highscores.txt");
    private ArrayList<HighScore> highScores;

    public HighScoreService() {
        readHighScoresFromFile();
    }

    private ArrayList<HighScore> readHighScoresFromFile() {
        List<String> allLines = new ArrayList<>();

        try {
            allLines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        highScores = new ArrayList<>();

        for(String line : allLines) {
            String[] highScore = line.split(",");
            highScores.add(new HighScore(highScore[0], Integer.parseInt(highScore[1])));
        }

        Collections.sort(highScores);

        return highScores;
    }

    public void displayHighScores() {
        System.out.println("\nHigh Scores:");
        for(HighScore score : highScores) {
            System.out.println(score.getName() + " - " + score.getScore() + "pts");
        }
    }

    public boolean isHighScore(int score) {
        if(highScores.size() < MAX_NUMBER_OF_HIGH_SCORES) return true;

        for(HighScore highScore : highScores) {
            if(score > highScore.getScore()) {
                return true;
            }
        }

        return false;
    }

    public void addHighScore(String name, int score) {
        highScores.add(new HighScore(name, score));
        Collections.sort(highScores);
        highScores = new ArrayList<HighScore>(highScores.subList(0, Math.min(highScores.size(), MAX_NUMBER_OF_HIGH_SCORES)));
        saveHighScores();
    }

    private void saveHighScores(){
        try {
            for(HighScore highScore : highScores){
                String line = String.format("%s,%s\n", highScore.getName(), highScore.getScore());
                Files.write(filePath, line.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
