package com.fcm.learning.exercises.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.TreeSet;

@Data
@EqualsAndHashCode(of = {"index"})
public class Node {
  private TreeSet<Edge> edges= new TreeSet<>();
  private int value;
  private int index;


}
