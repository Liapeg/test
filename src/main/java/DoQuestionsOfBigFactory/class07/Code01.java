package DoQuestionsOfBigFactory.class07;

import com.sun.javaws.IconUtil;

import javax.security.auth.login.CredentialException;
import java.lang.management.ManagementFactory;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/31 8:48
 */
public class Code01 {

    /**
     * 给定一个非负数组成的数组，长度一定大于1
     * 想知道数组中哪两个数&的结果最大
     * 返回这个最大结果
     * @param arr
     * @return
     *
     * 若使两个数&的结果最大，则从高位开始都是1的结果最大
     * 时间复杂度N，额外空间复杂度1
     */
    public static int maxAnd(int[] arr) {
        int M = arr.length;

        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            int j =0;
            /**
             * tmp的作用 在第i位有0个或者2个1时，回到开始位置 让下一位继续
             * 如果M小于2，则回到上一位的起始位置重新开始试，而不是直接回到arr的尾部
             */
            int tmp = M;
            while (j < M) {
                if ((arr[j] & (1<<i)) == 0) {
                    swap(j, --M, arr);
                } else {
                    j++;
                }
            }
            if (M == 2) {
                return (arr[0] & arr[1]);
            }
            if (M < 2) {
                M = tmp;
            }else {
                ans|=(1<<i);
            }
        }

        return ans;

    }
    public static int maxAndValue2(int[] arr) {
        // arr[0...M-1]  arr[M....]
        int M = arr.length;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            // arr[0...M-1] arr[M...]
            int i = 0;
            int tmp = M;
            while (i < M) { // arr[0...M-1]
                if ((arr[i] & (1 << bit)) == 0) {
                    swaps(arr, i, --M);
                } else {
                    i++;
                }
            }
            if (M == 2) { // arr[0,1]
                return arr[0] & arr[1];
            }
            if (M < 2) {
                M = tmp;
            } else { // > 2个数  bit位上有1
                ans |= (1 << bit);
            }
        }
        return ans;
    }
    public static void swaps(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void swap(int a, int b, int[] arr){
        //交换数组中a，b位置的值
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    /**
     * 暴力解
     */
    public static int maxOne(int[] arr){
        int ans = Integer.MIN_VALUE;
        for(int i =0; i< arr.length;i++){
            for(int j =0;j<i;j++){
                ans = Math.max(ans, (arr[j] & arr[i]));
            }

        }

        return ans;
    }

    public static int maxAndValue1(int[] arr) {
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                max = Math.max(max, arr[i] & arr[j]);
            }
        }
        return max;
    }

    //for test
    public static int[] generateArr(int maxValue, int maxLength){
        int length = (int)(Math.random()* maxLength) +1;
        int[] arr = new int[length];
        for(int i =0; i<length;i++){
            arr[i] = (int)(Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArr(int[] arr){
        for(int a : arr){
            System.out.print(a+",");

        }
        System.out.println();
    }

    //for test
    public static void main(String[] args) {
        int maxValue = 50;
        int maxLength = 20;
        int[] arrs = generateArr(50, 20);

        int val1 = maxAnd(arrs);
        int val2 = maxOne(arrs);
        int val3 =maxAndValue1(arrs);
        int val4 = maxAndValue2(arrs);
        if(val1 != val2){
            printArr(arrs);
            System.out.println(val1);
            System.out.println(val2);
            System.out.println(val3);
            System.out.println(val4);
            System.out.println("fucingnnnnnnnn");
        }

    }

}

   