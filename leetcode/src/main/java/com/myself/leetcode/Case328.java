package com.myself.leetcode;

public class Case328 {

    /**
     * java是引用传递语言，直接这种写法操作的是同一个变量cur
     */
    public ListNode oddEvenList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        boolean flag = true;
        ListNode cur = head;
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode cur1 = h1;
        ListNode cur2 = h2;
        while (null != cur) {
            if (flag) {
                cur1.next = cur;
                cur1 = cur1.next;
            } else {
                cur2.next = cur;
                cur2 = cur2.next;
            }
            flag = !flag;
            cur = cur.next;
        }
        cur1.next = h2.next;
        return h1.next;
    }

    /**
     * 这种写法是直接擦作的结点连接关系
     */
    public ListNode oddEvenList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode second = head.next;
        ListNode h1 = head, h2 = second;
        while (h2 != null && h2.next != null) {
            h1.next = h2.next;
            h1 = h1.next;
            h2.next = h1.next;
            h2 = h2.next;
        }
        h1.next = second;
        return head;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode listNode = new Case328().oddEvenList(node1);
        printList(listNode);

    }

    private static void printList(ListNode listNode) {
        ListNode cur = listNode;
        while (null != cur) {
            System.out.print(cur.val + " -> ");
        }
        System.out.println("");
    }
}
