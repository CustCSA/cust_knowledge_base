package study.javase;

import study.javase.exception.FailException;
import study.javase.model.Student;
import study.javase.service.Service;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要查询的学生id：");
		Integer id = null;
		try {
			id = Integer.valueOf(sc.nextLine().trim());
		} catch (Exception e) {
			System.out.println("输入的id格式不正确");
			return;
		}

		Service service = new Service();
		try {
			Student s = service.queryStudentById(id);
			System.out.println("查询成功： id=" + s.getId() + ", name=" + s.getName() + ", age=" + s.getAge() + ", score=" + s.getScore());
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("错误：" + ex.getMessage());
		} catch (FailException fe) {
			System.out.println("成绩未通过：" + fe.getMessage());
		}
	}
}
