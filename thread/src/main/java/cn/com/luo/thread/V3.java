package cn.com.luo.thread;

import static java.lang.Thread.sleep;

/**
 * volatile
 *  内存模型的相关概念
 *    cup   cache
 *    cup   cache        总线锁或者缓存一致性协议    主存
 *    cup   cache
 * 并发编程中的三个概念
 *    原子性   即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
 *    可见性  可见性是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值  V41
 *    有序性  即程序执行的顺序按照代码的先后顺序执行
 *  volatile  可见性     原子性 AtomicInteger  Lock  synchronized 保证i++的原子性
 *  常用场景:
 *     1.状态标记量
 *     2.double check
 *详见：https://www.cnblogs.com/dolphin0520/p/3920373.html  5volatile使用场景
 *
 *-----------------------------------------------------------------------------------------------------------------------------
 *    //每开启一个线程  从主内存复制变量到线程的私有工作空间  主线程是 stop = false    A:stop = false    B:stop = false
 *   //没有volatile修饰  B线程将stop =true因为B线程还没有结束所以主内存还是stop = false 当B线程执行完毕更新主内存stop =true 接着A结束
 *   //有volatile修饰  B线程将stop =true 立即将主内存的stop =true 接着A结束，接着B执行
 *
 *   //主内存改变每个子线程就会同步改变  子线程改变如果不是volatile则必须等待子线程完毕才会改变主内存，如果volatile修饰就会立即改变主内存
 *   //没有volatile修饰时子线程改变变量主线程为什么不会变，  因为如果有多个子线程都改变则主内存不知道用哪个才正确
 *   没有volatile修饰stop
 *主要事件                    主               私有线程A             私有线程B
 *
 *stop = false
 *
 *开启A,B线程             stop = false          拷贝stop = false       拷贝stop = false
 *
 *stop = false           stop = false          stop = true;这时候B还没有结束
 *
 * stop = true            stop = true            B结束
 *
 *有volatile修饰stop
 *主要事件                    主               私有线程A             私有线程B
 *
 *stop = false
 *
 *开启A,B线程             stop = false          拷贝stop = false       拷贝stop = false
 *
 *stop = flase           stop = flase          stop = true;这时候B还没有结束但是只要改变主内存同步改变
 *
 *stop = true            stop = true
 *主要总结volatile修饰 线程私有变量改变主内存立即改变，没有修饰线程私有变量改变等线程结束主内存改变
 *
 * 下面例子证明 volatile他有可见性
 */
public class V3  {
//    private static   volatile  boolean stop = false;
      private static   boolean stop = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop){
                    System.out.println("等待主内存改变");
                }
                System.out.println("线程A结束");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                stop =true;
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程B结束");
            }
        }).start();


    }

}
