package com.fcm.learning.tree.binarytree;

import junit.framework.TestCase;

public class TreeUtilsTest extends TestCase {

  public void testSortedArrayToBST() {
    int[] nums = new int[11];
    for (int i = 1; i <= nums.length; i++) {
      nums[i - 1] = i;
    }
    TreeNode node = TreeUtils.sortedArrayToBST(nums);
    TreeUtils.midOrderTraversal(node);
  }
}