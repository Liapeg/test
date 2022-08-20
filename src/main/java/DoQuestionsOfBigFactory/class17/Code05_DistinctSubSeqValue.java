package DoQuestionsOfBigFactory.class17;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/20 14:43
 */
public class Code05_DistinctSubSeqValue {

    /**
     * 给定一个字符串Str
     * 返回Str的所有子序列中有多少不同的字面值
     *
     * Leetcode原题：https://leetcode.com/problems/distinct-subsequences-ii/
     * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
     * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
     * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
     *
     * 思路：从左往右的模型
     * 难点：怎么根据前面的推出以当前字符结尾的字符串 有多少不通的字面值
     *初始的时候维护一个空集合 到达一个字符的时候将这个字符加到上一步的集合里 加进去的都会组成新的集合 就会产生上一个集合个数个新集合 再加上上一步的集合数
     *
     * 这样会有重复的 所以要减去重复的
     *
     * 重复的就是上一个以该字符结尾的集合
     */
    public int distinctSubseqII(String s) {
        if(s ==null){
            return 0;
        }
        int M = 1000000007;
        int N = s.length();
        //初始化0位置的字符 可以让后续的结果继续下去
        int[] dp = new int[N];
        //记录字符上一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        dp[0] = 1;
        map.put(s.charAt(0),dp[0]);
        for(int i=1; i<N;i++){
            //要加上一个空集合
            int newAdd = dp[i-1];
            int all = newAdd +1;
            dp[i] = (all +newAdd)%M - ((map.get(s.charAt(i)) == null ? 0 : map.get(s.charAt(i)))+M)%M;
            map.put(s.charAt(i), all);
        }
        return dp[N-1];
    }
}

   