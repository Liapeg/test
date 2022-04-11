package DoQuestionsOfBigFactory.class07;

import java.util.Arrays;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/6 22:24
 */
public class Code03 {

    /**
     * 给定一个数组arr，
     * 返回如果排序之后，相邻两数的最大差值
     * 要求：时间复杂度O(N)
     */
    //时间复杂度O（N） 所以不可排序
    //借用桶排序的思想，将这些数，分在N/10个桶中 其中必定有空桶 求相邻桶的最大值和最小值的差值
    public static int maxGap(int[] arr){
        int ans = 0;
        int N = arr.length;
        //桶数 N+1
        //桶中是否有数
        boolean[] booleans = new boolean[N +1];
        int[] maxs= new int[N +1];
        int[] mins = new int[N +1];
        int max = Integer.MIN_VALUE,min= Integer.MAX_VALUE;
        for(int i =0; i< N;i++){
            max = Math.max(max,arr[i]);
            min = Math.min(min,arr[i]);
        }
        if(min == max){
            return 0;
        }
        for(int i =0; i< N;i++){
            //桶号
            int num = bucket(arr[i], N, min, max);
            maxs[num] = booleans[num] ? Math.max(maxs[num], arr[i]) : arr[i];
            mins[num] = booleans[num] ? Math.min(mins[num], arr[i]) : arr[i];
            booleans[num] = true;
        }

        //上一个非空桶的最大值
        int last = maxs[0];
        //N+1个桶 所以能到N
        for(int i =1; i<= N;i++){
            if(booleans[i]){
                //差值
                ans =Math.max(ans, mins[i] - last);
                last = maxs[i];
            }
        }
        return ans;

    }

    //现在的数是num 总共有len个数 范围在min-max 这个数改进几号桶
    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }



    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int value = maxGap(arr1);
            int val2 = comparator(arr2);
            if (value != val2) {
                System.out.println(arr1.length);
                System.out.println(value);
                System.out.println(val2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}

   