package cn.com.luo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class V7 {
    private static final Long  LENGTH = 1000000000L;
    private static final Long  AREA = 100000000L;


    public static void main(String[] args) {
        V7 v7 = new V7();
        long l1 = System.currentTimeMillis();
        List<Future<Long>> forkJoinTasks = new ArrayList<>();
        long poolSize = LENGTH/AREA;
        ExecutorService executorService =  Executors.newFixedThreadPool((int)poolSize);
        for (int i = 0; i < poolSize ; i++) {
            ComputeTask addTask = v7.new ComputeTask(AREA*i,AREA*(i+1));
            Future<Long> submit = executorService.submit(addTask);
            forkJoinTasks.add(submit);
        }
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
