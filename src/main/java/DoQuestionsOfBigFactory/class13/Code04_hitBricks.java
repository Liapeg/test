package DoQuestionsOfBigFactory.class13;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/12 22:16
 */
public class Code04_hitBricks {
    /**
     * 有一个 m x n 的二元网格grid，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
     *
     * 一块砖直接连接到网格的顶部，或者
     * 至少有一块相邻（4个方向之一）砖块 稳定 不会掉落时
     * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而 掉落 。一旦砖块掉落，它会 立即 从网格 grid 中消失（即，它不会落在其他稳定的砖块上）。
     * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
     * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
     *
     * 思路： 并查集
     * 很难
     *
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {

        int len = hits.length;
        int wid = hits[0].length;
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) {
                grid[hits[i][0]][hits[i][1]] = 2;
            }
        }

        int[] ans = new int[len];
        UnionFind unionFind = new UnionFind(grid);


        for(int i = hits.length-1; i>=0;i--){
            if(grid[hits[i][0]][ hits[i][1]] == 2){
                ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }

    public class UnionFind{
        int N;
        int M;
        int cellingAll;
        //是否是天花板属性
        boolean[] cellingSet;
        //原始数组
        int[][] grid;
        //父节点
        int[] fatherMap;
        int[] sizeMap;
        int[] stack;

        public UnionFind(int[][] grid) {
            this.grid = grid;
            initSpace();
            initConnect();
        }

        //将每个是1的元素 都连成一个小集合
        public void initSpace(){
            N = grid.length;
            M= grid[0].length;
            int all = N *M;
            fatherMap = new int[all];
            sizeMap = new int[all];
            stack = new int[all];
            cellingSet = new boolean[all];
            for(int row = 0;row <N;row++){
                for(int col = 0; col < M;col++){

                    if(grid[row][col] == 1){
                        int cur = row * M +col;
                        fatherMap[cur] = cur;
                        sizeMap[cur] = 1;
                        //第一行的1是天花板集合
                        if(row == 0){
                            cellingSet[cur] = true;
                            cellingAll++;
                        }
                    }
                }
            }
        }

        //初始化连接  将上下左右的1连起来
        public void initConnect(){
            for(int row = 0;row <N;row++){
                for(int col = 0; col < M;col++){
                    //上下左右合并
                    union(row, col,row-1,col);
                    union(row, col,row+1,col);
                    union(row, col,row,col-1);
                    union(row, col,row,col+1);

                }
            }
        }

        public boolean isValid(int row, int col){
            return row >=0 && row <N && col >=0 && col < M && grid[row][col] ==1;
        }

        //连接方法
        public void union(int row1, int col1, int row2, int col2){
            if(isValid(row1, col1) && isValid(row2, col2)){
                int father1 = find(row1, col1);
                int father2 = find(row2, col2);
                if (father2 != father1) {
                    int size1 = sizeMap[father1];
                    int size2 = sizeMap[father2];
                    boolean status1 = cellingSet[father1];
                    boolean status2 = cellingSet[father2];
                    if(size1 <= size2){
                        fatherMap[father1] = father2;
                        sizeMap[father2] = size1 +size2;
                        //变更天花板集合
                        if(status1 ^ status2){
                            cellingSet[father2] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    }else {
                        fatherMap[father2] = father1;
                        sizeMap[father1] = size1 +size2;
                        //变更天花板集合
                        if(status1 ^ status2){
                            cellingSet[father1] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    }
                }
            }
        }

        //寻找父节点的方法
        public int find(int row, int col){
            int index = row * M +col;
            int stackSize = 0;
            //他的父不是他自己
            while (index != fatherMap[index]){
                //记录每次的index  设置他的父为最后找到的index
                stack[stackSize++] = index;
                index = fatherMap[index];
            }
            while(stackSize !=0) {
                fatherMap[stack[--stackSize]] = index;
            }

            return index;
        }


        public int finger(int row ,int col){
            //总的天花板砖数
            grid[row][col] = 1;
            int cur = row * M + col;
            if(row == 0){
                cellingSet[cur] = true;
                cellingAll++;
            }

            fatherMap[cur] = cur;
            sizeMap[cur] = 1;
            int pre = cellingAll;
            union(row, col, row-1, col);
            union(row, col, row+1, col);
            union(row, col, row, col-1);
            union(row, col, row, col+1);
            //合并之后天花板砖数量的变化就是之前击落的砖数
            int now = cellingAll;
            if(row == 0){
                return now -pre;
            }else {
                return now == pre ? 0 : now - pre - 1;
            }

        }

    }
}

   