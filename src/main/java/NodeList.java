import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/7/29 21:08
 */
public class NodeList {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
    }
    //产生随机链表
    public static Node NodecreateNodeList(int len, int value){
        int size = (int)(Math.random()*(len+5));
        if(size == 0){
            return null;
        }
        Node head = new Node((int)(Math.random()*(value+1)));
        Node pre = head;
        while (size != 0){
            Node cur = new Node((int)(Math.random()*(value+1)));
            pre.next = cur;
            pre = cur;
            size -- ;
        }
        return head;
    }
    //打印链表
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<Integer>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    //单链表 反转

    public static Node revserList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    //双向链表 反转
    public static DoubleNode reserDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head !=null){

//            next = head.next;
//            head.next = pre;
//            head.last = next;
//            pre = head;
//            head = next;

            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
    public static void main(String[] args) {
        //System.out.println(NodecreateNodeList(5,8).value);
        Node a= NodecreateNodeList(5,8);
        System.out.println(getLinkedListOriginOrder(a));
        Node b = revserList(a);
        System.out.println(getLinkedListOriginOrder(b));

    }
}

   