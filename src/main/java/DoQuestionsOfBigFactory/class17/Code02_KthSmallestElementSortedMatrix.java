package DoQuestionsOfBigFactory.class17;

import DoQuestionsOfBigFactory.class07.Code02;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/19 19:11
 */
public class Code02_KthSmallestElementSortedMatrix {

    /**
     * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组
     * 在给定一个正数k，
     * 返回二维数组中，最小的第k个数
     *
     * Leetcode原题：
     * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
     */


    /**
     * 思路： 总体二分
     * 1、可以找出<=100 的数有多少个 同时可以找到《=他 离他最近的数是多少
     * 2、最大数在右下角 最小数在左上角
     * 3、如果《= min+max/2 的数大于k个 那么范围大了 缩小范围
     * 4、
     */

    public static int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        int max = matrix[N-1][M-1];
        int min = matrix[0][0];
         int ans= 0;
       while (min <=max){
           int mid= min + (max-min)>>1;
           Info info = getNums(matrix, mid);
           if(info.getNums()>= k){
               max = mid -1;
               ans = info.getMinVal();
           }else {
               min = mid +1;
           }
       }
      return ans;

    }


    //小于等于val的数
    public static Info getNums(int[][] matrix, int val){
        int N = matrix.length;
        int M = matrix[0].length;
        int nums= 0;
        int minVal = val;

        int row =0;
        int col=  M-1;
        while (row < N && col>-1){
            if(matrix[row][col] <= val){
                nums+=col+1;
                minVal = Math.max(minVal,matrix[row][col]);
                row++;
            }else {
                col--;
            }
        }
        return new Info(nums, minVal);
    }


    static class Info{
        //比他小的数有多少
        int nums;
        //小于等于他 且最近的数是多少
        int minVal;

        public Info(int nums, int minVal) {
            this.nums = nums;
            this.minVal = minVal;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public int getMinVal() {
            return minVal;
        }

        public void setMinVal(int minVal) {
            this.minVal = minVal;
        }
    }
}

   