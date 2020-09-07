package com.fcm.learning.exercises;

import java.util.Arrays;

public class ArrayLearning {

  public static Object[] reversrArray(Object[] input){
    if(input.length<2){
      return input;
    }
    for (int i = 0; i < input.length/2; i++) {
      Object temp = input[i];
      input[i]=input[input.length-1-i];
      input[input.length-1-i]=temp;
    }

    return input;
  }

  public static void main(String[] args) {
    Integer[] input = new Integer[]{0,1,2,3,4,5};
    System.out.println(Arrays.toString(reversrArray(input)));
  }
}
