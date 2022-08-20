package leetcodeTop100;

import java.util.HashMap;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/18 14:49
 */
public class Problem03 {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度
     * dp表
     */
    public int lengthOfLongestSubstring1(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ans = 1;
        char[] chars = s.toCharArray();

        int[] dp = new int[chars.length];
        dp[0] = 1;
        //上一个最长子串结束的位置
        int last = 0;
        //dp[i]：以i位置结尾的字符，最大不重复子串的长度
        for(int i = 1; i< chars.length; i++){
            //在L。。。R上包含字符c
            int con = contains(chars, chars[i], last , i);
            if(con ==-1){
                dp[i] = dp[i-1]  +1;
            }else {
                last = con +1;
                dp[i] = i -last + 1;
            }

            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 是否可优化  hashmap？
     */
    public int contains(char[] chars, char c, int L, int R){
        int ret = -1;
        for(int i = L; i< R; i++){
            if(chars[i] == c){
                return i;
            }
        }
        return ret;
    }


    /**
     * map优化时间复杂度   额外空间复杂度 N
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 1;
        char[] chars = s.toCharArray();

        int[] dp = new int[chars.length];
        dp[0] = 1;
        //上一个最长子串结束的位置
        int last = 0;
        map.put(chars[0], 0);
        //dp[i]：以i位置结尾的字符，最大不重复子串的长度
        for(int i = 1; i< chars.length; i++){
            //在L。。。R上包含字符c
            //int con = contains(chars, chars[i], last , i);
            if(map.get(chars[i]) == null   || map.get(chars[i]) < last){
                dp[i] = dp[i-1]  +1;
            }else {
                last = map.get(chars[i]) +1;
                dp[i] = i -last + 1;
            }
            map.put(chars[i], i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}

   