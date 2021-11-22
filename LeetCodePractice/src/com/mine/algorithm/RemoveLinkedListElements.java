package com.mine.algorithm;

import com.mine.util.ListNode;

/**
 * Created: 2021/06/05 17:17
 *
 * https://leetcode-cn.com/problems/remove-linked-list-elements
 */
public class RemoveLinkedListElements {
    /**
     * 递归
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        dfs(dummy, dummy.next, val);
        return dummy.next;
    }
    void dfs(ListNode prev, ListNode root, int val) {
        if (root == null) return;
        if (root.val == val) prev.next = root.next;
        else prev = root;
        dfs(prev, prev.next, val);
    }

    public ListNode removeElementsByForEach(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (ListNode tmp = dummy.next, prev = dummy; tmp != null; tmp = tmp.next) {
            if (tmp.val == val) prev.next = tmp.next;
            else prev = tmp;
        }
        return dummy.next;
    }
}

class RemoveLinkedListElementsRun{
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        int val = 6;
        RemoveLinkedListElements removeLinkedListElements = new RemoveLinkedListElements();
        System.out.println(removeLinkedListElements.removeElements(head, val));
    }
}