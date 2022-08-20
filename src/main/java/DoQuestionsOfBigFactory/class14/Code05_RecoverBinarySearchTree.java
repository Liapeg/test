package DoQuestionsOfBigFactory.class14;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/23 21:55
 */
public class Code05_RecoverBinarySearchTree {
    /**
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树
     *
     * 搜索二叉树 ：一个节点左侧都比他大 右侧都比他小
     * 思路： 中序遍历的结果是升序的
     */
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
    public void recoverTree(TreeNode root) {
        int pre = root.val;

        while (root != null){
            if(root.left != null){

            }
        }


        List list = new ArrayList<>();

        if(!list.isEmpty()){

        }
    }
}

   