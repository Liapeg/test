package fullStudySchedule;

import com.sun.jmx.snmp.internal.SnmpSubSystem;

import java.util.Stack;

/**
 * TODO
 *
 *
 * @version 2.0
 * @author lianp
 * @date 2021/12/20 14:08
 */
public class MonotonousStack {

    /**
     * 无重复数据的单调栈
     * @param arr
     * @return
     */
    public static int[][] MonotonoueStack(int[] arr){
        int N = arr.length;
        int[][] res = new int[N][2];//第i位的数组【0】表示 arr中第i个数的左边最小的数的位置  【1】表示右边比他小的数的最近的位置

        Stack<Integer> stack = new Stack<Integer>();

        //栈   从栈底到栈顶 依次从小到大压入
        //---只存位置
        for(int i =0;i<N;i++){

            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int  j = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                res[j][0] = left;
                res[j][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int right = stack.pop();
            int left = stack.isEmpty() ? -1:stack.peek();
            res[right][0]=left;
            res[right][1] = -1;

        }

        return res;
    }





    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        // 只存位置！
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < arr.length; i++) { // 当遍历到i位置的数，arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int j = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[j][0] = leftLessIndex;
                res[j][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[j][0] = leftLessIndex;
            res[j][1] = -1;
        }
        return res;
    }
    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {/*
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), MonotonousStack(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");*/
        int[] arrs = {5,4,2,3,8,6};
        int[][] res = MonotonoueStack(arrs);

        int N = res[0].length;
        for(int i =0;i<res.length;i++){
            for (int j=0;j<N;j++){
                System.out.print(res[i][j] + "   ");
            }
            System.out.println();
        }
    }


}

   