package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.VehicleExample;

public class Main {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle luxuryVehicle = vehicleFactory.getVehicle("luxury");
        Vehicle regularVehicle = vehicleFactory.getVehicle("regular");

        luxuryVehicle.drive();
        regularVehicle.drive();
    }
}
