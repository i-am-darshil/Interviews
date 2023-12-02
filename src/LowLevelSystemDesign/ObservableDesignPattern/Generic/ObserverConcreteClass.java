package LowLevelSystemDesign.ObservableDesignPattern.Generic;

public class ObserverConcreteClass implements ObserverInterface {

    private final ObservableInterface observableObj;
    private final int id;

    public ObserverConcreteClass (ObservableInterface observableObj, int id) {
        this.observableObj = observableObj;
        this.id = id;
    }

    @Override
    public void update() {
        System.out.println("Notified by observar id: " + id + ", data is " + observableObj.getData().toString());
    }
}
