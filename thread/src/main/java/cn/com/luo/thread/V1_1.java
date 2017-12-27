package cn.com.luo.thread;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * Thread
 * 继承实现创建线程
 * */
public class V1_1 extends Thread {

    @Autowired
    public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        V1_1 v1_1 = new V1_1();
        v1_1.start();
    }
}
