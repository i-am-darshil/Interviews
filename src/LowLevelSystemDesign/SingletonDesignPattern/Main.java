package LowLevelSystemDesign.SingletonDesignPattern;

public class Main {
    public static void main(String[] args) {
        EagerSingleton es1 = EagerSingleton.getInstance();
        EagerSingleton es2 = EagerSingleton.getInstance();

        System.out.println(es1 == es2);

        LazySingleton ls1 = LazySingleton.getInstance();
        LazySingleton ls2 = LazySingleton.getInstance();

        System.out.println(ls1 == ls2);

        InitialIzationHolderSingleton is1 = InitialIzationHolderSingleton.getInstance();
        InitialIzationHolderSingleton is2 = InitialIzationHolderSingleton.getInstance();

        System.out.println(is1 == is2);
    }
}
