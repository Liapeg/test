package fullStudySchedule;

import java.util.TreeMap;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/9/15 18:53
 *
 *
 * 归并排序
 */
public class MergeSort {


    /**
     * 递归实现
     * @param arr
     */
    public static void process(int[] arr, int l, int r){
        if(r==l){
            return ;
        }
        int m = l + ((r-l)>>1);
        process(arr, l, m);
        process(arr,m+1, r);
        merge(arr, l, m, r);
        //打印数组

    }

    public static void merge(int[] arr, int l, int m, int r){
        int p1 = l;
        int p2 = m + 1;
        int index = 0;
        int[] help = new int[r-l+1];
        while (p1 <= m && p2 <= r){
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[index++] = arr[p1++];
        }
        while (p2 <= r){
            help[index++] = arr[p2++];
        }
        for(int i = 0;i< help.length;i++){
            arr[l+i] = help[i];
        }

    }

    /**
     * 非递归实现 迭代
     * @param args
     */

    public static int[] mergeSort2(int [] arr){
        int stepSize = 1;
        int N = arr.length;
        while (stepSize < N){
            int l = 0;
            while(l < N){
                if(stepSize > N -l){
                    break;
                }
                int mid = l + stepSize -1;
                int R = mid + Math.min(stepSize, N-mid -1);
                merge(arr, l,mid, R);
                l = R +1;
            }
            if(stepSize > N/2){
                break;

            }
            stepSize = stepSize<<1;
        }

        return arr;

    }


    public static void main(String[] args) {
        for(int l = 0;l<10;l++) {
            int[] a = generteList(10, 10);
            if(a ==null || a.length<2){
                return;
            }
            printList(a);
            //mergeSort1(a);
            //int lne = a.length;
            //System.out.print(process1(a,0,lne-1));
            System.out.print(rightDoubelThanLeft(a));
            System.out.println();
        }
    }



    public static int[] generteList(int len, int val){
        int size = ((int)(Math.random()*(len+1)))+2;
        int[] arr = new int[size];
        int index = 0;
        while (size != 0){
            arr[index++] = (int)(Math.random()*(val+1));
            size--;
        }
        return arr;
    }


   public static void printList(int[] arr){
        int lne = arr.length;
        for(int i = 0;i < lne;i++){
            System.out.print(arr[i]+" ");

        }
       System.out.println();
   }


    /**
     *
     *
     * 一些附加题
     *
     *
     *
     *
     *
     *
     *
     */
    //1、最小和问题

    public static int process1(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R-L)>>1);

        return process1(arr,L, M) +
        process1(arr,M+1,R) +
        merge1(arr, L, M,R);
    }

    public static int merge1(int[] arr, int L, int M, int R){
        int ans = 0;
        int p1=L;
        int p2=M+1;
        int index = 0;
        int[] help = new int[R-L+1];
        while (p1 <= M && p2 <=R){
            ans += arr[p1] < arr[p2] ? (R-p2+1)*arr[p1] :0;
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];

        }

        while (p1<=M){
            help[index++] = arr[p1++];
        }
        while (p2<=R){
            help[index++] = arr[p2++];
        }

        //复制回原数组
        for(int i = 0;i<help.length;i++){
            arr[L+i] = help[i];
        }
        return ans;
    }

    /**
     *
     *
     * 逆序对个数问题
     *
     * 逆序对 ： 左边数大于右边数，则称为一个逆序对
     *
     */

    public static int getReverseOrderNum(int[] arr){
        if(arr==null || arr.length<2){
            return 0;
        }

        return processNum(arr,0,arr.length-1);
    }

    public static int processNum(int[] arr, int l,int r){
        if(l==r){
            return 0;
        }
        int m = l + ((r-l)>>1);
        return processNum(arr, l,m) + processNum(arr,m+1,r) + mergeNum(arr,l,m,r);
    }

    public static int mergeNum(int[] arr,int l,int m,int r){
        int[] help = new int[r-l+1];
        int ans = 0;
        int p1 = m;
        int p2 = r;
        int index = help.length -1;
        while (p1>=l && p2 > m){
            ans += arr[p1] > arr[p2] ? (p2-m) : 0;//左边大于右边时，此时该数是右边p2到m 所有数都构成逆序对
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }

        while(p1 >=l){
            help[index--] = arr[p1--];
        }
        while(p2 > m){
            help[index--] = arr[p2--];
        }
        //复制数组

        for(int i= 0;i<help.length;i++){
            arr[l+i] = help[i];
        }



        return ans;

    }


    /**
     * 右边数的2倍还小于左边数的个数
     */

    public static int rightDoubelThanLeft(int[] arr){
        if(arr == null || arr.length<2){
            return 0;
        }
        return processLeft(arr, 0 , arr.length-1);
    }

    public static int processLeft(int[] arr,int l, int r){
        if(l==r){
            return 0;
        }
        int m = l + ((r-l)>>1);
        return processLeft(arr, l,m) + processLeft(arr,m+1,r) + mergeLeft(arr, l, m, r);

    }

    public static int mergeLeft(int[] arr, int l, int m, int r){
        int ans = 0;

        int windowR = m+1;
        for(int i=l;i<m;i++){
            while (windowR <= r && arr[i] > (arr[windowR]*2)){
                windowR ++;
            }
            ans += windowR -m-1;
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }


        return ans;
    }


    /**
     *
     *给定一个数组arr和两个整数 lower  upper
     *求arr中有多少个子数组的和在[lower, upper]上
     */

    public static int countRangeSum(int[] nums, int lower, int upper){
        if(nums ==null || nums.length==0){
            return 0;
        }
        long[] arr = new long[nums.length];
        arr[0] = nums[0];
        for(int i =1;i<arr.length;i++){
            arr[i] = arr[i-1] + nums[i];
        }
       return processCount(arr, 0, arr.length -1,lower,upper);
    }


    public static int processCount(long[] arr, int l, int r, int lower, int upper){
        if(l == r){
            return arr[l] >= lower && arr[l] <= upper ? 1:0;

        }
        int m = l + ((r-l)>>1);
        return processCount(arr,l,m,lower,upper) + processCount(arr, m+1, r ,lower, upper)
                + mergeCount(arr, l ,m, r, lower, upper);
    }

    public static int mergeCount(long[] arr, int l, int m ,int r, int lower, int upper){

        int ans =0;
        int windowR = l;
        int windowL = l;


        for(int j = m +1;j<= r;j++){
            long min = arr[j] - upper;
            long max = arr[j] - lower;
            while (windowR <=m && arr[windowR] <= max){
                windowR++;
            }
            while (windowL <=m && arr[windowL] < min){
                windowL++;
            }
            ans += windowR -windowL;
        }

        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }




}

   