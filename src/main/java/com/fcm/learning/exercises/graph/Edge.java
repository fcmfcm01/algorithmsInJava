package com.fcm.learning.exercises.graph;

public class Edge implements Comparable<Edge> {
  private Color color = Color.GRAY;
  private Node from;
  private Node to;
  private int weight;


  public Edge(Node from, Node to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  @Override
  public int compareTo(Edge o) {
    return this.getWeight() - o.getWeight();
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Node getFrom() {
    return from;
  }

  public void setFrom(Node from) {
    this.from = from;
  }

  public Node getTo() {
    return to;
  }

  public void setTo(Node to) {
    this.to = to;
  }

  @Override
  public int hashCode() {
    int result = (from != null ? from.hashCode() : 0);
    result = 31 * result + (to != null ? to.hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Edge edge = (Edge) o;

    if (from != null ? !from.equals(edge.from) : edge.from != null) return false;
    return to != null ? to.equals(edge.to) : edge.to == null;
  }
}
