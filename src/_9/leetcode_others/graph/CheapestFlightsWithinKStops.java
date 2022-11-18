package _9.leetcode_others.graph;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops, medium, companies
 * <p>
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 * <p>
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
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
        Map<Integer, List<int[]>> map = new HashMap<>(); // demo 偷懶用 int[]不用 Cell
        for (int[] flight : flights) {
            List<int[]> to = map.getOrDefault(flight[0], new ArrayList<>());
            to.add(new int[]{flight[1], flight[2]});
            map.put(flight[0], to);
        }

        // 題目有提到 There will not be any multiple flights between two cities., 所以不會有環,不需要查重紀錄訪問過的節點

        PriorityQueue<Cell> heap = new PriorityQueue<>();
        heap.offer(new Cell(src, k, 0));
        while (!heap.isEmpty()) {
            Cell curr = heap.poll();
            if (curr.dst == dst) return curr.price; // heap 永遠是最優的, 所以到達就直接返回
            if (curr.stop >= 0 && map.containsKey(curr.dst)) { // 用減到0的方式限制轉機次數(stop) k
                for (int[] next : map.get(curr.dst)) {
                    heap.offer(new Cell(next[0], curr.stop - 1, curr.price + next[1]));
                }
            }
        }

        return -1;
    }

    class Cell implements Comparable<Cell> {
        int dst, stop, price;

        Cell(int dst, int stop, int price) {
            this.dst = dst;
            this.stop = stop;
            this.price = price;
        }

        public int compareTo(Cell other) {
            return price - other.price;
        }
    }

//    private int findCheapestPrice(int n, int[][] flights, int  src, int dst, int k) {
//        // build graph
//        Map<Integer, List<Cell>> graph = new HashMap<>();
//        for (int[] flight : flights) {
//            List<Cell> nextStops = graph.getOrDefault(flight[0], new ArrayList<>());
//            nextStops.add(new Cell(flight[1], flight[2], k));
//            graph.put(flight[0], nextStops);
//        }
//
//        // use heap do dijstra
//        PriorityQueue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.price));
//        heap.add(new Cell(src, 0, k));
//
//        while (!heap.isEmpty()) {
//            Cell curr = heap.poll();
//            if (curr.dest == dst)
//                return curr.price;
//            // add all next stops to heap
//            if (curr.transCount >= 0 && graph.containsKey(curr.dest)) {
//                for (Cell next : graph.get(curr.dest)) {
//                    heap.add(new Cell(next.dest, curr.price + next.price, curr.transCount - 1));
//                }
//            }
//        }
//
//        return -1;
//    }
//
//    class Cell {
//        int dest;
//        int price;
//        int transCount;
//
//        Cell(int dest, int price, int transCount) {
//            this.dest = dest;
//            this.price = price;
//            this.transCount = transCount;
//        }
//    }

}
