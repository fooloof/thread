package cn.com.luo.thread;

/**
 * join()
 *  1、
 *  2、
 *  3、
 */
public class V2_3 extends Thread{
    int num = 1000000;
    public void run() {
        increment();
    }

    public void increment() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public static void main(String[] args) {
        V2_3 thread = new V2_3();
        Thread a = new Thread(thread);
        Thread b = new Thread(thread);

        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(thread.num);
    }
}
