package cn.com.luo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FutureTask
 *
 Future是一个接口，代表可以取消的任务，并可以获得任务的执行结果

 FutureTask 是基本的实现了Future和runnable接口
 实现runnable接口，说明可以把FutureTask实例传入到Thread中，在一个新的线程中执行。
 实现Future接口，说明可以从FutureTask中通过get取到任务的返回结果，也可以取消任务执行（通过interreput中断）
 * //从1+10000000000
 */
public class V5 {
    private static final Long  LENGTH = 1000000000L;
    private static final Long  AREA = 10000000L;

    public static void main(String[] args) {

        V5 inst=new V5();
        long l = System.currentTimeMillis();
        // 创建任务集合
        List<FutureTask<Long>> taskList = new ArrayList<FutureTask<Long>>();
        // 创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (long i = 0; i < LENGTH/AREA; i++) {
            // 传入Callable对象创建FutureTask对象
            FutureTask<Long> ft = new FutureTask<Long>(inst.new ComputeTask(i*AREA,(i+1)*AREA));
            taskList.add(ft);
            // 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
            exec.submit(ft);
        }

        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

        // 开始统计各计算线程计算结果
        Long totalResult = 0L;
        for (FutureTask<Long> ft : taskList) {
            try {
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        exec.shutdown();
        long l1 = System.currentTimeMillis();
        System.out.println("多任务计算后的总结果是:" + totalResult);
        System.out.println("time:"+(l1-l));
    }

    private class ComputeTask implements Callable<Long> {
        private Long start;
        private Long end;

        public ComputeTask(Long start, Long end){
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            Long result =0L;
           for (Long i =  start; i<end;i++){
               result = result+i;
           }
            return result;
        }
    }
}
