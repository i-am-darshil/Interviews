package LowLevelSystemDesign.ObservableDesignPattern.AmazonNotifyMe;

import java.util.LinkedList;
import java.util.List;

public class StockObservable implements ObservableInterface<Integer>{

    private final List<ObserverInterface> observerList;
    private Integer stockRemaining = 0;

    public StockObservable() {
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

    @Override
    public void setData(Integer newData) {
        int prevStock = stockRemaining;
        stockRemaining = newData;

        if (prevStock == 0) {
            notifyObservers();
        }
    }

    @Override
    public Integer getData() {
        return stockRemaining;
    }
}
