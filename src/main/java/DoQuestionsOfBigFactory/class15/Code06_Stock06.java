package DoQuestionsOfBigFactory.class15;

import javax.swing.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/7 10:53
 */
public class Code06_Stock06 {
    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     * 给定一个整数数组prices，其中 prices[i]表示第i天的股票价格 ；整数fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */
    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length <=1){
            return 0;
        }
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
         //dp[i] 第i天获得的最大利润  提前扣fee
        buy[0] = -prices[0] -fee;
        sell[0] = 0;
        for(int i = 1; i< prices.length;i++){
            buy[i] = Math.max(sell[i-1] - prices[i] -fee, buy[i-1]);
            sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]);
        }

        return sell[prices.length -1];
    }

}

   