package _2.leetcode_string;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Examples:
 * s = "leetcode" return 0.
 * s = "loveleetcode", return 2. 
 * Note: You may assume the string contain only lowercase letters.
 * 
 * @author steven
 *
 */
public class FirstUniqChar {

	public static void main(String[] args) {
		String s = "loveleetcode";
		System.out.print(firstUniqChar(s));
	}

	private static int firstUniqChar(@NotNull String s) {
		// String to char array
		// LinkedHashMap's key is orderd by input sequence
		Map<Character, Integer> map = new LinkedHashMap<>();
		char[] charArr = s.toCharArray();
		// use Map<char, count> to iterate all String
		for (char c : charArr) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}

		Character uniq = null;
		// find the first count 1 element in Map
		for (char k : map.keySet()) {
			if (map.get(k) == 1) {
				uniq = k;
				break;
			}
		}

		// find the element index in String
		for (int i = 0; i < charArr.length; i++) {
			if (uniq != null && charArr[i] == uniq) {
				return i;
			}
		}

		return -1;
	}

	private static int firstUniqChar1(@NotNull String s) {
		// new a Map to store <character, count>
		Map<Character, Integer> map = new LinkedHashMap<>();
		char[] charArr = s.toCharArray();
		for (Character c : charArr) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}

		// find the first character which value count is 1
		int index = 0;
		for (Character c : charArr) {
			if (map.get(c) == 1) {
				return index;
			} else {
				index += 1;
			}
		}

		return -1;
	}


	 public int firstUniqChar2(String s) {
         Map<Character, Integer> map = new LinkedHashMap<>();
         Set<Character> set = new HashSet<>();
         for (int i = 0; i < s.length(); i++) {
             if (set.contains(s.charAt(i))) {
                 if (map.get(s.charAt(i)) != null) {
                     map.remove(s.charAt(i));
                 }
             } else {
                 map.put(s.charAt(i), i);
                 set.add(s.charAt(i));
             }
         }
         return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
     }
}
