package study.javase.computer;

public class Monitor {
    private String brand;          // 品牌
    private double size;           // 尺寸(英寸)
    private String resolution;     // 分辨率
    private String panelType;      // 面板类型(IPS, VA, TN等)

    public Monitor(String brand, double size, String resolution, String panelType) {
        this.brand = brand;
        this.size = size;
        this.resolution = resolution;
        this.panelType = panelType;
    }

    // 显示内容
    public void displayContent(String content) {
        System.out.println("显示器正在显示: " + content);
        System.out.println("显示器规格: " + brand + " " + size + "英寸 " + resolution + " " + panelType + "面板");
    }

    // 调节亮度
    public void adjustBrightness(int brightness) {
        System.out.println("显示器亮度调节至: " + brightness + "%");
    }

    // 启动
    public void startup() {
        System.out.println("显示器启动成功");
    }

    // 关闭
    public void shutdown() {
        System.out.println("显示器已关闭");
    }

    // Getter方法
    public String getBrand() { return brand; }
    public double getSize() { return size; }
    public String getResolution() { return resolution; }
    public String getPanelType() { return panelType; }
}
