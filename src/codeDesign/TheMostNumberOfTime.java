package codeDesign;

import java.util.*;

/**
 * Given an array of integers a, your task is to calculate the digits that occur the most number of times in the array. Return the array of these digits in ascending order.
 * <p>
 * Example
 * <p>
 * For a = [25, 2, 3, 57, 38, 41], the output should be solution(a) = [2, 3, 5].
 * <p>
 * Here are the number of times each digit appears in the array:
 * <p>
 * 0 -> 0
 * 1 -> 1
 * 2 -> 2
 * 3 -> 2
 * 4 -> 1
 * 5 -> 2
 * 6 -> 0
 * 7 -> 1
 * 8 -> 1
 * The most number of times any number occurs in the array is 2, and the digits which appear 2 times are 2, 3 and 5. So the answer is [2, 3, 5].
 */
public class TheMostNumberOfTime {
    public static void main(String[] args) {
        TheMostNumberOfTime tm = new TheMostNumberOfTime();
        int[] input = new int[]{25, 2, 3, 57, 38, 41};
        int[] ret = tm.theMostNumbers(input);
        Arrays.stream(ret).forEach(System.out::println);
    }

    private int[] theMostNumbers(int[] a) {
        // convert elements to charArr
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : a) {
            String nStr = String.valueOf(n);
            char[] cArr = nStr.toCharArray();
            for (char c : cArr) {
                int k = Character.getNumericValue(c);
                map.put(k, map.getOrDefault(k, 0) + 1);
            }
        }
        // count the digits and find out the most(if same number, return in ASC)
        int maxCount = 0;
        for (int k : map.keySet()) {
            maxCount = Math.max(maxCount, map.get(k));
        }

        // find out there's if the same count as the maxInt
        List<Integer> ls = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k) == maxCount) {
                ls.add(k);
            }
        }

        ls.sort(Comparator.naturalOrder());

        return ls.stream().mapToInt(Number::intValue).toArray();
    }
}
