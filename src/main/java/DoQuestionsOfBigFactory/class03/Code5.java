package DoQuestionsOfBigFactory.class03;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/24 19:15
 */

import java.util.Arrays;

/**
 * 给定一个正数数组arr，代表若干人的体重
 * 再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少
 * 返回最少的船数
 */
public class Code5 {

    public static int minBoat(int[] arr, int limit){
        if(arr == null || arr.length <1){
            return 0;
        }

        int ans = 0;
        Arrays.sort(arr);
        int N = arr.length;
        if(arr[N-1] > limit){
            return -1;
        }
        /**
         * 中间小等于limit/2的位置
         */
        int midR = -1;
        for(int i = N; i>=0;i--){
            if(arr[i] <= limit/2){
                midR = i;
                break;
            }
        }
        if(midR == -1){
            return N;
        }
        int R = midR + 1;
        int L = midR ;

        int noUsed = 0;
        while (L >=0){

            int resolved = 0;
            while (R< N && arr[L] +arr[R] <=limit ){
                R++;
                resolved++;
            }

            if(resolved == 0){
                L--;
                noUsed++;
            }else {
                L = Math.max(-1, L - resolved);
            }
        }

        int lAll = midR+1;
        int used = lAll - noUsed;
        int r = N - used - lAll;
        return used + r + ((noUsed +1) >>2);
    }




    public static int minBoat1(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        if (arr[N - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (N - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }




    //for test
    public static int[] generateRandomArray(int maxValue, int maxLen){
        int length = (int)(Math.random() * maxLen) +1;
        int[] arr = new int[length];
        for(int i=0;i< length;i++){
            arr[i] = (int)(Math.random() * maxValue) +1;
        }

        return arr;
    }


    //test
    public static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i+" ");

        }
        System.out.println();
    }

    public static void main(String[] args) {

        for(int i=0;i<500;i++){

            int[] arr = generateRandomArray(50, 100);
            int limit = (int)(Math.random()* 20) +1;
            int p1 = minBoat1(arr, limit);
            int p2 = minBoat(arr, limit);
            System.out.println("-------------测试开始");
            if(p1 != p2){
                System.out.println("-----------------ops");
                printArr(arr);
                System.out.println(limit);
                System.out.println(p1);
                System.out.println(p2);
                break;
            }

            System.out.println("---------测试结束");
        }
    }


}

   