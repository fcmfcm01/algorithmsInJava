package com.fcm.learning.hw;

import java.util.Scanner;

public class SnakeArray {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextInt()) {
      printSnakeMatrix(scanner.nextInt());
    }
    scanner.close();
  }

  public static void printSnakeMatrix(int n) {
    int firstCol = 1;
    for (int i = 1; i <= n; i++) {
      System.out.print(firstCol);
      int temp = firstCol;
      for (int j = i + 1; j <= n; j++) {
        temp += j;
        System.out.print(" " + temp);
      }
      firstCol += i;
      System.out.println();
    }
  }
}
