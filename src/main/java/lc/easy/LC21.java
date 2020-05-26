package main.java.lc.easy;

import main.java.lc.ListNode;

/**
 * 合并两个有序链表
 *
 */
public class LC21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode root = new ListNode(0);
        ListNode cursor = root;

        while(l1!=null || l2!= null){

            if(l1 != null && l2 != null){
                if(l1.val < l2.val){
                    cursor.next = new ListNode(l1.val);
                    l1 = l1.next;
                }else{
                    cursor.next = new ListNode(l2.val);
                    l2 = l2.next;
                }
            }else if (l1!=null){
                cursor.next = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                cursor.next = new ListNode(l2.val);
                l2 = l2.next;
            }

            cursor = cursor.next;

        }

        return root.next;
    }
}
