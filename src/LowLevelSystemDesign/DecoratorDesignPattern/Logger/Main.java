package LowLevelSystemDesign.DecoratorDesignPattern.Logger;

// Decorater pattern is often used to add or extend functionality to objects dynamically
public class Main {
    public static void main(String[] args) {
        Logger logger = new LevelLogger(new TimestampLogger(new BasicLogger()), "INFO");
        logger.log("Hello World!");
    }
}
