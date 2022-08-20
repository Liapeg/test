package DoQuestionsOfBigFactory.class10;

import java.util.PriorityQueue;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/18 10:18
 */
public class Code01 {

    /**
     * https://leetcode.com/problems/jump-game-ii/
     *
     * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。
     */

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int step = 0;
        int cur = 0;
        int next = 0;
        //来到i位置，如果往下跳，最远能够来到哪个位置
        for (int i = 0; i < nums.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return step;
    }

}

   