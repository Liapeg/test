package DoQuestionsOfBigFactory.class09;

import io.micrometer.shaded.org.pcollections.PStack;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/14 8:52
 */
public class Code04 {

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 严格递增
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length ==0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }

        int[] end = new int[nums.length];
        end[0] = nums[0];
        int left = 0;
        int right = 0;
        int initRight = 0;
        //最小子序列长度为1，即nums[0]
        int max = 1;
        for(int i = 1;i< nums.length;i++){
            left = 0;
            right = initRight;

            while (left <= right){
                int mid = left + ((right -left)>>1);
                //找到nums【i】左边比他小的值，否则将子序列长度扩1
                if(nums[i] > end[mid]){
                    left = mid+1;
                }else {
                    right = mid - 1;
                }
            }
            //右边初始值：arr【i】未使子序列长度增加，则用原来的initRight；若长度增加，是l增加推动了长度增加， 所以使用l的值
            initRight = Math.max(initRight, left);
            end[left] = nums[i];
            //子序列长度，下标+1
            max = Math.max(max, left+1);

        }
        return max;
    }

    public static void main(String[] args) {
        int num1 = 12;
        int num2 = 133;
        System.out.println(num1 + ((num2 -num1)>>1));
        System.out.println((num1+num2)/2);
    }

}

   