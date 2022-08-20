package DoQuestionsOfBigFactory.class18;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/23 11:26
 */
public class Code03_CherryPickup {
    /**
     * https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
     * 给定一个矩阵matrix，先从左上角开始，每一步只能往右或者往下走，走到右下角。然后从右下角出发，
     * 每一步只能往上或者往左走，再回到左上角。任何一个位置的数字，只能获得一遍。返回最大路径和
     *
     *思路： A ：B两个人同时从左上角往右下角走 但是当A B来到同一个格子时， 只能获得一份桃子
     * 这样就得到了 去和来的最大桃子数
     *
     *
     *暴力递归到动态规划（记忆化搜索）
     */
    public static int maxCherry(int[][] matrix){

        int all= 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][][] dp = new int[N][M][N];
        //三维dp表 d可以用a+b-c代替
        dp[0][0][0] = matrix[0][0];
        for(int i =0;i<N;i++){
            for(int j =0;j<M;j++){
                for(int k =0;k<N;k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        int ans = process(matrix,0,0,0, N, M, dp);
        return  ans> 0 ? ans : 0;
    }

    //a b 此时A的位置
    //c d 此时B的位置
    public static int process(int[][] matrix, int a,int b, int c, int N, int M, int [][][] dp){
        //base-acse
        if(dp[a][b][c] != -1){
            return dp[a][b][c];
        }
        if(a == N-1 && b == M-1){
            dp[N-1][M-1][N-1] = matrix[a][b];
            return matrix[a][b];
        }
        //A  B a+b-c
        //下 下
        int d =a+b-c;
        int best = 0;
        if(b < M-1){
            if(d < M-1){
                best = Math.max(best,process(matrix,a,b+1,c, N, M,dp));
            }
            if(c < N-1){
                //下 右
                best = Math.max(best,process(matrix,a,b+1,c+1, N, M,dp));
            }
        }

        if(a < N-1){
            if(d < M-1){
                //右 下
                best = Math.max(best,process(matrix,a+1,b,c, N, M,dp));
            }

            if(c < N-1){
                //右 右
                best = Math.max(best,process(matrix,a+1,b,c+1, N, M,dp));
            }
        }
        if(a== c && b ==d ){
            best += matrix[a][b];
        }else {
            best += matrix[a][b] + matrix[c][d];
        }
        dp[a][b][c] = best;
        return best;
    }

}

   