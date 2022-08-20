package DoQuestionsOfBigFactory.class16;

import java.util.Arrays;
import java.util.HashSet;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/13 19:05
 */
public class Code02_ADDToSmallNum {
    /**
     * 给定一个正数数组arr，
     * 返回arr的子集不能累加出的最小正数
     * 1）正常怎么做？
     * 2）如果arr中肯定有1这个值，怎么做？
     */

    static public int isAddToSum01(int[] arr){
        if(arr == null || arr.length <1){
            return 1;
        }

        Arrays.sort(arr);
        if(arr[0] != 1){
            return 1;
        }
        int pre = 1;
        for(int i=1; i< arr.length;i++){
            if(arr[i] - pre >1){
                return pre +1;
            }else {
                pre += arr[i];
            }
        }

        return pre+1;

    }


    static public int isAddToSum02(int[] arr){
        if(arr == null || arr.length <1){
            return 1;
        }

        Arrays.sort(arr);
        int pre = 1;
        for(int i=1; i< arr.length;i++){
            if(arr[i] - pre >1){
                return pre +1;
            }else {
                pre += arr[i];
            }
        }
        return pre+1;
    }

    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        process(arr, 0, 0, set);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }
    public static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set);
        process(arr, i + 1, sum + arr[i], set);
    }


    /*public static void main(String[] args) {
        int[] arr = {2,4,6,8,9,89};
        System.out.println(isAddToSum01(arr));
    }*/
    public static void main(String[] args) {
        int len = 27;
        int max = 30;
        int[] arr = generateArray(len, max);
        printArray(arr);
        long start = System.currentTimeMillis();
        System.out.println(unformedSum1(arr));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        start = System.currentTimeMillis();
        System.out.println(isAddToSum01(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        System.out.println("set arr[0] to 1");
        arr[0] = 1;
        start = System.currentTimeMillis();
        System.out.println(isAddToSum02(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }


    public static int[] generateArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

   