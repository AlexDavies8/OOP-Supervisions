public class Question7 {
    public static class Person {
        private String _forename;
        private String _surname;

        public Person(String forename, String surname) {
            _forename = forename;
            _surname = surname;
        }

        @Override
        public String toString() {
            return _forename + " " + _surname;
        }
    }

    public static void testOutput() {
        Person p = new Person("Joe", "Bloggs");
        System.out.println("Person details: " + p);
    }
}
