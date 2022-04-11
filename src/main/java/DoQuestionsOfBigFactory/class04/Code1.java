package DoQuestionsOfBigFactory.class04;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/26 14:43
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)
 * 意思是在数组里下标0~3这个范围上，有几个2？答案返回2。
 * 假设给你一个数组arr，
 * 对这个数组的查询非常频繁，都给出来
 * 请返回所有查询的结果
 *
 *
 *
 * 思路：维护一个数字出现位置的map表， 二分找出位置
 */

public class Code1 {

    public static int findNum(int[] arr, int L, int R, int k) {
        int N = arr.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<Integer>());
            }
            map.get(arr[i]).add(i);

        }

        if(!map.containsKey(k)){
            return 0;
        }
        ArrayList<Integer> curList = map.get(k);
        /**
         * 二分查找位置的下标
         */
        int p1 = twoDivide(L, curList);
        int p2 = twoDivide(R+1, curList);

        return p2 - p1 ;
    }

    //找到<f最右侧的位置
    public static int twoDivide(int f, ArrayList<Integer> arrayList) {
        int M = arrayList.size();
        int L = 0;
        int R = M-1;
        int mostLess = -1;
        while (L <= R) {
            int index = L + ((R-L)>>1);
            int mid = arrayList.get(index);
            if (mid < f) {
                mostLess = index;
                L = index +1;
            } else {
                R = index  - 1;
            }
        }

        return mostLess +1;
    }



    public static class QueryBox2 {
        private HashMap<Integer, ArrayList<Integer>> map;

        public QueryBox2(int[] arr) {
            map = new HashMap<Integer, ArrayList<Integer>>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<Integer>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> indexArr = map.get(value);
            // 查询 < L 的下标有几个
            int a = countLess(indexArr, L);
            // 查询 < R+1 的下标有几个
            int b = countLess(indexArr, R + 1);
            return b - a;
        }

        // 在有序数组arr中，用二分的方法数出<limit的数有几个
        // 也就是用二分法，找到<limit的数中最右的位置
        private static int countLess(ArrayList<Integer> arr, int limit) {
            int L = 0;
            int R = arr.size() - 1;
            int mostRight = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (arr.get(mid) < limit) {
                    mostRight = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return mostRight + 1;
        }

    }





    //test
    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 100;
        int queryTimes = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                int p1 = findNum(arr, L, R,v);
                int p2 = box2.query(L, R, v);
                if ( p1!= p2) {
                    System.out.println("----"+p1);
                    System.out.println("---"+p2);
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");

    }


    /*public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(14);
        list.add(33);

        list.add(35);
        list.add(36);list.add(56);list.add(88);
        list.add(89);
        list.add(144);



        System.out.println(twoDivide(88,list));
        System.out.println(QueryBox2.countLess(list,88));
    }*/
}

   