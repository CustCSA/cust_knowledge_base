package study.javase.mapper.impl;

import study.javase.mapper.Mapper;
import study.javase.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentMapperImpl implements Mapper {

    public static Map studentmap = new HashMap<Integer, Student>();
    static {
        studentmap.put(1, new Student(1, "zhangsan", 18, 90));
        studentmap.put(2, new Student(2, "lisi", 19, 85));
        studentmap.put(3, new Student(3, "wangwu", 17, 95));
        studentmap.put(4, new Student(4, "zhaoliu", 17, 50));
        studentmap.put(5, new Student(5, "zhaoliu", 17, 58));
        studentmap.put(6, new Student(6, "zhaoliu", 17, 66));
    }

    @Override
    public Student getStudentById(Integer id) {
        return (Student) studentmap.get(id);
    }
}
