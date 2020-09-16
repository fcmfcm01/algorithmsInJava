package com.fcm.learning.leetcode;

public class MoveZero {
  public void moveZeroes(int[] nums) {
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        //非零值填入
        nums[j] = nums[i];
        //num[i]的值已经被移动，将该项置零
        if (i != j) {
          nums[i] = 0;
        }
      }
      j++;
    }

  }
}
