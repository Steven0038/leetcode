package _5.leetcode_sort_and_search;

import java.util.*;

/**
 * 56. Merge Intervals, medium
 * <p>
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * <p>
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 */
public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
//        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][]{{1, 4}, {0, 4}}; // {0, 4}
        System.out.println(Arrays.deepToString(mi.merge(intervals)));
    }

    /**
     * Solution 1: use only one list and merge  97%
     **/
    public int[][] merge(int[][] intervals) {
        List<int[]> answer = new ArrayList<>();

        if (intervals.length != 0) {
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            int start = intervals[0][0];
            int end = intervals[0][1];
            for (int[] i : intervals) {
                if (i[0] <= end) {
                    end = Math.max(end, i[1]);
                } else {
                    answer.add(new int[]{start, end});
                    start = i[0];
                    end = i[1];
                }
            }
            answer.add(new int[]{start, end});

        }

        return answer.toArray(new int[0][]);
    }

    /**
     * Solution DIY: use list and stack(no need it) and merge  10%
     **/
    public int[][] merge0(int[][] intervals) {

        // sort our intervals
//        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> rets = new ArrayList<>();
        Stack<int[]> stack = new Stack<>(); // save the last arr element to check overlap

        for (int[] interval : intervals) {
            int prevA = 0;
            int prevB = 0;

            if (!stack.isEmpty()) {
                prevA = stack.peek()[0];
                prevB = stack.peek()[1];
            }

            int currA = interval[0];
            int currB = interval[1];

            // check overlap
            // no need to handle, [[1,4],[0,0]]  => [[0,0],[1,4]]
            if (stack.isEmpty() || prevA < currA && prevB < currA) {
                stack.add(interval);
                rets.add(interval);
            } else {
                // reduce overlap
                // 1. get and remove the prior arr in rets
                int[] prev = stack.pop();
                rets.remove(rets.size() - 1);
                // 2. new a non-overlap arr and add it to rets
                // [1,3],[2,6] => [1,6], [[1,4],[0,4]] => [0, 4]
                int[] cross = new int[2];
                int newA = Math.min(prevA, currA);
                int newB = Math.max(prevB, currB);
                cross[0] = newA;
                cross[1] = newB;

                rets.add(cross);
                stack.add(cross);
            }
        }

//         int[][] retArr = new int[rets.size()][2];
//         for(int i = 0; i < rets.size(); i++) {
//             retArr[i] = rets.get(i);
//         }

//         return retArr;
        return rets.toArray(new int[0][]);
    }
}
