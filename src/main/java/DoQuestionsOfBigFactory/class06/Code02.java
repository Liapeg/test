package DoQuestionsOfBigFactory.class06;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/29 9:03
 */
public class Code02 {
    /**
     *TODO
     * 数组中哪两个数的异或和最大
     *https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
     */
    public static class Node{
        Node[] nexts = new Node[2];
    }

    public static class TreeNode{
        Node head = new Node();

        //将一个数加进去
        public void add(int num){
            Node cur= head;
            for(int i =31;i>=0;i--){
                int path = ((num>>i)&1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }
        //这个数和前面数的异或和的最大值
        public int maxXor(int num){
            Node cur = head;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                //该位置的值
                int path = ((num >> i) & 1);
                //期望的值--符号位
                int best = i == 31 ? path : (path ^ 1);
                //实际的得到的值
                best = cur.nexts[best] == null ? (best ^ 1) : best;
                ans |= ((path ^ best) << i);
                cur = cur.nexts[best];

            }
            return ans;
        }
    }
    public static int findMaximumXOR(int[] arrs){
        if (arrs == null || arrs.length == 0) {
            return 0;
        }
        int anss = Integer.MIN_VALUE;
        TreeNode treeNode = new TreeNode();
        treeNode.add(arrs[0]);
        for (int j = 1; j < arrs.length; j++) {
            anss = Math.max(anss,treeNode.maxXor(arrs[j]));
            treeNode.add(arrs[j]);
        }
        return anss;
    }

    public static void main(String[] args) {
        int[] arr = {3,10,5,25,2,8};
        findMaximumXOR(arr);
    }

}

   