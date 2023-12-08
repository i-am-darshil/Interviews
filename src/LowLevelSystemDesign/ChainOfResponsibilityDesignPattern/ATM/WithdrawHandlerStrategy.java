package LowLevelSystemDesign.ChainOfResponsibilityDesignPattern.ATM;

public class WithdrawHandlerStrategy {

    public void process(WithdrawHandler handler, int requiredAmount) {
        int denomination = handler.getDenomination();
        int availableNotes = handler.getAvailableNotes();

        int requiredNotes = requiredAmount / denomination;

        if (availableNotes >= requiredNotes) {
            handler.giveOut(requiredNotes);
            availableNotes -= requiredNotes;
            handler.setAvailableNotes(availableNotes);

            int remainingAmount = requiredAmount - (requiredNotes * denomination);
            handler.processViaNextHandler(remainingAmount);
        } else {
            handler.processViaNextHandler(requiredAmount);
        }
    }
}
