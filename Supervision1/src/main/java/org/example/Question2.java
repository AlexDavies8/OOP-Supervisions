package org.example;
import asd58.suporun.Runner;

public class Question2 {
    public static void Run() {
        // a. Some Primitive Types
        int amount = 5;
        float change = 0.4f;
        String name = "Philip";
        char letter = 'A';
        Runner.LogValue("amount", amount);
        Runner.LogValue("change", amount);
        Runner.LogValue("name", amount);
        Runner.LogValue("letter", amount);

        System.out.println();

        // b. A reference type variable
        // A reference is stored that points to the array's position in memory
        // This is in contrast to primitive types, which are immutable and therefore are copied
        // each time an assignment takes place
        int[] values = {1, 2, 3, 4};
        Runner.LogArray("values", values);
        MutateArray(values);
        Runner.LogArray("values", values);

        System.out.println();

        // d. Instantiating the previously defined Vector2 class as an object
        Vector2 direction = new Vector2(0.80, 0.15);
        Runner.LogValue("direction", direction.x + ", " + direction.y);
        direction.Normalise(); // Calling method on an object
        Runner.LogValue("direction", direction.x + ", " + direction.y);
        double magnitude = direction.Magnitude(); // Calling function on an object
        Runner.LogValue("magnitude", magnitude);
    }

    // b. Function that modifies elements of an array by reference
    static void MutateArray(int[] arr) {
        arr[2] = 5;
    }

    // c. A class to represent a 2D vector
    static class Vector2 {
        public double x;
        public double y;

        public Vector2(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double Magnitude() {
            return Math.sqrt(x * x + y * y);
        }

        public void Normalise() {
            double mag = Magnitude();
            x /= mag;
            y /= mag;
        }
    }
}
