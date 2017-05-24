package storage;

import consumer.ConsumerThread;
import producer.ProducerThread;
import utils.RandomUtil;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by quentin on 2017/5/24.
 */

/**
 * 仓库
 */
public class Storage {
    public BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    /**
     * 仓库中可存放的最大数量
     */
    public static final Integer MAX_SIZE = 100;
    /**
     * 仓库中最少要剩余的数量
     */
    public static final Integer MIN_SIZE = 10;

    public static final Integer RANDOM_NUMBER_SIZE = 8;

    public synchronized void produceElement() throws InterruptedException {
        while (true) {
            if (blockingDeque.size() > MIN_SIZE) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String data = RandomUtil.generateRandomCode(RANDOM_NUMBER_SIZE);
            blockingDeque.put(data);
            System.out.println("生产者生产的数据为:" + data);
            System.out.println("仓库中的物品数量为:" + blockingDeque.size());
        }
    }

    public synchronized void consumeElement() throws InterruptedException {
        while (true) {
            if (blockingDeque.size() < MIN_SIZE) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String consumeData = blockingDeque.take();
            System.out.println("消费者消费的数据为:" + consumeData);
            System.out.println("仓库中的物品数量为:" + blockingDeque.size());
        }
    }


    public static void main(String[] args) {
        Storage storage = new Storage();
        new Thread(new ProducerThread(storage)).start();
        new Thread(new ConsumerThread(storage)).start();
    }

}
