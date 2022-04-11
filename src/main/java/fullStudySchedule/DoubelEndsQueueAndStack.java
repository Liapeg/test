package fullStudySchedule;

import java.util.Stack;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/9/14 15:53
 */

public class DoubelEndsQueueAndStack {
    public class Node<T>{
        T value;
        Node<T> last;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 双端队列的实现   双向链表
     */

    public class DoubleEndsQueue<T>{
         Node<T> head;
         Node<T> tail;

        public void addFromHead(T val){
            Node<T> cur = new Node<T>(val);
            if(head ==null){
                head = cur;
                tail = cur;
            }else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }

        }

        public void addFromBottom(T val){
            Node<T> cur = new Node<T>(val);
            if(head ==null){
                head = cur;
                tail = cur;

            }else{
                cur.last = tail;
                tail.next = cur;
                tail =cur;
            }

        }

    }


    /**
     * 数组实现
     */
    public static class myQueue {
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private int limit;

        public myQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void add(int val) {
            if(size == limit){
                throw new RuntimeException("duileimanl");
            }
            size--;
            arr[pushi] = val;
            pushi = nextIndex(pushi);
        }

        public int nextIndex(int i){
            return i< limit-1 ? i+1 :0;
        }
    }

    /**
     * 实现时间复杂度是O（1）的获取栈中最小值的方法  可以使用现有的栈结构
     */

    public class mySatck{
        Stack<Integer> data = new Stack<Integer>();//数据栈
        Stack<Integer> min = new Stack<Integer>();//最小值栈

        public mySatck(Stack<Integer> data, Stack<Integer> min) {
            this.data = data;
            this.min = min;
        }

        public void push(int newNum){
            if(this.min.empty()){
                this.min.push(newNum);
            }else if(newNum < this.min.peek()){
                this.min.push(newNum);
            }else {
                int newMin = this.min.peek();
                this.min.push(newMin);
            }
            this.data.push(newNum);
        }

        public int getMin(){
            if(this.min.empty()){
                throw new RuntimeException("");
            }

            return this.min.peek();
        }


    }


    public static void quickSort(int[] arr){
        if(arr ==null || arr.length <2){
            return;
        }

       processQuick(arr,0,arr.length);
    }
    public static void processQuick(int[] arr, int l, int r){

        if(l >= r){
            return;
        }
        swap(arr, (int)(Math.random() * (r-l+1)),r);
        int[] equals = henanguoqi(arr,l,r);
        processQuick(arr,l,equals[0]-1);
        processQuick(arr,equals[1]+1,r);

    }

    public static int[] henanguoqi(int[] arr, int l, int r){
        if(l > r){
            return new int[]{-1,-1};
        }
        if(l == r){
            return new int[]{l,r};
        }

        int less = l-1;
        int more = r;
        int index = l;
        while (index<more){
            if(arr[index] == arr[r]){
                index++;
            }else if(arr[index] > arr[r]){
                swap(arr, index, --more);
            }else {
                swap(arr, index++, ++less);
            }
        }
        swap(arr,more,r);
        return new int[]{less+1,more};

    }

    public static void swap(int[] arr ,int x,int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}

   