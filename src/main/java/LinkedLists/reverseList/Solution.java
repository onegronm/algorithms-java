package LinkedLists.reverseList;

class Solution {

    public ListNode reverseList(ListNode head) {
        /* iterative approach */
        /*
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            ListNode nextTemp = curr.next;
            curr.next = prev; // make curr's next to be prev
            prev = curr; // move prev to the current spot for next loop
            curr = nextTemp; // move curr forward for next loop
        }
        return prev;
        // time = O(n)
        // space = O(1)
         */

        /* recursive approach */
        if(head == null || head.next == null) return head; // first go as far as possible then go back up (scan list in reverse order0
        ListNode p = reverseList(head.next);
        head.next.next = head; // as you pop from the call stack, update pointer to prev
        head.next = null;
        return p; // last node is "trickled up"
        // time = O(n)
        // space = O(n) where n is the maximum depth of the call stack
    }

    public static void main(String[] args){
        ListNode node = new ListNode(1);
        ListNode head = node;
        node = node.next = new ListNode(2);
        node = node.next = new ListNode(3);
        node = node.next = new ListNode(4);
        node = node.next = new ListNode(5);

        Solution s = new Solution();
        head = s.reverseList(head);
    }
}
