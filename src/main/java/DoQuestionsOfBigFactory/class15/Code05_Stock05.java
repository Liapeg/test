package DoQuestionsOfBigFactory.class15;

import javax.swing.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/7 10:51
 */
public class Code05_Stock05 {
    /**
     *https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     * 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <1){
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] sell = new int[N];
        //dp[i]的含义 在第i天的时候可以获得的最大利润
        //1、该天不参与交易
        //2、该天参与交易  买入/卖出
        buy[1] = Math.max(-prices[0],-prices[1]);//该天参与交易 -prince【1】 不参与交易就是0位置买入的最优解-prince【0】 而不是buy【0】！！！！
        sell[1] = Math.max(sell[0],prices[1] -prices[0]);
        for(int i = 2; i< N; i++){
            buy[i] = Math.max(sell[i-2] - prices[i], buy[i-1]) ;
            sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]);
        }
        return sell[N-1];

    }
}

   