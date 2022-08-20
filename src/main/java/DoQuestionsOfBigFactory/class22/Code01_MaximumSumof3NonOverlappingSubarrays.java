package DoQuestionsOfBigFactory.class22;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/15 20:11
 */
public class Code01_MaximumSumof3NonOverlappingSubarrays {

    /**
     * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
     * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）最大的子数组，并返回这三个子数组。
     *
     * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
     */
    /**
     *思路：1、从0.。i算出长度为k的子数组的累加和
     *     2、从i...N-1算出长度为k的子数组的累加和
     *     3、从i+2开始依次找出长度为k的子数组 从i左边找一个 右边找一个 求出最大累加和
     * @param nums
     * @param k
     * @return
     *
     * 下标变化！！！！！！！！！！！！！！！！！！！！！
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        //从左往右每k个数的累计和
        int[] rgange = new int[N];
        //i左侧最大累计和的位置
        int[] left = new int[N];
        //i右侧最大累加和的位置
        int[] right = new int[N];

        int total = 0;
        for(int i=0; i<k;i++){
            total += nums[i];
        }
        int max = total;
        rgange[0] = total;
        for(int i = k;i < N;i++){
            total = total - nums[i-k] + nums[i];
            rgange[i-k+1] = total;
            left[i] = left[i-1];
            if(total > max){
                max = total;
                left[i] = i-k+1;
            }
        }
        total = 0;
        for(int i= N-1;i>N-k-1;i--){
            total +=nums[i];
        }
        right[N-k] = N-k;
        max = total;
        for(int i=N-k -1;i >=0 ;i--){
            total = total  + nums[i] - nums[i+k];
            right[i] = right[i+1];
            //=是为了让字典序小的在前
            if(total >= max){
                max = total;
                right[i] = i;
            }
        }

        int a=0;
        int b = 0;
        int c = 0;
        max = rgange[0];
        //到N-2*k+1时，此时右侧只剩第k个数组 和 右侧可选的数组
        for(int i = k; i < N - 2*k+1;i++){
            //选3部分
            int p1 = rgange[left[i-1]];
            int p2 = rgange[i];
            int p3 = rgange[right[i+k]];
            if(p1 + p2 + p3 > max){
                max = p1+p2+p3;
                a= left[i-1];
                b= i;
                c= right[i+k];
            }
        }



        return new int[] {a,b,c};
    }
}


