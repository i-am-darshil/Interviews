package LowLevelSystemDesign.ChainOfResponsibilityDesignPattern.ATM;

public class FiveHundredWithdrawHandler extends WithdrawHandler{

    private final WithdrawHandlerStrategy handlingStrategy;

    public FiveHundredWithdrawHandler(WithdrawHandler nextWithdrawHandler, int availableNotes, WithdrawHandlerStrategy handlingStrategy) {
        super(nextWithdrawHandler, 500, availableNotes);
        this.handlingStrategy = handlingStrategy;
    }

    public void handle(int requiredAmount) {
        handlingStrategy.process(this, requiredAmount);
    }
}
