package fullStudySchedule;
/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/9/9 20:30
 */

import javax.xml.ws.soap.MTOM;

/**
 * 链表
 */
public class linkedList {
    //单链表
    public static class SimpleNode{
        int value;
        SimpleNode next;

        public SimpleNode(int value) {
            this.value = value;
        }
    }
    //双链表
    public static class DoubleNode{
        int value;
        DoubleNode pre;
        DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }
    /**
     * 1、单链表反转
     */
    static public SimpleNode reverseSimpleNode(SimpleNode head){//头节点
        SimpleNode pre = null;
        SimpleNode next =null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    //随机单链表
    public static SimpleNode generateSimpleNodeList(int len, int value){
        int size = (int)(Math.random()*(len+1));
        if(size == 0){
            return null;
        }
        size --;
        SimpleNode head = new SimpleNode((int)(Math.random() * (value+1)));
        SimpleNode pre = head;
        while (size!=0){
            SimpleNode  cur = new SimpleNode((int)(Math.random()*(value+1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }
    //for test simpleNode
    static public void testSimpleNode(){
        //generateSimpleNodeList(10,34);
        SimpleNode head = generateSimpleNodeList(10,34);
        printLinkedList(head);

        printLinkedList(reverseSimpleNode(head));
        //reverseSimpleNode(head);
    }

    /**
     * 2、双链表反转
     */
    static public DoubleNode reverseDoubleNode(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head !=null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 随机双链表
     */
    public static DoubleNode generateDoubleNodeList(int len,int value){
        int size = (int)(Math.random() *(len+1));
        if(size ==0){
            return null;
        }
        DoubleNode head = new DoubleNode((int)(Math.random()*(value+1)));
        size--;
        DoubleNode pre = head;
        while(size!=0){
            DoubleNode cur = new DoubleNode((int)(Math.random()*(value+1)));
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
            size--;
        }
        return head;
    }
    //for test doubleNode
    public static void testDoubelNode(){
        DoubleNode head = generateDoubleNodeList(12,87);
        printDoubleList(head);
        printDoubleList(reverseDoubleNode(head));
    }

    /**
     * 3、删除链表中指定值
     */
    public static SimpleNode deleteSpecailValue(SimpleNode head , int value){

        //head来到第一个不需要删除的节点
        while (head!=null){
            if(head.value!=value){
                break;
            }else {
                head = head.next;
            }

        }


        SimpleNode pre =head;
        SimpleNode cur = head;
        while(cur !=null){
            if(cur.value ==value){
                pre.next = cur.next;
            }else{
                pre = cur;
            }

            cur = cur.next;
        }





        return pre;
    }

    /**
     * 4、队列实现栈
     */

    /**
     * 5、栈实现队列
     */

    /**
     *6、Master公式  分析递归函数的复杂度 一次递归的时间复杂度+除递归之外的时间复杂度O(1)
     * T(N) = a*T(N/b) + O(N^d) a,b,d 是常数
     *
     * 1) logb^a < d  O(N^d)
     * 2) logb^a > d  O(N^logb^a)
     * 3) logb^a == d  O(N^d * logN)
     */

    /**
     * 7、hash表增删改查 O（1） 有序表 TreeMap O（logN） 由红黑树、avl、sb树 跳表 实现
     */

    /**
     *8、有序表对非内置类型需要指定key的比较方法（对非内置类型记内存地址 内存8字节）
     */




    public static void main(String[] args) {
        for(int i= 0;i<15;i++){
            //testSimpleNode();
            testDoubelNode();
        }

    }
    /**
     * 打印单链表
     */
    public static void printLinkedList(SimpleNode head){
        if(head ==null){
            return;
        }
        /*System.out.print(head.value+" ");
        while (head.next!=null){

            System.out.print(head.next.value+" ");
            head = head.next;
        }*/
        while (head!=null){
            System.out.print(head.value+" ");
            head= head.next;

        }
        System.out.println();
    }
    /**
     * 打印双链表
     */
    public static void printDoubleList(DoubleNode head){
        if(head==null){
            return;
        }
        //System.out.print(head.value+" ");
        while(head!=null){
            System.out.print(head.value+" ");
            head = head.next;
        }

        System.out.println();
    }


}
