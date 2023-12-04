package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class LuxuryCarTesla implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Driving Tesla luxury car");
    }
}