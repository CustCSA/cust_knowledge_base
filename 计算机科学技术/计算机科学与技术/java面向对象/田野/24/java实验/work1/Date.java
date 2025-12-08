package study.javase;

import java.time.LocalDate;
import java.util.Scanner;

public class Date {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        LocalDate date = LocalDate.parse(input);
        LocalDate nextDate = date.plusDays(1);
        System.out.println("Next date: " + nextDate);
        scanner.close();
    }
}