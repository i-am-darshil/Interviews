package LowLevelSystemDesign.StrategyDesignPattern.WithoutStrategyPattern;

public class SportsBikeVehicle extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Fast drive method");
    }
}
