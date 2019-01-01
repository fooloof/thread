package cn.com.luo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


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
