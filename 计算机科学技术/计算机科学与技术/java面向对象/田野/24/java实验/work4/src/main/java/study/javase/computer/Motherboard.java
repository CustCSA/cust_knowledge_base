package study.javase.computer;

public class Motherboard {
    private String brand;          // 品牌
    private String model;          // 型号
    private String chipset;        // 芯片组
    private String socketType;     // CPU插槽类型

    public Motherboard(String brand, String model, String chipset, String socketType) {
        this.brand = brand;
        this.model = model;
        this.chipset = chipset;
        this.socketType = socketType;
    }

    // 连接各个组件
    public void connectComponents() {
        System.out.println("主板正在连接各个硬件组件...");
        System.out.println("主板型号: " + brand + " " + model + " (芯片组: " + chipset + ")");
    }

    // 管理电源
    public void managePower() {
        System.out.println("主板正在管理电源分配");
    }

    // 数据传输
    public void transferData() {
        System.out.println("主板正在进行数据传输");
    }

    // 启动
    public void startup() {
        System.out.println("主板启动成功");
    }

    // 关闭
    public void shutdown() {
        System.out.println("主板已关闭");
    }

    // Getter方法
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getChipset() { return chipset; }
    public String getSocketType() { return socketType; }
}
