package LowLevelSystemDesign.ChainOfResponsibilityDesignPattern.ATM;

public class OneHundredWithdrawHandler extends WithdrawHandler{

    private final WithdrawHandlerStrategy handlingStrategy;

    public OneHundredWithdrawHandler(WithdrawHandler nextWithdrawHandler, int availableNotes, WithdrawHandlerStrategy handlingStrategy) {
        super(nextWithdrawHandler, 100, availableNotes);
        this.handlingStrategy = handlingStrategy;
    }

    public void handle(int requiredAmount) {
        handlingStrategy.process(this, requiredAmount);
    }
}
