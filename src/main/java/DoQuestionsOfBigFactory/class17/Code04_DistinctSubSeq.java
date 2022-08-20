package DoQuestionsOfBigFactory.class17;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/20 14:36
 */
public class Code04_DistinctSubSeq {

    /**
     * 给定两个字符串S和T
     * 返回S的所有子序列中
     * 有多少个子序列的字面值等于T
     *
     * 思路 ： 行列模式？？ 应该是 试试
     */
    public static int numDistinct1(String S, String T) {
        if(S == null || T ==null){
            return 0;
        }
        int N = S.length();
        int M = T.length();

        //dp的含义：从S从0....i上 有多少个子序列的字面值 等于 T 0...j的字面值
        int[][] dp = new int[N][M];
        char[] sChar = S.toCharArray();
        char[] tChar = T.toCharArray();

        dp[0][0] = sChar[0] == tChar[0] ? 1 : 0;

        //
        for(int i =1; i< N; i++){
            dp[i][0] = sChar[i] == tChar[0] ? (dp[i-1][0]+1) :dp[i-1][0];
        }
        for (int i = 1; i < N; i++) {
            for(int j = 1; j<M;j++){
                //分析可能性
                //1、不包含i位置的字符 那么需要i-1位置去搞定 t 0.。j
                dp[i][j] = dp[i-1][j];
                //2、包含i位置的字符 那么i位置的字符必须和T中j位置的字符一致 然后i-1去搞定j-1
                if(sChar[i] == tChar[j]){
                    dp[i][j] += dp[i-1][j-1];
                }
            }
        }

        return dp[N-1][M-1];
    }


    public static int numDistinct3(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] dp = new int[t.length + 1];
        dp[0] = 1;
        for (int j = 1; j <= t.length; j++) {
            dp[j] = 0;
        }
        for (int i = 1; i <= s.length; i++) {
            for (int j = t.length; j >= 1; j--) {
                dp[j] += s[i - 1] == t[j - 1] ? dp[j - 1] : 0;
            }
        }
        return dp[t.length];
    }

    public static void main(String[] args) {
        String S = "1212311112121132";
        String T = "13";

        System.out.println(numDistinct1(S, T));
        System.out.println(numDistinct3(S, T));

    }

}

   