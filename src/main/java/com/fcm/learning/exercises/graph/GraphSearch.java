package com.fcm.learning.exercises.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphSearch {

  public static List<Node> constructGraph(int nodeCounts) {
    List<Node> nodeList = new ArrayList<>();
    for (int i = 0; i < nodeCounts; i++) {
      nodeList.add(new Node(Integer.MAX_VALUE));
    }
    for (int j = 0; j < nodeCounts; j++) {
      nodeList.get(j).connectTo(nodeList.get(randomInt(0, nodeCounts - 1)), randomInt(2, 10));
    }
    return nodeList;
  }

  public static void findShortestPath(List<Node> shortestPath, Node start, Node end) {

    if (Node.hasConnection(start, end)) {
      start.getEdges().stream().filter(edge -> !edge.getColor().equals(Color.ORANGE)).forEach(edge -> {
        int nextNodeValue = start.getValue() + edge.getWeight();
        edge.setColor(Color.ORANGE);
        edge.getTo().setValue(nextNodeValue);
        findShortestPath(shortestPath, edge.getTo(), end);
      });
    } else {
      System.out.println("Has no connection.");
    }
  }

  public static void main(String[] args) {
    List<Node> nodeList = constructGraph(10);
    printGraphArray(nodeList);
    List<Node> shortestPath = new ArrayList<>();
    Node start = nodeList.get(randomInt(0, 10));
    Node end = nodeList.get(randomInt(0, 10));
    while (!Node.hasConnection(start, end)) {
      start = nodeList.get(randomInt(0, 10));
      end = nodeList.get(randomInt(0, 10));
    }
    start.setValue(0);
    shortestPath.add(start);
    findShortestPath(shortestPath, start, end);
    printGraphArray(nodeList);

  }

  public static void printGraphArray(List<Node> nodeList) {
    int[][] nodeGraph = new int[nodeList.size()][nodeList.size()];
    for (int i = 0; i < nodeGraph.length; i++) {
      Node currentNode = nodeList.get(i);
      nodeGraph[i][0] = currentNode.getValue();
      for (Edge edge : currentNode.getEdges()) {
        nodeGraph[i][nodeList.indexOf(edge.getTo())] = edge.getWeight();
      }
    }
    for (int i = 0; i < nodeList.size(); i++) {
      System.out.println(Arrays.toString(nodeGraph[i]));
    }
  }

  public static int randomInt(int min, int max) {
    int value = Math.round(Math.round(Math.random() * max + min));
    if (value > max) {
      value = max;
    }
    return value;
  }

}
