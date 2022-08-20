package DoQuestionsOfBigFactory.class16;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/15 8:39
 */
public class Code05_JosephusProblem {
    /**
     * 约瑟夫环问题
     * 给定一个链表头节点head，和一个正数m
     * 从头开始，每次数到m就杀死当前节点
     * 然后被杀节点的下一个节点从1开始重新数，
     * 周而复始直到只剩一个节点，返回最后的节点
     * Leetcode :
     * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
     */

    /**
     * 有一个f（x）函数 可以根据这个点 找出删之前他是哪个点
     * Y = X%m
     */

    //
    public int lastRemaining(int n, int m) {
        return getAlive(n,m) -1;
    }

    public int getAlive(int n ,int m){
        if(n==1){
            return 1;
        }
        return (getAlive(n-1,m) +m-1)%n +1;
    }
}

   