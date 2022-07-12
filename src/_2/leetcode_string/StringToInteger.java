package _2.leetcode_string;

/**
 * Implement atoi which converts a string to an integer.
 * 
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.
 * 
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function.
 * 
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty or
 * it contains only whitespace characters, no conversion is performed.
 * 
 * If no valid conversion could be performed, a zero value is returned.
 * 
 * Note:
 * 
 * Only the space character ' ' is considered as whitespace character. Assume we
 * are dealing with an environment which could only store integers within the
 * 32-bit signed integer range: [−231, 231 − 1]. If the numerical value is out
 * of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is
 * returned. 
 * 
 * Example 1:
 * Input: "42" Output: 42 
 * 
 * Example 2:
 * Input: " -42" Output: -42 Explanation: The first non-whitespace character is
 * '-', which is the minus sign. Then take as many numerical digits as possible,
 * which gets 42. 
 * 
 * Example 3:
 * Input: "4193 with words" Output: 4193 Explanation: Conversion stops at digit
 * '3' as the next character is not a numerical digit. 
 * 
 * Example 4:
 * Input: "words and 987" Output: 0 Explanation: The first non-whitespace
 * character is 'w', which is not a numerical digit or a +/- sign. Therefore no
 * valid conversion could be performed. 
 * 
 * Example 5:
 * Input: "-91283472332" Output: -2147483648 Explanation: The number
 * "-91283472332" is out of the range of a 32-bit signed integer. Thefore
 * INT_MIN (−231) is returned.
 * 
 * @author steven
 *
 */
public class StringToInteger {

	public static void main(String[] args) {
		StringToInteger st = new StringToInteger();
//		String str = "   -42";
		String str = "   ";

		System.out.print(st.myAtoi(str));
	}

	private int myAtoi(String str) {
		// check not null, empty string and check space
		if (str.isEmpty() || str == null) {
			return 0;
		}
		
		str = str.trim();// trim the front space

		System.out.println(" 123: " + str.equals(""));
		System.out.println(" 456: " + str.isEmpty());

		if (str.equals("")) {
			return 0;
		}
		
		int sign = 1;
		int start = 0;
		
		// check minus
		if (str.charAt(0) == '+') {
			start++;
		} else if (str.charAt(0) == '-') {
			sign = -1;
			start++;
		}
		
		long sum = 0;
		// check is digit 
		for (int i = start; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return (int)sum * sign;
			}
			sum = sum * 10 + (str.charAt(i) - '0');	// ANSII calculation
			if (sign == 1 && sum > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			} else if (sign == -1 && sum * -1 < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		
		// check is out of range or not
		return (int)sum * sign;
	}
	
//	public int myAtoi(String str) {
//	    int index = 0, sign = 1, total = 0;
//	    //1. Empty string
//	    if(str.length() == 0) return 0;
//
//	    //2. Remove Spaces
//	    while(str.charAt(index) == ' ' && index < str.length())
//	        index ++;
//
//	    //3. Handle signs
//	    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
//	        sign = str.charAt(index) == '+' ? 1 : -1;
//	        index ++;
//	    }
//	    
//	    //4. Convert number and avoid overflow
//	    while(index < str.length()){
//	        int digit = str.charAt(index) - '0';
//	        if(digit < 0 || digit > 9) break;
//
//	        //check if total will be overflow after 10 times and add digit
//	        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
//	            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//
//	        total = 10 * total + digit;
//	        index ++;
//	    }
//	    return total * sign;
//	}

}
