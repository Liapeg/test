package leetcodeTop100.Binarytree;

import java.util.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2021/12/29 17:34
 */


public class Calss01 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
    //中序
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if(root == null){
            return result;
        }
        process(root, result);
        return result;
    }

    public void process(TreeNode root, List<Integer> list){
        if(root != null){
            return;
        }

        process(root.left , list);
        list.add(root.val);
        process(root.right, list);
    }

    //https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
    //二叉搜索树II
    //int n    1...n  返回由这n个数组成的不同二叉搜索树      中序遍历是升序的

    /**
     *
     * 在L...R的区间上获取每个数作为节点的左子树和右子树
     * 左子树落在[L,i-1}
     * 右子树落在[i+1,R]
     * i作为根节点，在左右子树列表中各取一个作为左右子树
     */
    public List<TreeNode> generateTrees(int n) {

        if(n==0){
            return new LinkedList<TreeNode>();
        }

        return process1(1, n);
    }

    private List<TreeNode> process1(int L, int R){

        List<TreeNode> rootList = new LinkedList<TreeNode>();
        if(L > R){
            rootList.add(null);
            return rootList;
        }

        for(int i =L;i <= R;i++){
            List<TreeNode> leftNode = process1(L,i-1);

            List<TreeNode> rightNode = process1(i+1, R);


            for(TreeNode left : leftNode){

                for(TreeNode right : rightNode){
                    rootList.add(new TreeNode(i, left, right));
                }
            }
        }

        return rootList;

    }


    //https://leetcode-cn.com/problems/unique-binary-search-trees/
    public int numTrees1(int n) {

        if(n ==0){
            return 0;
        }

        return process2(1,n);
    }

    private int process2(int L, int R){
        int total = 0;

        if(L == R){
           return 1;
        }

        for(int i=L;i<=R; i++){

            int left = process2(L, i-1);
            int right = process2(i+1, R);

            total += (left * right);

        }

        return total;
    }

    public int numTrees(int n) {

        int[][] dp = new int[n+1][n+1];
        if(n ==0){
            return 0;
        }
        dp[1][1] = 1;
        for(int i=2;i<=n;i++){
            for(int j = 2;j<i;j++){
                dp[i][j] = 1;
            }
        }
        for(int i=2;i<=n; i++){
            for(int j=2;j<=n; j++){
                dp[i][j] = (dp[i][j-1] * dp[i+1][j]);
            }
        }

        return dp[n][n];
    }


    //https://leetcode-cn.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        return process3(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean process3(TreeNode head, long L, long R){
        if(head == null){
            return true;
        }
        if(head.val <= L || head.val >= R){
            return false;
        }
        return process3(head.left, L,head.val) && process3(head.right, head.val , R);

    }

    //https://leetcode-cn.com/problems/recover-binary-search-tree/
    public void recoverTree(TreeNode root) {


        process4(root);
    }

    public void process4(TreeNode root){
        if(root == null){
            return;
        }




    }

    //https://leetcode-cn.com/problems/same-tree/

    public boolean isSameTree(TreeNode p, TreeNode q) {

        return process5(p,q);
    }

    private boolean process5(TreeNode first, TreeNode second){
        if(first ==null && second ==null){
            return true;
        }

        if(first ==null || second ==null){
            return false;
        }

        if(first.val != second.val){
            return false;
        }

        return process5(first.left, second.left) && process5(first.right, second.right);
    }


    /**
     * 二叉树的宽度优先遍历   按层遍历    队列
     */


    public void level(TreeNode root){

        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root);

        while (!queue.isEmpty()){

            TreeNode cur = queue.poll();
            System.out.println(cur.val);//弹出即打印
            if(cur.left != null){
                queue.add(cur.left);
            }

            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }


    /**
     * 深度优先遍历
     */
    public void deep(TreeNode root){

        if(root ==  null){
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()){

            TreeNode cur = stack.pop();
            System.out.println(cur.val);

            if(cur.left != null){
                stack.push(cur.left);
            }

            if(cur.right != null){
                stack.push(cur.right);
            }
        }

    }

    //二叉树的序列化和反序列化


    //https://leetcode-cn.com/problems/symmetric-tree/
    public boolean isSymmetric(TreeNode root) {

        if(root == null){
            return true;
        }
        return  process6(root.left, root.right);
    }

    public boolean process6(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }

        if(left == null || right == null){
            return false;
        }

        return (left.val == right.val) && process6(left.left, right.right) && process6(left.right, right.left);
    }


    //https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(root == null){
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int sizeQ = queue.size();
            List<Integer> curList = new ArrayList<Integer>();
            while (sizeQ > 0){
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
                sizeQ--;
            }
            list.add(curList);
        }

        return list;

    }

    //https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean isLeft = true;
        while (!queue.isEmpty()) {
            int sizeQ = queue.size();
            Deque<Integer> curList = new LinkedList<Integer>();
            for(int i=0;i<sizeQ; i++){
                TreeNode cur = queue.poll();
                if(isLeft){
                    curList.addLast(cur.val);
                }else {
                    curList.addFirst(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            list.add(new LinkedList<Integer>(curList));
            isLeft = !isLeft;
        }

        return list;

    }


    //https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        return process7(root);
    }

    public int process7(TreeNode root){
        if(root == null){
            return 0;
        }

        return Math.max(process7(root.left), process7(root.right)) +1;
    }

    //https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || inorder ==null || preorder.length == 0 || inorder.length == 0){
            return new TreeNode();
        }

        for(int i =0; i< inorder.length; i++){
            indexMap.put(inorder[i], i);
        }

        int n = preorder.length;
        return process8(preorder, inorder, 0, n-1, 0, n-1);
    }

    public TreeNode process8(int[] preorder, int[] inorder, int preL, int preR, int inL, int inR){

        //basecase
        if(preL > preR){
            return null;
        }
        //indexMap 中序遍历结果的位置
        int preRoot = preL;
        TreeNode rootNode = new TreeNode(preorder[preRoot]);
        //左子树的长度
        int inRoot = indexMap.get(preorder[preRoot]);
        int leftLen = inRoot - inL;
        //
        rootNode.left = process8(preorder, inorder, preL +1, preL + leftLen, inL, inRoot -1);
        //右树
        rootNode.right = process8(preorder, inorder, preL +leftLen +1, preR, inRoot+1, inR);
        return rootNode;
    }


    //https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    Map<Integer, Integer> indexMap1 = new HashMap<Integer, Integer>();
    int index;
    int[] postorder;
    int[]  inorder;
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null){
           return null;
        }
        if(inorder.length != postorder.length){
            return null;
        }

        this.inorder = inorder;
        this.postorder = postorder;

        int N = inorder.length;
        index = N-1;
        for(int i = 0; i < N ;i++){
            indexMap1.put(inorder[i], i);
        }
        return process9( 0, N-1);
    }

    TreeNode process9(int inL, int inR) {

        if (inL > inR) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[index]);
        int val = indexMap1.get(postorder[index]);
        index--;
        //左树
        root.left = process9(inL, val - 1);
        //右树
        root.right = process9(val + 1, inR);
        return root;
    }

    /*int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }
    public TreeNode helper(int in_left, int in_right) {
        // 如果这里没有节点构造二叉树了，就结束
        if (in_left > in_right) {
            return null;
        }

        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两棵子树
        int index = idx_map.get(root_val);

        // 下标减一
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index - 1);
        return root;
    }*/

    /*private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }*/


}




   