package _2.leetcode_string;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * 
 * Example 1:
 * Input: s = "anagram", t = "nagaram" Output: true Example 2:
 * Input: s = "rat", t = "car" Output: false Note: You may assume the string
 * contains only lowercase alphabets.
 * 
 * Follow up: What if the inputs contain unicode characters? How would you adapt
 * your solution to such case?
 * 
 * @author steven
 *
 */
public class ValidAnagram {

	public static void main(String[] args) {
		ValidAnagram  va = new ValidAnagram();
		String s = "anagram"; 
		String t = "nagaram";
		System.out.print(va.isAnagram(s, t));
	}

	private boolean isAnagram(String s, String t) {
		int[] alphabet = new int[26];
		for (int i = 0; i < s.length(); i++) {
			alphabet[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			alphabet[t.charAt(i) - 'a']--;
		}
		for (int i : alphabet) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
