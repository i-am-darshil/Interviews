package LowLevelSystemDesign.ObservableDesignPattern.AmazonNotifyMe;

public class StockNotifierViaMail extends StockObserver{

    public StockNotifierViaMail(int id, ObservableInterface<Integer> stockObservable) {
        super(id, stockObservable);
    }

    @Override
    public void update() {
        super.update();
        sendMail();
    }

    private void sendMail() {
        System.out.println("Notifying via mail to: " + id + ", current stock count: " + stockObservable.getData());
    }
}
