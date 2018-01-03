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
 */
public class V5 {
    public static void main(String[] args) {

        //数据库查出商品集合
        //再根据集合查询每个商品对应的抵用券
        //返回Map<Item,List<toc>>
        //List<item> items = 第一步查询商品集合
        //FutureV1 inst=new FutureV1();
        //
        //  // 创建任务集合
        // List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
        // // 创建线程池
        // ExecutorService exec = Executors.newFixedThreadPool(5);
        //  for(item i :items){
        // // 传入Callable对象创建FutureTask对象
        //  FutureTask<Integer> ft = new FutureTask<Integer>(inst.new ComputeTask(i));
        //  taskList.add(ft);
        //  提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
        //  exec.submit(ft);
        //  }
        //  Map <item,List<toc>> temp = new HashMap<>();
        //  Map<item,List<toc>> map = ft.get()
        //  temp.putall(map);
        //  temp.values();
        //  private  ComputeTask implements callable<Map<item,List<toc>>>{
        //      item  i = null;
        //      ComputeTask(item i ){
        //       this.i = i
        //      }
        //   Map<item,List<toc> call(){
        //    Map<item,List<toc> ta = new HashMap<>();
        //    List<toc> tocs = 通过i查询抵用券；
        //    ta.put(item,tocs);
        //    return ta;
        // }

        //    @Override
//        public Integer call() throws Exception {
//            // TODO Auto-generated method stub
//            System.out.println("--------------------------------------"+taskName);
//            for (int i = 0; i < 100; i++) {
//                result =+ i;
//            }
//            // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
//            Thread.sleep(5000);
//            System.out.println("子线程计算任务: "+taskName+" 执行完成!");
//            return result;
//        }
        //
        //
        // }
        //


        V5 inst=new V5();
        // 创建任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
        // 创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象创建FutureTask对象
            FutureTask<Integer> ft = new FutureTask<Integer>(inst.new ComputeTask(i, ""+i));
            taskList.add(ft);
            // 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
            exec.submit(ft);
        }

        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
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
        System.out.println("多任务计算后的总结果是:" + totalResult);

    }

    private class ComputeTask implements Callable<Integer> {

        private Integer result = 0;
        private String taskName = "";

        public ComputeTask(Integer iniResult, String taskName){
            result = iniResult;
            this.taskName = taskName;
            System.out.println("生成子线程计算任务: "+taskName);
        }

        public String getTaskName(){
            return this.taskName;
        }

        @Override
        public Integer call() throws Exception {
            // TODO Auto-generated method stub
            System.out.println("--------------------------------------"+taskName);
            for (int i = 0; i < 100; i++) {
                result =+ i;
            }
            // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
            Thread.sleep(5000);
            System.out.println("子线程计算任务: "+taskName+" 执行完成!");
            return result;
        }
    }
}
