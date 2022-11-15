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

        /**
         *            2
         *       (1) / \ (1)
         *          1   3
         *               \ (1)
         *                4
         */
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}; // times[i] = (ui, vi, wi) 起點,終點,權重
        int n = 4;
        int k = 2;

        System.out.println(nw.networkDelayTime(times, n, k));
        System.out.println(nw.networkDelayTime2(times, n, k));
        System.out.println(nw.networkDelayTime3(times, n, k));
    }

    /**
     * Best First Search (Dijsktra), 有權圖單源最短路徑問題(從節點K出發, 需要多久才能讓所有節點接收到信號), 優先展開最優的節點
     * <p>
     * 整個圖都滲透完的 cost, 就是題目所需的到達最遠節點所需 cost 時間(計算到達最遠節點所需的最短加權路徑), O(V+E)
     *
     * @param times input node 2D array [index][source node, destination target node, cost time]
     * @param n     given number of nodes will receive the signal
     * @param k     given source node
     * @return minimum delay time of all nodes received signal
     */
    private int networkDelayTime(int[][] times, int n, int k) {
        // 依據題目給定的 array, 整理好路徑建立 map graph
        Map<Integer, List<Cell>> graph = new HashMap<>(); // <srcNode, (desNode, cost)> 起點,終點,權重
        for (int[] time : times) { // time:[srcNode, desNode, cost]
            List<Cell> edges = graph.getOrDefault(time[0], new ArrayList<>());
            edges.add(new Cell(time[1], time[2])); // add new neighbor to source node
            graph.put(time[0], edges);
        }

        // <node, cost from start node>
        Map<Integer, Integer> visitedCosts = new HashMap<>(); // 用以紀錄 cost 以及查重, costs is delay time
//        PriorityQueue<Cell> heap = new PriorityQueue<>();
        PriorityQueue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.time)); // 不指定 comparator 默認為最小堆
        heap.offer(new Cell(k, 0));
        while (!heap.isEmpty()) {
            Cell cur = heap.poll(); // heap 永遠會從棧頂拉出最小 time cost Cell, 是故只要 heap 有最小路徑數據, 就從最小路徑繼續往鄰接節點找
            if (visitedCosts.containsKey(cur.node)) continue; // is visited (展開過就不需重複展開, 不然如果有環會死循環)
            visitedCosts.put(cur.node, cur.time); // 更新該節點從起點到該節點的路徑數據 cost

            // 如果有 neighbor, 就向 heap 添加其所有鄰居的路徑數據
            if (graph.containsKey(cur.node)) {
                for (Cell nei : graph.get(cur.node)) {
//                    if (!visitedCosts.containsKey(nei.node)) { // 可以不檢查,單純優化
                        // 向 heap 添加路徑數據(同一node 可能會被從不同路徑添加多次, 但 heap 會取最小 time 在棧頂)
                        heap.offer(new Cell(nei.node, cur.time + nei.time)); // 核心算法,更新cost: 起點到當前節點的最優路徑cost+當前節點到鄰居節點的cost
//                    }
                }
            }
        }

        if (visitedCosts.size() != n) return -1; // 不是所有節點都 reach 到 (有節點是不互相連通的)

        // 回傳 cost 中的最大值 (雖然題目問的是所有節點被滲透的最小時間, 但實際上問的就是到達最遠節點的 cost, 最遠節點被滲透,其他節點一定也被滲透)
        int res = 0;
        for (int x : visitedCosts.values()) {
            res = Math.max(res, x);
        }

        return res;
    }

    class Cell
//            implements Comparable<Cell>
    {
        int node; // destination
        int time; // cost

        Cell(int node, int time) {
            this.node = node;
            this.time = time;
        }
//        // for heap
//        public int compareTo(Cell c2) {
//            return time - c2.time;
//        }
    }

    public int networkDelayTime2(int[][] times, int N, int K) {
        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>(); // <源, <目標,權值>>
        // times中的數組 a, a[0]代表源，a[1]代表目標，a[2]代表權值，單向的
        for (int[] t : times) {
            graph.putIfAbsent(t[0], new HashMap<>());
            graph.get(t[0]).put(t[1], t[2]);
        }

        // 距離, 目標 [權值(從起點到當前節點), 節點]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 存放二個點之間的 weight
        pq.offer(new int[]{0, K}); // 放入起始點K (權值0, 因為從K點到K點沒有花費)
        boolean[] visited = new boolean[N + 1]; // 紀錄節點是否有訪問過
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDistance = cur[0]; // 當前節點的代價(從起點到當前節點的權值)
            int curNode = cur[1];
            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true; // 標註為已訪問
            res = curDistance;
            N--;
            if (N == 0) {
                break;
            }
            if (graph.containsKey(curNode)) {
                for (int next : graph.get(curNode).keySet()) { // 遍歷當前所有節點的鄰居節點 next
                    // 把走到下一節點 next 的代價,累加到當前的代價 curDistance 上, 再放入 pq 繼續循環
                    pq.offer(new int[]{curDistance + graph.get(curNode).get(next), next}); // 距離, 目標
                }
            }
        }
        return N == 0 ? res : -1; // 所有節點都遍歷完就返回代價 res, 不然就返回-1(代表有節點無法從K出發訪問到)
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