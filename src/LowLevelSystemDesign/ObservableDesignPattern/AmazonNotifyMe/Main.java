package LowLevelSystemDesign.ObservableDesignPattern.AmazonNotifyMe;

public class Main {
    public static void main(String[] args) {
        StockObservable iphoneStockObservable = new StockObservable();

        StockObserver iphoneObserver1 = new StockNotifierViaMail(1, iphoneStockObservable);
        StockObserver iphoneObserver2 = new StockNotifierViaMail(2, iphoneStockObservable);
        StockObserver iphoneObserver3 = new StockNotifierViaMessage(3, iphoneStockObservable);
        StockObserver iphoneObserver4 = new StockNotifierViaMessage(4, iphoneStockObservable);

        iphoneStockObservable.addObserver(iphoneObserver1);
        iphoneStockObservable.addObserver(iphoneObserver2);
        iphoneStockObservable.addObserver(iphoneObserver3);
        iphoneStockObservable.addObserver(iphoneObserver4);

        iphoneStockObservable.setData(10);
        iphoneStockObservable.setData(0);
        iphoneStockObservable.setData(1);



    }
}
