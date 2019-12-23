package nowcoder;

import java.io.*;
import java.util.*;

public class Nowcoder {

	public int points(int input1, int[] input2) {
		// Read only region end
		// Write code here...//numOfBattles(N),bottles numbers arr[]

		int numberOfPoint = 0;
		while (input2.length != 0) {
			input2 = shootThePalindrome(input1, input2);
		}

		// return the min number of steps clear the bottles
		return numberOfPoint;
	}

	public int[] shootThePalindrome(int numberOfPoint, int[] input2) {
		for (int size = input2.length; size > 0; size--) {
			for (int low = 0, high = low + size - 1; high < input2.length; low++, high++) {
				if (shrinkCheckPalindrome(input2, low, high)) {
					input2 = Arrays.copyOfRange(input2, low, high + 1);
					int[] newArry1 = Arrays.copyOfRange(input2, 0, low + 1);
					int[] newArry2 = Arrays.copyOfRange(input2, high + 1, input2.length);
					List list = new ArrayList(Arrays.asList(newArry1));
					list.addAll(Arrays.asList(newArry2));
					Object[] c = list.toArray();
					Integer[] d = (Integer[]) Arrays.copyOf(c, c.length);
					int[] e = new int[d.length];

					for (int i = 0; i < e.length; i++) {
						e[i] = d[i];
					}

					numberOfPoint++;

					return e;
				}
			}
		}
		return input2;
	}

	public boolean shrinkCheckPalindrome(int[] input2, int low, int high) {
		while (low <= high) {
			if (input2[low] == input2[high]) {
				low++;
				high--;
			} else {
				return false;
			}
		}
		return true;
	}

}
