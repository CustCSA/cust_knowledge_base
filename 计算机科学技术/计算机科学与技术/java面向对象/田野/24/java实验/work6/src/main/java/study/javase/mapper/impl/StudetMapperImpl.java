package study.javase.mapper.impl;

import study.javase.mapper.Mapper;
import study.javase.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudetMapperImpl implements Mapper {
    private static Map<Integer, Student> studentMap = new HashMap<>();

    static {
        studentMap.put(1, new Student(1, "张三", 80, 1, 8));
        studentMap.put(2, new Student(2, "李四", 90, 2, 9));
        studentMap.put(3, new Student(3, "王五", 85, 1, 7));
    }

    @Override
    public int getScoreById(int id) {
        Student student = studentMap.get(id);
        if (student != null) {
            return student.getScore();
        }
        return -1; // 如果未找到学生，返回-1表示错误
    }

    @Override
    public List<Student> getScoreByGrade(int grade) {
        List<Student> studentList = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getGrade() == grade) {
                studentList.add(student);
            }
        }
        return studentList;
    }

}
