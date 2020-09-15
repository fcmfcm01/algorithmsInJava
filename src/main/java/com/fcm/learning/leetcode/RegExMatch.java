package com.fcm.learning.leetcode;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class RegExMatch {
  public boolean isMatch(String str, String pattern) {
    int strLength = str.length();
    int patternLength = pattern.length();

    boolean[][] dp = new boolean[strLength + 1][patternLength + 1];
    //str 和pattern都没有输入
    dp[0][0] = true;
    for (int indexOfStr = 0; indexOfStr <= strLength; ++indexOfStr) {
      for (int indexOfPattern = 1; indexOfPattern <= patternLength; ++indexOfPattern) {
        //* 时需要匹配前置条件
        if (pattern.charAt(indexOfPattern - 1) == '*') {
          dp[indexOfStr][indexOfPattern] = dp[indexOfStr][indexOfPattern - 2];
          if (matches(str, pattern, indexOfStr, indexOfPattern - 1)) {
            dp[indexOfStr][indexOfPattern] = dp[indexOfStr][indexOfPattern] || dp[indexOfStr - 1][indexOfPattern];
          }
        } else {
          if (matches(str, pattern, indexOfStr, indexOfPattern)) {
            dp[indexOfStr][indexOfPattern] = dp[indexOfStr - 1][indexOfPattern - 1];
          }
        }
      }
    }
    return dp[strLength][patternLength];
  }

  public boolean matches(String str, String pattern, int indexOfStr, int indexOfPattern) {
    //默认dp[0][0]为true
    if (indexOfStr == 0) {
      return false;
    }
    //
    if (pattern.charAt(indexOfPattern - 1) == '.') {
      return true;
    }
    return str.charAt(indexOfStr - 1) == pattern.charAt(indexOfPattern - 1);
  }


}
