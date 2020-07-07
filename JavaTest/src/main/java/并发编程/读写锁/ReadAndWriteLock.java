package 并发编程.读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();

        try {

            System.out.println(Thread.currentThread().getName() + "\t 正在写入");
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成 " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        rwLock.readLock().lock();

        try {

            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            TimeUnit.MILLISECONDS.sleep(300);
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成 " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

    }

}

public class ReadAndWriteLock {

    public static void main(String[] args) {
        Cache cache = new Cache();

        for (int i = 1; i <= 5; i++) {
            final int t = i;
            new Thread(() -> {
                cache.put(t + "", t + "");
            }, String.valueOf("write" + i)).start();
        }


        for (int i = 1; i <= 5; i++) {
            final int t = i;
            new Thread(() -> {
                cache.get(t + "");
            }, String.valueOf("read" + i)).start();
        }
    }
}
