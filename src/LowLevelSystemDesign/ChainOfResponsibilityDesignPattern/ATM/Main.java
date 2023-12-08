package LowLevelSystemDesign.ChainOfResponsibilityDesignPattern.ATM;

public class Main {

    // when the client request is to be processed by a chain of handlers.
    // Like ATM withrawal request can be processed by 2000 handler, 500 handler and 100 handler

    // ATM machines will be operated one at a time, we can use single threading here
    public static void main(String[] args) {
        WithdrawHandlerStrategy handlerStrategy = new WithdrawHandlerStrategy();

        WithdrawHandler oneHundredWithdrawHandler = new OneHundredWithdrawHandler(null, 10, handlerStrategy);
        WithdrawHandler fiveHundredWithdrawHandler = new FiveHundredWithdrawHandler(oneHundredWithdrawHandler, 10, handlerStrategy);
        WithdrawHandler twoThousandWithdrawHandler = new TwoThousandWithdrawHandler(fiveHundredWithdrawHandler, 10, handlerStrategy);

        int withdrawalAmount = 19001;

        twoThousandWithdrawHandler.handle(withdrawalAmount);
    }
}
