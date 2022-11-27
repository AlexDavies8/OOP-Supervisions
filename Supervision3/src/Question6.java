public class Question6 {
    // Sort of cheating for singletons, but seems like a surprisingly common implementation
// By just making everything static, we are effectively creating a single,
// globally shared object
    public static class SingletonSimple {
        private static int _counter;

        public static void Increment() {
            _counter++;
        }

        public static int GetCount() {
            return _counter;
        }
    }

    // Lazy instantiation
    public static class LazySingleton {
        private static LazySingleton _instance;

        public int Counter;

        private LazySingleton(int counter) {
            Counter = counter;
        }

        private static LazySingleton GetInstance() {
            if (_instance == null) {
                _instance = new LazySingleton(0);
            }
            return _instance;
        }

        public static void Increment() {
            GetInstance().Counter++;
        }

        public static int GetCount() {
            return GetInstance().Counter;
        }
    }
}
