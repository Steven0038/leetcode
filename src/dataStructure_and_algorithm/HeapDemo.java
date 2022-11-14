package dataStructure_and_algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapDemo {
    public static void main(String[] args) {

        HeapDemo hd = new HeapDemo();
        System.out.println("=== min heap ===");
        hd.minHeapPrint();
        System.out.println("=== max heap ===");
        hd.maxHeapPrint();
        System.out.println("=== customized string heap ===");
        hd.customizedHeap();
    }

    private void minHeapPrint() {
        Queue<Integer> queue = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                //注意理解，這裡是奇數在左側，數字大的在左側
                int result = i % 2 - j % 2;
                if (result == 0) result = i - j; // 照 constructor 排列順序減
                return result;
            }
        });
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.poll());
        }
    }

    private void maxHeapPrint() {
        Queue<Integer> queue = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer j, Integer i) {
                int result = i % 2 - j % 2;
                if (result == 0) result = i - j; // 反向順序減
                return result;
            }
        });
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.poll());
        }
    }

    private void customizedHeap() {
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }

    class UserComparator implements Comparator<User> {
        public int compare(User u1, User u2) {
            if (u1.number.charAt(0) == u2.number.charAt(0)) {
                // 如果两人的号都是A开头或者都是V开头,比较号的大小:
                return u1.number.compareTo(u2.number);
            }
            if (u1.number.charAt(0) == 'V') {
                // u1的号码是V开头,优先级高:
                return -1;
            } else {
                return 1;
            }
        }
    }

    class User {
        public final String name;
        public final String number;

        public User(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String toString() {
            return name + "/" + number;
        }
    }
}
