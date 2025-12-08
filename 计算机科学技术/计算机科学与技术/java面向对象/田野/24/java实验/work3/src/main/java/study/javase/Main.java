package study.javase;

import study.javase.model.User;
import study.javase.service.UserService;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static User currentUser=null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("银行管理系统，输入 exit 退出");

        while (true) {
            System.out.print("请输入账号: ");
            String username = scanner.nextLine();
            if ("exit".equalsIgnoreCase(username)) {
                System.out.println("退出系统");
                break;
            }

            System.out.print("请输入密码: ");
            String password = scanner.nextLine();
            if ("exit".equalsIgnoreCase(password)) {
                System.out.println("退出系统");
                break;
            }

            User tempUser = userService.login(password);
            if (tempUser != null && Objects.equals(tempUser.getName(), username)) {
                currentUser = tempUser;
                System.out.printf("登录成功，欢迎 %s！\n", currentUser.getName());
                break;
            } else {
                System.out.println("账号或密码错误，请重新输入。\n");
            }
        }

        if (currentUser != null) {
            while (true) {
                System.out.printf("当前余额: %.2f\n", currentUser.getDeposit());
                System.out.print("请输入存款金额 (输入 exit 退出): ");
                String depositInput = scanner.nextLine();
                if ("exit".equals(depositInput)) {
                    System.out.println("退出系统");
                    break;
                }
                try {
                    float depositAmount = Float.parseFloat(depositInput);
                    if (depositAmount <= 0) {
                        System.out.println("存款金额必须大于零，请重新输入。");
                        continue;
                    }
                    currentUser.setDeposit(currentUser.getDeposit() + depositAmount);
                    userService.updateUser(currentUser);
                    System.out.printf("存款成功！当前余额: %.2f\n", currentUser.getDeposit());
                } catch (NumberFormatException e) {
                    System.out.println("无效的金额格式，请输入数字。");
                }
                userService.updateUser(currentUser);
            }
        }
        scanner.close();
    }
}