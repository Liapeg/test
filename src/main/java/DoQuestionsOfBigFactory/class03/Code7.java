package DoQuestionsOfBigFactory.class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/25 18:14
 */
public class Code7 {

    /**
     * 递归 前一步选择的字符会对后面产生影响  动态规划
     * @param ring
     * @param key
     * @return
     */

    public static int findRotateSteps(String ring, String key){

        char[] c = ring.toCharArray();
        char[] target = key.toCharArray();

        int N= c.length;
        int M = target.length;

        //M+1的原因？？  index=M-1时 需依赖index=M的值
        int[][] dp = new int[N][M+1];

        for(int i =0;i < N; i++){
            for(int j = 0;j< M; j++){
                //该字符未计算
                dp[i][j] = -1;
            }
        }

        Map<Character, ArrayList<Integer>> map = new HashMap();
        //ring中各个字符分别在什么位置
        for(int i=0; i< N;i++){
            if(!map.containsKey(c[i])){
                map.put(c[i], new ArrayList<Integer>());
            }
            map.get(c[i]).add(i);
        }


        return process(0, 0, target, map, dp, N);
    }

    public static int process(int preButt, int index, char[] tar, Map<Character, ArrayList<Integer>> map, int[][]dp, int N){
        if(dp[preButt][index] != -1){
            return dp[preButt][index];
        }
        //base case
        if(index == tar.length){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        ArrayList<Integer> curList = map.get(tar[index]);
        for(int cur : curList){
            int cost = dail(preButt, cur, N) +1+ process(cur, index +1, tar,map, dp, N);
            ans = Math.min(ans, cost);
        }
        dp[preButt][index] = ans;
        return ans;
    }
    //0。。。N中    x 到  y 循环的最小距离
    public static int dail(int x, int y, int N){
        return Math.min(Math.abs(x-y), N - Math.max(x,y) +Math.min(x, y));
    }
}

   