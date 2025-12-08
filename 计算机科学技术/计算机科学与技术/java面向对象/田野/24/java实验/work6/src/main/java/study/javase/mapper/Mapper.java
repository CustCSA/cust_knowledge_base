package study.javase.mapper;

import study.javase.model.Student;

import java.util.List;

public interface Mapper {
    public int getScoreById(int id);
    public List<Student> getScoreByGrade(int grade);
}
