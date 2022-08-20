package DoQuestionsOfBigFactory.class19;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/28 18:19
 */
public class Code01_LRU {

    /**
     * https://leetcode.com/problems/lru-cache/
     * 请你设计并实现一个满足LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
     * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字
     *
     * 思路： map加双向链表
     * 在map中记录每个node所在的内存地址
     * get方法：每个node都有一个头指针和一个尾指针  get时如果该node是头 那么将下一个node作为头 这个node的next指向null
     * 如果不是头 该node的last的next指向它的next 他的next的last指向他的last 他的last next指向空 返回他的value
     *
     * put：方法
     * 如果list为空 那么他既是头 也是尾  不为空 判断list的长度是否到达capacity 如果到达 删除头 头往下移 这个node变为尾
     *
     */

    public class LRUCache {

        MyCache myCache= null;
        public LRUCache(int capacity) {

            new MyCache<>(capacity);
        }

        public int get(int key) {

            Integer val  = (Integer) myCache.get(key);
            return val == null ? -1 : val;
        }

        public void put(int key, int value) {
            myCache.set(key, value);
        }


        //由list的节点移动来代替cache的移动
        public void moveToTail(){

        }

    }
    public class DoubleNodeLinkedList<V>{
        Node head;
        Node tail;

        public DoubleNodeLinkedList() {
            this.head = null;
            this.tail = null;
        }

        //有三个方法
        //把一个node加到尾巴上
        public void addNode(Node node){
            if(node == null){
                return;
            }else {
                if(head == null){
                    head = node;
                    tail = node;
                }else {
                    tail.next = node;
                    node.last = tail;
                    tail = node;
                }
            }
        }

        public void moveNodeToTail(Node node){

            if(node== null || tail == node){
                return;
            }else {
                if(head == node){
                    head = node.next;
                    head.last = null;



                }else {
                    node.last.next = node.next;
                    node.next.last = node.last;

                }
                //把当前node连到尾巴上
                node.last = tail;
                node.next = null;
                tail.next = node;
                tail = node;
            }
        }

        public Node removeHead(){
            if(head == null){
                return null;
            }
            Node res = head;
                if(head == tail){
                    head = null;
                    tail =null;
                }else {
                    head = res;
                    res.next = null;
                    head.last = null;
                }
            return res;
        }


    }

    public class MyCache<K, V>{
        private Map<K, Node> nodeMap;
        private DoubleNodeLinkedList nodeLinkedList;
        private int cap;

        public MyCache(int cap) {
            this.nodeMap = new HashMap<>();
            this.nodeLinkedList = new DoubleNodeLinkedList<>();
            this.cap = cap;
        }


        //两个方法
        //set
        public void set(K key, V value){
            if(nodeMap.containsKey(key)){
                //更新
                Node node = nodeMap.get(key);
                node.value = value;
                nodeLinkedList.moveNodeToTail(node);
            }else {
                //新增
                Node newNode = new Node(key , value);
                nodeLinkedList.addNode(newNode);
                nodeMap.put(key, newNode);
                if(nodeMap.size() == cap +1){
                    removeMoreNode();
                }
            }

        }
        //get
        public V get(K key){
            if(nodeMap.containsKey(key)){
                Node node = nodeMap.get(key);
                nodeLinkedList.moveNodeToTail(node);
                return (V) node.value;
            }
            return null;
        }
        //大于capacity
        public void removeMoreNode(){

            Node node = nodeLinkedList.removeHead();
            nodeMap.remove(node.key);
        }
    }
    class Node<K, V>{

        private K key;
        private V value;

        private Node last;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

   