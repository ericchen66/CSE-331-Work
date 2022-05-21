/*
 * Copyright (C) 2022 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder.scriptTestRunner;

import graph.Graph;
import pathfinder.Dijkstra;
import pathfinder.datastructures.Path;

import java.io.*;
import java.rmi.NoSuchObjectException;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {
    private final Map<String, Graph<String, Double>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;

    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        // TODO: Implement this, reading commands from `r` and writing output to `w`.
        // See GraphTestDriver as an example.
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effects Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        graphs.put(graphName, new Graph<>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        Graph<String, Double> g = graphs.get(graphName);
        Graph<String, Double>.GraphNode n = g.new GraphNode(nodeName);
        g.addNode(n);
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments){
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        Double edgeLabel = Double.parseDouble(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel){
        Graph<String, Double> g = graphs.get(graphName);
        Graph<String, Double>.GraphNode parent = g.getNode(parentName);
        Graph<String, Double>.GraphNode child = g.getNode(childName);
        Graph<String, Double>.GraphEdge e = g.new GraphEdge(parent, child, edgeLabel);
        g.addEdge(e);
        output.println("added edge " + String.format("%.3f", edgeLabel) + " from " + parentName + " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {

        Graph<String, Double> g = graphs.get(graphName);
        List<Graph<String, Double>.GraphNode> nodes = g.nodes();
        SortedSet<String> sortedNodeNames = new TreeSet<>();
        output.print(graphName + " contains:");
        for(int i = 0; i < nodes.size(); i++){
            sortedNodeNames.add(nodes.get(i).getData());
        }
        for(String s : sortedNodeNames){
            output.print(" " + s);
        }
        output.println();

    }

    private void listChildren(List<String> arguments){
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName){

        output.print("the children of " + parentName + " in " + graphName + " are:");
        Graph<String, Double> g = graphs.get(graphName);
        Graph<String, Double>.GraphNode parent = g.getNode(parentName);
        List<Graph<String, Double>.GraphEdge> edges = parent.getEdges();

        SortedSet<String> sortedChildren = new TreeSet<>();
        for(int i = 0; i < edges.size(); i++) {
            sortedChildren.add(edges.get(i).getEnd().getData() + "(" + String.format("%.3f", edges.get(i).getData()) + ")");
        }

        for(String labels : sortedChildren){
            output.print(" " + labels);
        }
        output.println();

    }

    private void findPath(List<String> arguments){
        if(arguments.size() != 3) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);

        findPath(graphName, parentName, childName);
    }

    private void findPath(String graphName, String parentName, String childName){
        Graph<String, Double> g = graphs.get(graphName);
        Set<String> nodeLabels = new HashSet<>();
        for(Graph<String, Double>.GraphNode node : g.nodes()){
            nodeLabels.add(node.getData());
        }

        if(!nodeLabels.contains(parentName) && !nodeLabels.contains(childName)){
            output.println("unknown: node");
            output.println("unknown: node");
        }else if(!nodeLabels.contains(parentName) || !nodeLabels.contains(childName)){
            output.println("unknown: node");
        }else if(parentName.equals(childName)){
            output.println("path from " + parentName + " to " + parentName + ":");
            output.println("total cost: 0.000");
        }else{
            Dijkstra<String> pathFinder = new Dijkstra<>(g, g.getNode(parentName), g.getNode(childName));
            output.println("path from " + parentName + " to " + childName + ":");


            Path<String> minPath = pathFinder.findMinPath();
            if(minPath == null){
                output.println("no path found");
            }else {
                Iterator<Path<String>.Segment> minPathSegments = minPath.iterator();
                double total = 0.0;
                while (minPathSegments.hasNext()) {
                    Path<String>.Segment minPathSegment = minPathSegments.next();
                    total += minPathSegment.getCost();
                    output.println(minPathSegment.getStart() + " to " + minPathSegment.getEnd() + " with weight " + String.format("%.3f", minPathSegment.getCost()));
                }
                output.println("total cost: " + String.format("%.3f", total));
            }
        }
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
