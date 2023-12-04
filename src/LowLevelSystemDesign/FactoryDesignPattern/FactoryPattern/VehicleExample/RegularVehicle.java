package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.VehicleExample;

public class RegularVehicle implements Vehicle{
    @Override
    public void drive() {
        System.out.println("Driving regular vehicle");
    }
}
