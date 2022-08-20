package DoQuestionsOfBigFactory.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/25 9:50
 */
public class Code01 {

    /**
     * 一个字符串至少需要添加多少个字符能整体变成回文串
     * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
     */

    /**
     *从i..j字符串至少添加多少字符整体能变成回文串
     */
    public static int minInsertions(String s) {
        if(s == null || s.length() < 1){
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][] dp = new int[N][N];
        for(int i = 0;i < N-1;i++){
            dp[i][i+1] = chars[i] == chars[i+1] ? 0 :1;

        }
        for(int i = N-3;i >=0;i--){
            for(int j = i+2; j < N;j++){
                //三种可能
                //1、从i和j位置字符相同 那么只要i+1,j-1位置是回文串即可
                //2、从i。。。j-1位置是回文串，那么在开头添加第j个字符即可
                //3、i+1位置到j位置是回文，那么在最后添加i字符jike
                //小贪心：如果i。。j位置相同，那么比另外两种的代价更低（未验证） 但leetcode可通过
                if(chars[i] == chars[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    //
                    dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) +1;
                }
            }
        }
        return dp[0][N-1];
    }



    //返回上述问题的其中一种添加结果
    public static String minInsertionsOneWay(String s) {
        if(s == null || s.length() < 1){
            return null;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][] dp = new int[N][N];
        for(int i = 0;i < N-1;i++){
            dp[i][i+1] = chars[i] == chars[i+1] ? 0 :1;

        }
        for(int i = N-3;i >=0;i--){
            for(int j = i+2; j < N;j++){
                //三种可能
                //1、从i和j位置字符相同 那么只要i+1,j-1位置是回文串即可
                //2、从i。。。j-1位置是回文串，那么在开头添加第j个字符即可
                //3、i+1位置到j位置是回文，那么在最后添加i字符jike
                //小贪心：如果i。。j位置相同，那么比另外两种的代价更低（未验证） 但leetcode可通过
                if(chars[i] == chars[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    //
                    dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) +1;
                }
            }
        }

        //回溯结果
        //最优结果的字符长度
        char[] ans = new char[N+ dp[0][N-1]];
        int M = ans.length;
        int L = 0;
        int R = N-1;
        int ansl = 0;
        int ansr= M -1 ;
        //所有的字符都过一遍
        while (L < R){
            //看来自于哪种可能
            //1、在最左侧加一个字符
            if(dp[L][R-1] == (dp[L][R] -1)){
                ans[ansl++] = chars[R];
                ans[ansr++] = chars[R--];
            }else if(dp[L+1][R] == dp[L][R]-1 ){
                //2、在右侧加一个字符
                ans[ansl++] = chars[L];
                ans[ansr++] = chars[L++];
            }else {
                //L和R位置的字符相同
                ans[ansl++] = chars[L++];
                ans[ansr++] = chars[R--];
            }
        }

        //剩下L>R   L==R
        if(L == R){
            //来到最中间的位置 剩最后一个位置了
            ans[ansl] = chars[L];
        }

        return String.valueOf(ans);


    }

    public static List<String> minInsertionsAllWay(String s) {
        if(s == null || s.length() < 1){
            return null;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][] dp = new int[N][N];
        for(int i = 0;i < N-1;i++){
            dp[i][i+1] = chars[i] == chars[i+1] ? 0 :1;

        }
        for(int i = N-3;i >=0;i--){
            for(int j = i+2; j < N;j++){
                //三种可能
                //1、从i和j位置字符相同 那么只要i+1,j-1位置是回文串即可
                //2、从i。。。j-1位置是回文串，那么在开头添加第j个字符即可
                //3、i+1位置到j位置是回文，那么在最后添加i字符jike
                //小贪心：如果i。。j位置相同，那么比另外两种的代价更低（未验证） 但leetcode可通过
                if(chars[i] == chars[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    //
                    dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) +1;
                }
            }
        }


        List<String> ans = new ArrayList<>();
        //回溯所有结果
        //最少添加字符的长度
        int M = N + dp[0][N-1];
        char[] chars1 = new char[M];
        process(chars, dp, 0, N-1, 0, M-1, chars1, ans);

        return ans;

    }
    public static void process(char[] str, int[][] dp, int L, int R, int pl, int pr,char[] path, List<String> ans){

        //base-case
        //当L > R时就可以收集答案了
        if(L >= R){
            //此时只剩最后一个位置没有添了
            if(L == R){
                path[pl] = str[L];
            }
            //左右错过时， 可以收集答案了
            ans.add(path.toString());
        }

        //正常位置填写答案
        //左侧添加一个R位置字符
        if(dp[L][R-1] == dp[L][R] -1){
            path[pl] = str[R];
            path[pr] = str[R];
            //调递归
            process(str, dp, L, R-1, pl +1, pr-1, path, ans);
        }else if(dp[L+1][R] == dp[L][R] -1){
            //此时i位置字符和L+1位置字符可以构成回文串，所以在右侧添加一个L位置字符
            path[pl] = str[L];
            path[pr] = str[L];
            process(str, dp, L+1, R, pl +1, pr -1, path, ans);
        }else if(str[L] == str[R] && (L == R-1 || dp[L+1][R-1]== dp[L][R])){
            //此时L和R位置字符相同 那么中间是回文串
            path[pl] = str[L];
            path[pr] = str[R];
            process(str, dp, L +1, R -1, pl+1, pr-1, path, ans);
        }
    }

}

   