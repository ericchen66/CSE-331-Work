package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a mutable directed labeled graph containing a set of nodes
 * and a set of edges that connects the nodes. An edge may connect
 * a node to itself or to another node, which makes the second node
 * a child of the first (and the first a parent of the second).
 *
 * Abstract Invariant:
 * In one graph, no two nodes could share the same label, and no two
 * edges could share the same label and have identical parent and
 * identical child nodes.
 */
public class Graph {

    /**
     * Creates a directed labeled graph containing no nodes
     */
    public Graph(){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Creates a directed labeled graph containing the given list of nodes
     * @param nodes List of nodes for the initial Graph
     */
    public Graph(List<GraphNode> nodes){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Adds a node to the graph
     * @param nodeData The label of the node being added to the graph
     */
    public void addNode(String nodeData){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Removes a node from the graph along with any edges that have
     * the removed node as a parent or child node
     * @param nodeData The label of the node to be removed
     */
    public void removeNode(String nodeData){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Adds an edge to the graph, connecting two nodes
     * @param start The parent node or node where the added edge will originate
     * @param end The child node or node where the added edge will travel to
     * @param edgeData The label of the edge to be added
     */
    public void addEdge(GraphNode start, GraphNode end, String edgeData){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Removes an edge of the graph
     * @param start The parent node or node where the edge to be removed originates
     * @param end The child node or node where the edge to be removed travels to
     * @param edgeData The label of the edge to be removed
     */
    public void removeEdge(GraphNode start, GraphNode end, String edgeData){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a list of the nodes in the graph
     * @return List containing all GraphNode objects in this
     */
    public List<GraphNode> graphContains(){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a list of nodes that are child nodes of a specific node
     * @param n The node whose child nodes will be returned
     * @return List containing all child GraphNode objects of n
     */
    public List<GraphNode> childrenOfNode(GraphNode n){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a list of edges that could be used to travel between
     * two nodes (edges are always one-way)
     * @param start The node to begin at
     * @param end The node we want to arrive at
     * @return List containing a possible combination of edges that could
     * form a path between the starting and ending nodes
     */
    public List<GraphEdge> path(GraphNode start, GraphNode end){
        throw new RuntimeException("Method not yet implemented");
    }
}
