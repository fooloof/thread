package cn.com.luo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Lock
 *      多线程lock区间的同步执行;
 */
public class V4_1  {
    private static  Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("start lock A");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end lock A");
                lock.unlock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("start lock B");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end lock B");
                lock.unlock();
            }
        }).start();
    }
}
