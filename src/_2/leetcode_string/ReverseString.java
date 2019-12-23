package _2.leetcode_string;

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] srcArray = new char[] { 'h','e','l','l','o' };
		SolutionReverseString s = new SolutionReverseString();
		s.reverseString(srcArray);
	}
}

class SolutionReverseString {

	/*
	 * Input: ["h","e","l","l","o"] Output: ["o","l","l","e","h"]
	 */
	public <T> void reverseString(char[]s) {
//    	Stack<Character> stack = new Stack<Character>();
//    	for (char c : s) {
//    		stack.add(c);
//    	}
//    	
//    	char[] out = null;
//    	
//    	for (int i = 0; i < stack.size(); i++) {
//    		out[i] = stack.pop();
//    	}

		int start = 0;
		int end = s.length - 1;

		while (start < end ) {
			char startTmp = 's';
			char endTmp = 'e';
			startTmp = s[start];
			endTmp = s[end];
			s[start] = endTmp;
			s[end] = startTmp;
			start++;
			end--;
		}

		for (char c : s) {
			System.out.println(c);
		}

	}

}
