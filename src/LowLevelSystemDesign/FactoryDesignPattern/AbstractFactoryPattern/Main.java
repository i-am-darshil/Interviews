package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class Main {
    public static void main(String[] args) {
        VehicleTypeFactory vehicleTypeFactory = new VehicleTypeFactory();
        CarFactory luxuryCarFactory = vehicleTypeFactory.getCarFactory("luxury");
        CarFactory regularCarFactory = vehicleTypeFactory.getCarFactory("regular");

        Vehicle bmw = luxuryCarFactory.getCar("bmw");
        Vehicle toyota = regularCarFactory.getCar("toyota");

        bmw.drive();
        toyota.drive();

    }
}
