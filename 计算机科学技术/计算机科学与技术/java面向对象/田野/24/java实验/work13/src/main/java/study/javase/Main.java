package study.javase;

// 商品仓库类（共享资源）
class Store {
    private int product = 0;  // 当前商品数量

    // 生产商品
    public synchronized void produce(String producerName) {
        while (product >= 10) {
            // 仓库满了，暂停生产
            try {
                System.out.println(producerName + "：商品达到上限，暂停生产...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product++;
        System.out.println(producerName + " 生产了 1 个商品，当前库存：" + product);

        notifyAll();  // 唤醒等待的消费者
    }

    // 消费商品
    public synchronized void consume(String consumerName) {
        while (product == 0) {
            // 没有商品，暂停消费
            try {
                System.out.println(consumerName + "：没有商品可买，等待中...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product--;
        System.out.println(consumerName + " 购买了 1 个商品，当前库存：" + product);

        notifyAll(); // 唤醒等待的生产者
    }
}

// 生产者线程
class Producer implements Runnable {
    private Store store;
    private String name;

    public Producer(Store store, String name) {
        this.store = store;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {   // 每个生产者生产 10 个
            store.produce(name);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " 已完成生产任务！");
    }
}

// 消费者线程
class Consumer implements Runnable {
    private Store store;
    private String name;

    public Consumer(Store store, String name) {
        this.store = store;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            store.consume(name);

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 主类
public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        // 启动 2 个生产者
        new Thread(new Producer(store, "生产者1")).start();
        new Thread(new Producer(store, "生产者2")).start();

        // 启动 3 个消费者
        new Thread(new Consumer(store, "消费者A")).start();
        new Thread(new Consumer(store, "消费者B")).start();
        new Thread(new Consumer(store, "消费者C")).start();
    }
}
