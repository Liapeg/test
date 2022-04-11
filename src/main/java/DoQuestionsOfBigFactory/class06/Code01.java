package DoQuestionsOfBigFactory.class06;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/16 15:36
 */
public class Code01 {

    /**
     * 数组中所有数都异或起来的结果，叫做异或和
     * 给定一个数组，返回 子数组最大异或和
     */

    /**
     * 异或和树    。。。。    路径上的值
     * 左和右   往0走或者往1走
     */
    public static class Node{
        Node[] nodes = new Node[2];
    }

    public static class NodeTree{

        Node head = new Node();

        //方法1:将一个树的异或和加进去
        public void add(int num){

            Node cur = head;
            for (int i = 31;i>=0; i--){
                int path = (num>>i)&1;
                cur.nodes[path] = cur.nodes[path] == null ? new Node() : cur.nodes[path];
                cur  = cur.nodes[path];
            }
        }


        //方法2：该数能够合并的最大异或和
        public int xor(int sum){

            Node cur = head;
            int ans = 0;
            for(int i=31;i>=0;i--){
                int path = ((sum>>i)&1);
                //期望的值 0期望是1    1期望是0
                //符号位单独处理
                int wish = i==31? path: (path^1);
                int real = cur.nodes[wish] ==null ? (wish^1): wish;
                cur = cur.nodes[real];
                ans |=((real^path)<<i);
            }
            return ans;
        }

    }

    public static int macXor(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        NodeTree nodeTree = new NodeTree();
        nodeTree.add(0);
        int xor =0;
        for(int j=0;j<arr.length;j++){
            //0~i的异或和
            xor = xor^arr[j];
            ans = Math.max(nodeTree.xor(xor), ans);
            nodeTree.add(xor);
        }

        return ans;
    }

    public static int maxXorSubarray1(int[] arr) {

        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        if(arr ==null || arr.length ==0){
            return 0;
        }
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 20;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubarray1(arr);
            int res = macXor(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}

   