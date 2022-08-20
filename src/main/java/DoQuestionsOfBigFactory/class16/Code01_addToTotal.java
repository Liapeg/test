package DoQuestionsOfBigFactory.class16;

import java.lang.reflect.Array;
import java.sql.Struct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/12 16:57
 */
public class Code01_addToTotal {
    /**
     * 给定一个有正、有负、有0的数组arr，
     * 给定一个整数k，
     * 返回arr的子集是否能累加出k
     * 1）正常怎么做？
     * 2）如果arr中的数值很大，但是arr的长度不大，怎么做？
     *
     * 子集 子序列问题： 从左往右的动态规划
     * 到一个位置要不要这个数
     */
    static boolean isAddToTotal(int[] arr, int sum){

        if (sum == 0) {
            return true;
        }
        // sum != 0
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;

        int min = 0;
        int max = 0;
        //算出最大、小值 累计值的范围在min~max上
        for(int in : arr){
            min += in < 0? in :0;
            max += in>0 ? in :0;
        }
        // min~max
        if (sum < min || sum > max) {
            return false;
        }
       boolean[][] dp = new boolean[N][max-min+1];
        //对应dp[0][0]
       dp[0][-min] = true;
       //0位置的数 必然可以累加出 arr【0】
        //
       dp[0][arr[0]-min] = true;
       //
       for(int i=1;i<N;i++){
           for(int j=min; j<=max;j++){
               //不要当前数
               dp[i][j-min] = dp[i-1][j-min];
               //不要当前数 即：i-1可以凑出k-arr【i】
               int next = j - min - arr[i];
               dp[i][j-min] |= (next >=0 && next <= (max-min) && dp[i-1][next]);
           }
       }

       return dp[N-1][sum-min];
    }


    /**
     * 第二小题
     *分治 ： 左右两个各拿一个数 看能不能加出sum
     */

    static public boolean isSum4(int[] arr, int sum){
        if (sum == 0) {
            return true;
        }
        // sum != 0
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;
        //计算左右两边的累加和
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        int mid = N>>1;
        process(arr, 0, mid,0,left);
        process(arr, mid, N,0,right);

        //从做左边拿一个数， 从右边拿一个数
        //两个集合种中肯定都存在0
        for(int i : left){
            if(right.contains(sum-i)){
                return true;
            }
        }
        return false;
    }

    static public void process(int[] arr, int l, int r, int total,Set cur){
        if(l==r){
            cur.add(total);
        }else {
            //要这个位置 /不要这个位置的数
            process(arr, l+1, r,total+arr[l], cur);
            process(arr, l+1, r,total, cur);
        }

    }



    // arr中的值可能为正，可能为负，可能为0
    // 自由选择arr中的数字，能不能累加得到sum
    // 经典动态规划
    public static boolean isSum3(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        // sum != 0
        if (arr == null || arr.length == 0) {
            return false;
        }
        // arr有数，sum不为0
        int min = 0;
        int max = 0;
        for (int num : arr) {
            min += num < 0 ? num : 0;
            max += num > 0 ? num : 0;
        }
        // min~max
        if (sum < min || sum > max) {
            return false;
        }

        // min <= sum <= max
        int N = arr.length;
        // dp[i][j]
        //
        //  0   1   2   3  4    5   6    7 (实际的对应)
        // -7  -6  -5  -4  -3   -2  -1   0（想象中）
        //
        // dp[0][-min] -> dp[0][7] -> dp[0][0]
        boolean[][] dp = new boolean[N][max - min + 1];
        // dp[0][0] = true
        dp[0][-min] = true;
        // dp[0][arr[0]] = true
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < N; i++) {
            for (int j = min; j <= max; j++) {
                // dp[i][j] = dp[i-1][j]
                dp[i][j - min] = dp[i - 1][j - min];
                // dp[i][j] |= dp[i-1][j - arr[i]]
                int next = j - min - arr[i];
                dp[i][j - min] |= (next >= 0 && next <= max - min && dp[i - 1][next]);
            }
        }
        // dp[N-1][sum]
        return dp[N - 1][sum - min];
    }


    // 为了测试
    // 生成长度为len的随机数组
    // 值在[-max, max]上随机
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
        }
        return arr;
    }

    // 对数器验证所有方法
    public static void main(String[] args) {
        int N = 20;
        int M = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(size, M);
            int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
            boolean ans1 = isAddToTotal(arr, sum);

            boolean ans3 = isSum3(arr, sum);
            boolean ans4 = isSum4(arr, sum);
            if (ans1 ^ ans3 || ans4 ^ ans1) {
                System.out.println("出错了！");
                System.out.print("arr : ");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("sum : " + sum);
                System.out.println("方法一答案 : " + ans1);
                System.out.println("方法三答案 : " + ans3);
                //System.out.println("方法四答案 : " + ans4);
                break;
            }
        }
        System.out.println("测试结束");
    }

}

   