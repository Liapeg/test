package DoQuestionsOfBigFactory.class06;

import java.util.HashMap;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/29 15:28
 */
public class Code04 {

    /**
     * 数组中所有数都异或起来的结果，叫做异或和
     * 给定一个数组arr，可以任意切分成若干个不相交的子数组
     * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
     * 返回这个最多数量
     *
     * 子数组问题：以第i个元素结尾；分析可能性
     *
     * 两种可能性
     * 1、0~i上，最优划分中，最后一部分是异或和为0 那么存在一个位置j 0-j的异或和 等于j+1到i的异或和
     * 2、0~i上，最优划分中，最后一部分是异或和不为0 那么他和dp【i-1】位置一样
     *
     */
    public static int comparator(int[] arr) {

        if(arr== null || arr.length==0){
            return 0;
        }
        int N = arr.length;
        //dp[i]表示 0~i上最有划分下 最大的异或和为0的子数组数 所以返回dp[N-1]
        int[] dp = new int[N];

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        //0-i的异或和
        int xor = 0;
        for(int i =0;i<N;i++){
            xor^=arr[i];
            if(map.containsKey(xor)){
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] +1);
            }
            if(i >0){
                dp[i] = Math.max(dp[i], dp[i-1]);
            }
            map.put(xor,i);
        }

        return dp[N-1];
    }


    // 时间复杂度O(N)的方法
    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];

        // key 某一个前缀异或和
        // value 这个前缀异或和上次出现的位置(最晚！)
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        // 0~i整体的异或和
        int xor = 0;
        for (int i = 0; i < N; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) { // 可能性2
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
        }
        return dp[N - 1];
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}



