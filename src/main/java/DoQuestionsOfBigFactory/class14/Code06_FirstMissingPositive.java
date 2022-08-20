package DoQuestionsOfBigFactory.class14;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/23 22:21
 */
public class Code06_FirstMissingPositive {
    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     */
    public int firstMissingPositive(int[] nums) {
        if(nums == null && nums.length == 0){
            return 1;
        }
        //左边的有效位置
        int left = 0;
        //右侧的无效位置
        int right = nums.length;
        while (left < right){
            if(nums[left] != (left+1)){
                //如果要交换过去的那个位置的数字 和当前位置的数字一样 也是垃圾  =-----即相同的两个数字有1个是垃圾
                if(nums[left] > right || nums[left] <=0 || nums[nums[left -1]] == nums[left]){
                    swap(nums, left,right-1);
                    right--;
                }else {
                    swap(nums, left, nums[left]-1);
                }

            }else {
                left++;
            }
        }
        return right +1;
    }


    //数组中交换两个位置的数字
    public void swap(int[] arr, int i ,int j){
        int tmp= 0;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

   