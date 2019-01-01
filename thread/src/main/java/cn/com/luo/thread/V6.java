package cn.com.luo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


public class V6 {
    private static final Long  LENGTH = 1000000000L;
    private static final Long  AREA = 100000000L;


    public static void main(String[] args) {
        V6 v6 = new V6();
        long l1 = System.currentTimeMillis();
        List<ForkJoinTask<Long>> forkJoinTasks = new ArrayList<>();
        long poolSize = LENGTH/AREA;
        ForkJoinPool forkJoinPool = new ForkJoinPool((int)poolSize);
        for (int i = 0; i < poolSize ; i++) {
            AddTask addTask = v6.new AddTask(AREA*i,AREA*(i+1));
            forkJoinTasks.add(forkJoinPool.submit(addTask));
        }
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
