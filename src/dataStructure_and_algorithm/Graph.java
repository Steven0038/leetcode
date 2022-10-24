package dataStructure_and_algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList; // 儲存頂點集合
    private int[][] edges; // 儲存圖對應的鄰接矩陣
    private int numOfEdges; // 表示邊的數目
    private boolean[] isVisited; // 定義給數組 boolean[], 記錄某個節點是否被訪問

    public static void main(String[] args) {
        // 測試圖是否成功創建
        int n = 5; // 節點的個數
//        int n = 8; // 節點的個數
        String[] Vertexes = {"A", "B", "C", "D", "E"};
//        String Vertexes[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        // 創建圖對象
        Graph graph = new Graph(n);
        // 循環添加頂點
        for (String vertex : Vertexes) {
            graph.insertVertex(vertex);
        }

        // 添加邊
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 顯示鄰接矩陣
        graph.showGraph();

        // 測試 dfs
        System.out.println("深度遍歷");
        graph.dfs();
    }

    // 顯示圖對應的矩陣
    private void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    public Graph(int n) {
        // 初始化矩陣與 vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    /**
     * 得到第一個鄰接節點的下標 w
     *
     * @param index 查找的頂點(row index)
     * @return 存在就返回對應的下標, 否則返回 -1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    /**
     * 根據前一個鄰接節點的下標, 來獲取下一個鄰接節點
     *
     * @param v1 查找的頂點 (row index)
     * @param v2 前一個鄰接節點的下標 (column index)
     * @return 下一個鄰接節點, -1 為找不到
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    // 深度優先遍歷算法
    // i 第一次就是 0
    private void dfs(boolean[] isVisited, int i) {
        // 首先訪問該節點, 並輸出
        System.out.print(getValueByIndex(i) + "->");
        // 將節點設置為已經訪問
        isVisited[i] = true;
        // 查找節點 i 的第一個鄰接節點 w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果 w 已經被訪問過
            w = getNextNeighbor(i, w);
        }

    }

    // overloading method, 遍歷圖所有的節點, 並進行 dfs
    private void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // === 圖中常用的方法 ===

    // 返回節點的個數
    private int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回邊的數目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回節點 i(下標) 對應的數據 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 插入節點
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加邊
     *
     * @param v1     表示點的下標, 即第幾個頂點 "A"-"B" "A"->0 "B"->1
     * @param v2     第二個頂點對應的下標
     * @param weight 邊的權重
     */
    private void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
