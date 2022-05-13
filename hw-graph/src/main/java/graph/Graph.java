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
     * Creates a directed labeled graph containing no nodes or edges
     */
    public Graph(){
        this.nodes = new LinkedList<>();
        this.edges = new LinkedList<>();
        checkRep();
    }

    /**
     * Adds a node to the graph directly
     * @param n The node being added to the graph
     * @spec.requires n is not null
     * @spec.effects list of nodes in this
     */
    public void addNode(GraphNode n){
        checkRep();
        this.nodes.add(n);
        checkRep();
    }

    /**
     * Adds an edge to the graph directly
     * @param edge The edge to be added
     * @spec.requires Given edge is not null
     * @spec.effects list of edges in this
     */
    public void addEdge(GraphEdge edge){
        checkRep();
        this.edges.add(edge);
        checkRep();
    }

    /**
     * Returns a list of nodes in the graph
     * @return List containing all GraphNode objects in this
     */
    public List<GraphNode> nodes(){
        checkRep();
        List<GraphNode> l = new LinkedList<>();
        for(int i = 0; i < this.nodes.size(); i++){
            l.add(this.nodes.get(i));
        }
        checkRep();
        return l;
    }

    /**
     * Returns a list of edges in the graph
     * @return List containing all GraphEdge objects in this
     */
    public List<GraphEdge> edges(){
        checkRep();
        List<GraphEdge> l = new LinkedList<>();
        for(int i = 0; i < this.edges.size(); i++){
            l.add(this.edges.get(i));
        }
        checkRep();
        return l;
    }

    /**
     * Returns a list of the nodes that are child nodes
     * of a specific node
     * @param node The node whose child nodes will be returned
     * @spec.requires Given node is in the graph and is not null
     * @return Set containing all children GraphNode objects of n
     */
    public Set<GraphNode> childrenOfNode(GraphNode node){
        checkRep();
        Set<GraphNode> children = new TreeSet<>();
        if(this.nodes.contains(node)){
            GraphNode parent = this.nodes.get(this.nodes.indexOf(node));
            for(int i = 0; i < parent.connections.size(); i++){
                children.add(parent.connections.get(i).end);
            }
        }
        checkRep();
        return children;
    }

    public GraphNode getNode(String name) throws NoSuchFieldException{
        checkRep();
        for(int i = 0; i < this.nodes.size(); i++){
            if(this.nodes.get(i).data.equals(name)){
                return this.nodes.get(i);
            }
        }
        checkRep();
        throw new NoSuchFieldException("This graph does not contain a node with this data");
    }

    public GraphEdge getEdge(String name, GraphNode start, GraphNode end) throws NoSuchFieldException{
        checkRep();
        for(int i = 0; i < this.edges.size(); i++){
            GraphEdge e = this.edges.get(i);
            if(e.data.equals(name) && e.end.equals(end) && start.connections.contains(e)){
                return e;
            }
        }
        checkRep();
        throw new NoSuchFieldException("This graph does not contain an edge with this data, parent, and child");
    }

    /**
     * Represents a node of a directed labeled graph. A node
     * could have an unlimited number of edges originating from
     * it and have an unlimited number of edges connecting to
     * it. Each node could be identified using a label.
     */
    public static class GraphNode {
        private String data;
        private List<GraphEdge> connections;

        /**
         * Creates a node containing a given label
         * @param data The label that identifies the new node
         */
        public GraphNode(String data){
            this.data = data;
            this.connections = new LinkedList<>();
        }

        /**
         * Returns the label of the node
         * @return this.data
         */
        public String getData(){
            return this.data;
        }

        /**
         * Returns every outgoing edge of this
         * @return List containing the outgoing edges of this
         */
        public List<GraphEdge> getEdges(){
            List<GraphEdge> l = new LinkedList<>();
            for(int i = 0; i < this.connections.size(); i++){
                l.add(this.connections.get(i));
            }
            return l;
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
        private String data;
        private GraphNode end;

        /**
         * Creates an edge originating and ending at given nodes,
         * identifiable by a label
         * @param start Node that the new edge will originate from
         * @param end Node that the new edge will travel to
         * @param data Label of the new node
         */
        public GraphEdge(GraphNode start, GraphNode end, String data){
            this.data = data;
            this.end = end;
            start.connections.add(this);
        }

        public String getData(){
            return this.data;
        }


        public GraphNode getEnd(){
            return this.end;
        }
    }
}
