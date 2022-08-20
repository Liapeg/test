package DoQuestionsOfBigFactory.class13;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/1 16:32
 */
public class Code01_NCardsWins {

    /**
     * 谷歌面试题扩展版
     * 面值为1~N的牌组成一组，
     * 每次你从组里等概率的抽出1~N中的一张
     * 下次抽会换一个新的组，有无限组
     * 当累加和<a时，你将一直抽牌
     * 当累加和>=a且<b时，你将获胜
     * 当累加和>=b时，你将失败
     * 返回获胜的概率，给定的参数为N，a，b
     */

    public static double fun1(int N , int a, int b){
        if(N <=0 || a >=b || a<0 || b<0){
            return 0.0;
        }
        if(b - a >= N){
            return 1.0;
        }
        //
        return process(0, N, a,b);
    }


    //暴力递归
    public static double process(int cur, int N, int a, int b){
        if(cur >=a && cur < b){
            return 1.0;
        }
        if(cur >= b){
            return 0.0;
        }
        double ans = 0.0;
        //继续抽牌 每种抽法的概率和
        for(int i =0 ;i<N;i++){
            ans += process(cur +i, N, a,b);
        }

        return ans/N;
    }
    //动态规划 一维表
    public static double f4(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        double[] dp = new double[b];
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }
        if (a - 1 >= 0) {
            dp[a - 1] = 1.0 * (b - a) / N;
        }
        for (int cur = a - 2; cur >= 0; cur--) {
            double w = dp[cur + 1] + dp[cur + 1] * N;
            if (cur + 1 + N < b) {
                w -= dp[cur + 1 + N];
            }
            dp[cur] = w / N;
        }
        return dp[0];
    }



}

   