package multithread._2.threadSafe;

import java.util.List;

public class ArrayListThreadTest1 implements Runnable {

    private List<String> list;

    public ArrayListThreadTest1(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                this.list.add("a" + i);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
