package com.fcm.learning.exercises.graph;

import java.util.Arrays;
import java.util.TreeSet;

public class Node {
  private Color color = Color.GRAY;
  //最短边最靠前
  private TreeSet<Edge> edges;
  private int value;

  public Node(int value) {
    this.value = value;
    this.edges = new TreeSet<>();
  }

  public Node() {
    this.value = Integer.MAX_VALUE;
    this.edges = new TreeSet<>();
  }

  public static boolean hasConnection(Node from, Node to) {
    //深度优先,每次都从最小的edge开始检测，检测从起始点到未检测最短边的终点为止
    for (Edge edge : from.getEdges()) {
      Node toNode = edge.getTo();
      if (to.equals(toNode)) {
        return true;
      } else {
        return hasConnection(toNode, to);
      }
    }
    return false;
  }

  public void connectTo(Node to, int weight) {
    if (!this.equals(to)) {
      Edge edge = new Edge(this, to, weight);
      this.getEdges().add(edge);
    }
  }

  public TreeSet<Edge> getEdges() {
    return edges;
  }

  public void setEdges(TreeSet<Edge> edges) {
    this.edges = edges;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    if (this.value > value) {
      this.value = value;
    }
  }

  public boolean hasDirectLink(Node to){
    for (Edge edge:edges){
      if(edge.getTo().equals(to)){
        return true;
      }
    }
    return false;
  }


}
