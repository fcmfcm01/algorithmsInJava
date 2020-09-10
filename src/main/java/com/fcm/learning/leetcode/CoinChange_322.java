package com.fcm.learning.leetcode;

import java.util.Arrays;

public class CoinChange_322 {
    public static void main(String[] args) {

    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int coinsCount = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            int temp = amount / coins[i];
            coinsCount += temp;
            if (i == 0 && temp != 0) {
                return -1;
            }
        }
        return coinsCount;
    }


}
