package cn.com.luo.thread;

/**
 * Runnable
 * 实现接口创建线程
 * */
public class V1 implements Runnable {
    @Override
    public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Thread  thread = new Thread(new V1());
        thread.start();
    }
}
