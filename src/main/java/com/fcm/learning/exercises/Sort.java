package com.fcm.learning.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sort {


  public static void main(String[] args) {
    int[] inputs = randomInput(10);
    System.out.println(Arrays.toString(inputs));
//    mergeSort(inputs);

    System.out.println(Arrays.toString(inputs));
    System.out.println(Arrays.toString(quickSort(inputs, 0, inputs.length - 1)));
  }

  public static void merge(int[] inputs, int[] left, int[] right) {

    System.out.println("Merging:");
    System.out.println("\tLeft:" + Arrays.toString(left));
    System.out.println("\tRight:" + Arrays.toString(right));
    int i = 0, j = 0, k = 0;
    //比较左右值大小，小的放入数组中
    while (i < left.length && j < right.length) {
      if (left[i] <= right[j]) {
        inputs[k++] = left[i++];
      } else {
        inputs[k++] = right[j++];
      }
    }
    //将剩余的未放入数组中的值暂存到原始数组中
    while (i < left.length) {
      inputs[k++] = left[i++];
    }
    while (j < right.length) {
      inputs[k++] = right[j++];
    }
  }

  public static int[] mergeSort(int[] inputs) {
    if (inputs.length < 2) {
      //递归退出的基线条件
      return inputs;
    }
    int mid = inputs.length / 2;
    int[] left = Arrays.copyOfRange(inputs, 0, mid - 1);
    int[] right = Arrays.copyOfRange(inputs, mid, inputs.length - 1);
    System.out.println("------------------------");
    System.out.println("Division of :" + Arrays.toString(inputs));
    mergeSort(left);
    mergeSort(right);
    System.out.println("------------------------");

    merge(inputs, left, right);
    return inputs;
  }

  public static int[] quickSort(int[] inputs, int begin, int end) {
    if ((begin < end)) {
      int partitionIndex = partition(inputs, begin, end);
      quickSort(inputs, begin, partitionIndex - 1);
      quickSort(inputs, partitionIndex + 1, end);
    }
    return inputs;
  }

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

  protected static int partition(int[] inputs, int begin, int end) {
    int base = inputs[end];
    int i = begin - 1;
    for (int j = begin; j < end; j++) {
      if (inputs[j] < base) {
        i++;
        swap(inputs, i, j);
      }
    }
    swap(inputs, i + 1, end);
    return i + 1;
  }
}
