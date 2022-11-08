package org.example;

import java.util.HashSet;

public class Question78 {
    public static class LinkedList {
        private ListNode head;
        private int length;

        public int Length() {
            return length;
        }

        public int Head() {
            return head.value;
        }

        // Only so that I can purposefully create a cycle in Q8
        public void ConsNode(ListNode node) {
            node.tail = head;
            head = node;
            length++;
        }

        public void Cons(int value) {
            head = new ListNode(value, head);
            length++;
        }

        public void Append(int value) {
            if (head == null) {
                head = new ListNode(value);
            } else {
                ListNode tail = head;
                while (tail.tail != null) tail = tail.tail;
                tail.tail = new ListNode(value);
            }
            length++;
        }

        public void Insert(int value, int index) {
            if (index == 0) {
                head = new ListNode(value, head);
            } else {
                ListNode curr = head;
                for (int i = 0; i < index - 1; i++) {
                    curr = curr.tail;
                }
                ListNode temp = curr.tail;
                curr.tail = new ListNode(value, temp);
            }
            length++;
        }

        public void RemoveAt(int index) {
            if (index == 0) {
                head = head.tail;
            } else {
                ListNode curr = head;
                for (int i = 0; i < index - 1; i++) {
                    curr = curr.tail;
                }
                curr.tail =  curr.tail.tail;
            }
            length--;
        }

        public void Remove(int value) {
            if (head != null && head.value == value) {
                head = head.tail;
                length--;
                return;
            }
            ListNode curr = head;
            while (curr != null && curr.tail != null) {
                if (curr.tail.value == value) {
                    curr.tail = curr.tail.tail;
                    length--;
                    return;
                } else {
                    curr = curr.tail;
                }
            }
        }

        public void RemoveAll(int value) {
            while (head != null && head.value == value) {
                head = head.tail;
                length--;
            }
            ListNode curr = head;
            while (curr != null && curr.tail != null) {
                if (curr.tail.value == value) {
                    curr.tail = curr.tail.tail;
                    length--;
                } else {
                    curr = curr.tail;
                }
            }
        }

        public int Get(int index) {
            ListNode curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.tail;
            }
            return curr.value;
        }

        // Question 8
        public boolean HasCycles() {
            HashSet<ListNode> seen = new HashSet<ListNode>();
            ListNode curr = head;
            while (curr != null) {
                if (seen.contains(curr)) return true;
                seen.add(curr);
                curr = curr.tail;
            }
            return false;
        }

        @Override
        public String toString() {
            if (head == null) return "[]";
            String[] values = new String[length];
            ListNode curr = head;
            for (int i = 0; i < length - 1; i++) {
                values[i] = String.valueOf(curr.value);
                curr = curr.tail;
            }
            values[length-1] = String.valueOf(curr.value);
            return "[" + String.join(", ", values) + "]";
        }

        public static class ListNode {
            public ListNode tail;
            public int value;

            public ListNode(int value) {
                this.value = value;
            }

            public ListNode(int value, ListNode tail) {
                this.value = value;
                this.tail = tail;
            }
        }
    }
}
