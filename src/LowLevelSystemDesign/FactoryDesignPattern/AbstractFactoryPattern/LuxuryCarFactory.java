package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class LuxuryCarFactory implements CarFactory{
    public Vehicle getCar (String brandName) {
        switch (brandName) {
            case "bmw":
                return new LuxuryCarBMW();
            case "tesla":
                return new LuxuryCarTesla();
            default:
                return null;
        }
    }
}
