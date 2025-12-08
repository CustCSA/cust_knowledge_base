package study.javase;

import study.javase.service.ScoreCalculation;

public class Main {
    public static void main(String[] args) {
        ScoreCalculation scoreCalculation = new ScoreCalculation();
        int scoreById = scoreCalculation.ScoreCalculationById(1);
        System.out.println("Score by ID 1: " + scoreById);
        int totalScoreByGrade = scoreCalculation.ScoreCalculationByGrade(1);
        System.out.println("Total Score by Grade 1: " + totalScoreByGrade);
    }
}
