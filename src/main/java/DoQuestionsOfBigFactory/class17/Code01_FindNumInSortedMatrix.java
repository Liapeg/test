package DoQuestionsOfBigFactory.class17;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/18 16:25
 */
public class Code01_FindNumInSortedMatrix {

    /**
     * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组
     * 再给定一个数num，
     * 返回二维数组中有没有num这个数
     */

    /**
     * 从右上角开始 判断当前数比num大 那么num就在当前数的左侧 向左移
     *            如果比当前数小那么往下移 直到比num大 然后向左移 直到越界
     * @param matrix
     * @param num
     * @return
     */
    public static boolean findNumInSortedMatrix(int[][] matrix, int num){
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0;
        int col = M-1;
        while (row < N && col > -1){
            if(matrix[row][col] == num){
                return true;
            }else if(matrix[row][col] > num){
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 }, // 0
                { 10, 12, 13, 15, 16, 17, 18 }, // 1
                { 23, 24, 25, 26, 27, 28, 29 }, // 2
                { 44, 45, 46, 47, 48, 49, 50 }, // 3
                { 65, 66, 67, 68, 69, 70, 71 }, // 4
                { 96, 97, 98, 99, 100, 111, 122 }, // 5
                { 166, 176, 186, 187, 190, 195, 200 }, // 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 190;
        System.out.println(findNumInSortedMatrix(matrix, K));
    }

}

   