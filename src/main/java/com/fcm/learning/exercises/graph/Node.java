package com.fcm.learning.exercises.graph;

import java.util.TreeSet;

public class Node {
    private Color color = Color.GRAY;
    //最短边最靠前
    private TreeSet<Edge> edges;
    private int value;
    private int index;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
        this.edges = new TreeSet<>();
    }

    public Node(int index) {
        this.index = index;
        this.value = Integer.MAX_VALUE;
        this.edges = new TreeSet<>();
    }

    public static boolean hasConnection(Node from, Node to) {
        //深度优先,每次都从最小的edge开始检测，检测从起始点到未检测最短边的终点为止
        boolean result = false;
        for (Edge edge : from.getEdges()) {
            if (edge.getColor().equals(Color.GRAY)) {
                edge.setColor(Color.GREEN);
                Node toNode = edge.getTo();
                if (to.index == toNode.index) {
                    System.out.println("end:" + toNode.getIndex());
                    result = true;
                    break;
                } else {
                    System.out.println("node:" + from.getIndex());
                    return hasConnection(toNode, to);
                }
            }
        }
        System.out.println("connected:" + result);
        resetEdges(from);
        return result;
    }

    public static void resetEdges(Node node) {
        node.getEdges().stream().filter(edge -> edge.getColor().equals(Color.GREEN)).forEach(edge -> edge.setColor(Color.GRAY));
    }

    public void connectTo(Node to, int weight) {
        if (!(this.getIndex() == to.getIndex())) {
            to.getEdges().add(new Edge(to, this, weight));
            this.getEdges().add(new Edge(this, to, weight));
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

    public boolean hasDirectLink(Node to) {
        for (Edge edge : edges) {
            if (edge.getTo().equals(to)) {
                return true;
            }
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
