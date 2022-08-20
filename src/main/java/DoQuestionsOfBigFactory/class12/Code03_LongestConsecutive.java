package DoQuestionsOfBigFactory.class12;

import cn.hutool.core.lang.hash.Hash;

import java.util.HashMap;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/5/28 16:20
 */
public class Code03_LongestConsecutive {

    /**
     * https://leetcode.com/problems/longest-consecutive-sequence/
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 时间复杂度O（N）
     */

    public static int longestConsecutive(int[] nums) {
        if(nums == null || nums.length ==0){
            return 0;
        }
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i : nums){
            //严格连续
            if(!map.containsKey(i)){
                map.put(i, 1);
                int pre = map.containsKey(i-1) ? map.get(i-1) : 0;
                int last = map.containsKey(i+1) ? map.get(i+1) :0;
                //这次的
                int all = pre + last +1;
                map.put(i-pre, all);
                map.put(i+last, last);
                len = Math.max(all, len);
            }
        }
        return len;
    }
}

   