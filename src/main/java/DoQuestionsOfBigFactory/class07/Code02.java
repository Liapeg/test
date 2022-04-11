package DoQuestionsOfBigFactory.class07;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/31 17:53
 */
public class Code02 {
    /**
     * https://leetcode.com/problems/binary-tree-cameras/
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //x为头节点
    public class Info{
        long uncovered;//x节点没有被覆盖，x为头的树至少需要多少相机
        long coveredNoCamera;//x节点被覆盖，但x节点没有相机，x为头的树至少需要多少相机
        long coveredHasCamera;//x节点被覆盖了，且x上有相机，x为头的树至少需要多少相机

        public Info(long uncovered, long coveredNoCamera, long coveredHasCamera) {
            this.uncovered = uncovered;
            this.coveredNoCamera = coveredNoCamera;
            this.coveredHasCamera = coveredHasCamera;
        }
    }


    public int minCameraCover(TreeNode root) {
        if(root == null){
            return 0;
        }
        Info data = process(root);
        return (int)Math.min(data.uncovered +1, Math.min(data.coveredHasCamera, data.coveredNoCamera));
    }

    /**
     *  潜台词：x的头节点，x下方的节点都被覆盖了
     * 三种情况
     * 1、这个节点被覆盖但无相机 底下节点都被覆盖了
     * 2、被覆盖有相机
     * 3、没被覆盖
     * @param node
     */
    public Info process(TreeNode node){
        if(node == null){
            //空节点默认被覆盖
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }

        Info left = process(node.left);
        Info right = process(node.right);

        //组装自己的信息
        //自己未被覆盖
        long uncovered = left.coveredNoCamera + right.coveredNoCamera;
        //自己被覆盖但无相机
        long coveredNoCamera = Math.min(left.coveredNoCamera + right.coveredHasCamera,
                Math.min(left.coveredHasCamera + right.coveredHasCamera, left.coveredHasCamera+ right.coveredNoCamera));
        //自己被覆盖且有相机
        //最优解 所以左右不会同时被覆盖
        //因为x上有相机 所以左右节点是哪个状态无所谓
        long coveredHasCamera = Math.min(left.uncovered, Math.min(left.coveredNoCamera,left.coveredHasCamera))+
        Math.min(right.uncovered, Math.min(right.coveredNoCamera,right.coveredHasCamera))+1;

        return new Info(uncovered, coveredNoCamera, coveredHasCamera);

    }
}

   