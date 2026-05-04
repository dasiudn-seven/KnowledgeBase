/**
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 

 

 示例 1： 
 
 
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
 

 示例 2： 
 
 
输入：head = [0,1,2], k = 4
输出：[2,0,1]
 

 

 提示： 

 
 链表中节点的数目在范围 [0, 500] 内 
 -100 <= Node.val <= 100 
 0 <= k <= 2 * 10⁹ 
 

 Related Topics 链表 双指针 👍 1190 👎 0

*/

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
        {
            return null;
        }
        if (k == 0) {
            return head;
        }
        ListNode last = new ListNode();
        ListNode p = head;
        int num = 0;
        while(p != null) {
            if (p.next == null) {
                last = p;
            }
            num ++;
            p = p.next;
        }
        k = k % num;
        if (k == 0) {
            return head;
        }
        k = num - k;
        p = head;
        for (int i = 1; i < k; i++) {
            p = p.next;
        }
        ListNode res = p.next;
        p.next = null;
        last.next = head;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
