package DoQuestionsOfBigFactory.class22;
/**
 * TODO
 *https://leetcode.com/problems/trapping-rain-water/
 * @version 2.0
 * @author lianp
 * @date 2022/8/15 20:12
 */
public class Code02_TrappingRainWater {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *双指针
     * 二维的 单调栈
     *
     *思路：维护左、右指针 哪边小先结算哪边 相等时可以同时接算  维护左右指针左侧和右侧的最大值 小的减此时的值大于0就是此时的水量
     */
    public int trap(int[] height) {

        int N = height.length;
        int left = 0;
        int right = N-1;
        //左侧最大值
        int maxl = height[0];
        //右侧最大值
        int maxr = height[N-1];
        int total = 0;
        while (left <= right){
            if(height[left] < height[right]){
                total+= maxl - height[left] >0? maxl - height[left]:0;
                maxl = height[left] > maxl ? height[left] : maxl;
                left++;
            }else {
                total+= maxr - height[right] >0? maxr - height[right]:0;
                maxr = height[right] > maxr ? height[right] : maxr;
                right--;
            }
        }
        return total;
    }
}

   