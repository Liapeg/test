import java.util.HashMap;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/8/12 14:24
 */
public class BinaryTree {
    //双lianbiao
    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //按照前序、中序组合树，返回树的头节点
    //遍历中序数组放入map
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    //俩个数组
    public Node fun(int[] in,int l1, int r1,int[] mi, int l2, int r2){
        if(in == null || mi == null || in.length != mi.length){
            return null;
        }
        if(l1 > r1){
            return null;
        }

        int root_size = l1;
        Node head = new Node(in[root_size]);//头节点
        int left_index = map.get(in[root_size]);//中序中根节点所在位置
        int left_size = left_index - l2;
        //构建左树 前序从 l1+1,l1+left_size, 中序从 l1,left_index-1
        head.left = fun(in ,  l1+1,l1 + left_size ,mi,l1,left_index-1);
        //构建右树 前序 left_size+1,r1 中序 left_index+1,r2
        head.right = fun(in ,  l1 + left_size + 1,r1 ,mi,left_index+1,r2);

        return head;
    }

    /*public Node buildTree(int[] pre, int[] mid){
        for(int i=0;i<pre.length;i++){
            map.put(mid[i], i);

        }

    }*/
}
   