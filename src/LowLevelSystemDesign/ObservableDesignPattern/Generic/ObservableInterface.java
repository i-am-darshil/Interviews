package LowLevelSystemDesign.ObservableDesignPattern.Generic;

public interface ObservableInterface <T> {
    void addObserver(ObserverInterface observer);
    void removeObserver(ObserverInterface observer);
    void notifyObservers();
    void setData(T data);
    T getData();

}
