package LowLevelSystemDesign.ObservableDesignPattern.Generic;

public class Main {

    // Multiple observers observing an object. When the object changes, notify all observers.
    public static void main(String[] args) {
        ObservableInterface<Integer> observableObj = new ObservableConcreteClass<>();

        ObserverInterface observerObj1 = new ObserverConcreteClass(observableObj, 1);
        ObserverInterface observerObj2 = new ObserverConcreteClass(observableObj, 2);
        ObserverInterface observerObj3 = new ObserverConcreteClass(observableObj, 3);

        observableObj.addObserver(observerObj1);
        observableObj.addObserver(observerObj2);
        observableObj.addObserver(observerObj3);

        observableObj.setData(20);
        observableObj.setData(10);

    }
}
