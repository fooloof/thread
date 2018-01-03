package cn.com.luo.thread;

import static java.lang.Thread.sleep;

/**
 * volatile
 *  内存模型的相关概念
 *    cup   cache
 *    cup   cache        总线锁或者缓存一致性协议    主存
 *    cup   cache
 * 并发编程中的三个概念
 *    原子性   即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
 *    可见性  可见性是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值  V41
 *    有序性  即程序执行的顺序按照代码的先后顺序执行
 *  volatile  可见性     原子性 AtomicInteger  Lock  synchronized 保证i++的原子性
 */
public class V3 {
    private static volatile int nonAtomicCounter = 0;

    private static int times = 0;

    public static void caculate() {
        times++;
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName()+"_"+i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("--------------------------------------------------------------");
                    nonAtomicCounter++;
                }
            }).start();
        }

        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {


        caculate();
        while (nonAtomicCounter == 1000) {
            nonAtomicCounter = 0;

            caculate();
        }

        System.out.println("Non-atomic counter: " + times + ":"
                + nonAtomicCounter);
    }

}
