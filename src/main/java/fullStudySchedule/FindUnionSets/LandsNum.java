package fullStudySchedule.FindUnionSets;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2021/11/24 10:59
 * */
public class LandsNum {


    //递归 200题
    public int findCircleNum(int[][] isConnected) {
        int sets = 0;
        for(int i=0;i< isConnected.length;i++){
            for(int j = i;j<isConnected[0].length;j++){
                if(isConnected[i][j] =='1'){
                    sets++;
                    infect(isConnected,i,j);
                }

            }
        }
        return sets;
    }

    public void infect(int[][] isConnected, int i, int j){

        if(i<0 || i== isConnected.length || j<0 || j==isConnected[0].length || isConnected[i][j] !=1){
            return;
        }

        isConnected[i][j]=2;
        infect(isConnected,i-1,j);
        infect(isConnected,i+1,j);
        infect(isConnected,i,j-1);
        infect(isConnected,i,j+1);

    }

    //并查集

    public int numIslands(char[][] grid){
        int col = grid[0].length;
        int row = grid.length;
        UnionFind unionFind = new UnionFind(grid);

        for(int m=1;m<col;m++){
            if(grid[0][m] == '1'&& grid[0][m-1]=='1'){
                unionFind.union(0,m-1,0,m);
            }
        }
        for(int n=1;n<row;n++){
            if(grid[n][0] == '1'&& grid[n-1][0]=='1'){
                unionFind.union(n-1,0,n,0);
            }
        }

        for (int m = 1; m < row; m++) {
            for (int n = 1; n < col; n++) {
                if (grid[m][n] == '1') {
                    if (grid[m][n - 1] == '1') {
                        unionFind.union(m, n - 1, m, n);
                    }
                    if (grid[m - 1][n] == '1') {
                        unionFind.union(m - 1, n, m, n);
                    }
                }
            }
        }

        return unionFind.sets();
    }


    public class UnionFind{
        int[] parent;
        int[] size;
        int[] help;
        int sets;
        int col;

        public UnionFind(char[][] board) {
            sets =0;
            int row = board.length;
            col = board[0].length;
            int length = row * col;
            parent = new int[length];
            size = new int[length];
            help = new int[length];
            for(int i = 0;i< row;i++){
                for(int j = 0;j<col;j++){
                    if(board[i][j] == '1') {
                        int id = index(i, j);
                        parent[id] = id;
                        size[id] = 1;
                        sets++;
                    }

                }
            }
        }

        private int index(int r, int c){
            return r*col + c;
        }

        public int find(int i){
            int hi = 0;
            while (i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }

            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            /*for (int g = 0; g < hi; g++) {
                parent[help[g]] = i;
            }*/
            return i;
        }

        void union(int r1, int c1, int r2, int c2){
            int i1 = index(r1,c1);
            int i2 = index(r2,c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if(f1 != f2){
                if(size[f1] > size[f2]){
                    parent[f2] = f1;
                    size[f1]+=size[f2];
                }else {
                    parent[f1] = f2;
                    size[f2]+=size[f1];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }




}
   