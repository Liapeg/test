package DoQuestionsOfBigFactory.class13;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/8 17:24
 */
public class Code03_isScramble {
    /**
     *https://leetcode.com/problems/scramble-string/
     *
     * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
     * 如果字符串的长度为 1 ，算法停止
     * 如果字符串的长度 > 1 ，执行下述步骤：
     * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
     * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
     * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
     * 给你两个 长度相等 的字符串 s1 和s2，判断s2是否是s1的扰乱字符串。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isScramble0(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()){
            return false;
        }
        //大过滤 两个字符串各种字符的个数必须一样
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        if(!isValidate(chars1,chars2)){
            return false;
        }
        int N = s1.length();
        return process(chars1, 0, N-1, chars2, 0,N-1);
    }

    //s1从l1到r1  s2从l2到r2上是否是扰乱字符串 s1 s2和必须等长
    public boolean process(char[] chars1, int L1, int R1, char[] chars2, int L2, int R2){
        //base-case
        if(L1 == R1){
            return chars1[L1] == chars2[L2];
        }

        //分析可能性
        for(int size = 0; size< R1-L1;size++) {
            //1、左和左相等
            boolean flag1 = process(chars1, L1, L1+size,chars2, L2, L2+size) && process(chars1,L1+size+1, R1,chars2, L2 +size+1, R2);
            //2、左和右相等
            boolean flag2 = process(chars1, L1, L1+size,chars2, R2-size, R2) && process(chars1, L1+size+1, R1,chars2, L2, L2+size);

            if (flag1 || flag2) {
                return true;
            }
        }
        return false;
    }


    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()){
            return false;
        }
        //大过滤 两个字符串各种字符的个数必须一样
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        if(!isValidate(chars1,chars2)){
            return false;
        }
        int N = s1.length();
        int[][][] dp = new int[N][N][N+1];
        return process2(chars1, 0, chars2, 0,N, dp);
    }
    //dp中 dp[i] = 0 表示没算过
    //dp中 dp[i] = 1 表示算过了  是true
    //dp中 dp[i] = -1 表示算过了 是false
    //size 表示L1到R1这段的长度
    public boolean process2(char[] chars1, int L1, char[] chars2, int L2,int size, int[][][] dp){
        if(dp[L1][L2][size] != 0){
            return dp[L1][L2][size] == 1;
        }
        boolean ans = false;
        //base-case
        if(size == 1){
            ans = chars1[L1] == chars2[L2];
        }else {

            //分析可能性
            for (int cur = 0; cur < size; cur++) {
                if ((process2(chars1, L1, chars2, L2, cur, dp) && process2(chars1, L1 + cur, chars2, L2 + cur, size - cur, dp)) ||
                        (process2(chars1, L1, chars2, L2 + size - cur - 1, cur, dp) && process2(chars1, L1 + cur, chars2, L2, size - cur, dp))) {
                    ans = true;
                    break;
                }
            }
        }
        dp[L1][L2][size] = ans ? 1 :-1;
        return ans;
    }

    //检查有效性
    public boolean isValidate(char[] chars1, char[] chars2){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : chars1){
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else {
                map.put(c, 1);
            }
        }
        for(char c : chars2){
            if(!map.containsKey(c) || (map.get(c)-1 <0)){
                return false;
            }else {
                map.put(c, map.get(c)-1);
            }
        }
        return true;
    }
}

   