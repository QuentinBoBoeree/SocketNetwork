package singleton;

/**
 * Created by quentin on 2017/6/5.
 */

/**
 * doublecheck单例
 */
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton ourInstance = null;

    public static DoubleCheckSingleton getInstance() {
        if (ourInstance == null) {                        //加此判断的目的:如果有多个线程进行单例请求的时候，
                                                         // 在不加该判断的情况下，会一起进行竞争线程锁的等待中，会造成很多没有必要的时间浪费，加上此判断之后，因为单例的产生目的，在单例对象非空的情况下可立刻得到结果
            synchronized (DoubleCheckSingleton.class) {  //加锁，限制仅有一个线程可以对当前同步块进行操作，其他的线程只能等到获取到锁
                if (ourInstance == null) {               //单例判断，单例的产生目的
                    ourInstance = new DoubleCheckSingleton();
                }
            }
        }
        return ourInstance;
    }

    private DoubleCheckSingleton() {
        System.out.println("double check singleton initial");
    }
}
