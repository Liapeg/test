package DoQuestionsOfBigFactory.class12;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/5/29 21:19
 */
public class Regular_expression_matching {

    /**
     * https://leetcode-cn.com/problems/regular-expression-matching
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     *
     */
    public static boolean isMatch(String s, String p) {
        if(s == null || s.length()==0 || p ==null || p.length() ==0){
            return false;
        }
        System.out.println("val+++++"+isValid(s, p));
        int n = s.length();
        int m = p.length();
        int[][] dp = new int[n][m];

        char[] chars = s.toCharArray();
        char[] chars1 = p.toCharArray();
        return process(chars,0 ,chars1, 0, dp);
    }

    //两个字符串
    //向样本对应模型靠
    //dp[i,j]的含义： 以i开头的s字符串   能否匹配上以j开头的p字符串
    //0表示这个格子没算过  1表示可以匹配  -1表示不能匹配
    public static boolean process(char[] s, int l, char[] p, int r, int[][] dp) {
        //base-case
        if(p.length == r){
            return l == s.length;
        }
        //按照下一个格子是否是*来区分   p串还有字符
        //1、下个格子不是*号
        if(r+1 == p.length || p[r+1] !='*' ){
            return l != s.length&& (s[l] == p[r] || p[r] =='.') && process(s,l+1, p,r+1,dp);
        }

        //2、下个格子是*号
        while (l != s.length && (s[l] == p[r] || p[r] =='.')){
            if(process(s,l,p,r+2,dp)){
                return true;
            }
            l++;
        }
        //*号匹配完之后继续
        return process(s,l,p,r+2,dp);
    }

    /**
     * 动态规划版本  + 斜率优化
     */
    public static boolean process2(char[] s, int l, char[] p, int r, int[][] dp) {
        if(dp[l][r] != 0){
            return dp[l][r] ==1;
        }
        //base-case
        boolean ans = false;
        if(p.length == r){
            ans = l == s.length;
        }else {
            //按照下一个格子是否是*来区分   p串还有字符
            //1、下个格子不是*号
            if(r+1 == p.length || p[r+1] !='*' ){
                ans = l != s.length&& (s[l] == p[r] || p[r] =='.') && process2(s,l+1, p,r+1,dp);
            }else {
                //2、下个格子是*号
                //s是最后一个字符 直接判断是否匹配
                if(l == s.length){
                    ans = process2(s, l, p, r+2, dp);
                }else if(s[l] != p[r] && p[r] !='.'){
                    //l 和 r位置不同
                    ans = process2(s, l, p, r+2,dp);
                }else {
                    //左侧当前位置和*匹配   右侧process 相当于l下个位置和r位置去匹配
                    ans = process2(s, l, p, r+2, dp) | process2(s,l+1,p,r,dp);
                }
            }
        }

        dp[l][r] = ans ? 1:-1;
        return ans;
    }

    public static boolean isValid(String s,String p) {

         char[] char1 = s.toCharArray();
         for(char c : char1){
             if(c == '.' || c == '*'){
                 return false;
             }
         }
        char[] chars = p.toCharArray();
        if (chars[0] == '*') {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*' && chars[i - 1] == '*') {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        String s = "aa";
        String p = "a*";
        System.out.println(isMatch(s, p));
    }


}

   