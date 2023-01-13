package _9.leetcode_others;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        int n = 5;
        pt.generate(n);
    }

    private List<List<Integer>> generate(int numRows) {
        /**      1
         / \
         1   1
         / \ / \
         1   2   1
         / \ / \ / \
         1    3   3   1
         **/


        List<List<Integer>> ret = new ArrayList<>();

        for(int i = 1; i <= numRows; i++) {

            List<Integer> nowLevel = new ArrayList<>();

            for(int j = 1; j <= i; j++) {
                if(j == 1 || j == i) {
                    nowLevel.add(1);
                } else {
                    nowLevel.add(ret.get(i - 1 - 1).get(j - 1 - 1) + ret.get(i - 1 - 1).get(j - 1));
                }
            }
            ret.add(nowLevel);
        }

        return ret;
    }
}
