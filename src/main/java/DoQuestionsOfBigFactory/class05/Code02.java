package DoQuestionsOfBigFactory.class05;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/9 17:03
 * 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的树是相等树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 */
public class Code02 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    public static int sameTreeNum(Node head){

        if(head == null){
            return 0;
        }

        return sameTreeNum(head.left) + sameTreeNum(head.right) + (same(head.left, head.right) ? 1:0);
    }

    public static boolean same(Node l,  Node r){
        if(l == null && r == null){
            return true;
        }
        if(l == null ^ r == null){
            return false;
        }

        return (l.value == r.value) && same(l.left, l.right) && same(r.left, r.right) ;
    }





    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameTreeNum(head);
            int ans2 = sameTreeNum(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");

    }

    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }


    // 时间复杂度O(N)
    /*public static int sameNumber2(Node head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }

    public static class Info {
        public int ans;
        public String str;

        public Info(int a, String s) {
            ans = a;
            str = s;
        }
    }

    public static Info process(Node head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info l = process(head.left, hash);
        Info r = process(head.right, hash);
        int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
        String str = hash.hashCode(String.valueOf(head.value) + "," + l.str + r.str);
        return new Info(ans, str);
    }*/
}

   