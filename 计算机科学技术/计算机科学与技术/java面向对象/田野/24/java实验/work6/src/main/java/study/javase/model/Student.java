package study.javase.model;

public class Student {
    public int id;
    public String name;
    public int score;
    public int grade;
    public int singScore;
    public  Student(int id, String name, int score, int grade, int singScore) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.grade = grade;
        this.singScore = singScore;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }
    public int getSingScore() {
        return singScore;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSingScore(int singScore) {
        this.singScore = singScore;
    }
}
