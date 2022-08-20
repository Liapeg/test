package DoQuestionsOfBigFactory.class19;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/3 17:44
 */
public class Code04_SmallestRangeCoveringElementsfromKLists {

    /**
     * 你有k个 非递减排列 的整数列表。找到一个 最小 区间，使得k个列表中的每个列表至少有一个数包含在其中。
     * 我们定义如果b-a < d-c或者在b-a == d-c时a < c，则区间 [a,b] 比 [c,d] 小。
     * 链接：https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        TreeSet<Node> treeSet = new TreeSet<>(new NodeCompare());
        //将每组的第一个数放入有序表
        for(int i =0; i< N;i++){
            treeSet.add(new Node(nums.get(i).get(0), i, 0));
        }

        //判断是否第一次形成区间
        boolean set = false;
        int a = 0;
        int b = 0;
        //当有一个数组的数耗尽时 停止
        while (treeSet.size() == N){
            Node min = treeSet.first();
            Node max = treeSet.last();
            if(!set || max.value - min.value < b-a){
                //更新区间
                set = true;
                a = min.value;
                b = max.value;
            }
            min = treeSet.pollFirst();
            int from = min.fromArr;
            int next = min.index+1;
            if(nums.get(from).size() > next){
                treeSet.add(new Node(nums.get(from).get(next), from, next));
            }
        }
        return new int[]{a, b};
    }

    //大的在上面
    public class NodeCompare implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.fromArr - o2.fromArr;
        }
    }

    class Node{
        //数字
        public int value;
        //来自哪个数组
        public int fromArr;
        //第几个数
        public int index;

        public Node(int value, int fromArr, int index) {
            this.value = value;
            this.fromArr = fromArr;
            this.index = index;
        }
    }
}

   