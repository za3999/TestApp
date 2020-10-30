package com.test.lib.double_pointer;

import com.test.lib.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode temp = new ListNode(1);
        ListNode head = temp;
        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        ListNode result = removeNthFromEnd(head, 2);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            stringBuilder.append(result.val);
            result = result.next;
            if (result != null) {
                stringBuilder.append("->");
            } else {
                break;
            }
        }
        System.out.println("result:" + stringBuilder.toString());
    }
}
