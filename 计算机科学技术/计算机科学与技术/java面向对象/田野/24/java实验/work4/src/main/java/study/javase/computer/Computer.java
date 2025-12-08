package study.javase.computer;

public class Computer {
    private CPU cpu;                    // CPU组件
    private Memory memory;              // 内存组件
    private Motherboard motherboard;    // 主板组件
    private Monitor monitor;            // 显示器组件
    private String computerName;        // 电脑名称
    private boolean isRunning;          // 运行状态

    public Computer(String computerName, CPU cpu, Memory memory, Motherboard motherboard, Monitor monitor) {
        this.computerName = computerName;
        this.cpu = cpu;
        this.memory = memory;
        this.motherboard = motherboard;
        this.monitor = monitor;
        this.isRunning = false;
    }

    // 开机
    public void powerOn() {
        System.out.println("=== 电脑 [" + computerName + "] 正在开机 ===");
        motherboard.startup();
        cpu.startup();
        memory.startup();
        monitor.startup();
        motherboard.connectComponents();
        motherboard.managePower();
        isRunning = true;
        System.out.println("电脑开机完成！");
        System.out.println();
    }

    // 关机
    public void powerOff() {
        System.out.println("=== 电脑 [" + computerName + "] 正在关机 ===");
        monitor.shutdown();
        memory.shutdown();
        cpu.shutdown();
        motherboard.shutdown();
        isRunning = false;
        System.out.println("电脑已关机！");
        System.out.println();
    }

    // 运行程序
    public void runProgram(String programName) {
        if (!isRunning) {
            System.out.println("电脑未开机，请先开机！");
            return;
        }

        System.out.println("=== 运行程序: " + programName + " ===");
        memory.storeData(programName + "程序数据");
        cpu.processData();
        motherboard.transferData();
        monitor.displayContent(programName + "运行界面");
        System.out.println("程序运行完成！");
        System.out.println();
    }

    // 处理文档
    public void processDocument(String documentName) {
        if (!isRunning) {
            System.out.println("电脑未开机，请先开机！");
            return;
        }

        System.out.println("=== 处理文档: " + documentName + " ===");
        memory.readData(documentName);
        cpu.processData();
        monitor.displayContent(documentName + "文档内容");
        memory.storeData("已处理的" + documentName);
        System.out.println("文档处理完成！");
        System.out.println();
    }

    // 显示系统信息
    public void showSystemInfo() {
        System.out.println("=== 电脑系统信息 ===");
        System.out.println("电脑名称: " + computerName);
        System.out.println("运行状态: " + (isRunning ? "运行中" : "已关机"));
        System.out.println("CPU: " + cpu.getBrand() + " " + cpu.getModel() + " " + cpu.getFrequency() + "GHz " + cpu.getCores() + "核");
        System.out.println("内存: " + memory.getBrand() + " " + memory.getCapacity() + "GB " + memory.getType() + " " + memory.getSpeed() + "MHz");
        System.out.println("主板: " + motherboard.getBrand() + " " + motherboard.getModel() + " (" + motherboard.getChipset() + ")");
        System.out.println("显示器: " + monitor.getBrand() + " " + monitor.getSize() + "英寸 " + monitor.getResolution() + " " + monitor.getPanelType());
        System.out.println();
    }

    // 调节显示器亮度
    public void adjustMonitorBrightness(int brightness) {
        if (!isRunning) {
            System.out.println("电脑未开机，请先开机！");
            return;
        }
        monitor.adjustBrightness(brightness);
        System.out.println();
    }

    // Getter方法
    public String getComputerName() { return computerName; }
    public boolean isRunning() { return isRunning; }
    public CPU getCpu() { return cpu; }
    public Memory getMemory() { return memory; }
    public Motherboard getMotherboard() { return motherboard; }
    public Monitor getMonitor() { return monitor; }
}
