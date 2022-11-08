package org.example;

import java.util.EmptyStackException;

public class Question10 {

    public static class StackFullException extends RuntimeException {
        public StackFullException() {
            super();
        }
    }
    public static class StackEmptyException extends RuntimeException {
        public StackEmptyException() {
            super();
        }
    }

    // Note that this stack only takes integers
    // I tried using generics, but it appears Java doesn't allow you to create generic arrays
    // without using reflection to have the user pass the array type in
    public static class StaticStack {
        protected int[] stack;
        private int top;

        public StaticStack(int capacity) {
            stack = new int[capacity];
            top = -1;
        }

        public int Count() {
            return top + 1;
        }

        public int Capacity() {
            return stack.length;
        }

        public void Push(int value) {
            if (top+1 >= Capacity()) throw new StackFullException();
            stack[++top] = value;
        }

        public int Pop() {
            if (top <= -1) throw new StackEmptyException();
            return stack[top--];
        }

        public int Peek() {
            if (top <= -1) throw new StackEmptyException();
            return stack[top];
        }
    }

    // This stack uses StaticStack internally and automatically resizes it if it is full
    public static class DynamicStack extends StaticStack {

        // Define an initial capacity
        public DynamicStack(int capacity) {
            super(capacity);
        }

        // Automatic capacity (starts at 4)
        public DynamicStack() {
            super(4);
        }

        @Override
        public void Push(int value) {
            try {
                super.Push(value);
            } catch (StackFullException e) {
                ResizeStack();
                Push(value); // Try again now that there's room
            }
        }

        private void ResizeStack() {
            int newCapacity = (int)Math.ceil(Capacity()*1.5);
            int[] newStack = new int[newCapacity];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
        }
    }

    public static class LinkedStack {
        StackNode top = null;
        int count = 0;

        public int Count() {
            return count;
        }

        public void Push(int value) {
            top = new StackNode(value, top);
            count++;
        }

        public int Pop() {
            if (count <= 0) throw new StackEmptyException();
            int value = top.value;
            top = top.next;
            count--;
            return value;
        }

        public int Peek() {
            if (count <= 0) throw new StackEmptyException();
            return top.value;
        }

        private static class StackNode {
            public StackNode next;
            public int value;

            public StackNode(int value, StackNode next) {
                this.next = next;
                this.value = value;
            }
        }
    }
}
