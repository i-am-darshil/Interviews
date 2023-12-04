package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class RegularCarToyota implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Driving Toyota regular car");
    }
}