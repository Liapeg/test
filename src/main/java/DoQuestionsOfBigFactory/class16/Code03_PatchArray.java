package DoQuestionsOfBigFactory.class16;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/13 19:34
 */
public class Code03_PatchArray {
    /**
     * https://leetcode.com/problems/patching-array/
     * 给定一个已排序的正整数数组 nums，和一个正整数n 。从[1, n]区间内选取任意个数字补充到nums中，
     * 使得[1, n]区间内的任何数字都可以用nums中某几个数字的和来表示。
     *
     * 请返回 满足上述要求的最少需要补充的数字个数。
     *
     * 补充数字使得子集的累加和为n
     */
    static public int minPatches(int[] nums, int n) {

        //之前的范围
        //range可能越界 所以用long
        long range = 0;
        //需要补充的数字个数
        int patch = 0;
        int i=0;
        while (range < n){
            if(i < nums.length){
                if(nums[i] >(range+1)){
                    patch++;
                    range+=(range +1);
                }else {
                    range +=nums[i++];
                }
                if(range >= n){
                    return patch;
                }
            }else {
                range+=(range +1);
                patch++;
                if(range >= n){
                    return patch;
                }
            }

        }

        return patch;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,31,33};

        System.out.println(minPatches(arr, 2147483647));
    }
}

   