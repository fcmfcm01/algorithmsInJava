package com.fcm.learning.exercises.graph;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Dijkstra {
  public static Node constructNode(int index, int value) {
    Node node = new Node();
    node.setIndex(index);
    node.setValue(value);
    return node;
  }

  public static int[][] initInputs() {
    int[][] graph = new int[5][5];
    for (int[] ints : graph) {
      Arrays.fill(ints, Integer.MAX_VALUE);
    }
    graph[0][1] = 2;
    graph[0][2] = 5;
    graph[1][2] = 2;
    graph[1][3] = 6;
    graph[2][3] = 7;
    graph[2][4] = 1;
    graph[3][2] = 2;
    graph[3][4] = 4;
    return graph;
  }

  public static void main(String[] args) {
    int[][] graph = initInputs();
    for (int[] ints : graph) {
      System.out.println(Arrays.toString(ints));
    }
    Map<Integer, Node> searchedNodesMap = new TreeMap<>();
    searchPath(graph, 0, searchedNodesMap);
    printPath(searchedNodesMap.get(0), searchedNodesMap.get(4));
  }

  public static void printPath(Node node, Node end) {
    if (node.getEdges().size() > 0 && node.getIndex() != end.getIndex()) {
      for (Edge edge : node.getEdges()) {
        System.out.println("node:" + node.getIndex() + ",value:" + node.getValue());
        printPath(edge.getEnd(), end);
      }
    }
    if (node.getIndex() == end.getIndex()) {
      System.out.println("node:" + node.getIndex() + ",value:" + node.getValue());
    }
  }

  public static void searchPath(int[][] graph, int index, Map<Integer, Node> searchedNodesMap) {

    Node start = getNode(index, 0, searchedNodesMap);
    int lengthOfEdge = Integer.MAX_VALUE;
    int nextNodeIndex = -1;
    for (int i = 0; i < graph[index].length; i++) {
      int currentLength = graph[index][i];
      if (lengthOfEdge > currentLength) {
        lengthOfEdge = currentLength;
        nextNodeIndex = i;
      }
    }
    if (nextNodeIndex == -1) {
      return;
    }
    final int tempIndex = nextNodeIndex;
    Edge edge = start.getEdges().stream().filter(edge1 -> edge1.getIndex() == tempIndex).findFirst().orElseGet(() -> null);
    if (edge == null) {
      edge = new Edge();
      edge.setIndex(nextNodeIndex);
      edge.setWeight(lengthOfEdge);
      edge.setStart(start);
    }
    Node end = getNode(nextNodeIndex, Integer.MAX_VALUE, searchedNodesMap);
    if (end.getValue() > (start.getValue() + lengthOfEdge)) {
      end.setValue(start.getValue() + lengthOfEdge);
    }
    edge.setEnd(end);
    start.getEdges().add(edge);
    searchPath(graph, nextNodeIndex, searchedNodesMap);
  }

  private static Node getNode(int index, int value, Map<Integer, Node> searchedNodesMap) {
    Node start = searchedNodesMap.get(index);
    if (start == null) {
      start = constructNode(index, value);
      searchedNodesMap.put(index, start);
      System.out.println("added node:" + index);
    }
    return start;
  }
}
