package DoQuestionsOfBigFactory.class05;

import java.util.Stack;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/2 14:01
 */
public class Code1 {

    /**
     *https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
     */
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode bstFromPreorder(int[] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[] help = new int[arr.length];
        for (int j=0;j< arr.length ;j++) {
            help[j] = -1;
        }
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                help[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process(arr, 0, arr.length - 1, help);
    }

    public TreeNode process(int[] arr, int L, int R, int[] help){
        //base-case
        if(L > R){
            return null;
        }

        TreeNode head = new TreeNode(arr[L]);
        //-1是等于R+1的原因：对于左节点 R+1 -1后是右边界；对于右节点R+1 左边界大于有边界所以返回null
        int right = help[L] == -1 ? R + 1 : help[L];
        head.left = process(arr, L+1, right -1, help);
        head.right = process(arr, right, R, help);
        return head;
    }


}

   