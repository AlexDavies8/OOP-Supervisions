package org.example;
import asd58.suporun.Runner;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Runner.Run("Question 2", Question2::Run);
        Runner.Run("Question 4", () -> {
            int[] values = IntStream.rangeClosed(-3, 7).toArray();
            Runner.LogArray("values", values);
            Runner.LogValue("sum", Question4.sum(values));
            Runner.LogArray("cumsum", Question4.cumsum(values));
            Runner.LogArray("positives", Question4.positives(values));
            Runner.LogArray("positivesAlt", Question4.positivesAlt(values));
        });
        Runner.Run("Question 5", () -> {
            var matrix = Question5.CreateMatrix(3, 4);
            for (int i = 0; i < matrix.length; i++) {
                Runner.LogArray("Row " + i, matrix[i]);
            }
        });
        Runner.Run("Question 6", () -> {
            Question6.main(args);
        });
        Runner.Run("Question 7", () -> {
            var list = new Question78.LinkedList();
            Runner.LogValue("list", list);
            for (int i = 1; i <= 10; i++) {
                list.Append(i);
            }
            Runner.LogValue("appended 1-10", list);
            list.Insert(1, 5);
            Runner.LogValue("inserted 1 at index 5", list);
            Runner.LogValue("list[3]", list.Get(3));
            list.Remove(4);
            Runner.LogValue("removed 4", list);
            list.RemoveAll(1);
            Runner.LogValue("removed all 1s", list);
            list.RemoveAt(3);
            Runner.LogValue("removed at index 3", list);
            Runner.LogValue("list.Head()", list.Head());
            Runner.LogValue("list.Length()", list.Length());
        });
        Runner.Run("Question 8", () -> {
            var list = new Question78.LinkedList();
            var tail = new Question78.LinkedList.ListNode(5);
            list.ConsNode(tail);
            list.Cons(4);
            list.Cons(3);
            list.Cons(2);
            Runner.LogValue("list.HasCycles()", list.HasCycles());
            var head = new Question78.LinkedList.ListNode(1);
            list.ConsNode(head);
            tail.tail = head;
            Runner.LogValue("list.HasCycles()", list.HasCycles());
        });
        Runner.Run("Question 10 | Array Implementation", () -> {
            var staticStack = new Question10.StaticStack(8);
            staticStack.Push(4);
            staticStack.Push(9);
            Runner.LogValue("capacity", staticStack.Capacity());
            Runner.LogValue("count", staticStack.Count());
            Runner.LogValue("peek", staticStack.Peek());
            Runner.LogValue("pop", staticStack.Pop());
            Runner.LogValue("pop", staticStack.Pop());
            try {
                Runner.LogValue("pop", staticStack.Pop());
            } catch (Question10.StackEmptyException e) {
                System.out.println(e);
            }
            System.out.println("Pushing 100 values to the stack...");
            try {
                for (int i = 0; i < 100; i++) {
                    staticStack.Push(i);
                }
            } catch (Question10.StackFullException e) {
                System.out.println(e);
            }
            Runner.LogValue("count", staticStack.Count());
            Runner.LogValue("pop", staticStack.Pop());
        });
        Runner.Run("Question 10 | Dynamic Array Implementation", () -> {
            var staticStack = new Question10.DynamicStack();
            staticStack.Push(4);
            staticStack.Push(9);
            Runner.LogValue("capacity", staticStack.Capacity());
            Runner.LogValue("count", staticStack.Count());
            Runner.LogValue("peek", staticStack.Peek());
            Runner.LogValue("pop", staticStack.Pop());
            Runner.LogValue("pop", staticStack.Pop());
            try {
                Runner.LogValue("pop", staticStack.Pop());
            } catch (Question10.StackEmptyException e) {
                System.out.println(e);
            }
            System.out.println("Pushing 100 values to the stack...");
            for (int i = 0; i < 100; i++) {
                staticStack.Push(i);
            }
            Runner.LogValue("count", staticStack.Count());
            Runner.LogValue("capacity", staticStack.Capacity());
            Runner.LogValue("pop", staticStack.Pop());
        });
        Runner.Run("Question 10 | Linked List Implementation", () -> {
            var staticStack = new Question10.LinkedStack();
            staticStack.Push(4);
            staticStack.Push(9);
            Runner.LogValue("count", staticStack.Count());
            Runner.LogValue("peek", staticStack.Peek());
            Runner.LogValue("pop", staticStack.Pop());
            Runner.LogValue("pop", staticStack.Pop());
            try {
                Runner.LogValue("pop", staticStack.Pop());
            } catch (Question10.StackEmptyException e) {
                System.out.println(e);
            }
            System.out.println("Pushing 100 values to the stack...");
            for (int i = 0; i < 100; i++) {
                staticStack.Push(i);
            }
            Runner.LogValue("count", staticStack.Count());
            Runner.LogValue("pop", staticStack.Pop());
        });
    }
}