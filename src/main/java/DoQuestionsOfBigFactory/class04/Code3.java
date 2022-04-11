package DoQuestionsOfBigFactory.class04;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/26 17:39
 */

/**
 * 返回一个二维数组中，子矩阵最大累加和
 */
public class Code3 {

    public static int maxSum(int[][] matrix){
        if(matrix == null || matrix.length <1 || matrix[0].length <1){
            return 0;
        }
        int l1,l2,r1,r2=0;
        int N = matrix.length;
        int M = matrix[0].length;

        int preMax = 0;
        int[] preArr = new int[N];
        for(int i =0;i< N; i++){
            for(int k = 0; k < M;k++){
                matrix[i][k] += matrix[i][k];
            }
            preMax = Math.max(preMax, subMaxSum(matrix[i]));
        }
        return preMax;
    }


    //数组的最大累加和
    public static int subMaxSum(int[] arr){
        if(arr == null || arr.length < 1){
            return 0;
        }
        int pre = arr[0];
        int max = arr[0];

        for(int j = 0; j<arr.length;j++){
            pre = Math.max(arr[j], pre + arr[j]);
            max = Math.max(max, pre);
        }

        return max;
    }



    public static int[] getMaxMatrix(int[][] matrix){
        if(matrix == null || matrix.length <1 || matrix[0].length <1){
            return new int[0];
        }
        int l1=0,l2=0,r1=0,r2=0;
        int N = matrix.length;
        int M = matrix[0].length;
        int cur = 0;
        int preMax = Integer.MIN_VALUE;
        for(int i =0;i< N; i++){
            int[] preArr = new int[M];

            //i~j行之间组成的矩阵
            for(int j = i;j<N;j++) {
                int begin = 0;
                 cur = 0;
                for (int k = 0; k < M; k++) {
                    preArr[k] += matrix[j][k];
                    cur += preArr[k];
                    if (cur > preMax) {
                        preMax = cur;
                        l1 = i;
                        l2 = begin;
                        r1 = j;
                        r2 = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k+1;
                    }


                }
            }
            //preMax = Math.max(preMax, subMaxSum(matrix[i]));
        }
        return new int[] {l1,l2,r1,r2};
    }
}

   