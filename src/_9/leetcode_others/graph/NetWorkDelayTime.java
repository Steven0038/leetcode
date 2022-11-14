package _9.leetcode_others.graph;

import java.util.*;

/**
 * 743. Network Delay Time, medium, companies
 * <p>
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node,
 * and wi is the time it takes for a signal to travel from source to target.
 * <p>
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * <p>
 * Output: 2
 */
public class NetWorkDelayTime {
    public static void main(String[] args) {
        NetWorkDelayTime nw = new NetWorkDelayTime();
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;

        System.out.println(nw.networkDelayTime(times, n, k));
        System.out.println(nw.networkDelayTime2(times, n, k));
        System.out.println(nw.networkDelayTime3(times, n, k));
    }

    /**
     * Best First Search (Dijsktra), 有權圖單源最短路徑問題, 優先展開最優的節點
     * <p>
     * 整個圖都滲透完的 cost, 就是題目所需的到達最遠節點所需 cost 時間(計算到達最遠節點所需的最短加權路徑), O(V+E)
     *
     * @param times input node 2D array [index][source node, destination target node, cost time]
     * @param n     given number of nodes will receive the signal
     * @param k     given source node
     * @return minimum delay time of all nodes received signal
     */
    private int networkDelayTime(int[][] times, int n, int k) {
        // 依據題目給定的 array, 整理好路徑 map graph
        Map<Integer, List<Cell>> map = new HashMap<>(); // <src, (des, cost)>
        for (int[] time : times) { // time:[src, des, cost]
            List<Cell> edges = map.getOrDefault(time[0], new ArrayList<>());
            edges.add(new Cell(time[1], time[2])); // add new neighbor to source node
            map.put(time[0], edges);
        }

        Map<Integer, Integer> costs = new HashMap<>(); // costs is delay time
        PriorityQueue<Cell> heap = new PriorityQueue<>();
        heap.offer(new Cell(k, 0));
        while (!heap.isEmpty()) {
            Cell cur = heap.poll(); // heap 永遠會從棧頂拉出最大 Cell, 是故只要 heap 有最大路徑數據, 就從最大路徑繼續往鄰接節點找
            if (costs.containsKey(cur.node)) continue; // is visited
            costs.put(cur.node, cur.time);

            if (map.containsKey(cur.node)) { // 如果有 neighbor
                for (Cell nei : map.get(cur.node)) {
                    if (!costs.containsKey(nei.node)) { // is not visited
                        heap.offer(new Cell(nei.node, cur.time + nei.time)); // 向 heap 添加路徑數據(同一node 可能會被從不同路徑添加多次, 但 heap 會取最大 time)
                    }
                }
            }
        }

        if (costs.size() != n) return -1;

        int res = 0;
        for (int x : costs.values()) {
            res = Math.max(res, x);
        }

        return res;
    }

    public int networkDelayTime2(int[][] times, int N, int K) {
        HashMap<Integer, HashMap<Integer, Integer>> g = new HashMap<>(); // <源, <目標,權值>>
        // times中的数组 a, a[0]代表源，a[1]代表目标，a[2]代表权值，单向的
        for (int[] t : times) {
            g.putIfAbsent(t[0], new HashMap<>());
            g.get(t[0]).put(t[1], t[2]);
        }

        // 距离, 目标
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, K});
        boolean[] visited = new boolean[N + 1];
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDistance = cur[0];
            int curNode = cur[1];
            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            res = curDistance;
            N--;
            if (N == 0) {
                break;
            }
            if (g.containsKey(curNode)) {
                for (int next : g.get(curNode).keySet()) {
                    pq.offer(new int[]{curDistance + g.get(curNode).get(next), next}); // 距离, 目标
                }
            }
        }
        return N == 0 ? res : -1;
    }

    public int networkDelayTime3(int[][] times, int n, int k) {
        // 構造鄰接表
        List<List<int[]>> list = new ArrayList<>(); // index: node, int[0] 目標節點, int[1] 傳遞時間
        for (int i = 0; i <= n; i++)
            list.add(new ArrayList<>());
        for (int[] edges : times) {
            list.get(edges[0]).add(new int[]{edges[1], edges[2]});
        }

        // Dijkstra 算法
        final int INF = (int) 1e5; // 不可能出現的最大時間,用於初始化
        int[] delay = new int[n + 1]; // 從節點k出發到各節點的最短時間, index: node, value: 傳遞時間
        Arrays.fill(delay, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{k, 0}); // 從節點k出發
        while (!pq.isEmpty()) {
            int[] edge = pq.poll(); // 選取目前最短邊
            int node = edge[0], time = edge[1];
            if (time >= delay[node]) // 該節點已到達過,跳過
                continue;
            delay[node] = time;
            for (int[] nodes : list.get(node)) { // 更新從當前節點出發可到達節點的最短邊
                if (delay[nodes[0]] > time + nodes[1]) // 從當前出發的邊更短
                    pq.offer(new int[]{nodes[0], time + nodes[1]}); // 入堆
            }
        }

        // 判斷是否所有節點都收到了信號, 並輸出答案
        delay[0] = 0; // 避免delay[0]干擾
        int ans = Arrays.stream(delay).max().getAsInt(); // 獲取 delay[] 中的最大值

        return (ans == INF) ? -1 : ans; // 存在未更新的時間值,說明有節點無法到達,反之輸出最長時間
    }

}

class Cell implements Comparable<Cell> {
    int node; // destination
    int time; // cost

    Cell(int node, int time) {
        this.node = node;
        this.time = time;
    }

    public int compareTo(Cell c2) {
        return time - c2.time;
    }
}
