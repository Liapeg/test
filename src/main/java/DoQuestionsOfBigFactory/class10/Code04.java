package DoQuestionsOfBigFactory.class10;

import DoQuestionsOfBigFactory.class07.Code02;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/20 22:13
 */
public class Code04 {
    /**
     *  https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
     * 将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
     *
     * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     *
     * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     */
    // 提交时不要提交这个类
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    // 提交下面的代码

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node treeToDoublyList(Node head) {
        if (head == null) {
            return null;
        }

        Info info = func(head);
        info.start.left = info.start;
        info.end.right = info.end;
        return info.start;
    }

    public static Info func(Node cur){
        if(cur == null){
            return new Info(null, null);
        }
        Info left = func(cur.left);
        Info right = func(cur.right);

        if(left.end != null){
            left.end.right = cur;
        }
        if(right.start != null){
            right.start.left = cur;
        }
        cur.left = left.end;
        cur.right = right.start;

        return new Info(left.end != null ? left.end : cur , right.start != null ? right.start : cur);
    }
}

   