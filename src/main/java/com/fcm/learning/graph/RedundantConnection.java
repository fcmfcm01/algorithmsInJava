package com.fcm.learning.graph;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * https://leetcode-cn.com/problems/redundant-connection-ii/
 */
public class RedundantConnection {
  /**
   * 该题实际上是移除有向图上的环
   *
   * @param edges
   * @return
   */
  public int[] findRedundantDirectedConnection(int[][] edges) {
    if (edges == null || edges.length == 0) {
      return null;
    }
    for (int i = 0; i < edges.length; i++) {
      System.out.println(Arrays.toString(edges[i]));
      ;
    }
    //起点
    DirectedNode start = null;
    TreeMap<Integer, DirectedNode> nodeMap = new TreeMap<>();
    for (int[] edge : edges) {
      if (start == null) {
        start = new DirectedNode(edge[0]);
        nodeMap.put(start.val, start);
      }
      DirectedNode node = nodeMap.getOrDefault(edge[0], new DirectedNode(edge[0]));
      DirectedNode next = nodeMap.getOrDefault(edge[1], new DirectedNode(edge[1]));
      node.next.add(next);
      nodeMap.putIfAbsent(node.val, node);
      nodeMap.putIfAbsent(next.val, next);
    }


    DirectedNode slow = start;
    DirectedNode fast = null;
    if (slow.next != null) {
      if (slow.next.next != null) {
        fast = slow.next.next;
      }
    }
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return new int[]{slow.val, slow.next.val};
      }
    }

    return null;
  }

}
