package _2.leetcode_string;

/* Longest Common Prefix
 * Topic: 給定一個 string array, 找出共同最大前綴子字串
 * Solution: 假定陣列第一個字串為暫存前綴, 之後比較陣列中每個元素, 如果與暫存不一樣就把暫存前綴最後一個字串剪掉, 直到與暫存前綴一樣為止,
 *           之後逐步 iterate 陣列中每個元素做一樣的事
 * hard point: 比較用 String.indexOf(), return 值為指定字符在字串中第一次出現處的索引, -1 代表沒有這樣的字符,
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        LongestCommonPrefix lp = new LongestCommonPrefix();
        String prefix = lp.longestCommonPrefix(strs);

        System.out.println("prefix: " + prefix);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String curPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(curPrefix) != 0) {
                curPrefix = curPrefix.substring(0, curPrefix.length() - 1); // 刪掉最後一個 char 直到 curPrefix 在第一個 Index 被發現 (Common Prefix)
                if (curPrefix.isEmpty()) {
                    return "";
                }
            }
        }

        return curPrefix;

        // if (strs == null || strs.length == 0) return "";
        // String pre = strs[0];
        // int i = 1;
        // while (i < strs.length){
        //     while (strs[i].indexOf(pre) != 0)
        //         pre = pre.substring(0, pre.length() - 1);
        //     i++;
        // }
        // return pre;

    }
}
