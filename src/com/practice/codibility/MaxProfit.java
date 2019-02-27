package com.practice.codibility;

public class MaxProfit {
    final static int[] A = {23171,21011,21123,21366,21013,21367};

    public static int maxProfit(int[] array) {
        int minBuy = Integer.MAX_VALUE;
        int currentProfit = 0;
        int maxProfit = 0;

        for(int i = 0; i < array.length; i++) {
            minBuy = Math.min(minBuy, array[i]);
            currentProfit = array[i] - minBuy;
            maxProfit = Math.max(currentProfit, maxProfit);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(A));
    }
}
