package producer;

/**
 * Created by quentin on 2017/5/24.
 */

import storage.Storage;

/**
 * 生产者线程
 */
public class ProducerThread implements Runnable {

    private Storage storage;

    public ProducerThread(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            storage.produceElement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
