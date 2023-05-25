package multithread.ThreadPool;


import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<Integer> future = executor.submit(new Task());

        // 阻塞当前线程，直到任务完成并返回结果
        int result = future.get();
        System.out.println("Task result: " + result);

        executor.shutdown();
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 模拟耗时操作
        Thread.sleep(2000);
        return 42;
    }
}

