package DoQuestionsOfBigFactory.class04;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/28 9:30
 */
public class Code5 {

    /**
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * 你需要按照以下要求，给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     * @param arr
     * @return
     */
    public int candy(int[] arr){

        if(arr == null || arr.length <1){
            return 0;
        }
        int ans=0;
        int N = arr.length;
        int[] left = new int[N];
        int[] right = new int[N];

        int cur = 1;
        left[0] = cur;
        for(int i=1; i< N; i++){
            if(arr[i] > arr[i-1]){
                cur+=1;
                //相等时同糖果数可以不等
            }else if(arr[i] <=  arr[i-1]){
                cur=1;
            }
            left[i] = cur;
        }

        cur = 1;
        right[N-1] = cur;
        ans += Math.max(left[N-1], right[N-1]);
        for(int j=N-2; j>-1; j--){
            if(arr[j] > arr[j+1]){
                cur+=1;
            }else if(arr[j] <= arr[j+1]){
                cur=1;
            }
            right[j] = Math.max(cur, left[j]);
            ans+= right[j];
        }
        return ans;
    }
}

   