package ocp.test;

public class MaxProfit {

    static void main() {
        IO.println(
                maxProfit(
                        new double[]{100, 105, 98, 120, 115}
                )
        );
    }

    private static double maxProfit(double[] prices) {
        if (prices == null || prices.length < 2) {
            throw new IllegalArgumentException("At-least two prices required");
        }
        double minPrice = prices[0];
        double maxProfit = 0.0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        return maxProfit;
    }
}
