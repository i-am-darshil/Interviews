package DSA.DP.Subsequence;

//https://www.codingninjas.com/studio/problems/rod-cutting-problem_80028

public class RodCuttingProblem {
    public static void main(String[] args) {
        int[] price = new int[] {2, 5, 7, 8, 10};
        int n = 5;
        System.out.println(cutRod(price, n));
    }

    public static int cutRod(int price[], int n) {
        // Write your code here.
        return cutRodUtility(n, n, price);
    }

    public static int cutRodUtility(int length, int remainingLength, int[] price) {

        if (remainingLength == 0) return 0;

        if (length == 1) {
            if (remainingLength % length == 0) return price[length-1] * (remainingLength / length);
            else return 0;
        }

        int take = 0;
        if (remainingLength - length >= 0) {
            take = price[length-1] + cutRodUtility(length, remainingLength - length , price);

        }
        int notTake = cutRodUtility(length-1, remainingLength, price);

        return Math.max(take, notTake);


    }
}
