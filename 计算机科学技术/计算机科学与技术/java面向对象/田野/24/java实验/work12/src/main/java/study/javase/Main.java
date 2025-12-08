package study.javase;

import java.util.Random;
import java.util.Scanner;

// 共享数据类
class SharedNumber {
    private int number;
    private boolean ready = false;

    public synchronized void setNumber(int number) {
        this.number = number;
        this.ready = true;
        notify(); // 唤醒在等待数字的猜数字线程
    }

    public synchronized int getNumber() {
        while (!ready) {
            try {
                wait(); // 等待随机数生成线程通知
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return number;
    }
}

// 随机数生成任务
class NumberGenerator implements Runnable {
    private SharedNumber shared;

    public NumberGenerator(SharedNumber shared) {
        this.shared = shared;
    }

    @Override
    public void run() {
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        System.out.println("【生成线程】已生成 1~100 的随机数！");
        shared.setNumber(num);
    }
}

// 猜数字任务
class GuessTask implements Runnable {
    private SharedNumber shared;

    public GuessTask(SharedNumber shared) {
        this.shared = shared;
    }

    @Override
    public void run() {
        int number = shared.getNumber();
        Scanner sc = new Scanner(System.in);
        int guess;

        System.out.println("开始猜数字吧！(1~100)");

        while (true) {
            System.out.print("请输入你的猜测：");
            guess = sc.nextInt();

            if (guess > number) {
                System.out.println("太大了！");
            } else if (guess < number) {
                System.out.println("太小了！");
            } else {
                System.out.println("恭喜你，猜中了！！！");
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SharedNumber shared = new SharedNumber();

        Thread t1 = new Thread(new NumberGenerator(shared));
        Thread t2 = new Thread(new GuessTask(shared));

        t1.start();
        t2.start();
    }
}
