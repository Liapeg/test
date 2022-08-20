package DoQuestionsOfBigFactory.class13;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/7 19:29
 */
public class Code02_findMinMoves {
    /**
     * https://leetcode.cn/problems/super-washing-machines/
     * 假设有 n台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
     *
     * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
     *
     * 给定一个整数数组machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
     */

    /**
     * 思路:到达每个点 左侧和右侧的衣服数决定了需要移动的次数
     * 1、左侧大于0 右侧大于0
     * 2、左侧
     */
    public int findMinMoves(int[] machines) {
        //累加和如果不能
        if(machines == null || machines.length ==0){
            return 0;
        }
        int N = machines.length;
        int total = 0;
        int ans = 0;
        for(int i=0;i<N;i++){
            total += machines[i];
        }
        if(total%N != 0){
            return -1;
        }
        int leftTotal = 0;
        int avg = total/N;
        for (int i = 0; i < N; i++) {
            int cur =0;
            int leftSum=leftTotal - i*avg;
            int rightSum = total-leftSum-machines[i] - (N-i-1)*avg;
            if(leftSum < 0 && rightSum < 0){
                cur = Math.abs(leftSum + rightSum);
            }else {
                cur = Math.max(Math.abs(leftSum),Math.abs(rightSum));
            }
            leftTotal += machines[i];
            ans = Math.max(ans, cur);
        }
        return ans;
    }

}

   