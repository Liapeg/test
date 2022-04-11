package DoQuestionsOfBigFactory.class08;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/9 18:38
 */
public class Code02 {

    /**
     * https://leetcode.com/problems/container-with-most-water/
     * @param height
     * @return
     *
     *
     *
     * 思想： 在求最终的结果时，我们只关心会把最后结果推高的组合，而忽略那些已经包含在其他组合里的结果
     */
    public int maxArea(int[] height) {
        if(height.length == 0){
            return 0;
        }
        int ans = 0;
        int N = height.length;
        int left = 0;
        int right = N-1;
        while (left < right){
            if(height[left] < height[right]){
                ans = Math.max(ans, (height[left] * (right-left)));
                left++;
            }else {
                ans = Math.max(ans, (height[right] * (right-left)));
                right--;
            }
        }

        return ans;
    }
}

   