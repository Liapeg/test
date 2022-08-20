package DoQuestionsOfBigFactory.class22;

import java.util.PriorityQueue;

/**
 * TODO
 *https://leetcode.com/problems/trapping-rain-water-ii/
 * @version 2.0
 * @author lianp
 * @date 2022/8/15 20:12
 *
 */
public class Code03_TrappingRainWaterII {

    /**
     * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     * 思路： 单调栈
     * 单调栈从下往上 依次减小
     * 1、先把最外面一圈放进去 然后弹出第一个（最小的） 也就是出水口 维护max 围墙的最高点
     * 2、把弹出的数 上 下 左 右 没入栈的数加进去 加之前计算他的积水量
     * 3、往外弹出时，更新max
     */
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length ==0 || heightMap[0].length == 0){
            return 0;
        }

        int total = 0;
        //往上依次减小
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>((a,b)-> a.val - b.val);
        //把外面一圈加进去
        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] contains = new boolean[N][M];
        for(int i =0; i< N;i++){
            priorityQueue.add(new Node(i,0,heightMap[i][0]));
            contains[i][0] = true;
        }
        for(int i =0; i< N;i++){
            priorityQueue.add(new Node(i,M-1,heightMap[i][M-1]));
            contains[i][M-1] = true;
        }
        for(int i =0; i< M;i++){
            priorityQueue.add(new Node(0,i,heightMap[0][i]));
            contains[0][i] = true;
        }
        for(int i =0; i< M;i++){
            priorityQueue.add(new Node(N-1,i,heightMap[N-1][i]));
            contains[N-1][i] = true;
        }



        int max = 0;
        while (!priorityQueue.isEmpty()){
            Node cur = priorityQueue.poll();
            int row = cur.row;
            int col = cur.col;
            max = max > cur.val ? max : cur.val;
            //上 下 左 右加进去  算水量
            if(row > 0 && !contains[row-1][col]){
                priorityQueue.add(new Node(row-1,col,heightMap[row-1][col]));
                total += max - heightMap[row-1][col] > 0 ? max - heightMap[row-1][col] :0;
                contains[row-1][col] = true;
            }
            //下
            if(row < N-1 && !contains[row+1][col]){
                priorityQueue.add(new Node(row+1,col,heightMap[row+1][col]));
                total += max - heightMap[row+1][col] > 0 ? max - heightMap[row+1][col] :0;
                contains[row+1][col] = true;
            }
            //左
            if(col > 0 && !contains[row][col-1]){
                priorityQueue.add(new Node(row,col-1,heightMap[row][col-1]));
                total += max - heightMap[row][col-1] > 0 ? max - heightMap[row][col-1] :0;
                contains[row][col-1] = true;
            }
            //右
            if(col < M-1 && !contains[row][col+1]){
                priorityQueue.add(new Node(row,col+1,heightMap[row][col+1]));
                total += max - heightMap[row][col+1] > 0 ? max - heightMap[row][col+1] :0;
                contains[row][col+1] = true;
            }

        }
        return total;
    }



    public class Node{
        int row;
        int col;
        int val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}

   