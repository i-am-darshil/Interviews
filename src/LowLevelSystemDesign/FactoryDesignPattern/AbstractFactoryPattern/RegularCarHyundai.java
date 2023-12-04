package LowLevelSystemDesign.FactoryDesignPattern.AbstractFactoryPattern;

public class RegularCarHyundai implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Driving Hyundai regular car");
    }
}