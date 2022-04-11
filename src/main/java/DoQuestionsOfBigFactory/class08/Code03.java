package DoQuestionsOfBigFactory.class08;

import com.sun.jmx.snmp.SnmpNull;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/10 9:51
 */
public class Code03 {

    /**
     *给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
     * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
     *  char[][] m = { { 'a', 'b', 'z' },
     *                        { 'c', 'd', 'o' },
     *                        { 'f', 'e', 'o' },
     * 设定1：可以走重复路的情况下，返回能不能找到
     * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
     * 设定2：不可以走重复路的情况下，返回能不能找到
     * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
     */

    //1、可以走重复路
    public static boolean findWordsCanLoop(char[][] matrix, String word){
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;

        char[] str = word.toCharArray();
        boolean ans = false;
        //从任意位置（i，j）出发，能否走出word
        for(int i =0; i< N;i++){
            for(int j =0;j<M;j++ ){
                if(process(matrix, i,j,0,str)){
                    ans = true;
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean process(char[][] matrx, int i, int j, int k, char[] str){

        if(k == str.length){
            return true;
        }

        if(i < 0 || j< 0 ||j == matrx[0].length || i== matrx.length|| matrx[i][j] != str[k]){
            return false;
        }

        //此时，i，j既不越界，matrix[i][j]位置也等于此时的str【k】
        if(process(matrx, i-1, j, k+1, str) ||process(matrx, i+1, j, k+1, str)
                ||process(matrx, i, j-1, k+1, str)
                ||process(matrx, i, j+1, k+1, str)){
            return true;
        }

        return false;
    }


    //dp
    public static boolean findWordsCanLoopDp(char[][] matrix, String word){
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;

        char[] str = word.toCharArray();
        int k = str.length;
        boolean[][][] dp = new boolean[N][M][k];
        for(int i =0; i< N;i++){
            for(int j =0;j<M;j++ ){
                dp[i][j][0] = matrix[i][j] == str[0];
            }
        }
        //从任意位置（i，j）出发，能否走出word
        for (int n = 1; n < k; n++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                    if (n == k) {//空字符是可以匹配的 或者说来到k位置时， 说明之前的路是正确的
                        dp[i][j][n] = true;
                    } else if (i < 0 || j < 0 || j == M-1 || i == N-1 || matrix[i][j] != str[n]) {
                        dp[i][j][n] = false;
                    } else if (dp[i + 1][j][n - 1] || dp[i - 1][j][n - 1] || dp[i][j + 1][n - 1] || dp[i][j - 1][n - 1]) {
                        dp[i][j][n] = true;
                    } else {
                        dp[i][j][n] = false;
                    }
                }
            }
        }
        boolean ans = false;
        for(int i = 0; i< N;i++){
            for(int j =0;j<M;j++){
                if(dp[i][j][k-1]){
                    ans =  true;
                    break;
                }
            }
        }
        return ans;
    }


    //1、可以走重复路
    public static boolean findWordsNoLoop(char[][] matrix, String word){
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;

        char[] str = word.toCharArray();
        boolean ans = false;
        //从任意位置（i，j）出发，能否走出word
        for(int i =0; i< N;i++){
            for(int j =0;j<M;j++ ){
                if(process1(matrix, i,j,0,str)){
                    ans = true;
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean process1(char[][] matrx, int i, int j, int k, char[] str){

        if(k == str.length){
            return true;
        }

        if(i < 0 || j< 0 ||j == matrx[0].length || i== matrx.length|| matrx[i][j] != str[k]){
            return false;
        }

        matrx[i][j] = 0;
        //此时，i，j既不越界，matrix[i][j]位置也等于此时的str【k】
        if(process(matrx, i-1, j, k+1, str) ||process(matrx, i+1, j, k+1, str)
                ||process(matrx, i, j-1, k+1, str)
                ||process(matrx, i, j+1, k+1, str)){
            return true;
        }
        matrx[i][j] = str[k];
        return false;
    }


    public static void main(String[] args) {
        char[][] m = { { 'a', 'b', 'z' }, { 'c', 'd', 'o' }, { 'f', 'e', 'o' }, };
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
        System.out.println(findWordsCanLoop(m, word1));
        System.out.println(findWordsCanLoopDp(m, word2));
        // 不可以走重复路的设定
        System.out.println(findWordsNoLoop(m, word1));
        System.out.println(findWordsNoLoop(m, word2));

    }


}

   