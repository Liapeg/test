package DoQuestionsOfBigFactory.class10;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/20 21:19
 */
public class Code03 {
    /**
     * https://leetcode-cn.com/problems/k-inverse-pairs-array
     */
    /**
     *给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数。
     *
     * 逆序对的定义如下：对于数组的第i个和第j个元素，如果满i<j且a[i]>a[j]，则其为一个逆序对；否则不是。
     *
     * 由于答案可能很大，只需要返回 答案 mod 109+ 7 的值。
     *
     */
    public int kInversePairs(int n, int k) {
        if(k ==0){
            return 1;
        }
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 1;
        //所有以i结尾，组成0个逆序对的个数为1，即第一列为1
        for(int i =1;i<=n;i++){
            dp[i][0] = 1;
        }
        //一个行都是0，除了一个位置都是0  默认的
        /*for(int i =1;i<=k;i++){
            dp[1][i] = 0;
        }*/

        //dp[i,j]表示：以第i个数结尾，恰好有k个里、逆序对的数组个数
        for(int i = 1; i<=n;i++){
            for(int j = 1; j<= k;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                if(i < j){
                    dp[i][j] -= dp[i-1][j-i];
                }
            }
        }
        return dp[n][k];
    }
}

   