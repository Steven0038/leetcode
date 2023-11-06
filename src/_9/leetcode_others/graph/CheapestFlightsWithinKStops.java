package _9.leetcode_others.graph;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops, medium, companies
 * <p>
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 * <p>
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 * <p>
 * There will not be any multiple flights between two cities.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * <p>
 * Output: 700
 * <p>
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 */
public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cfw = new CheapestFlightsWithinKStops();

        /**
         *                       0
         *                     /   ^
         *              (100)v      \(100)
         *                 1 ->(100) 2
         *             (600) \       / (200)
         *                    v     v
         *                       3
         */
        int n = 4;
        int src = 0;
        int dst = 3;
        int k = 1;
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println(cfw.findCheapestPrice(n, flights, src, dst, k));
    }

    /**
     * 找出指定條件的航班路徑的最便宜機票
     *
     * @param n       cities
     * @param flights flights data array,  flights[i] = [fromi, toi, pricei]
     * @param src     source start city
     * @param dst     destination end city
     * @param k       maximum of city stops of a flight
     * @return cheapest price to reach above condition
     * @see NetWorkDelayTime
     */
    private int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // build graph
        Map<Integer, List<Cell>> graph = new HashMap<>();
        for (int[] flight : flights)
            graph.computeIfAbsent(flight[0], value -> new ArrayList<>())
                    .add(new Cell(flight[1], flight[2], 0));

//        int[] stops = new int[n]; Arrays.fill(stops, Integer.MAX_VALUE);
        Map<Integer, Integer> visited = new HashMap<>();
        PriorityQueue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(c -> c.price)); // use heap to do Dijsktra
        heap.offer(new Cell(src, 0, 0));

        while (!heap.isEmpty()) {
            Cell curr = heap.poll();
            int node = curr.node;
            int price = curr.price;
            int steps = curr.steps;
            // We have already encountered a path with a lower cost and fewer stops,
            // or the number of stops exceeds the limit.
//            if (steps > stops[node] || steps > k + 1) continue; stops[node] = steps;
            int prevSteps = visited.getOrDefault(node, Integer.MAX_VALUE); // NOTE
            if (steps > prevSteps || steps > k + 1) continue;
            visited.put(node, steps);

            if (node == dst)
                return price; // heap 永遠是最優的, 所以到達就直接返回

            if (!graph.containsKey(node)) continue;
            for (Cell nei : graph.get(node)) {
                heap.offer(new Cell(nei.node, price + nei.price, steps + 1)); // 累加票價與轉機次數
            }
        }
        return -1;
    }

    class Cell {
        int node;
        int price;
        int steps;

        Cell(int node, int price, int steps) {
            this.node = node;
            this.price = price;
            this.steps = steps;
        }
    }
}
