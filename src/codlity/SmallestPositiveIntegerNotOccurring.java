package codlity;

/**
 * Write a function that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A. <br/>
 * For example, given <code>A = [1, 3, 6, 4, 1, 2]</code>, the function should return 5. <br/>
 * Given <code>A = [1, 2, 3]</code>, the function should return 4. <br/>
 * Given <code>A = [-1, -3]</code>, the function should return 1. <br/>
 * <br/>
 * Write an efficient algorithm for the following assumptions: <br/>
 * N is an integer within the range <code>[1..100,000]</code>. <br/>
 * Each element of array A is an integer within the range <code>[-1,000,000..1,000,000]</code>. <br/>
 * <br/>
 * Max time for resolution: 30 minutes.
 */
public class SmallestPositiveIntegerNotOccurring {
    public static void main(String[] args) {
        SmallestPositiveIntegerNotOccurring spi = new SmallestPositiveIntegerNotOccurring();
        int[] A = {1, 3, 6, 4, 1, 2}; // 5
//        int[] A = {1, 2, 3};
//        int[] A = {-1, -3};
        System.out.println(spi.solution(A));
    }

    public int solution(int[] A) {
        // handle corner case
        if (A == null || A.length == 0) return 1;

        // make a recorder arr
        boolean[] ck = new boolean[1000000 + 1]; // initiated by JVM mark default false

        // iterate A and mark the visited as true
        for (int i = 0; i < A.length; i++) {
            int curr = A[i];
            if (curr > 0) {
                ck[curr] = true;
            }
        }

        // iterate ck and find the first one as false, this is the smallest not occur in A
        for (int i = 1; i < ck.length; i++) {
            if (!ck[i]) {
                return i;
            }
        }

        return 10000001; // fallback: all positive numbers exist in the A array
    }
}
