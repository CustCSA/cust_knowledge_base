package study.javase;

import study.javase.Util.FileUtil;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileUtil fileUtil = new FileUtil();

        try {
            // 1.1
            System.out.println("请输入完整的文件路径（包含文件扩展名）");
            String sourcePath = sc.nextLine();

            // 1.3 - 1.6
            System.out.println("请选择以下任意操作...");
            System.out.println("A 复制-粘贴图片");
            System.out.println("B 剪切-粘贴文本文件");
            System.out.println("请输入操作对应的字母（请注意大小写）...");
            String choice = sc.nextLine();

            // 1.8
            System.out.println("请输入要保存的文件路径（包含完整文件名）...");
            String destPath = sc.nextLine();

            // 1.9 执行相应操作，调用用户原有方法（copyFile, cutFile）
            if ("A".equals(choice)) {
                fileUtil.copyFile(sourcePath, destPath);
                System.out.println("操作完成：已复制到 " + destPath);
            } else if ("B".equals(choice)) {
                fileUtil.cutFile(sourcePath, destPath);
                System.out.println("操作完成：已剪切并粘贴到 " + destPath);
            } else {
                System.out.println("无效的选项：" + choice);
            }
        } catch (IOException e) {
            System.out.println("操作失败: " + e.getMessage());
        } finally {
            sc.close();
        }

    }
}
