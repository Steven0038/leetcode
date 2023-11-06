package _0.UtileLibraries;

import _0.UtileLibraries.IterableExamples.Product;
import _0.UtileLibraries.IterableExamples.ShoppingCart;

public class JavaApis {
    public static void main(String[] args) {
        JavaApis javaApis = new JavaApis();
        /* ======================= Chapter I - Basics ======================= */
        /* 1.2 Base class of everything and its useful member functions */
        javaApis.testObjectMethod();
        /* 1.3 Write two static printing function */
        javaApis.printLines();
        /* 1.4 What is the comparable and comparator interface */
        javaApis.comparable(new ListNode(0), new ListNode(1));
        /* 1.5 What is the iterable and iterator interface */
        javaApis.iterator();
    }

    private void testObjectMethod() {
        Object objA = new Object();
        Object objB = new Object();
        System.out.println("[Object equals()]: " + objA.equals(objB));
        System.out.println("[Object toString()]: " + objA.toString());
        System.out.println("[Object toString()]: " + objA.hashCode());
    }

    private void printLines() {
        System.out.printf("[printf() %d - %d]", 1, 2);
        System.out.println();
    }

    private void comparable(ListNode nodeSmall, ListNode nodeLarge) {
        // negative if less, positive if more
        System.out.println("[compareTo] : " + nodeSmall.compareTo(nodeLarge));
    }

    private void iterator() {
        ShoppingCart<Product> shoppingCart = new ShoppingCart<>();
        shoppingCart.add(new Product("Tuna", 42));
        shoppingCart.add(new Product("Eggplant", 65));

        shoppingCart.forEach(p -> System.out.println(p.getName()));
    }
}

class ListNode implements Comparable<ListNode>{
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public int compareTo(ListNode node) {
        return val - node.val;
    }
}

