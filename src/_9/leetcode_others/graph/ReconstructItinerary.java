package _9.leetcode_others.graph;

import java.util.*;

/**
 * 332. Reconstruct Itinerary, hard, companies
 * <p>
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 * <p>
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * <p>
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 * <p>
 * Example 1:
 * <p>
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * <p>
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * <p>
 * Example 2:
 * <p>
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * <p>
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * <p>
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 */
public class ReconstructItinerary {
    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();
        List<List<String>> tickets = new ArrayList<>();
        List<String> a = List.of("JFK", "SFO");
        List<String> b = List.of("JFK", "ATL");
        List<String> c = List.of("SFO", "ATL");
        List<String> d = List.of("ATL", "JFK");
        List<String> e = List.of("ATL", "SFO");
        tickets.add(a);
        tickets.add(b);
        tickets.add(c);
        tickets.add(d);
        tickets.add(e);

        System.out.println(ri.findItinerary(tickets));
    }

    /**
     * 遞迴從給定的機票與出發站尋找飛航順序列表, 每張機票只能用一次且必須要用一次
     * <p>
     * O(V+ElogE)
     *
     * @param tickets given flight tickets (ticket: from dest)
     * @return list of itinerary
     */
    private List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>(); // adjacency Heap Map (因為題目有說字符串有區分順序)
        for (List<String> edges : tickets) {
//            PriorityQueue<String> next =  map.getOrDefault(edges.get(0), new PriorityQueue<>());
//            map.put(edges.get(0), next);
            graph.computeIfAbsent(edges.get(0), k -> new PriorityQueue<>()).add(edges.get(1));
        }

        List<String> res = new LinkedList<>(); // linked list 可以插在前面, 這樣就不用 reverse 結果
        dfs(res, graph, "JFK");

        return res;
    }

    // post-order dfs (因為有死路的可能, 先取出鄰居做 dfs 加入 res, 最後再加入自己)
    private void dfs(List<String> res, Map<String, PriorityQueue<String>> graph, String cur) {
        PriorityQueue<String> neis = graph.getOrDefault(cur, new PriorityQueue<>()); // 如果拿不到,代表是個死結點, 就返回空的 queue
        while (!neis.isEmpty()) {
            dfs(res, graph, neis.poll());
        }
        res.add(0, cur); // 插在最前面
    }
}
