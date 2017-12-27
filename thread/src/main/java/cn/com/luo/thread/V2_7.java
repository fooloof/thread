package cn.com.luo.thread;

/**
 * sleep()和wait()区别
 *      1、wait()等待释放锁
 *      2、sleep()等待不释放锁
 */
public class V2_7 extends Thread{
    private static Object object = new Object();

    @Override
    public void run() {
       waitV2_7(1);
       // sleepV2_7(1);
    }

    private void waitV2_7(Object object){
        synchronized (object){
            System.out.println("wait");
            try {
                object.wait(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleepV2_7(Object object){
        synchronized (object){
            try {
                System.out.println("sleep");
                this.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        V2_7 v2_7 = new V2_7();
        V2_7 v2_8 = new V2_7();
        V2_7 v2_9 = new V2_7();

        v2_7.start();
        v2_8.start();
        v2_9.start();
    }
}
