package DoQuestionsOfBigFactory.class14;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/6/23 21:27
 */
public class Code04_CompleteTreeNodeNumber {

    /**
     * 给定一个棵完全二叉树，
     * 返回这棵树的节点个数，
     * 要求时间复杂度小于O(树的节点数)\
     *
     * 完全二叉树：从左往右是依次填满的
     *
     * 思路：如果
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public int completeTreeNum(Node head){
        if(head == null){
            return 0;
        }

        return fun(head, 1, mostLeftLevel(head, 1));
    }

    // level 当前node位于第几层  size是总层数
    public int fun(Node root, int level, int size){
        //bas-case
        if(level == size){
            return 1;
        }

        //第一种情况 右树是不满的
        if (mostLeftLevel(root.right, level+1) == size) {
            //左树是满的
            return (1 << (size -level)) + fun(root.right, level+1, size);
        }else {
            //level < size
            //右树是满的
            return (1 << (size -level -1)) + fun(root.left, level+1,size);
        }
    }

    /**
     * 一定是完全二叉树
     * 以head为头的数，处在第level层  他的子树有多少层
     */

    public int mostLeftLevel(Node head, int level){
        while (head !=null){
            level++;
            head = head.left;
        }

        return level-1;
    }
}

   