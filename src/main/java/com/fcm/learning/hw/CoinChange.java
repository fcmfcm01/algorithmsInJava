package com.fcm.learning.hw;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = coinChange(coins, rem - coin, count);
            if (result >= 0 && result < min)
                min = 1 + result;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    private int coinChangeWithDP(int[] coins, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length ; i++) {
            for (int coin : coins) {
                //当前金额不足以消耗当前的coin
                if (i < coin) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[target] == target + 1 ? -1 : dp[target];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5, 7};
        CoinChange coinChange = new CoinChange();
        long start = System.currentTimeMillis();
        System.out.println(coinChange.coinChange(coins, 100000));
        System.out.println(System.currentTimeMillis() - start + "ms");
        start = System.currentTimeMillis();
        System.out.println(coinChange.coinChangeWithDP(coins, 100000));
        System.out.println(System.currentTimeMillis() - start + "ms");

    }
}
