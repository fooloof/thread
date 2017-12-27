package cn.com.luo.thread;

/**
 * wait()
 *      1、java.lang.Object类里的非静态方法
 *      2、wait()调用后，释放调用wait对象的线程锁，如果对象不占用锁则报IllegalMonitorStateException
 *      3、无参 则会一直等直到 notify()或notifyAll()
 *      4、wait(100) 单位毫秒 超时自动被唤醒
 */
public class V2_1 {

    public static void main(String[] args) {
      //new V2_1().wait(); 直接调用会报IllegalMonitorStateException

//        V2_1.class 有锁，但是v2_11没有锁所以也会报IllegalMonitorStateException
//        synchronized (V2_1.class){
//            System.out.println(Thread.currentThread().getName());
//            try {
//                V2_1 v2_11 = new V2_1();
//                v2_11.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        V2_1 v2_1 = new V2_1();
        synchronized (v2_1){
            System.out.println(Thread.currentThread().getName());
            try {
                v2_1.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
