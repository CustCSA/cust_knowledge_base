package study.javase;

import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(1000);
        Scanner scanner= new Scanner(System.in);
        System.out.printf("请输入一个0到1000之间的整数：");
        while (true){
            int innum = scanner.nextInt();
            if (innum > i) {
                System.out.println("猜大了");
            } else if (innum < i) {
                System.out.println("猜小了");
            } else {
                System.out.println("猜对了");
                break;
            }
        }
    }
}
