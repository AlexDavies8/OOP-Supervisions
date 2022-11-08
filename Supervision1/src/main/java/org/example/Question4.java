package org.example;

public class Question4 {
    public static int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static int[] cumsum(int[] a) {
        int[] result = new int[a.length];
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            result[i] = sum;
        }
        return result;
    }

    public static int[] positives(int[] a) {
        int length = 0;
        int[] positives = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) positives[length++] = a[i];
        }
        int[] trimmed = new int[length];
        for (int i = 0; i < length; i++) {
            trimmed[i] = positives[i];
        }
        return trimmed;
    }

    // Alternative solution for positives
    public static int[] positivesAlt(int[] a) {
        int length = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) length++;
        }
        int[] positives = new int[length];
        for (int i = 0, j = 0; i < a.length; i++) {
            if (a[i] > 0) positives[j++] = a[i];
        }
        return positives;
    }
}
