package LowLevelSystemDesign.ObservableDesignPattern.AmazonNotifyMe;

public class StockNotifierViaMessage extends StockObserver{
    public StockNotifierViaMessage(int id, ObservableInterface<Integer> stockObservable) {
        super(id, stockObservable);
    }

    @Override
    public void update() {
        super.update();
        sendMessage();
    }

    private void sendMessage() {
        System.out.println("Notifying via message to: " + id + ", current stock count: " + stockObservable.getData());
    }
}
