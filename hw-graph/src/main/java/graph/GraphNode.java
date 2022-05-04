package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a mutable node of a directed labeled graph. A node
 * could have an unlimited number of edges originating from
 * it and have an unlimited number of edges connecting to
 * it. Each node could be identified using a label.
 */
public class GraphNode {

    /**
     * Creates a node containing a given label
     * @param data The label that identifies the new node
     */
    public GraphNode(String data){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Creates an edge originating from this and traveling to a
     * target node
     * @param target The node that the new edge travels to
     * @param edgeData The label of the new edge
     */
    public void createEdge(GraphNode target, String edgeData){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Removes an edge originating from this and traveling to a
     * target node
     * @param target The node that the edge to be removed travels to
     */
    public void removeEdge(GraphNode target){
        throw new RuntimeException("Method not yet implemented");
    }
}
