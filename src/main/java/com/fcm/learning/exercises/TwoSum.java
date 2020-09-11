package com.fcm.learning.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TwoSum {

  public static void main(String[] args) {
    int[] ints = Utils.randomInputUnique(100000);
    Arrays.sort(ints);
    long start = System.currentTimeMillis();
    System.out.println(Arrays.toString(twoSumWithMap(ints, 45656)));
    System.out.println("twoSumWithMap:" + (System.currentTimeMillis() - start) + "ms");
    start = System.currentTimeMillis();
    System.out.println(Arrays.toString(twoSum(ints, 45656)));
    System.out.println("twoSumWithArray:" + (System.currentTimeMillis() - start) + "ms");
  }


  public static int[] twoSumWithMap(int[] ints, int target) {
    // value as key ,index as value
    Map<Integer, Integer> valueIndexMap = new HashMap<>();

    for (int i = 0; i < ints.length; i++) {
      valueIndexMap.put(ints[i], i);
    }
    for (int i = 0; i < ints.length; i++) {
      if (valueIndexMap.get(target - i) != null && valueIndexMap.get(i) != null) {
        return new int[]{valueIndexMap.get(i) + 1, valueIndexMap.get(target - i) + 1};
      }
    }

    return new int[]{-1, -1};
  }

  public static int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int sum = nums[left] + nums[right];
      if (sum == target) {
        // 题目要求的索引是从 1 开始的
        return new int[]{left + 1, right + 1};
      } else if (sum < target) {
        left++; // 让 sum 大一点
      } else if (sum > target) {
        right--; // 让 sum 小一点
      }
    }
    return new int[]{-1, -1};
  }

}
