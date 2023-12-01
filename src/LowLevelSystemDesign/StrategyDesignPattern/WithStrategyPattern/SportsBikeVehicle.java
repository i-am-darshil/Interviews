package LowLevelSystemDesign.StrategyDesignPattern.WithStrategyPattern;

public class SportsBikeVehicle extends Vehicle {

    public SportsBikeVehicle() {
        super(new FastDrive());
    }

}
