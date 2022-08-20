package leetcodeTop100;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/15 11:00
 */
public class Problem02 {
    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1 == null || l2 == null){
            return new ListNode(0);
        }
        ListNode head = l1;
        //两个list都有数
        ListNode last = null;
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val;
            if(sum >= 10){
                sum%=10;
                if(l1.next == null){
                    l1.next = new ListNode(1);
                }else {
                    l1.next.val += 1;
                }
            }
            last = l1;
            l1.val = sum;
            l1  = l1.next;
            l2 = l2.next;
        }

        //l2空了
        while (l1 != null){
            if(l1.val >=10){
                l1.val %=10;
                if(l1.next != null){
                    l1.next.val +=1;
                }else {
                    l1.next = new ListNode(1);
                }
            }
            l1 = l1.next;

        }
        //l1空了
        while (l2 != null){
            l1 = last;
            l1.next = new ListNode(l2.val);
            last = l1.next;
            l2 = l2.next;
        }

        return head;

    }
}

   