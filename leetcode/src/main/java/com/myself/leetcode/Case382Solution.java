package com.myself.leetcode;

import java.util.Random;

/**
 * @author hemaoling
 */
public class Case382Solution {
    private ListNode head;

    public Case382Solution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        ListNode cur = this.head;
        Random random = new Random();
        int ret = 0, i = 0;
        while (cur != null) {
            //[0,i]内随机数为0的概率是1/i
            if (random.nextInt(++i) == 0) {
                ret = cur.val;
            }
            cur = cur.next;
        }
        return ret;
    }
}
