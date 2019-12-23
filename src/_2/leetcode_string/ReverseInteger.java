package _2.leetcode_string;

public class ReverseInteger {

	public static void main(String[] args) {
//		SolutionReverseInteger s = new SolutionReverseInteger();
		GFG gfg = new GFG();
		int x = -123456;
		
		
//		System.out.print(s.reverse(x));
		System.out.print(gfg.reversDigits(x));
	}

}

//Java program to reverse a number  

class GFG {
	/*
	 * Iterative function to reverse digits of num
	 */
	static int reversDigits(int x) {

		boolean isNegative = false;
		if (x < 0) {
			isNegative = true;
			x = -x;
		}

		int prev_revNum = 0, revNum = 0;
		while (x > 0) {
			int currDigit = x % 10;

			revNum = revNum * 10 + currDigit; // 緇计单ぃ耞Ю计

			// checking if the reverse overflowed or not.
			// The values of (rev_num - curr_digit)/10 and
			// prev_rev_num must be same if there was no
			// problem.
			if ((revNum - currDigit) / 10 != prev_revNum) {
				System.out.println("Warrning Overflow!!!");
				return 0;
			}

			prev_revNum = revNum;

			x = x / 10; // 埃 10 单奔竒Ю计
		}

		return isNegative ? -revNum : revNum;
	}
}

final class SolutionReverseInteger{
	public int reverse(int x) {
        try {
			// convert int to string
			String y = String.valueOf(x);
			
			// switch the end and tail
			if (y.startsWith("-")) {
				y = y.substring(1);
				y = changePosition(y);
				return - Integer.valueOf(y);
			} else {
				y = changePosition(y);
				return Integer.valueOf(y);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	private String changePosition(String y) {
		char[] chars = y.toCharArray();
		int s = 0;
		int e = chars.length - 1;

		while (s < e) {
			char startTmp = 's';
			char endTmp = 'e';
			startTmp = chars[s];
			endTmp = chars[e];
			chars[s] = endTmp;
			chars[e] = startTmp;
			s++;
			e--;
		}
		return String.valueOf(chars);
	}
}
