package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class RegularCarFactory implements CarFactory {
    public Vehicle getCar (String brandName) {
        switch (brandName) {
            case "toyota":
                return new RegularCarToyota();
            case "hyundai":
                return new RegularCarHyundai();
            default:
                return null;
        }
    }
}
