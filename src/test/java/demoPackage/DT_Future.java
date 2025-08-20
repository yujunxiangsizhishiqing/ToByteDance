package demoPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DT_Future {

    public static void main(String[] args) {
        //future测试1
        DT_Future dtFuture = new DT_Future();
        //dtFuture.futureTest1();
        dtFuture.futureTest2();
    }

    /**
     * 创建一个Future对象，通过线程池来调用，并阻塞线程获取等待结果。
     */
    private void futureTest1(){
        // 创建一个 Callable 任务
        Callable<Integer> callable = () -> {
            Thread.sleep(2000); // 模拟耗时计算
            return 42; // 返回计算结果
        };

        // 创建 FutureTask，传入 Callable 任务
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 提交 FutureTask 到线程池执行
        executor.submit(futureTask);

        // 此处线程可以继续执行其他任务

        try {
            // 阻塞等待计算结果，并获取结果
            int result = futureTask.get();
            System.out.println("异步计算结果为：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }


    /**
     * @apiNote 使用线程池并发调用多个 Future 并等待结果的示例。
     * 在这个示例中，我们将创建一个固定大小的线程池，并使用 CompletionService 来管理 Future，
     * 以便可以并发地执行多个任务，并等待它们的结果
     * */
    public void futureTest2(){
        //最大线程池大小
        int maxFutureTask = 3;
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(maxFutureTask);
        //任务列表
        List<Future<Integer>> futures = new ArrayList<>();
        // 创建 CompletionService 来管理 Future
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        // 提交多个任务给线程池执行，并将 Future 注册到 CompletionService 中
        for (int i = 0; i < 5; i++) {
            futures.add(completionService.submit(new MyCallable(i)));
        }

        try {
            for (Future<Integer> future : futures) {
                // 阻塞等待任务执行结果，并获取结果
                int result = future.get();
                System.out.println("任务执行结果：" + result);
            }
        }catch (Exception e){
            e.printStackTrace();
            //throw e;
        } finally {
            executor.shutdown(); // 关闭线程池
        }


        // 循环等待每个任务的执行结果
//        for (int i = 0; i < 5; i++) {
//            try {
//                // 获取已完成的任务结果
//                Future<Integer> future = completionService.take();
//                // 阻塞等待任务执行结果，并获取结果
//                int result = future.get();
//                System.out.println("任务执行结果：" + result);
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

        // 关闭线程池
        executor.shutdown();
    }

    // 自定义 Callable 任务
    class MyCallable implements Callable<Integer> {
        private final int value;

        public MyCallable(int value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            // 模拟耗时计算
            //int sleepTime = 1000+ (int)(Math.random()*5000);
            //Thread.sleep(sleepTime);
            Thread.sleep(2000);
            // 返回计算结果
            return value;
        }
    }
}
