package DoQuestionsOfBigFactory.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/28 18:51
 */
public class Code02 {

    /**
     * https://leetcode.com/problems/palindrome-partitioning-ii/
     * 问题一：一个字符串至少要切几刀能让切出来的子串都是回文串
     *
     * 问题二：返回问题一的其中一种划分结果
     *
     * 问题三：返回问题一的所有划分结果
     *
     */

    public int minCut(String s) {
        if(s == null || s.length()==0){
            return 0;
        }
        int N = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[N+1];
        //
        boolean[][] help = new boolean[N][N];
        //只判断help[i][j] 在i，j范围上是不是回文串
        help[N-1][N-1] = true;
        for(int i = 0; i < N-1; i++){
            help[i][i] = true;
            help[i][i+1] = chars[i] == chars[i+1];
        }
        for (int i= N-3; i >=0; i--){
            for(int j = i+2; j < N;j++ ){
                help[i][j] = (chars[i] == chars[j] && help[i+1][j-1] ==true);
            }
        }

        //dp[i]以i开头的字符最少可以切分出的回文串
        dp[N] = 0;
        int next = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            if (help[i][N - 1]) {
                dp[i] = 1;

            }else {
                for (int j = i; j < N - 1; j++) {
                    if(help[i][j]){
                        next = Math.min(next, dp[j+1]);
                    }
                }
                //i..j是回文为1，加上后面分割的部分
                dp[i] = next +1;

            }

        }

        return dp[0] -1;
    }


    public List<String> findOneWay(String s){
        List<String> list = new ArrayList<String>();
        if(s == null || s.length()< 1){
            list.add(s);
            return list;
        }
        int N = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[N+1];
        //
        boolean[][] help = new boolean[N][N];
        //只判断help[i][j] 在i，j范围上是不是回文串
        help[N-1][N-1] = true;
        for(int i = 0; i < N-1; i++){
            help[i][i] = true;
            help[i][i+1] = chars[i] == chars[i+1];
        }
        for (int i= N-3; i >=0; i--){
            for(int j = i+2; j < N;j++ ){
                help[i][j] = (chars[i] == chars[j] && help[i+1][j-1] ==true);
            }
        }

        //dp[i]以i开头的字符最少可以切分出的回文串
        dp[N] = 0;
        int next = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            if (help[i][N - 1]) {
                dp[i] = 1;

            }else {
                for (int j = i; j < N - 1; j++) {
                    if(help[i][j]){
                        next = Math.min(next, dp[j+1]);
                    }
                }
                //i..j是回文为1，加上后面分割的部分
                dp[i] = next +1;

            }

        }

        //回溯
        //
        for(int i = 0, j=1;j < N; j++){
            //回文串，且是之前的一种分割
            if(help[i][j-1] && (dp[i] == dp[j] +1 )){
                //此时收集到一个答案
                list.add(s.substring(i, j));
                i = j;
            }
        }
        return list;
    }

    public List<List<String>> findAllWay(String s){
        List<List<String>> returnList = new ArrayList<>();
        List<String> list = new ArrayList<String>();
        if(s == null || s.length()< 1){
            list.add(s);
            returnList.add(list);
            return returnList;
        }
        int N = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[N+1];
        //
        boolean[][] help = new boolean[N][N];
        //只判断help[i][j] 在i，j范围上是不是回文串
        help[N-1][N-1] = true;
        for(int i = 0; i < N-1; i++){
            help[i][i] = true;
            help[i][i+1] = chars[i] == chars[i+1];
        }
        for (int i= N-3; i >=0; i--){
            for(int j = i+2; j < N;j++ ){
                help[i][j] = (chars[i] == chars[j] && help[i+1][j-1] ==true);
            }
        }

        //dp[i]以i开头的字符最少可以切分出的回文串
        dp[N] = 0;
        int next = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            if (help[i][N - 1]) {
                dp[i] = 1;

            }else {
                for (int j = i; j < N - 1; j++) {
                    if(help[i][j]){
                        next = Math.min(next, dp[j+1]);
                    }
                }
                //i..j是回文为1，加上后面分割的部分
                dp[i] = next +1;

            }

        }

        //回溯
        //
        for (int i = 0, j = 1; j < N; j++) {
            //回文串，且是之前的一种分割
            if (help[i][j - 1] && (dp[i] == dp[j] + 1)) {
                //此时收集到一个答案
                list.add(s.substring(i, j));
                i = j;
            }
        }
        process(s, 0, 1, help, dp, new ArrayList<>(), returnList);

        return returnList;
    }

    public void process(String s, int i, int j, boolean[][] help, int[] dp, List<String> path, List<List<String>> list) {
        //base-case
        if (j == s.length()) {
            //收集答案
            if(help[i][j-1] &&  dp[i] == (dp[j] +1)){
                path.add(s.substring(i, j));
                list.add(copyStringList(path));
                //恢复现场
                path.remove(path.size() -1);
            }
        }else {
            //s[i....j-1]位置
            if(help[i][j-1] && dp[i] == (dp[j] +1)){
                //一个划分
                path.add(s.substring(i, j));
                process(s, j ,j+1, help,dp, path, list);
                path.remove(path.size()-1);
            }
            process(s, i, j+1, help, dp, path, list);
        }
    }

    public static List<String> copyStringList(List<String> list) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            ans.add(str);
        }
        return ans;
    }
}

   