package cn.com.luo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class V9 {
    private static final Long  START = 1L;
    private static final Long  END = 1000000000L;


    private static final Long  START1 = 1L;
    private static final Long  END1= 10000L;

    public static void main(String[] args) {
        V9 v7 = new V9();
        long l1 = System.currentTimeMillis();
        List<Future<Long>> forkJoinTasks = new ArrayList<>();

        ExecutorService executorService =  Executors.newFixedThreadPool(5);
        ComputeTask addTask = v7.new ComputeTask(START,END);
        ComputeTask addTask1 = v7.new ComputeTask(START1,END1);
        Future<Long> submit = executorService.submit(addTask);
        Future<Long> submit1 = executorService.submit(addTask1);
        forkJoinTasks.add(submit);
        forkJoinTasks.add(submit1);

        long result = 0;
        for (Future<Long> longForkJoinTask : forkJoinTasks) {
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

     class ComputeTask implements Callable<Long> {
        long start;
        long end;

        public ComputeTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

         @Override
         public Long call() throws Exception {
             if(end -start <5000){
                 long sum = 0;
                 for (long i = start; i < end; i++) {
                     sum = sum+i;
                 }
                 return sum ;
             }

             long mid = (start + end)/2;
             ComputeTask t = new ComputeTask(start, mid);
             ComputeTask t1 = new ComputeTask(mid, end);
             return t.call()+t1.call();
         }
     }
}
