package DoQuestionsOfBigFactory.class18;

import java.util.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/23 11:27
 */
public class Code04_TopKSumCrossTwoArrays {

    /**
     * https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
     */

    /**
     * 给定两个有序数组arr1和arr2，再给定一个整数k，返回来自arr1和arr2的两个数相加和最大的前k个，两个数必须分别来自两个数组
     * 按照降序输出
     *
     * 思路：大根堆
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            arr2[i] = sc.nextInt();
        }
        int[] topK = kth(arr1, arr2, K);
        for (int i = 0; i < K; i++) {
            System.out.print(topK[i] + " ");
        }
        System.out.println();
        sc.close();
    }

    public static int[] kth(int[] arr1, int[] arr2, int k){
        if (arr1 == null || arr2 == null || k < 1) {
            return null;
        }
        int N = arr1.length;
        int M = arr2.length;
        int topK = Math.min(k, N * M);
        int[] result = new int[topK];
        PriorityQueue<Info> priorityQueue = new PriorityQueue<>(new compareSum());
        Set<Long> set = new HashSet<>();

        int index1= N-1;
        int index2= M-1;
        priorityQueue.add(new Info(index1, index2, arr1[index1]  + arr2[index2]));
        set.add(total(index1,index2, M));
        int resultIndex = 0;
        while (resultIndex != topK){
            Info info = priorityQueue.poll();
            result[resultIndex++] = info.sum;
            index1 = info.num1;
            index2 = info.num2;
            set.remove(total(index1, index2, M));
            //数组1的前一个数跟数组2的当前数 并且没算过
            if(index1-1 >= 0 && !set.contains(total(index1-1, index2, M))){
                priorityQueue.add(new Info(index1-1, index2, arr1[index1-1]+ arr2[index2]));
                set.add(total(index1-1, index2, M));
            }
            //数组1的当前数跟数组2的前一个数
            if(index2-1 >= 0 && !set.contains(total(index1, index2-1, M))){
                priorityQueue.add(new Info(index1, index2-1, arr1[index1]+ arr2[index2-1]));
                set.add(total(index1, index2-1, M));
            }
        }


        return result;
    }


    private static long total(int x1, int x2, int M){
        return (long)x1*(long)M + (long)x2;
    }


    static class Info{
        private int num1;
        private int num2;
        private int sum;

        public Info(int num1, int num2, int sum) {
            this.num1 = num1;
            this.num2 = num2;
            this.sum = sum;
        }
    }


    static class compareSum implements Comparator<Info>
    {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.sum - o1.sum;
        }
    }
}

   