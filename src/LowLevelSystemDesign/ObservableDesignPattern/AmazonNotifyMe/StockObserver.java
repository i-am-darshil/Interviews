package LowLevelSystemDesign.ObservableDesignPattern.AmazonNotifyMe;

public class StockObserver implements ObserverInterface{

    protected final ObservableInterface<Integer> stockObservable;
    protected final int id;

    public StockObserver (int id, ObservableInterface<Integer> stockObservable) {
        this.stockObservable = stockObservable;
        this.id = id;
    }

    @Override
    public void update() {
        System.out.println("Notifying observer: " + id + ", current stock count: " + stockObservable.getData());
    }
}
