package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.VehicleExample;

public class LuxuryVehicle implements Vehicle{
    @Override
    public void drive() {
        System.out.println("Driving luxury vehicle");
    }
}
