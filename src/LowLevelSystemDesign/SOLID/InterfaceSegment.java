package LowLevelSystemDesign.SOLID;

// Interfaces should be such that no unnecessary functions should be part of
public class InterfaceSegment {

    public class IncorrectBike {
        public void turnOnEngine() {}

        public void accelerate() {}
    }

    public class IncorrectMotorBike extends IncorrectBike {
        public void turnOnEngine() {}

        public void accelerate() {}
    }

    public class IncorrectCycleBike extends IncorrectBike {
        public void turnOnEngine() {
            throw new AssertionError("Cycle does not have engine");
        }

        public void accelerate() {}
    }

    public interface Bike {
        public void accelerate();
    }

    public class MotorBike implements Bike {
        public void turnOnEngine() {}

        public void accelerate() {}
    }

    public class CycleBike implements Bike {

        public void accelerate() {}
    }


}
