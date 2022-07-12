package _6.leetcode_dynamic_programming;

import _2.leetcode_string.ValidAnagram;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock btbss = new BestTimeToBuyAndSellStock();

        int[] prices = new int[]{7,1,5,3,6,4};

        System.out.println(btbss.bestTimeToBuyAndSellStock(prices));

    }

    private int bestTimeToBuyAndSellStock(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
