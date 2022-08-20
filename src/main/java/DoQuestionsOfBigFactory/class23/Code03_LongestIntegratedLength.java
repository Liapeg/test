package DoQuestionsOfBigFactory.class23;

import org.apache.xerces.impl.dv.dtd.NMTOKENDatatypeValidator;

import java.util.HashSet;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/18 17:10
 */
public class Code03_LongestIntegratedLength{

    /**
     * 定义什么是可整合数组：
     * 一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1
     * 则称这个数组为可整合数组
     * 比如{5,1,2,4,3}、{6,2,3,1,5,4}都是可整合数组
     * 返回arr中最长可整合子数组的长度
     *
     * 思路； 重新定义可整合数组的概念
     * 1、无重复值
     * 2、这个子数组最大值和最小值的差 = 数组长度-1
     */
    public static int longestLength(int[] arr){

        int N = arr.length;
        int ans = 1;

        HashSet<Integer> hashSet = new HashSet<>();


        for(int L =0; L < N;L++){
            //新的数组 清空
            hashSet.clear();
            int max =arr[L];
            int min = arr[L];
            hashSet.add(arr[L]);
            for(int R = L+1;R < N;R++){
                if(hashSet.contains(arr[R])){
                    break;//能有重复值
                }
                //无重复值
                hashSet.add(arr[R]);
                min = Math.min(arr[R], min);
                max = Math.max(arr[R], max);
                if((max -min) == (R - L)){
                    ans = Math.max(ans, R-L+1);
                }
            }
        }

        return ans;
    }

    public static int getLIL2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int L = 0; L < arr.length; L++) { // L 左边界
            // L .......
            set.clear();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int R = L; R < arr.length; R++) { // R 右边界
                // arr[L..R]这个子数组在验证 l...R L...r+1 l...r+2
                if (set.contains(arr[R])) {
                    // arr[L..R]上开始 出现重复值了，arr[L..R往后]不需要验证了，
                    // 一定不是可整合的
                    break;
                }
                // arr[L..R]上无重复值
                set.add(arr[R]);
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                if (max - min == R - L) { // L..R 是可整合的
                    len = Math.max(len, R - L + 1);
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(longestLength(arr));
        System.out.println(getLIL2(arr));

    }
}

   