package fullStudySchedule.BinaryTree;
/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/11/22 17:44
 */
public class test01 {

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
         this.left = left;
         this.right = right;
      }
  }

    //47 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。

    public static void main1(){

    }

    public static TreeNode process(TreeNode root){
        if(root == null){
            return null;
        }
        root.left = process(root.left);
        root.right = process(root.right);

        if(root.val==0 && root.left==null && root.right==null){
            return null;
        }
        return root;

    }
}

   