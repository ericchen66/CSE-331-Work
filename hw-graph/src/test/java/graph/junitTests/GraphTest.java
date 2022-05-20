package graph.junitTests;

import graph.*;
import org.junit.Test;
import static org.junit.Assert.*;

/** GraphTest is a collection of tests for the Graph class */
public class GraphTest {
    private static Graph<String, String> graph1 = new Graph<>();
    Graph<String, String>.GraphNode node1 = graph1.new GraphNode("Node1");
    Graph<String, String>.GraphNode node2 = graph1.new GraphNode("Node2");
    Graph<String, String>.GraphNode node3 = graph1.new GraphNode("Node3");
    Graph<String, String>.GraphEdge edge1 = graph1.new GraphEdge(node1, node2, "Edge1");
    Graph<String, String>.GraphEdge edge2 = graph1.new GraphEdge(node2, node3, "Edge2");
    Graph<String, String>.GraphEdge edge3 = graph1.new GraphEdge(node1, node2, "Edge2");
    Graph<String, String>.GraphEdge edge4 = graph1.new GraphEdge(node1, node1, "Edge4");

    /** Tests retrieving an edge of the graph through its label, start, and end */
    @Test
    public void testGetEdge() throws NoSuchFieldException{
        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);
        graph1.addEdge(edge1);
        graph1.addEdge(edge2);
        graph1.addEdge(edge3);
        graph1.addEdge(edge4);

        assertEquals(edge3, graph1.getEdge(node1, node2, "Edge2"));
        assertEquals(edge1, graph1.getEdge(node1, node2, "Edge1"));
        assertEquals(edge2, graph1.getEdge(node2, node3, "Edge2"));
        assertEquals(edge4, graph1.getEdge(node1, node1, "Edge4"));
    }

    /** Tests getEdge method throws an exception */
    @Test(expected = NoSuchFieldException.class)
    public void testGetEdgeThrows() throws NoSuchFieldException{
        graph1.getEdge(node1, node2, "Edge2");
    }

    /** Tests getNode method throws an exception */
    @Test(expected = NoSuchFieldException.class)
    public void testGetNodeThrows() throws NoSuchFieldException{
        graph1.getNode("Node11");
    }

    /** Tests returning children of a node */
    @Test
    public void testChildrenOfNode(){
        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);
        graph1.addEdge(edge1);
        graph1.addEdge(edge2);
        graph1.addEdge(edge3);
        graph1.addEdge(edge4);

        assertTrue(graph1.childrenOfNode(node1).contains(node2));
        assertTrue(graph1.childrenOfNode(node1).contains(node1));
        assertTrue(graph1.childrenOfNode(node2).contains(node3));
        assertFalse(graph1.childrenOfNode(node2).contains(node2));
        assertFalse(graph1.childrenOfNode(node1).contains(node3));
    }
}
