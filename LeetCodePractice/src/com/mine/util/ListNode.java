package com.mine.util;

/**
 * Created: 2021/06/05 17:19
 * <p>
 * FileName: ListNode
 * <p>
 * Doc:
 *  链表 单向链表
 * <p>
 * @author Admin
 * @version jdk
 */
// Definition for singly-linked list.
public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}