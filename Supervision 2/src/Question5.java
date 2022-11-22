public class Question5 {
    public static class Employee {
        private String _name;

        public String GetName() {
            return _name;
        }

        public void SetName(String name) {
            _name = name;
        }
    }

    public static class Ninja implements INinja {
        public void DoStuff() {
            System.out.println("Stuff has been done!");
        }

        public void Assassinate(String name) {
            System.out.println(name + " has been assassinated");
        }
    }

    public interface INinja {
        public void DoStuff();
        public void Assassinate(String name);
    }

    public static class NinjaEmployee extends Employee implements INinja {
        // If Ninja methods are static, then not required
        // No state, so can be final
        // Since there is no state, this could also be a singleton,
        // which could be done via the static keyword, or by lazy instantiation:
        //  GetNinjaInstance() {
        //      if (_ninjaInstance == null) _ninjaInstance = new Ninja();
        //      return _ninjaInstance;
        //  }
        private final Ninja _ninjaInstance = new Ninja();

        public void DoStuff() {
            _ninjaInstance.DoStuff();
        }
        public void Assassinate(String name) {
            _ninjaInstance.Assassinate(name);
        }
    }
}
