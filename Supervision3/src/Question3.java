import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Question3 {
    public static class Car {
        private String manufacturer;
        private int age;

        public Car(String manufacturer, int age) {
            this.manufacturer = manufacturer;
            this.age = age;
        }
        
        public String GetManufacturer() {
            return manufacturer;
        }
        public int GetAge() {
            return age;
        }
    }
    
    public static void Run() {
        var cars = new Car[]{
                new Car("BMW", 5),
                new Car("Audi", 6),
                new Car("BMW", 2),
                new Car("Ford", 9),
                new Car("Ford", 3),
        };
        
        var comparator = Comparator.comparing(Car::GetManufacturer).thenComparing(Car::GetAge);
        
        var sorted = Arrays.stream(cars).sorted(comparator).collect(Collectors.toList());
        for (var car : sorted) {
            System.out.printf("%s %d%n", car.GetManufacturer(), car.GetAge());
        }
    }
}