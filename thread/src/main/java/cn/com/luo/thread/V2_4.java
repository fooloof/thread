package cn.com.luo.thread;

/**
 * Interrupt()
 *      1、Thread的非静态方法
 *      2、thread.interrupt();//设置中断标志位为true
 *      3、sleep() wait() join() 不断检测线程的中断位 如果中断为true报InterruptedException 如果false不处理
 *      4、抛出InterruptedException 清除中断标志位即设置中断标志位为false
 *      5、thread.isInterrupted()  获取中断标志位的结果
 */
public class V2_4 extends Thread{
    public static Object lock = new Object();

    @Override
    public void run() {
        System.out.println("start");
        synchronized (lock) {
            try {
                lock.wait();//sleep wait join  不断检测线程的中断位 如果中断为true报InterruptedException 如果false不处理
            } catch (InterruptedException e) {//清除中断标志位即设置中断标志位为false
                System.out.println(Thread.currentThread().isInterrupted());//获取中断标志位的结果
                Thread.currentThread().interrupt(); //设置中断标志位为true
                System.out.println(Thread.currentThread().isInterrupted());//获取中断标志位的结果
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new V2_4();
        thread.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();//设置中断标志位为true
    }
}
