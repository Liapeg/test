package DoQuestionsOfBigFactory.class03;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/23 11:25
 */

import java.sql.Array;
import java.util.Arrays;

/**
 * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k
 * 如果两个人能力差值正好为k，那么可以凑在一起比赛
 * 一局比赛只有两个人
 * 返回最多可以同时有多少场比赛
 */
public class Code4 {

    public static int MaxPairNumber(int[] arr, int k){
        int ans = 0;
        Arrays.sort(arr);
        boolean[] user = new boolean[arr.length];
        int leftIndex = 0;
        int rightIndex =0;
        while (rightIndex < arr.length && leftIndex<arr.length){
            if(user[leftIndex]){
                leftIndex++;
            }else if(leftIndex >= rightIndex){
                rightIndex ++;
            } else {
                int distance = arr[rightIndex] - arr[leftIndex];
                if(distance == k){
                    user[rightIndex] = true;
                    rightIndex++;
                    leftIndex++;
                    ans++;
                }else if(distance > k){
                    leftIndex++;
                }else {
                    rightIndex ++;
                }

            }
        }

        return ans;

    }



    // 暴力解
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, index, r);
                ans = Math.max(ans, process1(arr, index + 1, k));
                swap(arr, index, r);
            }
        }
        return ans;
    }
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    //////
    // 为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = MaxPairNumber(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }



}

   