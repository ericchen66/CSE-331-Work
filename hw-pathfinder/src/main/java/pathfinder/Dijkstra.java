package pathfinder;

import graph.*;
import pathfinder.datastructures.Path;
import java.util.*;

/**
 * Represents an application of Dijkstra's algorithm
 * over a graph with node type T. Only supports edges
 * containing data of Double. Aims to find minimum-cost
 * path between two nodes
 * @param <T> Type of node data to run the algorithm on.
 */
public class Dijkstra<T> {
    //Start and destination nodes are stored in Graph<T, Double>.GraphNode
    //objects, while the graph the algorithm runs on is stored as a Graph<T, Double> object.
    //Uses a priority queue to parse through possible minimum-cost paths and a Set to store
    //nodes for which a minimum-cost path from the starting node is known.
    //RI: targetGraph != null and contains start and dest, start, dest != null
    //AF(this):
    //Paths to a certain node = this.active
    //Nodes with known minimum-cost path from start = this.finished
    private Graph<T, Double> targetGraph;
    private Graph<T, Double>.GraphNode start;
    private Graph<T, Double>.GraphNode dest;
    private PriorityQueue<Path<T>> active;
    private Set<Graph<T, Double>.GraphNode> finished;

    private static final boolean DEBUG = false;

    private void checkRep(){
        assert this.start != null : "this.start is null";
        assert this.dest != null : "this.dest is null";
        if(DEBUG) {
            assert targetGraph.getNode(this.start.getData()) != null;
            assert targetGraph.getNode(this.dest.getData()) != null;
        }
    }

    /**
     * Creates a Dijkstra object with no elements in active or finished.
     * Stores given start and end nodes in the object.
     * @param targetGraph The graph that Dijkstra's algorithm will run on
     * @param start Starting node
     * @param dest Ending/Destination node
     */
    public Dijkstra(Graph<T, Double> targetGraph, Graph<T, Double>.GraphNode start, Graph<T, Double>.GraphNode dest){
        this.targetGraph = targetGraph;
        this.start = start;
        this.dest = dest;
        this.active = new PriorityQueue<>();
        this.finished = new HashSet<>();
        checkRep();
    }

    /**
     * Finds and returns the minimum-cost path between this.start and
     * this.dest
     * @spec.modifies this.active, this.finished
     * @return Path<T> representing the minimum-cost path between this.start and this.dest,
     * null if a path does not exist between this.start and this.dest.
     */
    public Path<T> findMinPath(){
        checkRep();
        active.add(new Path<T>(start.getData()));
        Path<T> minPath;
        Graph<T, Double>.GraphNode minDest;

        while (!active.isEmpty()) {
            minPath = active.poll();
            minDest = targetGraph.getNode(minPath.getEnd());

            if (minDest.equals(this.dest)) {
                return minPath;
            }

            for (Graph<T, Double>.GraphEdge e : minDest.getEdges()) {
                if (!finished.contains(e.getEnd())) {
                    Path<T> newPath = minPath.extend(e.getEnd().getData(), e.getData());
                    active.add(newPath);
                }
            }

            finished.add(minDest);
        }
        checkRep();
        return null;
    }
}
