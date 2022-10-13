package multithread._1.waitAndNotify;

public class TestSum {
    public static void main(String args[]) throws Exception{
        SumThread sum = new SumThread();
        Thread thread = new Thread(sum);
        thread.start();
        synchronized(thread) {
            thread.wait();
        }
        int result = sum.sum;
        System.out.println("Count result is:" + result);
    }
}
