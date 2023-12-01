package LowLevelSystemDesign.StrategyDesignPattern.WithStrategyPattern;

public class RegularDrive implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Regular drive");
    }
}
