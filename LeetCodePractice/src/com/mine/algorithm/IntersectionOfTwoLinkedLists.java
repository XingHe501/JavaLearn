package com.mine.algorithm;

import com.mine.util.ListNode;

/**
 * Created: 2021/06/04 19:42
 *
 *  https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 */
public class IntersectionOfTwoLinkedLists {
    /**
     * 分别为链表A和链表B设置指针A和指针B，然后开始遍历链表，如果遍历完当前链表，则将指针指向另外一个链表的头部继续遍历，直至两个指针相遇。
     * 最终两个指针分别走过的路径为：
     * 指针A :a+c+b
     * 指针B :b+c+a
     * 明显 a+c+b = b+c+a,因而如果两个链表相交，则指针A和指针B必定在相交结点相遇。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

class IntersectionOfTwoLinkedListsRun {
    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists intersectionOfTwoLinkedLists = new IntersectionOfTwoLinkedLists();
    }
}

