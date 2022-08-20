package DoQuestionsOfBigFactory.class14;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/19 21:24
 */
public class Code01_Parentheses {
    /**
     * 给定一个只由左括号和右括号的字符串
     * 返回最长的有效括号子串的长度
     *
     * 思路： dp
     * 子串问题：以i结尾的字符串可以组成的最长有效括号子串的长度
     */
    public int maxValidateLength(String str){
        if(str == null || str.length() < 2){
            return 0;
        }

        int N = str.length();
        char[] chars = str.toCharArray();
        int[] dp = new int[N];
        int ans = 0;
        int pre = 0;
        for(int i = 1 ;i < N;i++){
            //当前是右括号才去匹配
            if(chars[i] ==')'){
                //可以匹配的位置
                pre = i - dp[i-1] -1;
                if(pre >=0 && chars[pre] =='('){
                    //pre>0 可以保证在有效位置 加上匹配到的字符 的前一个位置的长度
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre-1] :0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }
}

   