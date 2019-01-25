package cn.com.luo.thread;

/**
 * notify()
 *      1、java.lang.Object类里的非静态方法
 *      3、notify()调用后，通知同一个线程锁下wait()，继续往下执行
 *      4、notifyAll()调用后，通知所有线程锁下wait()，继续往下执行
 */
public class V2_2 extends Thread{
    private static  Boolean BOOLEAN = true;

    public void run() {
        synchronized (V2_2.class){
            if(BOOLEAN){
                try {
                    System.out.println("wait start");
                    V2_2.class.wait();
                    System.out.println("wait end");
                } catch (InterruptedException e) {

                }
            }else {
                System.out.println("notify start");
                V2_2.class.notify();
                System.out.println("notify end");
            }
        }
    }
    // 以BOOLEAN为锁，BOOLEAN重新赋值后为一个新的对象，所以无法唤醒BOOLEAN.wait()等待
//    public void run() {
////        synchronized (BOOLEAN){
////            if(BOOLEAN){
////                try {
////                    System.out.println("wait start");
////                    BOOLEAN.wait();
////                    System.out.println("wait end");
////                } catch (InterruptedException e) {
////
////                }
////            }else {
////                System.out.println("notify start");
////                BOOLEAN.notify();
////                System.out.println("notify end");
////            }
////        }
////    }


    public static void main(String[] args) throws InterruptedException {
        V2_2 v2_2 = new V2_2();
        V2_2 v2_3 = new V2_2();
        v2_2.start();
        sleep(10);
        BOOLEAN = false;
        v2_3.start();


    }
}
