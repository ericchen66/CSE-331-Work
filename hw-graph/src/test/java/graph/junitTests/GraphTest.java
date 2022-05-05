package graph.junitTests;

import graph.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

public class GraphTest {
    private static Graph graph1 = new Graph();
    Graph.GraphNode node1 = new Graph.GraphNode("Node1");
    Graph.GraphNode node2 = new Graph.GraphNode("Node2");
    Graph.GraphNode node3 = new Graph.GraphNode("Node3");
    Graph.GraphEdge edge1 = new Graph.GraphEdge(node1, node2, "Edge1");
    Graph.GraphEdge edge2 = new Graph.GraphEdge(node2, node3, "Edge2");
    Graph.GraphEdge edge3 = new Graph.GraphEdge(node1, node2, "Edge2");

    /** Tests Graph's ability to find a path between two nodes */
    @Test
    public void testPath(){
        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);
        graph1.addEdge(edge1);
        graph1.addEdge(edge2);

        assertTrue(graph1.path(node1, node2));
        assertTrue(graph1.path(node1, node3));
        assertFalse(graph1.path(node1, node1));
        assertFalse(graph1.path(node2, node1));
        assertFalse(graph1.path(node3, node1));
    }

    @Test
    public void testRemoveNode(){
        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);

        graph1.removeNode(node1);

        assertTrue(graph1.nodes().contains(node3));
        assertTrue(graph1.nodes().contains(node2));
        assertFalse(graph1.nodes().contains(node1));
    }

    @Test
    public void testRemoveEdge(){
        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);
        graph1.addEdge(edge1);
        graph1.addEdge(edge2);
        graph1.addEdge(edge3);

        graph1.removeEdge(edge2);

        assertTrue(graph1.edges().contains(edge1));
        assertTrue(graph1.edges().contains(edge3));
        assertFalse(graph1.edges().contains(edge2));
        assertFalse(graph1.childrenOfNode(node2).contains(node3));
    }
}
