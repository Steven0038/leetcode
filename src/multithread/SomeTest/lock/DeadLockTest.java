package multithread.SomeTest.lock;

// 指两个或两个以上的进程（或线程）在执行过程中，因争夺资源而造成的一种互相等待的现象（至少两个资源），若无外力作用，它们都将无法推进下去。
public class DeadLockTest {
    public static final Object resource1 = new Object();
    public static final Object resource2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (resource1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "：wait");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "：executable");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (resource2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "：wait");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + "：executable");
                }
            }
        }, "B").start();
    }
}
