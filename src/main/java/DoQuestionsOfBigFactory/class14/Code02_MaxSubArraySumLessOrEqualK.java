package DoQuestionsOfBigFactory.class14;

import java.util.TreeSet;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/19 22:01
 */
public class Code02_MaxSubArraySumLessOrEqualK {
    /**
     * 请返回arr中，求子数组的累加和，是<=K的并且是最大的
     * 返回这个最大的累加和
     *0-1000 <=700
     * 思路 ： 有序表
     */
    public int getMaxSubArray(int[] arr, int k){

        if(arr == null || arr.length ==0){
            return 0;
        }
        //有序表
        TreeSet<Integer> treeSet = new TreeSet<>();
        //一个数没有的时候就已经有一个前缀和是0 了
        treeSet.add(0);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < arr.length; i++){
            sum += arr[i];
            //
            if(treeSet.ceiling(sum - k) != null){
                max = Math.max(max, (sum - treeSet.ceiling(sum-k)));
            }
            treeSet.add(sum);
        }
        return max;
    }
}

   