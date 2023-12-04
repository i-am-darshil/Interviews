package LowLevelSystemDesign.FactoryDesignPattern.FactoryPattern.VehicleExample;

public class VehicleFactory {

    public Vehicle getVehicle(String vehicleType) {
        switch (vehicleType) {
            case "luxury":
                return new LuxuryVehicle();
            case "regular":
                return new RegularVehicle();
            default:
                return null;
        }
    }
}
