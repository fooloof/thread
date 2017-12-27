package cn.com.luo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep(100)
 *      1、Threadk类里的静态方法
 *      2、睡眠时间毫秒
 *      3、不释放所在线程锁
 */
public class V2 {
    private static  final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");

    public static void main(String[] args) {
        System.out.println(df.format(new Date()));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(df.format(new Date()));
    }

}
