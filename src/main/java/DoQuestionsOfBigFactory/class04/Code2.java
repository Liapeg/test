package DoQuestionsOfBigFactory.class04;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/26 16:37
 */

/**
 *返回一个数组中，子数组最大累加和
 *
 *
 * 思路：字数组问题：   以i位置结尾往前推，能够达到的最大最
 * 当前值必须要
 */
public class Code2 {


    public static int  maxSubArray(int[] arr){
        if(arr == null || arr.length <1){
            return 0;
        }
        int N = arr.length;
        int pre = arr[0];
        int max = arr[0];
        for(int i=1;i< N;i++){
            int cur = Math.max(pre + arr[i], arr[i]);
            pre = cur;
            max = Math.max(pre, max);
        }

        return max;
    }




}

   