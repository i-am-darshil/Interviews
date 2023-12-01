package LowLevelSystemDesign.StrategyDesignPattern.WithoutStrategyPattern;

public class SportsCarVehicle extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Fast drive method");
    }
}
