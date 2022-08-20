package DoQuestionsOfBigFactory.class15;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/27 17:22
 */
public class Code02_Stock02 {

    /**
     * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润。
     * 可以买卖无限次
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <1){
            return 0;
        }
        int ans = 0;
        int cur = 0;
        for(int i = 1; i< prices.length; i++){
            cur = prices[i] - prices[i-1];
            if(cur > 0){
                ans += cur;
            }
        }

        return ans;
    }
}

   