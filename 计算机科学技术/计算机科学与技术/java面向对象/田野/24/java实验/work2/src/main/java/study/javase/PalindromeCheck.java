package study.javase;
import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个1到9999之间的整数：");
        int num = scanner.nextInt();

        if (num < 1 || num > 9999) {
            System.out.println("输入不在范围内！");
        } else {
            String strNum = String.valueOf(num);
            String reversed = new StringBuilder(strNum).reverse().toString();

            if (strNum.equals(reversed)) {
                System.out.println(num + " 是回文数。");
            } else {
                System.out.println(num + " 不是回文数。");
            }
        }
        scanner.close();
    }
}
