public class Question7 {
    public interface Enemy {
        Enemy Clone();
    }

    public static class Zombie implements Enemy {
        private final String[] _decayStages = {
                "Fresh",
                "Rotting",
                "Bloated",
                "Skeletal"
        };

        private final int _decayStage;

        public Zombie(int decayStage) {
            _decayStage = decayStage;
        }

        @Override
        public Enemy Clone() {
            return new Zombie(_decayStage);
        }

        @Override
        public String toString() {
            return _decayStages[_decayStage] + " Zombie";
        }
    }

    public static class Wolf implements Enemy {
        private final boolean _isAlpha;

        public Wolf(boolean isAlpha) {
            _isAlpha = isAlpha;
        }

        @Override
        public Enemy Clone() {
            return new Wolf(_isAlpha);
        }

        @Override
        public String toString() {
            return _isAlpha ? "Alpha Wolf" : "Wolf";
        }
    }

    public static class EnemyFactory {
        private final Enemy _prototype;

        public EnemyFactory(Enemy prototype) {
            _prototype = prototype;
        }

        public Enemy CreateEnemy() {
            return _prototype.Clone();
        }
    }

    public static void Run() {
        var freshZombieFactory = new EnemyFactory(new Zombie(0));
        var bloatedZombieFactory = new EnemyFactory(new Zombie(2));
        var wolfFactory = new EnemyFactory(new Wolf(false));
        var alphaWolfFactory = new EnemyFactory(new Wolf(true));

        System.out.println(freshZombieFactory.CreateEnemy());
        System.out.println(bloatedZombieFactory.CreateEnemy());
        System.out.println(wolfFactory.CreateEnemy());
        System.out.println(alphaWolfFactory.CreateEnemy());
    }
}
