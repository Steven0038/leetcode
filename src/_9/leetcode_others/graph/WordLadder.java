package _9.leetcode_others.graph;

import java.util.*;

/**
 * 127. Word Ladder, hard, companies
 * <p>
 * A transformation sequence from word beginWord to word endWord
 * using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * <p>
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 */
public class WordLadder {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>(List.of(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println("wordList: " + wordList);

        WordLadder wl = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        System.out.println("ladderLength: " + wl.ladderLength(beginWord, endWord, wordList));
    }

    /**
     * BFS 查找無向圖
     * @param beginWord 開始字串
     * @param endWord 結束字串
     * @param wordList input 字串列表
     * @return 無向圖中, 開始字串訪問至結束字串的節點數
     */
    private int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        if (!wordList.contains(beginWord)) wordList.add(beginWord);

        Map<String, List<String>> graph = constructGraph(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(beginWord);
        queue.add(beginWord);

        int level = 1; // 節點數量 = 路徑數量, 所以初始設為1
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // NOTE: size
                String cur = queue.poll();
                if (cur.equals(endWord)) {
                    return level;
                }

                for (String neighbor : graph.getOrDefault(cur, new ArrayList<>())) { // 從圖中取得鄰接節點
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                        System.out.println("visit current node: " + cur);
                    }
                }
            }

            level++;
        }

        return 0;
    }

    /**
     * 使用 wordList 構建無向圖, 如果 word 間只相差一個 char, 就建立連線
     * @param wordList input word list
     * @return graph
     */
    private Map<String, List<String>> constructGraph(List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>(); // adjacency list map graph
        int n = wordList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                String w1 = wordList.get(i), w2 = wordList.get(j);
                if (oneChangeAway(w1, w2)) {
                    graph.computeIfAbsent(w1, k -> new ArrayList<>()).add(w2); // w1-w2 建立無向圖連線
                    graph.computeIfAbsent(w2, k -> new ArrayList<>()).add(w1); // w2-w1
                }
            }
        }

        System.out.println("graph: " + graph);

        return graph;
    }

    // 是否只差一個 char
    private boolean oneChangeAway(String w1, String w2) {
        int diff = 0;
        for (int i = 0; i < w1.length(); i++) { // 因為題目設定的 str 長度一樣, 所以可以直接比
            char c1 = w1.charAt(i), c2 = w2.charAt(i);
            if (c1 != c2) {
                diff++;
            }
        }
        return diff == 1;
    }
}
