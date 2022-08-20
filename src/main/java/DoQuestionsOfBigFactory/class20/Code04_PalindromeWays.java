package DoQuestionsOfBigFactory.class20;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/5 10:15
 */
public class Code04_PalindromeWays {

    /**
     * 给定一个字符串str，当然可以生成很多子序列
     * 返回有多少个子序列是回文子序列，空序列不算回文
     * 比如，str = “aba”
     * 回文子序列：{a}、{a}、 {a,a}、 {b}、{a,b,a}
     * 返回5
     *
     * 范围模型： 从L。。。R上有多少个回文子序列
     * 最后返回   右上角的值
     */
    public static int palindromeWays(String str){

        if(str == null || str.length()==0){
            return 0;
        }

        char[] chars = str.toCharArray();
        int N = chars.length;
        int[][] dp = new int[N][N];

        //分析情况 i 、j位置的数相等和不相等
        //1、要i位置的数 要j位置的数   dp[i+1][j-1]
        //2、要i 不要j              dp[i+1][j]
        //3、不要i 不要j            dp[i][j-1]
        //4、不要i 要j
        // 为什么 dp[i+1][j] = 3 +4？？？ str从L+1到R的子序列都包含了要j和不要j的情况
        //所以 2 +3 + 4   = dp[i+1][j] + dp[i][j-1] -dp[L+1][R-1]
        //根据i j是否相等来决定有没有可能性 1   如果有那么 1= dp[L+1][R-1] +1
        for(int i=0; i< N;i++){
            dp[i][i] = 1;
        }

        //相邻位置的数量
        for(int i=0; i<N-1;i++){
            dp[i][i+1] = chars[i] == chars[i+1] ? 3 :2;
        }
        //剩下的从下往上 从左往右依次填  只填右上部分
        for(int i = N-3;i >=0;i--){
            for(int j=i+2;j <N;j++){
                dp[i][j] = dp[i+1][j] + dp[i][j-1] -dp[i+1][j-1];
                if(chars[i] == chars[j]){
                    dp[i][j] +=(dp[i+1][j-1] +1);
                }
            }
        }

        return dp[0][N-1];
    }

    public static int ways1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        char[] path = new char[s.length];
        return process(str.toCharArray(), 0, path, 0);
    }

    public static int process(char[] s, int si, char[] path, int pi) {
        if (si == s.length) {
            return isP(path, pi) ? 1 : 0;
        }
        int ans = process(s, si + 1, path, pi);
        path[pi] = s[si];
        ans += process(s, si + 1, path, pi + 1);
        return ans;
    }

    public static boolean isP(char[] path, int pi) {
        if (pi == 0) {
            return false;
        }
        int L = 0;
        int R = pi - 1;
        while (L < R) {
            if (path[L++] != path[R--]) {
                return false;
            }
        }
        return true;
    }

    public static String randomString(int len, int types) {
        char[] str = new char[len];
        for (int i = 0; i < str.length; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * types));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 10;
        int types = 5;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * N);
            String str = randomString(len, types);
            int ans1 = ways1(str);
            int ans2 = palindromeWays(str);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }


}

   