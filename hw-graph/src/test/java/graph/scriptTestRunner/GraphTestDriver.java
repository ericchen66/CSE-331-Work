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

package graph.scriptTestRunner;

import graph.Graph;

import java.io.*;
import java.util.*;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph.
 **/
public class GraphTestDriver {

    // ***************************
    // ***  JUnit Test Driver  ***
    // ***************************

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    // TODO for the student: Uncomment and parameterize the next line correctly:
    private final Map<String, Graph> graphs = new HashMap<String, Graph>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new GraphTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public GraphTestDriver(Reader r, Writer w) {
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
        // TODO Insert your code here.
        graphs.put(graphName, new Graph());
        output.println("created graph " + graphName);
        // graphs.put(graphName, ___);
        // output.println(...);
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
        // TODO Insert your code here.
        Graph g = graphs.get(graphName);
        Graph.GraphNode n = new Graph.GraphNode(nodeName);
        g.addNode(n);
        output.println("added node " + nodeName + " to " + graphName);
        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void addEdge(List<String> arguments) throws NoSuchFieldException {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         String edgeLabel) throws NoSuchFieldException{
        // TODO Insert your code here.
        Graph g = graphs.get(graphName);
        Graph.GraphNode parent = g.getNode(parentName);
        Graph.GraphNode child = g.getNode(childName);
        output.println("added edge " + edgeLabel + " from " + parentName + " to " + childName + " in " + graphName);
        Graph.GraphEdge e = new Graph.GraphEdge(parent, child, edgeLabel);

        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        // TODO Insert your code here.
        Graph g = graphs.get(graphName);
        List<Graph.GraphNode> nodes = g.nodes();
        SortedSet<String> sortedNodeNames = new TreeSet<>();
        output.print(graphName + " contains:");
        for(int i = 0; i < nodes.size(); i++){
            sortedNodeNames.add(nodes.get(i).getData());
        }
        for(String s : sortedNodeNames){
            output.print(" " + s);
        }
        output.println();
        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void listChildren(List<String> arguments) throws NoSuchFieldException{
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) throws NoSuchFieldException{
        // TODO Insert your code here.
        output.print("the children of " + parentName + " in " + graphName + " are:");
        Graph g = graphs.get(graphName);
        Graph.GraphNode parent = g.getNode(parentName);
        List<Graph.GraphEdge> edges = parent.getEdges();

        Map<String, String> childrenAndEdges= new TreeMap<>();
        for(int i = 0; i < edges.size(); i++) {
            childrenAndEdges.put(edges.get(i).getData(), edges.get(i).getEnd().getData());
        }

        //SortedSet<String> sortedNames = new TreeSet<>();
        //SortedSet<String> sortedEdgeNames = new TreeSet<>();
        for(String edgeLabel : childrenAndEdges.keySet()){
            output.print(" " + childrenAndEdges.get(edgeLabel) + "(" + edgeLabel + ")");
        }
        output.println();

        // ___ = graphs.get(graphName);
        // output.println(...);
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
