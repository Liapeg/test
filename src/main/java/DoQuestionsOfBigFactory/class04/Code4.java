package DoQuestionsOfBigFactory.class04;

import java.lang.management.ManagementFactory;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/27 15:26
 */


public class Code4 {

    /**
     * 返回一个数组中，选择的数字不能相邻的情况下，
     * 最大子序列累加和
     */

    public static int subSqeMaxSumNoNext(int[] arr){

        if(arr == null || arr.length <1){
            return 0;
        }
        int N = arr.length;
        int[] dp =  new int[N];
        for(int i =0;i< N;i++){
            dp[i] = Integer.MIN_VALUE;
        }
        return process(N-1, arr, dp);

    }

    public static int process(int index, int[] arr, int[] dp){
        int ans = 0;
        if(dp[index] != Integer.MIN_VALUE){
            return dp[index];
        }
        if(index == 0){
            return arr[0];
        }
        if(index == 1){
            return Math.max(arr[0], arr[1]);
        }

        int p1 = dp[index-1];
        int p2 = dp[index-2] + arr[index];
        int p3 = arr[index];
        ans = Math.max(p1, Math.max(p2,p3));
        dp[index] = ans;
        return ans;
    }


    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        if (N == 1) {
            return arr[0];
        }
        if (N == 2) {
            return Math.max(arr[0], arr[1]);
        }
        int[] dp = new int[N];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(Math.max(dp[i - 1], arr[i]), arr[i] + dp[i - 2]);
        }
        return dp[N - 1];
    }


    public static void main(String[] args) {

        for(int i =0;i < 10000;i++) {
            int length = (int) Math.random() * 20 ;
            int max = (int) Math.random() * 50 ;

            int[] arr = generate(length, max);
            if (subSqeMaxSumNoNext(arr) != maxSum(arr)) {
                System.out.println("--------ops");
            }

        }
        System.out.println("-----------测试结束");

    }
    public static int[] generate(int length, int max){
        int[] arr = new int[length];
        for(int a : arr){
            a = (int)Math.random() * max;
        }

        return arr;
    }



}

   