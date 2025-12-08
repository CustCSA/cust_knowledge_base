package study.javase;

import study.javase.computer.*;

public class Main {
    public static void main(String[] args) {
        // 创建各个硬件组件
        CPU cpu = new CPU("Intel", "i7-12700K", 3.6, 12);
        Memory memory = new Memory("Kingston", 16, "DDR4", 3200);
        Motherboard motherboard = new Motherboard("ASUS", "ROG STRIX B660-F", "B660", "LGA1700");
        Monitor monitor = new Monitor("Dell", 27.0, "2560x1440", "IPS");

        // 创建电脑对象
        Computer computer = new Computer("我的工作电脑", cpu, memory, motherboard, monitor);

        // 显示系统信息
        computer.showSystemInfo();

        // 开机
        computer.powerOn();

        // 运行程序
        computer.runProgram("Visual Studio Code");
        computer.runProgram("Google Chrome");

        // 处理文档
        computer.processDocument("Java作业.docx");
        computer.processDocument("项目报告.pdf");

        // 调节显示器亮度
        computer.adjustMonitorBrightness(80);

        // 关机
        computer.powerOff();

        // 尝试在关机状态下运行程序
        computer.runProgram("记事本");
    }
}
