package org.fcm.alg.example;

import java.util.Arrays;
import java.util.Stack;

public class Test {
  public static void main(String[] args) {
    System.out.println(-1 / 2);
  }

  private static void printAddCollectionToStack() {
    Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    Stack<Integer> stack = new Stack<>();
    stack.addAll(Arrays.asList(ints));
    while (!stack.isEmpty()) {
      System.out.println(stack.pop() + " ");
    }
  }
}
