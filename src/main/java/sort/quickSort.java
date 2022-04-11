package sort;
/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/8/28 14:47
 */

import static java.lang.Math.max;
import static java.lang.Math.random;

/**
 * 快排
 */
public class quickSort {
    public static void main(String[] args) {

    }

    //以数组最后一个数为中点 从小到大排序
    public static void process(int[] arr){

    }




    //局部最小值

    /**
     *
     * @param arr
     */
    static public void minValue(int[] arr, int l, int r){
        if(arr == null || arr.length<2){
            return;
        }
        int arrLength = arr.length;
        int midLength = l + (r-l)>>1;
        if(arr[l] < arr[l+1]){
            return;
        }
        if(arr[r] < arr[r-1]){
            return;
        }


    }


    static public int[] generIntArr(int maxLength, int maxValue){

        int maxLengthV = (int)Math.random()*maxLength +1;
        int[] newArr = new int[maxLengthV];
        int i=0;
        for(;i<maxLength;i++){
            newArr[i] = (int)(Math.random()*maxLength +1);
        }

        return newArr;
    }

}
   