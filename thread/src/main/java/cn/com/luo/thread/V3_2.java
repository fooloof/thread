package cn.com.luo.thread;

import static java.lang.Thread.sleep;

/**
 * ThreadLocal
 *
 */
public class V3_2 {
    private static ThreadLocal threadLocal = new ThreadLocal();
//    private static Integer integer= 0;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    threadLocal.set(i);
//                    integer=i;
                    try {
                        sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadLocal.get()+"_"+Thread.currentThread().getName());
//                    System.out.println(integer+"_"+Thread.currentThread().getName());
                }

            }
        },"thread");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 200; i < 300; i++) {
                    threadLocal.set(i);
//                    integer=i;
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadLocal.get()+"_"+Thread.currentThread().getName());
//                    System.out.println(integer+"_"+Thread.currentThread().getName());
                }

            }
        },"thread1");
        thread.start();
        thread1.start();
    }

}
