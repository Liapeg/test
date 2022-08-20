package DoQuestionsOfBigFactory.class23;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/18 17:09
 */
public class Code02_MaxABSBetweenLeftAndRight {
    /**
     *给定一个数组arr，长度为N > 1
     * 从中间切一刀，保证左部分和右部分都有数字，一共有N-1种切法
     * 如此多的切法中，每一种都有:
     * 绝对值(左部分最大值 – 右部分最大值)
     * 返回最大的绝对值是多少
     */
    public static int maxAbs(int[] arr){
        if(arr ==null || arr.length <1){
            return 0;
        }
        int N = arr.length;
        int max = arr[0];
        //抓出最大值
        for(int i =1; i<N;i++){
            max = Math.max(max, arr[i]);
        }
        //如果最大值在左侧  那么最右侧只剩一个时 差的绝对值最大

        //如果最大值在右侧 那么左侧只剩一个时 差的绝对值最大
        int p1 = Math.abs(max -arr[0]);
        int p2 = Math.abs(max-arr[N-1]);
        return p1 >p2 ? p1 :p2;
    }

    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxAbs(arr));
        System.out.println(maxABS3(arr));
    }

}

   