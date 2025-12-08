package study.javase.service;

import study.javase.exception.FailException;
import study.javase.mapper.Mapper;
import study.javase.mapper.impl.StudentMapperImpl;
import study.javase.model.Student;

public class Service {

	private Mapper mapper = new StudentMapperImpl();

	public Student queryStudentById(Integer id) throws FailException {
		Student s = mapper.getStudentById(id);
		if (s == null) {
			throw new IndexOutOfBoundsException("查无此学生，id=" + id);
		}
		if (s.getScore() != null && s.getScore() < 60) {
			throw new FailException("学生成绩不及格，id=" + id + "，score=" + s.getScore());
		}
		return s;
	}

}
