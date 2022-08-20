package DoQuestionsOfBigFactory.class15;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.swing.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/27 17:39
 */
public class Code04_Stock04 {

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int N = prices.length;
        if (k >= N / 2) {
            return allTrans(prices);
        }
        //动态规划
        //从左往右的模型 带限制条件
        int[][] dp = new int[N][k + 1];
        //第一行和第一列都是0
        //dp[i][j]的含义 ： 在0~i天上，不超过j次交易获得的最大利润
        //分析可能性:

        for (int j = 1; j <= k; j++) {
            //dp[1][j]位置
            int p1 = dp[0][j];
            int p2 = dp[1][j - 1] - prices[1];
            int p3 = dp[0][j - 1] - prices[0];
            int best = Math.max(p2, p3);
            dp[1][j] = Math.max(p1, best + prices[1]);
            for (int i = 2; i < N; i++) {
                //i不参与交易
                int cur1 = dp[i - 1][j];
                //i参与交易，那么i位置只能卖出  该次交易要结束
                //所以可以在之前的任意位置买入
                //dp[i-1][j-1] + prince[i] - prince[i-1] 省掉循环 斜率优化 位置依赖
                int cur3 = dp[i][j - 1] -prices[i];
                //best就是之前准备好的 dp[i-1][j]的最优结果
                best = Math.max(best, cur3);
                dp[i][j] = Math.max(cur1, best + prices[i]);
            }
        }
        return dp[N-1][k];
    }
    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] strings = {3,3,5,0,0,3,1,4};
        maxProfit(2, strings);
    }
}

   