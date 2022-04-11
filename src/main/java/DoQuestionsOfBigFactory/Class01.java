package DoQuestionsOfBigFactory;

import java.io.File;
import java.util.LinkedList;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/10 16:17
 */
public class Class01 {

    /**
     * 给定一个文件目录的路径，
     * 写一个函数统计这个目录下所有的文件数量并返回
     * 隐藏文件也算，但是文件夹不算
     */
    public static int FileAndDirectory(){
        int total = 0;

        File file = new File("E:\\学习 资料\\算法\\算法课资料\\ppt\\大厂刷题班");

        if(file.isFile()){
            return 1;
        }

        LinkedList list = new LinkedList();
        list.add(file);
        while (!list.isEmpty()){
            File newFile = (File) list.pop();

            for(File cur : newFile.listFiles()){
                if(cur.isFile()){
                    total++;
                }
                if(cur.isDirectory()){
                    list.push(cur);
                }
            }

        }

        return total;
    }

    /**
     * 给定一个非负整数num，
     * 如何不用循环语句，
     * 返回>=num，并且离num最近的，2的某次方
     * @param
     */
    public static int twoPower(int num){

        num -= 1;
        num |= num>>1;
        num |= num >>2;
        num |= num >> 4;
        num |= num>> 8;
        num |= num >> 16;

        num +=1;


        return num;
    }

    /**
     *给定一个二维数组matrix，
     * 你可以从任何位置出发，走向上下左右四个方向
     * 返回能走出来的最长的递增链长度
     * @param
     */
    /*public static int longestIncreasingPath(int[][] matrix) {
        int length = 0;
        int M = matrix.length;
        int N = matrix[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                length = Math.max(length, process(i, j, matrix));
            }
        }
        return length;
    }

    public static int process(int i, int j, int[][] mat) {

        int up = i>0&&mat[i-1][j] > mat[i][j] ? process(i-1,j,mat): 0;
        int down = i< (mat.length-1) && mat[i+1][j] > mat[i][j] ? process(i+1,j,mat): 0;
        int left = j>0&&mat[i][j-1] > mat[i][j] ? process(i,j-1,mat): 0;
        int right = j < (mat[0].length-1) &&mat[i][j+1] > mat[i][j] ? process(i,j+1,mat): 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) +1;
    }*/
    public static int longestIncreasingPath(int[][] matrix) {

        int length = 0;
        int M = matrix.length;
        int N = matrix[0].length;

        int[][] dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                length = Math.max(length, process(i, j, matrix, dp));
            }
        }
        return length;
    }

    public static int process(int i, int j, int[][] mat, int[][] dp) {

        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int up = i>0&&mat[i-1][j] > mat[i][j] ? process(i-1,j,mat,dp): 0;
        int down = i< (mat.length-1) && mat[i+1][j] > mat[i][j] ? process(i+1,j,mat,dp): 0;
        int left = j>0&&mat[i][j-1] > mat[i][j] ? process(i,j-1,mat,dp): 0;
        int right = j < (mat[0].length-1) &&mat[i][j+1] > mat[i][j] ? process(i,j+1,mat,dp): 0;
        dp[i][j] = Math.max(Math.max(up, down), Math.max(left, right)) +1;
        return Math.max(Math.max(up, down), Math.max(left, right)) +1;
    }



    public static void main(String[] args) {



        System.out.println(twoPower(59));
    }
}

   