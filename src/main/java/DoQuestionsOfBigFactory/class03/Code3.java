package DoQuestionsOfBigFactory.class03;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/21 17:42
 */

/**
 * 给定一个只有0和1组成的二维数组
 *
 * 返回边框全是1的最大正方形面积
 *
 * https://leetcode.com/problems/largest-1-bordered-square/
 */
public class Code3 {
    public static int largest1BorderedSquare(int[][] grid){
        if(grid == null || grid.length ==0 ){
            return 0;
        }
        //N * M 的数组
        int N = grid.length;
        int M = grid[0].length;

        int[][] right = new int[N][M];
        int[][] down = new int[N][M];
        //生成边框都是1的距离数组
        generateBorderMatrix(right, down, grid, N, M);

        System.out.println(right);
        System.out.println(down);
        for(int size = Math.min(grid.length,grid[0].length); size != 0; size--){
            if(hasBorder(size, right, down)){
                System.out.println(size);
                return size * size;
            }
        }


        return 0;
    }

    public static void generateBorderMatrix(int[][] right, int[][] down, int[][] mat, int N, int M){

        if(mat[N-1][M-1] == 1){
            right[N-1][M-1] = 1;
            down[N-1][M-1] = 1;
        }
        for(int i = N -2;i>-1;i--){
            if(mat[i][M-1] == 1){
                right[i][M-1] = 1;
                down[i][M-1] = down[i+1][M-1] +1;
            }
        }
        for(int i = M -2;i>-1;i--){
            if(mat[N-1][i] == 1){
                right[N-1][i] = right[N-1][i+1] +1;
                down[N-1][i] = 1;
            }
        }

        for(int i = N -2;i>-1;i-- ){
            for(int j = M -2;j>-1;j--){
                if(mat[i][j] == 1){
                    right[i][j] = right[i][j+1] +1;
                    down[i][j] = down[i+1][j] +1;
                }

            }
        }
    }

    public static boolean hasBorder(int size, int[][] right, int[][] down){
        for(int i = 0;i!=right.length- size +1;i++){
            for(int j = 0;j!=right[0].length -size +1;j++){
                if(right[i][j]>=size && down[i][j]>= size && right[i+size-1][j] >= size && down[i][j+size-1]>= size){
                    return true;
                }
            }
        }


        return false;
    }


    public static void main(String[] args) {
        int[] a1={1,1,1};
        int[] a2={1,0,1};
        int[] a3={1,1,1};
        int[][] grid = {a1,a2,a3};
        largest1BorderedSquare(grid);
    }

}

   