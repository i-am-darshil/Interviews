package LowLevelSystemDesign.SingletonDesignPattern;

// As soon as the class loads, the instance will be created
// Can cause delays in starting up
public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton(){};

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

}
