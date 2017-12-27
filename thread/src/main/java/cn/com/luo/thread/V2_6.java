package cn.com.luo.thread;

/**
 * 对象调用wait()
 * if(对象==类实例 && 类实例.start()){
 *     wait()等待的是类实例run方法执行完
 * }else {//!类实例.start() || 对象!=类实例
 *    wait()等待所在线程
 * }
 *
 */
public class V2_6 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run ");
        }

        public static void main(String[] args) throws InterruptedException {
            V2_6 V2_6 = new V2_6();
            synchronized(V2_6) {
                try {
                    System.out.println(Thread.currentThread().getName()+" start t1");
//                  V2_6.start(); 开启和注释验证
                    System.out.println(Thread.currentThread().getName()+" wait()");
                    V2_6.wait();
                    System.out.println(Thread.currentThread().getName()+" continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

}
