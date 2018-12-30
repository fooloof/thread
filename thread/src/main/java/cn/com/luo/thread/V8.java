package cn.com.luo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *
 * https://www.cnblogs.com/congsg2016/p/5621746.html
 *
 * BlockingQueue阻塞队列体系、Executor线程组执行框架、Future线程返回值体系、其他各种单独的并发工具
 * ScheduledExecutorService
 * fork-join
 *ForkJoinPool
 submit 异步，get() 阻塞等待线程返回结果
 invoke 同步

 ForkJoinTask
 RecursiveTask 有返回结果
 RecursiveAction 无返回结果
 fork 开启任务
 join 等待并获取任务结果

 ForkJoinWorkerThread
 线程等待工作队列c


 ForkJoinPool由ForkJoinTask数组和ForkJoinWorkerThread数组组成，ForkJoinTask数组负责存放程序提交给ForkJoinPool的任务，而ForkJoinWorkerThread数组负责执行这些任务


 ForkJoinTask的fork方法时   ForkJoinWorkerThread的pushTask方法异步的执行这个任务，然后立即返回this
 pushTask方法把当前任务存放在ForkJoinTask 数组queue里唤醒线程执行
 ForkJoinPool.WorkQueue  保存要执行的ForkJoinTask



 corePoolSize 核心线程池大小
 maximumPoolSize 线程池最大容量大小
 keepAliveTime 线程池空闲时，线程存活的时间
 TimeUnit 时间单位
 ThreadFactory 线程工厂
 BlockingQueue任务队列
 RejectedExecutionHandler 线程拒绝策略


 *
 */
public class V8 {
    private static final Long  START = 1L;
    private static final Long  END = 1000000000L;


    private static final Long  START1 = 1L;
    private static final Long  END1= 10000L;

    public static void main(String[] args) {
        V8 v6 = new V8();
        long l1 = System.currentTimeMillis();
        List<ForkJoinTask<Long>> forkJoinTasks = new ArrayList<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        AddTask addTask = v6.new AddTask(START,END);
        AddTask addTask1 = v6.new AddTask(START1,END1);
        forkJoinTasks.add(forkJoinPool.submit(addTask));
        forkJoinTasks.add(forkJoinPool.submit(addTask1));

        long result = 0;
        for (ForkJoinTask<Long> longForkJoinTask : forkJoinTasks) {
            try {
                Long l = longForkJoinTask.get();
                result = result + l;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("resut:"+result);
        long l2 = System.currentTimeMillis();
        System.out.println("time:"+(l2-l1));
    }

     class AddTask extends RecursiveTask<Long>{
        long start;
        long end;

        public AddTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if(end -start <5000){
                long sum = 0;
                for (long i = start; i < end; i++) {
                    sum = sum+i;
                }
                return sum ;
            }

            long mid = (start + end)/2;
            AddTask t = new AddTask(start, mid);
            AddTask t1 = new AddTask(mid, end);
            t.fork();
            t1.fork();
            return t.join()+t1.join();
        }
    }
}
