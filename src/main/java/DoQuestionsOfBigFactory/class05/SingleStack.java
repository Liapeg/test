package DoQuestionsOfBigFactory.class05;

import java.util.Stack;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/9 9:04
 */
public class SingleStack {
    /**
     * 单调
     * 左侧比他小的数的位置
     */

    public int[][] generateStack(int[] arr){
        int N = arr.length;
        int[][] ans = new int[N][2];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i< N; i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int rightLess = stack.pop();
                ans[i][1] = rightLess;
                ans[i][0] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int cur = stack.pop();
            ans[cur][0] = cur;
            ans[cur][1] = -1;
        }
        return ans;
    }
}

   