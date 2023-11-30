package LowLevelSystemDesign.SOLID;

import java.util.ArrayList;
import java.util.List;

// Your code should be functional if you swap a object with anouther child obj or parent object.
// Try to keep interface as generic as possible
public class LiskovSubstitution {

    interface Vehicle {
        public void accelerate();

        public String getEngine();
    }

    public static class MotorBike implements Vehicle {

        @Override
        public void accelerate() {}

        @Override
        public String getEngine() {
            return "BigEngine";
        }
    }

    public static class CycleBike implements Vehicle {

        @Override
        public void accelerate() {}

        @Override
        public String getEngine() {
            return null;
        }
    }

    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new MotorBike());
        vehicles.add(new CycleBike());


        for(Vehicle v: vehicles) {
            // This is will give error if we replace a MotorBike with CycleBike
            System.out.println(v.getEngine().toLowerCase());
        }

    }

    // Instead the following can be done
    interface CorrectVehilce {
        public void accelerate();

    }

    interface EngineVehcle extends CorrectVehilce {
        public void accelerate();
        public String getEngine();
    }

    interface NonEngineVehcle extends CorrectVehilce {
        public void accelerate();
    }
}
