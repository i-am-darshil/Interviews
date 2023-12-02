package LowLevelSystemDesign.ObservableDesignPattern.AmazonNotifyMe;

public interface ObservableInterface <T> {
    void addObserver(ObserverInterface observer);
    void removeObserver(ObserverInterface observer);
    void notifyObservers();

    void setData(T newData);
    T getData();

}
