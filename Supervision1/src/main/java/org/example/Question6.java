package org.example;

public class Question6 {
    public static int[] vectorAdd(int x, int y, int dx, int dy) {
        return new int[]{x + dx, y + dy};
    }

    public static void main(String[] args) {
        int a=0;
        int b=2;
        int[] vec = vectorAdd(a,b,1,1);
        a = vec[0];
        b = vec[1];
        System.out.println(a+" "+b);
    }
}
