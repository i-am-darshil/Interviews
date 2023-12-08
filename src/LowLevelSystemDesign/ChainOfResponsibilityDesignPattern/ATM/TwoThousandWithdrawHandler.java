package LowLevelSystemDesign.ChainOfResponsibilityDesignPattern.ATM;

public class TwoThousandWithdrawHandler extends WithdrawHandler{

    private final WithdrawHandlerStrategy handlingStrategy;

    public TwoThousandWithdrawHandler(WithdrawHandler nextWithdrawHandler, int availableNotes, WithdrawHandlerStrategy handlingStrategy) {
        super(nextWithdrawHandler, 2000, availableNotes);
        this.handlingStrategy = handlingStrategy;
    }

    public void handle(int requiredAmount) {
        handlingStrategy.process(this, requiredAmount);
    }

}
