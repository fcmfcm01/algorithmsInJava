package com.fcm.learning.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {


  public static int[] randomInputUnique(int length) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < length; i++) {
      set.add(Math.round(Math.round(Math.random() * length)));
    }

    int[] results = new int[set.size()];
    int i = 0;
    for (Integer val : set) {
      results[i++] = val;
    }
    return results;
  }
  public static int[] randomInput(int length) {
    int[] results = new int[length];
    for (int i = 0; i < length; i++) {
      results[i]=Math.round(Math.round(Math.random() * length));
    }

    return results;
  }
  public static void swap(int[] inputs, int fromIndex, int toIndex) {
    int temp = inputs[fromIndex];
    inputs[fromIndex] = inputs[toIndex];
    inputs[toIndex] = temp;
  }

}
