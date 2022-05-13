package graph.junitTests;

import graph.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
/** GraphTest is a collection of tests for the Graph class */
public class GraphTest {
    private static Graph graph1 = new Graph();
    Graph.GraphNode node1 = new Graph.GraphNode("Node1");
    Graph.GraphNode node2 = new Graph.GraphNode("Node2");
    Graph.GraphNode node3 = new Graph.GraphNode("Node3");
    Graph.GraphEdge edge1 = new Graph.GraphEdge(node1, node2, "Edge1");
    Graph.GraphEdge edge2 = new Graph.GraphEdge(node2, node3, "Edge2");
    Graph.GraphEdge edge3 = new Graph.GraphEdge(node1, node2, "Edge2");
}
