package LowLevelSystemDesign.SingletonDesignPattern;

// Java class loading happens only on-demand, dynamically.
// It doesn't load all the class files in your classpath at the startup. Rather, only when one's referred.
public class InitialIzationHolderSingleton {
    private InitialIzationHolderSingleton(){}

    private static class InitialIzationHolderSingletonHolder {
        static InitialIzationHolderSingleton INSTANCE = new InitialIzationHolderSingleton();
    }

    public static InitialIzationHolderSingleton getInstance() {
        return InitialIzationHolderSingletonHolder.INSTANCE;
    }
}
