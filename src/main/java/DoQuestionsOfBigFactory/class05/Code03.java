package DoQuestionsOfBigFactory.class05;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/15 15:53
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class Code03 {

    /**
     * 增 删 改的代价为1
     * str1变为str2
     * @param str1
     * @param str2
     * @return
     * 样本模型/行列模型
     */
    public int minDistance(String str1, String str2){

        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int N = c1.length;
        int M = c2.length;
        int[][] dp = new int[N + 1][M + 1];

        //str1到str2   str1第1行组成
        for (int i = 0; i <= N; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= M; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                //列举可能性
                //1.删
                dp[i][j] = dp[i - 1][j] + 1;
                //2.增
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1] + 1);
                //3.改或不动
                dp[i][j] = Math.min(dp[i][j],dp[i - 1][j - 1] + (c1[i-1] == c2[j-1] ? 0 : 1));
            }
        }
        return dp[N][M];
    }
}

   