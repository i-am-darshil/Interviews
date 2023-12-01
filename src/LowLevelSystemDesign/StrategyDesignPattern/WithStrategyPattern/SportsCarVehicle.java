package LowLevelSystemDesign.StrategyDesignPattern.WithStrategyPattern;

public class SportsCarVehicle extends Vehicle {
    public SportsCarVehicle() {
        super(new FastDrive());
    }

}
