package DoQuestionsOfBigFactory.class18;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/23 11:25
 */
public class Code01_HanoiProblem {

    /**
     * 给定一个数组arr，长度为N，arr中的值只有1，2，3三种
     * arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左
     * arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中
     * arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右
     * 那么arr整体就代表汉诺塔游戏过程中的一个状况
     * 如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1
     * 如果这个状况是汉诺塔最优解运动过程中的状况，返回它是第几个状况
     *
     *
     * 思路：0...i层的汉诺塔 从左侧到右侧需要3大步
     * 1、把0~i-1从左移到中
     * 2、把i层从左移到右
     * 3、把0~i-1从中移到右
     * 定义一个函数 f（arr ，i， from ，to。 other）
     * arr原数组 代表此时的圆饼位于 左右还是中
     * i表示现在到了0~1层
     * 从from移动到to  剩下的是哪个
     * f返回的是当前的移动是 汉诺塔问题移动最有解中的哪一步
     * N层汉诺塔问题的最优解是2^N -1
     */

    public static int hanoiProblem(int[] arr ){
        int N = arr.length;
        return process(arr, N-1, 1,3,2);
    }

    public static int process(int[] arr, int i, int from , int to, int other){
        //base-case
        if(i == -1){
            return 0;
        }
        //不是最优解的情况 说明：最底层的既不在第一个柱子  也不再最右的柱子
        if(arr[i] != from && arr[i] != to){
            return -1;
        }
        //第一步
        if(arr[i] == from){
           return process(arr, i-1, from, other, to);
        }else {
            //完成第一步了
            int p1= (1<<i) -1;
            //第二步
            int p2 = 1;
            //第三步
            int p3= process(arr, i-1, other, to, from);
            if(p3==-1){
                return -1;

            }
            return p1 + p2 +p3;
        }
    }



    public static int kth(int[] arr) {
        int N = arr.length;
        return step(arr, N - 1, 1, 3, 2);
    }

    // 0...index这些圆盘，arr[0..index] index+1层塔
    // 在哪？from 去哪？to 另一个是啥？other
    // arr[0..index]这些状态，是index+1层汉诺塔问题的，最优解第几步
    public static int step(int[] arr, int index, int from, int to, int other) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] == other) {
            return -1;
        }
        // arr[index] == from arr[index] == to;
        if (arr[index] == from) {
            return step(arr, index - 1, from, other, to);
        } else {
            int p1 = (1 << index) - 1;
            int p2 = 1;
            int p3 = step(arr, index - 1, other, to, from);
            if (p3 == -1) {
                return -1;
            }
            return p1 + p2 + p3;
        }
    }


    public static void main(String[] args) {
        int[] arr = { 3, 3, 2, 1 };
        System.out.println(hanoiProblem(arr));
        //System.out.println(step2(arr));
        System.out.println(kth(arr));
    }
}

   