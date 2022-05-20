package pathfinder;

import graph.*;
import pathfinder.datastructures.Path;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Dijkstra<T> {

    private Graph<T, Double> targetGraph;
    private Graph<T, Double>.GraphNode start;
    private Graph<T, Double>.GraphNode dest;
    private PriorityQueue<Path<T>> active;
    private Set<Graph<T, Double>.GraphNode> finished;

    public Dijkstra(Graph<T, Double> targetGraph, Graph<T, Double>.GraphNode start, Graph<T, Double>.GraphNode dest){
        this.targetGraph = targetGraph;
        this.start = start;
        this.dest = dest;
        this.active = new PriorityQueue<>();
        this.finished = new HashSet<>();
    }

    public Path<T> findMinPath() throws NoSuchFieldException, NoSuchObjectException {
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

        throw new NoSuchObjectException("No path exists from start to destination");


    }
}
