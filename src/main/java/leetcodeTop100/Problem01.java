package leetcodeTop100;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/11 16:30
 */
public class Problem01 {
    //https://leetcode-cn.com/problems/two-sum/

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        int N = nums.length;
        //不能排序
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i<N ; i++){
            map.put(nums[i], i);
        }

        for(int i = 0;i<N ; i++){
            if(map.get(target - nums[i])!= null && map.get(target - nums[i])!= i){
                return new int[]{i, map.get(target - nums[i])};
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] arr = {3,2,4};
        System.out.println(twoSum(arr, 6));
    }
}

   