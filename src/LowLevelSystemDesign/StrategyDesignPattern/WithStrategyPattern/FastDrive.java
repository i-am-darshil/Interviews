package LowLevelSystemDesign.StrategyDesignPattern.WithStrategyPattern;

public class FastDrive implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Fast drive");
    }
}
