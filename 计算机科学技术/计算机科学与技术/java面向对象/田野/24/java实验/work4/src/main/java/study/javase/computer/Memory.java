package study.javase.computer;

public class Memory {
    private String brand;      // 品牌
    private int capacity;      // 容量(GB)
    private String type;       // 类型(DDR4, DDR5等)
    private int speed;         // 频率(MHz)

    public Memory(String brand, int capacity, String type, int speed) {
        this.brand = brand;
        this.capacity = capacity;
        this.type = type;
        this.speed = speed;
    }

    // 存储数据
    public void storeData(String data) {
        System.out.println("内存正在存储数据: " + data);
        System.out.println("内存规格: " + brand + " " + capacity + "GB " + type + " " + speed + "MHz");
    }

    // 读取数据
    public void readData(String data) {
        System.out.println("内存正在读取数据: " + data);
    }

    // 启动
    public void startup() {
        System.out.println("内存启动成功");
    }

    // 关闭
    public void shutdown() {
        System.out.println("内存已关闭");
    }

    // Getter方法
    public String getBrand() { return brand; }
    public int getCapacity() { return capacity; }
    public String getType() { return type; }
    public int getSpeed() { return speed; }
}
