package DSA.DP.Stocks;

public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        int buy = 0;

        int maxP = 0;

        for (int sell = 1; sell < prices.length; sell++) {
            if (prices[sell] > prices[buy]) {
                maxP = Math.max(maxP, prices[sell] - prices[buy]);
            } else {
                buy = sell;
            }
        }

        return maxP;
    }
}
