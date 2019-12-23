package _1.leetcode_array;

public class StockProfit {

	/*Best Time to Buy and Sell Stock II

	Say you have an array for which the ith element is the price of a given stock on day i.
	Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
	Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
	Input: [7,1,5,3,6,4]
	Output: 7
	Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
	*
	*/
	
	
//    public int maxProfit(int[] prices) {
//    	int total = 0;
//    	
//    	for (int i = 0;i < prices.length -1; i++) {
//    		if (prices[i+1] > prices[i]) {
//    			total += prices[i+1] - prices[i];
//    		}
//    	}
//    	
//		return total;
//    }
    
	public int maxProfit(int[] prices) {
		int profit = 0, i = 0;
		while (i < prices.length) {
			// find next local minimum
			while (i < prices.length - 1 && prices[i + 1] <= prices[i])
				i++;
			int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
			// find next local maximum
			while (i < prices.length - 1 && prices[i + 1] >= prices[i])
				i++;
			profit += i < prices.length ? prices[i++] - min : 0;
		}
		return profit;
	}
	
	
	public static void main(String[] args) {
		int[] prices = new int[] {7, 1, 5, 3, 6, 4};
		StockProfit sp = new StockProfit();
		
		System.out.print(sp.maxProfit(prices));
		
	}

}
