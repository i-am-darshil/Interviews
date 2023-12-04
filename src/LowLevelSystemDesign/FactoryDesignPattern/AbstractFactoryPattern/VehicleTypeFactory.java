package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class VehicleTypeFactory {
    static CarFactory getCarFactory(String vehicleType) {
        switch (vehicleType) {
            case "luxury":
                return new LuxuryCarFactory();
            case "regular":
                return new RegularCarFactory();
            default:
                return null;
        }
    }
}
