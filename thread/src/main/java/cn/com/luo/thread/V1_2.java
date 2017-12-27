package cn.com.luo.thread;

/**
 *匿名内部类创建线程
 * */
public class V1_2 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.println("hello");
            }
        }).start();

        //jdk1.8
        new Thread( () -> {System.out.println("hello1");} ).start();
    }
}
