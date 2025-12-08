package study.javase.service;

import study.javase.mapper.impl.StudetMapperImpl;
import study.javase.model.Student;

public class ScoreCalculation {
    StudetMapperImpl mapper = new StudetMapperImpl();

    public int ScoreCalculationById(int id) {
        int scoreById = mapper.getScoreById(id);
        return scoreById;
    }

    public int ScoreCalculationByGrade(int grade) {
        int totalScore = 0;
        for (Student student : mapper.getScoreByGrade(grade)) {
            totalScore += student.getScore();
        }
        return totalScore;
    }
}
