package LowLevelSystemDesign.StrategyDesignPattern.WithoutStrategyPattern;

public class Main {
    // Child class have duplicate methods. Redundant code
    public static void main(String[] args) {
        Vehicle tv = new TruckVehicle();
        tv.drive();

        Vehicle scv = new SportsCarVehicle();
        scv.drive();
    }
}
