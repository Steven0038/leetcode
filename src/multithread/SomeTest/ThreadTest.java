package multithread.SomeTest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

// ref. https://mynamelancelot.github.io/java/concurrent.html
public class ThreadTest {
    public static void main(String[] args) throws Exception {
        // extends Thread
        Thread th = new ThreadDemo();
        th.start();
        // implement Runnable
        Thread thRun = new Thread(new RunnableDemo());
        thRun.start();
        // implement Callable and get return value
        FutureTask<String> ft = new FutureTask<>(new CallableDemo());
        Thread thFuture = new Thread(ft);
        thFuture.start();
        String result = ft.get();
        System.out.println("FutureTask result: " + result);
        // Timer
        Timer timer = new Timer();
        timer.schedule(new TimerTaskDemo(), 1000);
        // thread pool
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new ExecutorDemo());
        executor.shutdown(); // thread pool shutdown, btw except thread under execution and waiting in queue
    }

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("[run] Thread do something");
        }
    }

    static class RunnableDemo implements Runnable {
        @Override
        public void run() {
            System.out.println("[run] Runnable do something");
        }
    }

    static class CallableDemo implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "[call] Callable do something";
        }
    }

    static class TimerTaskDemo extends TimerTask {
        @Override
        public void run() {
            System.out.println("[run] TimerTask do something");
        }
    }

    static class ExecutorDemo implements Runnable {
        @Override
        public void run() {
            System.out.println("[run] Executor do something");
        }
    }
}
