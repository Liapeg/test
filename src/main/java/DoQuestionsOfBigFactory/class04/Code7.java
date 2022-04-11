package DoQuestionsOfBigFactory.class04;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/28 14:29
 */
public class Code7 {

    /**
     * https://leetcode-cn.com/problems/interleaving-string
     * dp 样本对应模型
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();

        int N = c1.length;
        int M  = c2.length;
        if(c3.length != N+M){
            return false;
        }
        //[0,0]位置默认为true
        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][0] = true;

        //只用str1    N行0列 从1...N位置
        for(int i =1; i<N;i++){
            if(c1[i-1] != c3[i-1]){
                break;
            }
            dp[i][0] = true;
        }
        //只用str2     0行M列   cong1.。。M列
        for(int i =1; i<M;i++){
            if(c2[i-1] != c3[i-1]){
                break;
            }
            dp[0][i] = true;
        }

        //普遍i,j位置
        //依赖前一个位置字符
        //N，M位置可以到达
        for (int i = 2; i<=N;i++){
            for (int j =2; j<=M;j++){
                if((c1[i-1] == c3[i+j-1] && dp[i-1][j]) || (c2[j-1] == c3[i+j-1] && dp[i][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        return dp[N][M];
    }



}

   