package multithread._2.threadSafe.safeCall;

import multithread._2.threadSafe.ArrayListThreadTest1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SafeListMain {
    public static void main(String args[]) throws Exception {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Thread thread1 = new Thread(new ArrayListThreadTest1(list));
        Thread thread2 = new Thread(new ArrayListThreadTest1(list));
        Thread thread3 = new Thread(new ArrayListThreadTest1(list));
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("list size is " + list.size());
    }
}
