package list;

/**
 * @Author: suntong
 * @Date:Created in 19:05 2019/7/25
 * 链表的操作
 */
public class ListOperation {

    public boolean Reverse(Node head) {
        Node p = head.next;
        if (p == null || p.next == null) {
            return false;
        }
        Node q = p.next;
        p.next = null;
        while (p != null) {
            p = q.next;
            q.next = head.next;
            head.next = q;
            q = p;
        }
        return false;
    }
}
