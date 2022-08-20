package DoQuestionsOfBigFactory.class16;

import java.util.Random;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/15 8:38
 */
public class Code04_MergeRecord {
    /**
     给定整数power，给定一个数组arr，给定一个数组reverse。含义如下：
     * arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
     * 例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
     * 任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
     * 比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
     * 接下来根据reverse对arr进行调整：
     * reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [3,1,4,2]，此时有3个逆序对。
     * reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [1,3,2,4]，此时有1个逆序对
     * reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [1,3,2,4]，此时有1个逆序对。
     * reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [4,2,3,1]，此时有4个逆序对。
     * 所以返回[3,1,1,4]，表示每次调整之后的逆序对数量。
     *
     * 输入数据状况：
     * power的范围[0,20]
     * arr长度范围[1,10的7次方]
     * reverse长度范围[1,10的6次方]
     */

    /**
     * 思路：计算出2的power次方的逆序对数量和正序对数量
     * 每次变换只会对比他小的次方产生影响，
     * 所以交换每次后 交换正序 和 逆序的数据
     * 因为每次都是组内交换 所以不会影响其他组的数据
     *
     */
    static public int[] mergeRecord(int[] arr, int[] reverse, int power){
        if(arr == null || reverse == null || arr.length<1 || reverse.length <1 || power%2 !=0) {
            return new int[]{0};
        }
        int[] result = new int[reverse.length];

        //算出每个次方下逆序对 和 正序对的数量

        //逆序对的数量
        int[] unOrderNum = new int[power+1];
        //正序对的数量
        int[] orderNum = new int[power+1];
        int[] reverseArr = copyArray(arr);
        reversePart(reverseArr, 0, reverse.length - 1);
        //merge-sort
        process(arr, 0, arr.length-1, power,unOrderNum);
        process(reverseArr, 0, arr.length-1, power,orderNum);
        for(int i = 0; i< reverse.length;i++){

            for(int j = 1; j<=reverse[i];j++){
                int tmp = orderNum[i];
                unOrderNum[i] = orderNum[i];
                orderNum[i] = tmp;
            }
            //算答案
            for(int k =1;k <= power;k++){
                result[i] += unOrderNum[k];
            }
        }
        return result;
    }

    static public void process(int[] arr, int L, int R, int power, int[] unOrderNum){
        //base-case
        if(L==R){
            return;
        }
        int mid = L + (R-L) >>1;
        process(arr, 0,mid, power-1, unOrderNum);
        process(arr, mid +1,R, power-1, unOrderNum);

        unOrderNum[power] += merge(arr, L, R);
    }

    static public int merge(int[] arr, int L, int R){
        //merge左右数交换的数量 即逆序对的数量
        int[] help=new int[R-L+1];
        int ans = 0;
        int p1 = L;
        int m = L + (R-L)>>1;
        int p2 = m+1;
        int i=0;
        while (p1 <= m && p2 <= R){
            ans += arr[p1] > arr[p2] ? (m-p1+1): 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }

        while (p2 <= R){
            help[i++] = arr[p2++];
        }

        for(int j =0; i<i;j++){
            arr[L+j] = help[j];
        }
        return ans;
    }

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

    public static void reversePart(int[] arr, int L, int R) {
        while (L < R) {
            int tmp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = tmp;
        }
    }


}

   