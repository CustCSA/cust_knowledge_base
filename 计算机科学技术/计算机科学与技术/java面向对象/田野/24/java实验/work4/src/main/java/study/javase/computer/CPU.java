package study.javase.computer;

public class CPU {
    private String brand;      // 品牌
    private String model;      // 型号
    private double frequency;  // 主频(GHz)
    private int cores;         // 核心数

    public CPU(String brand, String model, double frequency, int cores) {
        this.brand = brand;
        this.model = model;
        this.frequency = frequency;
        this.cores = cores;
    }

    // 处理数据
    public void processData() {
        System.out.println(brand + " " + model + " CPU正在处理数据...");
        System.out.println("主频: " + frequency + "GHz, 核心数: " + cores);
    }

    // 启动
    public void startup() {
        System.out.println("CPU启动成功");
    }

    // 关闭
    public void shutdown() {
        System.out.println("CPU已关闭");
    }

    // Getter方法
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getFrequency() { return frequency; }
    public int getCores() { return cores; }
}
