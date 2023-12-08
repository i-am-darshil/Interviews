package LowLevelSystemDesign.ChainOfResponsibilityDesignPattern.ATM;

public abstract class WithdrawHandler {
    WithdrawHandler nextWithdrawHandler;

    private final int denomination;
    private int availableNotes;

    public WithdrawHandler(WithdrawHandler nextWithdrawHandler, int denomination, int availableNotes) {
        this.nextWithdrawHandler = nextWithdrawHandler;
        this.denomination = denomination;
        this.availableNotes = availableNotes;
    }

    public void processViaNextHandler(int requiredAmount) {
        if (nextWithdrawHandler != null) {
            nextWithdrawHandler.handle(requiredAmount);
        } else {
            System.out.println("Cannot process " + requiredAmount + " amount");
        }
    }

    public abstract void handle(int requiredAmount);

    public void giveOut(int notesProcessed) {
        System.out.println("Giving out " + notesProcessed + " notes of " + denomination);
    };

    public int getDenomination() {
        return denomination;
    }

    public int getAvailableNotes() {
        return availableNotes;
    }

    public void setAvailableNotes(int availableNotes) {
        this.availableNotes = availableNotes;
    }
}
