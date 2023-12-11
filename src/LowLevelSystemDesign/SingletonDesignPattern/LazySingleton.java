package LowLevelSystemDesign.SingletonDesignPattern;

// Multithreading should be considered in this case
// Instance gets created whenever required
public class LazySingleton {
    private static volatile LazySingleton INSTANCE;
    private LazySingleton(){}

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
