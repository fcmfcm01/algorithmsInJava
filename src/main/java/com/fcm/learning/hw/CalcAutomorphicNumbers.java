package com.fcm.learning.hw;

import java.util.Scanner;

public class CalcAutomorphicNumbers {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      int count = 1;
      for (int i = 1; i <= n; i++) {
        String power = String.valueOf(i * i );
        if (power.endsWith(String.valueOf(i))) {
          count++;
        }
      }
      System.out.println(count);
    }
  }
}
