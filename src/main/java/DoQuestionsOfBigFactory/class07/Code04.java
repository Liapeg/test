package DoQuestionsOfBigFactory.class07;

import java.util.Arrays;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/7 8:51
 */
public class Code04 {

    /**
     * 一个有序整数数组，这些数可能为正数、负数或零
     * 请返回这个数组中每个数的平方不同的有多少种
     */

    /**|
     * #########坐标连续变化的技巧  while
     * @param arr
     * @return
     */
    public static int diff1(int[] arr){
        int ans = 0;
        int N = arr.length;
        int leftIndex = 0;
        int rightIndex = N-1;
        while (leftIndex < rightIndex){

            int la = Math.abs(arr[leftIndex]);
            int ra = Math.abs(arr[rightIndex]);
            //这三个条件同时只会有一个命中，此时下标就要变化
            if(arr[leftIndex] == arr[leftIndex+1]){
                leftIndex++;
                ans++;
            }else if(arr[rightIndex] == arr[rightIndex-1]){
                rightIndex--;
                ans++;
            }else {
                if (la == ra) {
                    leftIndex++;
                    rightIndex--;
                    ans++;
                } else if (la < ra) {
                    rightIndex--;
                } else {
                    leftIndex++;
                }
            }

        }



        return N-ans;
    }

    // 时间复杂度O(N)，额外空间复杂度O(1)
    public static int diff2(int[] arr) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int count = 0;
        int leftAbs = 0;
        int rightAbs = 0;
        while (L <= R) {
            count++;
            leftAbs = Math.abs(arr[L]);
            rightAbs = Math.abs(arr[R]);
            if (leftAbs < rightAbs) {
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
            } else if (leftAbs > rightAbs) {
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
            } else {
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] randomSortedArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        Arrays.sort(ans);
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int cur : arr) {
            System.out.print(cur + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 500;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomSortedArray(len, value);
            int ans1 = diff1(arr);
            int ans2 = diff2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}

   