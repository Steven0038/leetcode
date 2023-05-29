package multithread.ThreadPool;

import java.util.concurrent.*;

public class ThreadConceptExample {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /**
         * ThreadLocal（线程本地变量）：
         *
         * ThreadLocal是Java中一个特殊的类，它为每个线程提供了一个独立的变量副本。换句话说，每个线程都可以使用ThreadLocal对象来存储和访问自己的数据，而不会与其他线程的数据冲突。
         * ThreadLocal通常用于在多线程环境下共享一些数据，但又希望每个线程都拥有自己的独立副本。它在某些情况下可以提高性能和线程安全性。
         * 每个线程都可以通过get()和set()方法来读取和修改自己的ThreadLocal变量。
         */
        // ThreadLocal 示例
        threadLocal.set(42); // 设置线程本地变量的值

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 - ThreadLocal value: " + threadLocal.get()); //null
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set(99); // 修改线程本地变量的值
            System.out.println("Thread 2 - ThreadLocal value: " + threadLocal.get()); // 99
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final result of TheadLocal: " + threadLocal.get()); // 42

        /**
         * Fork/Join（分支/合并框架）：
         *
         * Fork/Join是Java 7中引入的一个框架，用于执行分治任务（divide-and-conquer）。
         * Fork/Join框架通过将大任务划分为更小的子任务，并通过工作窃取（work-stealing）算法在多个线程之间进行分配和执行，从而实现并行处理。
         * 框架中的核心类是ForkJoinPool，它是一个特殊的线程池，可以管理和调度Fork/Join任务。
         * ForkJoinTask是执行任务的基类，它有两个重要的子类：RecursiveTask（返回结果）和RecursiveAction（不返回结果）。
         */
        // Fork/Join 示例
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Integer sum = forkJoinPool.invoke(new SumTask(array, 0, array.length));
        System.out.println("Fork/Join - Sum: " + sum);

        /***
         * ThreadPool（线程池）：
         *
         * ThreadPool是一种线程管理的机制，它维护一个线程池，用于执行任务。它可以在应用程序中重复使用线程，减少线程创建和销毁的开销。
         * Java中的ThreadPoolExecutor类实现了线程池的功能。通过创建一个线程池对象，可以将任务提交给线程池执行。
         * 线程池的大小可以根据需求进行配置，可以限制并发线程的数量，以避免资源耗尽和过多的线程竞争。
         * 线程池提供了管理和监视线程执行的各种方法和功能，例如等待所有任务完成、取消任务、获取执行结果等。
         */
        // ThreadPool 示例
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executor.submit(() -> {
            System.out.println("ThreadPool - Task 1");
            return 42;
        });

        Future<Integer> future2 = executor.submit(() -> {
            System.out.println("ThreadPool - Task 2");
            return 99;
        });

        Integer result1 = future1.get();
        Integer result2 = future2.get();
        System.out.println("ThreadPool - Result 1: " + result1);
        System.out.println("ThreadPool - Result 2: " + result2);

        executor.shutdown();
    }
}

class SumTask extends RecursiveTask<Integer> {
    private int[] array;
    private int start;
    private int end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 2) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            leftTask.fork();
            rightTask.fork();

            int leftSum = leftTask.join();
            int rightSum = rightTask.join();

            return leftSum + rightSum;
        }
    }
}
