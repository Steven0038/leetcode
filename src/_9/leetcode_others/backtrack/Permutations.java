package _9.leetcode_others.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46. Permutations, medium, companies
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Constraints:
 * All the integers of nums are unique.
 */
public class Permutations {
    public static void main(String[] args) {
        Permutations pm = new Permutations();
        int[] nums = new int[]{1, 2, 3};
//        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(pm.permute(nums));
        System.out.println(pm.permute2(nums));
        System.out.println(pm.permute3(nums));
        /**
         *                            1      [1,2,3] -> 123   如果 dfs 不做 backtrack,下一步就會變成[1,2,3,4]
         *                            |      [1,2,4] -> 124
         *                            2      [1,2,5] -> 125
         *                          / | \
         *                         3  4  5
         *
         *
         *                         [1,2,3]
         *                  /         |            \
         *          [1#,2,3]       [2#,1,3]        [3#,2,1]   L1: index 0, n   possibilities
         *                          /    \
         *                   [2,1#,3]  [2,3#,1]               L2: index 1, n-1 possibilities
         *                        |        |
         *                   [2,1,3#]  [2,3,1#]              L3: index 2, n-2 possibilities
         */
    }

    /**
     * 鄰里互換法1
     * 返回所有給定的 array 元素的, 所有可能排序組合的 list
     *
     * @param nums input array
     * @return List of Permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, 0);

        return res;
    }

    // top-down DFS
    private void dfs(List<List<Integer>> res, int[] nums, int index) {
        if (index > nums.length - 1) {
            // make a copy
//            List<Integer> ans = new ArrayList<>(); for (int i = 0; i < nums.length; i++) ans.add(nums[i]);
            List<Integer> ans = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(ans);
            return;
        }
        // back-tack dfs
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i); // 1. backtrack 交換 index,
            dfs(res, nums, index + 1); // 2. 進行搜索
            swap(nums, index, i); // 換回來,因為須回朔不讓其他分支的狀態干擾之後的搜索分支
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    /**
     * 鄰里互換法2
     * ref: http://www.noteanddata.com/leetcode-46-Permutations-java-solution-note.html
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> allList = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), allList);
        return allList;
    }

    /**
     * 经典的backtrack（回溯法）， 有很多写法， 我个人认为下面的写法是比较简洁，并且比较通用的backtrack模版。
     * 很多backtrack问题，都可以通过这个模版来针对不同的问题写。
     * a. 基本思路就是模拟人肉枚举的过程，比如有三个数1,2,3
     * b. 首先在第一个位置可以有三种可能，可以选择1，2，3里面的任何一个， 第二个位置就只能在剩下的两个数里面选一个，
     * 最后一个位置就只能选最后一个数。
     * c. 这个选数的过程，可以通过交换位置来实现，每次把当前可以选择的数(from到nums.length-1)换到最前面，
     * 然后把这个数加到当前选项(cur)中, 然后递归。 递归完了以后， 要记得backtrack，就是reset到原始的状态，
     * 相当于什么都没发生过，然后在for循环中继续下一轮。
     * d. 递归需要有终止条件， 这里就是cur.size== nums.length, 说明已经递归到一个排列， 拷贝到allList里面
     */
    public void helper(int[] nums, int fromIdx, List<Integer> cur, List<List<Integer>> allList) {
        if (cur.size() == nums.length) {
            allList.add(new ArrayList<>(cur));
            return;
        }
        for (int i = fromIdx; i < nums.length; ++i) {
            swap(nums, fromIdx, i);
            cur.add(nums[fromIdx]);
            helper(nums, fromIdx + 1, cur, allList);
            cur.remove(cur.size() - 1);
            swap(nums, fromIdx, i);
        }
    }

    List<List<Integer>> list;

    /**
     * 回朔法: 效率較差
     * ref. https://segmentfault.com/a/1190000040142137
     */
    public List<List<Integer>> permute3(int[] nums) {
        list = new ArrayList<>();//最终的结果
        List<Integer> team = new ArrayList<>();//回溯过程收集元素
        boolean[] visits = new boolean[nums.length];//用来标记
        dfs(visits, nums, team, 0);
        return list;
    }

    private void dfs(boolean[] visits, int[] nums, List<Integer> team, int index) {
        int len = nums.length;
        if (index == len) { // 停止
            list.add(new ArrayList<>(team));
        } else
            for (int i = 0; i < len; i++) {
                if (visits[i]) //当前数字被用过 当前即不可用
                    continue;
                team.add(nums[i]);
                visits[i] = true;//标记该元素被使用
                dfs(visits, nums, team, index + 1);
                visits[i] = false;// 还原
                team.remove(index);//将结果移除临时集合
            }
    }
}
