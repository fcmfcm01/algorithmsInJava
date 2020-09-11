package com.fcm.learning.exercises.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"index"})
public class Edge implements Comparable<Edge> {
  private Node end;
  private Node start;
  private int weight;
  private int index;


  @Override
  public int compareTo(Edge o) {
    return this.getWeight() - o.getWeight();
  }
}
