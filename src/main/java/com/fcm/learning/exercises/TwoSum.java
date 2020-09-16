package com.fcm.learning.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TwoSum {

  public static void main(String[] args) {
//    int[] ints = Utils.randomInputUnique(100000);
//    Arrays.sort(ints);
//    long start = System.currentTimeMillis();
//    System.out.println(Arrays.toString(twoSumWithMap(ints, 45656)));
//    System.out.println("twoSumWithMap:" + (System.currentTimeMillis() - start) + "ms");
//    start = System.currentTimeMillis();
//    System.out.println(Arrays.toString(twoSum(ints, 45656)));
//    System.out.println("twoSumWithArray:" + (System.currentTimeMillis() - start) + "ms");
    System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
    System.out.println(Arrays.toString(twoSum(new int[]{0, 3, 4, 2, 3, 3}, 6)));
  }

  /**
   * 适合无重复值的输入
   *
   * @param ints
   * @param target
   * @return
   */
  public static int[] twoSumWithMap(int[] ints, int target) {
    // value as key ,index as value
    Map<Integer, Integer> valueIndexMap = new HashMap<>();

    for (int i = 0; i < ints.length; i++) {
      if (valueIndexMap.get(target - i) != null && valueIndexMap.get(target - i) != i) {
        return new int[]{i, valueIndexMap.get(target - i)};
      } else {
        valueIndexMap.put(ints[i], i);
      }
    }

    return new int[]{-1, -1};
  }

  /**
   * @param ints
   * @param target
   * @return
   */
  public static int[] twoSum(int[] ints, int target) {
    // value as key ,index as value
    Map<Integer, Integer> valueMap = new HashMap<>();
    for (int i = 0; i < ints.length; i++) {
      int value = ints[i];
      int want = target - value;
      if ( valueMap.containsKey(want)&& i != valueMap.get(want)) {
        return new int[]{ valueMap.get(want),i};
      }
      valueMap.put(value, i);
    }

    return new int[]{-1, -1};
  }

  /**
   * 有序数组
   *
   * @param nums
   * @param target
   * @return
   */
  public static int[] twoSumOrderedArray(int[] nums, int target) {
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
