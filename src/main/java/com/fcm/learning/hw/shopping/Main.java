package com.fcm.learning.hw.shopping;

import java.util.Scanner;

public class Main {


  public static void main(String[] args) {


    Scanner scanner = new Scanner(System.in);
    int totalPrice = scanner.nextInt() / 10;
    int totalNum = scanner.nextInt();
    //计算时从1 开始
    int priceArr[] = new int[totalNum + 1];
    int valueArr[] = new int[totalNum + 1];
    int annexArr[] = new int[totalNum + 1];
    for (int i = 1; i <= totalNum; i++) {
      priceArr[i] = scanner.nextInt() / 10;
      valueArr[i] = scanner.nextInt() * priceArr[i];
      annexArr[i] = scanner.nextInt();
    }
    scanner.close();
    for (int i = 1; i <= totalNum; i++) {
      System.out.println(i + ":" + valueArr[i]);
    }
    System.out.println("------------------------");
    System.out.println(getMaxValue(totalPrice, totalNum, priceArr, valueArr, annexArr) * 10);

  }

  public static int getMaxValue(int totalPrice, int totalNum, int[] priceArr, int[] valueArr, int[] annexArr) {
    int[][] dp = new int[totalNum + 1][totalPrice + 1];
    for (int i = 1; i < totalNum + 1; i++) {
      for (int j = priceArr[i]; j < totalPrice + 1; j++) {
        if (annexArr[i] == 0) {
          if (priceArr[i] <= j) {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - priceArr[i]] + valueArr[i]);
          }

        } else {
          if (priceArr[i] + priceArr[annexArr[i]] <= j) {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - priceArr[i]] + valueArr[i]);
          }
        }
      }
      System.out.println(i + ":" + dp[i][totalPrice]);
    }
    System.out.println("---------------");
    for (int i = 0; i < totalNum; i++) {
      int itemValue = dp[i + 1][totalPrice] - dp[i][totalPrice];
      if (itemValue == 0) {
        continue;
      }
      System.out.println("add item:" + findItemWithValue(valueArr, itemValue) + ",value:" + itemValue);
    }


    return dp[totalNum][totalPrice];
  }

  private static int findItemWithValue(int[] valArr, int value) {
    for (int i = 1; i <= valArr.length; i++) {
      if (value == valArr[i]) {
        return i;
      }
    }
    return -1;
  }

}