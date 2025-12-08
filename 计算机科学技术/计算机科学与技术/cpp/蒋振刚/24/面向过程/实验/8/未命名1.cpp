#include <iostream>
#include <vector>
#include <string>

struct Student {
    int num;
    std::string name;
    std::vector<int> score;
};

void print(const std::vector<Student>& students) {
    for (size_t i = 0; i < students.size(); ++i) {
        const Student& student = students[i];
        std::cout << "学号: " << student.num << ", 姓名: " << student.name << ", 成绩: ";
        for (size_t j = 0; j < student.score.size(); ++j) {
            std::cout << (j > 0 ? ", " : "") << student.score[j];
        }
        std::cout << std::endl;
    }
}

int main() { 
    std::vector<Student> students(5);
    for (size_t i = 0; i < students.size(); ++i) {
        std::cout << "输入第" << (i + 1) << "个学生的学号: ";
        std::cin >> students[i].num;
        std::cout << "输入第" << (i + 1) << "个学生的姓名: ";
        std::cin >> students[i].name;
        
        students[i].score.resize(3);
        std::cout << "输入第" << (i + 1) << "个学生的三门课成绩: ";
        for (size_t j = 0; j < 3; ++j) {
            std::cin >> students[i].score[j];
        }
    }
    print(students);
    return 0;
}
