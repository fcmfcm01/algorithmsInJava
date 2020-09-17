package com.fcm.learning.graph;

import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedundantConnectionTest extends TestCase {

  public void testFindRedundantDirectedConnection() {
    int[][] input = new int[3][3];
    input[0]=new int[]{1,2};
    input[1]=new int[]{1,3};
    input[2]=new int[]{2,3};

    RedundantConnection connection = new RedundantConnection();
    connection.findRedundantDirectedConnection(input);

  }

  private void getNodesFromInput() {
    Pattern pattern = Pattern.compile("(\\[\\d+,\\d+\\])");
    Matcher matcher = pattern.matcher("[1,2],[1,3],[2,3]");
    Pattern numberPattern = Pattern.compile("(\\d+)");

    while (matcher.find()) {
      for (int i = 0; i < matcher.groupCount(); i++) {
        Matcher numsMatcher = numberPattern.matcher(matcher.group(i));
        while (numsMatcher.find()) {

        }
      }
    }
  }
}