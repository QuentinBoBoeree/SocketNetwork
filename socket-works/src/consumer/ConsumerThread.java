package consumer;

/**
 * Created by quentin on 2017/5/24.
 */

import storage.Storage;

/**
 * 消费线程
 */
public class ConsumerThread implements Runnable {
    private Storage storage;

    public ConsumerThread(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            storage.consumeElement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
