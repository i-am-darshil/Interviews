package LowLevelSystemDesign.ObservableDesignPattern.Generic;

import java.util.LinkedList;
import java.util.List;

public class ObservableConcreteClass<T> implements ObservableInterface<T> {
    List<ObserverInterface> observerList;

    private T data;

    public ObservableConcreteClass () {
        observerList = new LinkedList<>();
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observer: observerList) {
            observer.update();
        }
    }

    public T getData() {
        return data;
    }

    @Override
    public void setData(T newData) {
        data = newData;
        notifyObservers();
    }
}
