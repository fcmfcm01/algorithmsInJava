package com.fcm.learning.exercises.graph;

import java.util.*;

public class GraphSearch {

    public static List<Node> constructGraph(int nodeCounts) {
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeCounts; i++) {
            nodeList.add(new Node(i, Integer.MAX_VALUE));
        }
        for (int j = 0; j < nodeCounts; j++) {
            nodeList.get(j).connectTo(nodeList.get(randomInt(0, nodeCounts - 1)), randomInt(2, 10));
        }
        return nodeList;
    }

    public static void findShortestPath(Node start, Node end) {

        if (Node.hasConnection(start, end) && !start.equals(end)) {
            System.out.println("start:"+start.getIndex()+",end:"+end.getIndex());

            //计算直接连接到当前node的node的值，这些节点之前并未计算过
            start.getEdges().stream().filter(edge -> edge.getTo().getValue() == Integer.MAX_VALUE).forEach(edge -> {
                int nextNodeValue = edge.getFrom().getValue() + edge.getWeight();
                edge.setColor(Color.GREEN);
                setNode(edge.getTo(), nextNodeValue, Color.GREEN);
            });

            //找到最短的边长，选择该边作为下一个循环的起点
            start.getEdges().stream().filter(edge -> edge.getColor().equals(Color.GREEN))
                    .min(Edge::compareTo)
                    .ifPresent(edge -> {
                        edge.setColor(Color.ORANGE);
                        edge.getTo().setColor(Color.ORANGE);
                    });
            //如果当前边已经被计算过
            start.getEdges().stream().filter(edge -> edge.getColor().equals(Color.ORANGE)).forEach(edge -> {
                int nextNodeValue = edge.getFrom().getValue() + edge.getWeight();
                Node toNode= edge.getTo();
                if (nextNodeValue < toNode.getValue()) {
                    toNode.getEdges().stream()
                            //this edge is not the shortest , ignore it.
                            .filter(edge1 -> (edge1.getWeight() + edge1.getFrom().getValue()) > nextNodeValue)
                            .forEach(edge1 -> edge1.setColor(Color.PURPLE));
                    setNode(edge.getTo(), nextNodeValue, Color.RED);
                    edge.setColor(Color.RED);
                    findShortestPath(toNode, end);
                }
            });

        } else {
            System.out.println("Has no connection.");
        }
    }

    public static void setNode(Node node, int value, Color color) {
        node.setColor(color);
        node.setValue(value);
    }

    public static void main(String[] args) {
        List<Node> nodeList = constructGraph(10);
        printGraphArray(nodeList);
        Node start = nodeList.get(randomInt(0, 9));
        Node end = nodeList.get(randomInt(0, 9));
        System.out.println("picked start:"+start.getIndex()+" ,end:"+end.getIndex());
        Node.hasConnection(start,end);
//        while (!Node.hasConnection(start, end)) {
//            end = nodeList.get(randomInt(0, 9));
//        }
//        start.setValue(0);
//        System.out.println("picked start:"+start.getIndex()+" ,end:"+end.getIndex());
//        findShortestPath(start, end);
//
//        printPath(start, end);

    }

    public static void printPath(Node node, Node end) {
        for (Edge edge : node.getEdges()) {
            if (edge.getTo().getColor().equals(Color.RED) && !edge.getTo().equals(end)) {
                System.out.println("node:" + node.getIndex());
                printPath(edge.getTo(), end);
            }
        }
    }

    public static void printGraphArray(List<Node> nodeList) {
        int[][] nodeGraph = new int[nodeList.size()][nodeList.size()];
        for (int i = 0; i < nodeGraph.length; i++) {
            Node currentNode = nodeList.get(i);
            for (Edge edge : currentNode.getEdges()) {
                nodeGraph[i][edge.getTo().getIndex()] = edge.getWeight();
            }
        }
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println((i)+":"+Arrays.toString(nodeGraph[i]));
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
