public class Question4 {
    public static class Entity {
        public void PrintName() {
            System.out.println("Entity");
        }
    }

    public static class Person extends Entity {
        public String Name;
        @Override
        public void PrintName() {
            System.out.println(Name);
        }
    }

    public static void Run() {
        Person person = new Person();
        person.Name = "Tom";
        person.PrintName(); // "Tom"
        Entity castEntity = (Entity)person;
        castEntity.PrintName(); // "Tom"
    }
}
