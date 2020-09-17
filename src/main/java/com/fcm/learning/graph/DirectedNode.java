package com.fcm.learning.graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedNode {
  List<DirectedNode> next= new ArrayList<>();
  int val;

  public DirectedNode(int val) {
    this.val = val;
  }

}
