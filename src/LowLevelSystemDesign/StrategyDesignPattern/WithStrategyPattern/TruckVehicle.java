package LowLevelSystemDesign.StrategyDesignPattern.WithStrategyPattern;

public class TruckVehicle extends Vehicle {
    public TruckVehicle() {
        super(new RegularDrive());
    }
}
