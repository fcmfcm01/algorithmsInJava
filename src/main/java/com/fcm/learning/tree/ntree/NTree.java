package com.fcm.learning.tree.ntree;

import java.util.*;

public class NTree {

  public static List<Integer> postOrderTraversal(Node root) {
    List<Integer> list = new ArrayList<>();
    if (root != null) {
      for (Node node : root.children) {
        list.addAll(postOrderTraversal(node));
      }
      list.add(root.val);
    }
    return list;
  }

  public static List<Integer> postorderNonRecursive(Node root) {
    List<Integer> list = new ArrayList<>();
    if (root != null) {
      Stack<Node> nodeStack = new Stack<>();
      nodeStack.push(root);
      Stack<Node> children = new Stack<>();
      children.addAll(root.children);
      while (!children.isEmpty()) {
        Node child = children.pop();
        nodeStack.push(child);
        if (child.children != null)
          children.addAll(child.children);
      }
      while (!nodeStack.isEmpty()) {
        list.add(nodeStack.pop().val);
      }
    }
    return list;
  }

  public static Node constructNodeFromArray(Integer[] input) {
    Node root = new Node(input[0], new ArrayList<>());
    Queue<Node> parent = new ArrayDeque<>();
    parent.add(root);
    for (int i = 2; i < input.length; i++) {
      Node parentNode = parent.poll();
      if (parentNode == null) {
        break;
      }
      while (i < input.length - 1 && input[i] != null) {
        Node node = new Node(input[i], new ArrayList<>());
        parent.add(node);
        parentNode.children.add(node);
        i++;
      }
    }
    return root;
  }

  public static List<Integer> preOrderTraversal(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      result.add(root.val);
      for (Node child : root.children) {
        result.addAll(preOrderTraversal(child));
      }
    }
    return result;
  }

  public static List<Integer> preOrderNonRecursiveTraversal(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      Stack<Node> stack = new Stack<>();
      stack.push(root);
      while (!stack.isEmpty()) {
        Node cur = stack.pop();
        result.add(cur.val);
        List<Node> children = cur.children;
        for (int i = children.size() - 1; i >= 0; i--) {
          stack.push(children.get(i));
        }
      }
    }
    return result;
  }

  public static List<Integer> levelTraversal(Node root) {
    List<Integer> result = new ArrayList<>();
    Queue<Node> nodes = new ArrayDeque<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
      Node cur = nodes.poll();
      result.add(cur.val);
      nodes.addAll(cur.children);
    }

    return result;
  }

  public static void main(String[] args) {
    Integer[] input = new Integer[]{1, null, 2, 3, 4, null, 5, 6, null, 7, 8, 9};
    Node root = constructNodeFromArray(input);
    System.out.println("层次：");
    for (Integer val : levelTraversal(root)) {
      System.out.print(val + " ");
    }
    System.out.println("\n前序：");
    for (Integer val : preOrderTraversal(root)) {
      System.out.print(val + " ");
    }
    System.out.println();
    for (Integer val : preOrderNonRecursiveTraversal(root)) {
      System.out.print(val + " ");
    }
    System.out.println("\n后序：");
    for (Integer val : postOrderTraversal(root)) {
      System.out.print(val + " ");
    }
    System.out.println();
    for (Integer val : postorderNonRecursive(root)) {
      System.out.print(val + " ");
    }
  }
}

class Node {
  public int val;
  public List<Node> children = new ArrayList<>();

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
};