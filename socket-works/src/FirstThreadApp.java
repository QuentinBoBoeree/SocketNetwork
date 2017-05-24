/**
 * Created by quentin on 2017/5/24.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * 第一个线程测试类
 */
public class FirstThreadApp implements Runnable {

    private static List<Integer> list = new ArrayList<>();

    @Override
    public void run() {
        while (true) {
            try {
                if (list.size() > 0) {
                    System.out.println(list.get(0));
                    list.remove(0);
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("当前的list中的元素个数:" + list.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        new FirstThreadApp();
    }

    public FirstThreadApp() {
        new Thread(this).start();
    }
}
