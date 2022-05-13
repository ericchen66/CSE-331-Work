package graph;

import java.util.*;

/**
 * Represents a mutable directed labeled graph containing a set of nodes
 * and a set of edges that connects the nodes. An edge may connect
 * a node to itself or to another node, which makes the second node
 * a child of the first (and the first a parent of the second).
 * Graphs will allow for edges connecting a node to itself but not for
 * two edges with the same label to connect the same two nodes. No two
 * nodes of one graph may share the same label.
 */
public class Graph {
    //Nodes in the graph are stored in a List of GraphNode objects while edges
    //are stored in a List of GraphEdge objects.
    //RI: this.nodes != null, this.edges != null, this.nodes.get(0), ..., this.nodes.get(n1) != null,
    //this.edges.get(0), ..., this.edges.get(n2) != null, n1 = this.nodes.size(), n2 = this.edges.size()
    //AF(this):
    //Graph nodes = this.nodes
    //Graph edges = this.edges
    private List<GraphNode> nodes;
    private List<GraphEdge> edges;
    private static final boolean DEBUG = false;

    //Private helper method to check the representation invariant (RI) holds
    private void checkRep(){
        assert this.nodes != null : "this.nodes is null";
        assert this.edges != null : "this.edges is null";
        if(DEBUG){
            for(int i = 0; i < this.nodes.size(); i++){
                assert this.nodes.get(i) != null : "this.nodes has a null element";
            }
            for(int i = 0; i < this.edges.size(); i++){
                assert this.edges.get(i) != null : "this.edges has a null element";
            }
        }
    }

    /**
     * Creates a directed labeled graph containing no nodes
     */
    public Graph(){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Adds a node to the graph directly
     * @param n The node being added to the graph
     * @spec.requires n is not null
     * @spec.effects list of nodes in this
     */
    public void addNode(GraphNode n){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Removes a node from the graph along with any edges that have
     * the removed node as a parent or child node
     * @param node The node to be removed
     * @spec.modifies list of nodes in this, if given node is in the graph
     */
    public void removeNode(GraphNode node){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Adds an edge to the graph directly
     * @param edge The edge to be added
     * @spec.requires Given edge is not null
     * @spec.effects list of edges in this
     */
    public void addEdge(GraphEdge edge){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Removes an edge of the graph
     * @param edge The edge to be removed
     * @spec.modifies list of edges in this, if given edge is in the graph
     */
    public void removeEdge(GraphEdge edge){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a list of the labels of nodes in the graph
     * @return List containing all GraphNode objects in this
     */
    public List<GraphNode> nodes(){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a list of the labels of edges in the graph
     * @return List containing all GraphEdge objects in this
     */
    public List<GraphEdge> edges(){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a list of the nodes that are child nodes
     * of a specific node
     * @param node The node whose child nodes will be returned
     * @spec.requires Given node is in the graph and is not null
     * @return List containing all children GraphNode objects of n
     */
    public List<GraphNode> childrenOfNode(GraphNode node){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Returns a boolean representing whether a path exists between
     * two nodes
     * @param start The node to begin at
     * @param end The node we want to arrive at
     * @spec.requires Given nodes are in the graph and are not null
     * @return True if there is a path between the two nodes, false otherwise
     */
    public boolean path(GraphNode start, GraphNode end){
        throw new RuntimeException("Method not yet implemented");
    }

    /**
     * Represents a node of a directed labeled graph. A node
     * could have an unlimited number of edges originating from
     * it and have an unlimited number of edges connecting to
     * it. Each node could be identified using a label.
     */
    public static class GraphNode {

        /**
         * Creates a node containing a given label
         * @param data The label that identifies the new node
         */
        public GraphNode(String data){
            throw new RuntimeException("Method not yet implemented");
        }
    }

    /**
     * Represents an edge connecting two nodes of a directed
     * labeled graph. Each edge has a parent node that it
     * originates from and a child node that it connects
     * the parent to. Each edge is also identifiable by
     * a label.
     */
    public static class GraphEdge {

        /**
         * Creates an edge originating and ending at given nodes,
         * identifiable by a label
         * @param start Node that the new edge will originate from
         * @param end Node that the new edge will travel to
         * @param data Label of the new node
         */
        public GraphEdge(GraphNode start, GraphNode end, String data){
            throw new RuntimeException("Method not yet implemented");
        }
    }
}
