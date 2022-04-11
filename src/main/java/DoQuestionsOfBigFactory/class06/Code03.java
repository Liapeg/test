package DoQuestionsOfBigFactory.class06;

import javax.swing.undo.CannotUndoException;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/29 10:32
 */
public class Code03 {
    /**
     * https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
     *
     * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
     * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
     * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
     */
    public static class Node{
        int minVal;
        Node[] nexts;

        public Node() {
            this.minVal = Integer.MAX_VALUE;
            this.nexts = new Node[2];
        }
    }

    public static class NodeTree{
        Node head = new Node();

        //新增方法 同时设置或更新最小值
        public void add(int num){
            Node cur = head;
            head.minVal = Math.min(num, head.minVal);
            for(int i=30;i>=0;i--){
                //第i位是0还是1
                int path = ((num>>i)&1);
                cur.nexts[path] = cur.nexts[path] == null ?  new Node(): cur.nexts[path];
                cur = cur.nexts[path];
                cur.minVal = Math.min(num, cur.minVal);
            }
        }
        //最大异或和方法
        public int maxXor(int num, int min) {
            if(head.minVal > min){
                return -1;
            }
            Node cur = head;
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = path ^ 1;
                best = (cur.nexts[best] != null && cur.nexts[best].minVal <= min) ? best : (best ^ 1);
                ans|=(best^path)<<i;
                cur = cur.nexts[best];
            }
            return ans;
        }

    }

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        NodeTree nodeTree = new NodeTree();
        for(int j =0;j< nums.length;j++){
            nodeTree.add(nums[j]);
        }

        int[] answer = new int[queries.length];
        for(int j = 0;j< queries.length;j++){
            answer[j] = nodeTree.maxXor(queries[j][0], queries[j][1]);

        }
        return answer;
    }


    public static void main(String[] args) {

        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};
        maximizeXor(nums, queries);

    }

}

   