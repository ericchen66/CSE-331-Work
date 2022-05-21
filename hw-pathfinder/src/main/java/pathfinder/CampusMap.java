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

package pathfinder;

import graph.*;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusMap implements ModelAPI {

    private List<CampusBuilding> buildings;
    private List<CampusPath> paths;
    private Graph<Point, Double> map;

    private Map<String, Graph<Point, Double>.GraphNode> nodes;

    /**
     * Creates a CampusMap object containing a list of buildings from campus_buildings.csv
     * and a list of possible paths on campus from campus_paths.csv.
     */
    public CampusMap(){
        this.buildings = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
        this.paths = CampusPathsParser.parseCampusPaths("campus_paths.csv");
        this.map = new Graph<>();

        for(CampusPath path : this.paths){
            Point location1 = new Point(path.getX1(), path.getY1());
            Point location2 = new Point(path.getX2(), path.getY2());

            if(map.getNode(location1) == null){
                map.addNode(map.new GraphNode(location1));
            }
            if(map.getNode(location2) == null){
                map.addNode(map.new GraphNode(location2));
            }
            map.addEdge(map.new GraphEdge(map.getNode(location1), map.getNode(location2), path.getDistance()));
        }
        this.mapStringToNode();
    }

    /**
     * @param shortName The short name of a building to query.
     * @return {@literal true} iff the short name provided exists in this campus map.
     */
    @Override
    public boolean shortNameExists(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        for(CampusBuilding building : buildings){
            if(shortName.equals(building.getShortName())){
                return true;
            }
        }
        return false;
    }

    /**
     * @param shortName The short name of a building to look up.
     * @return The long name of the building corresponding to the provided short name.
     * @throws IllegalArgumentException if the short name provided does not exist.
     */
    @Override
    public String longNameForShort(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        for(CampusBuilding building : this.buildings){
            if(shortName.equals(building.getShortName())){
                return building.getLongName();
            }
        }
        throw new IllegalArgumentException("Short name provided does not exist");
    }

    /**
     * @return A mapping from all the buildings' short names to their long names in this campus map.
     */
    @Override
    public Map<String, String> buildingNames() {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        Map<String, String> names = new HashMap<>();
        for(CampusBuilding building : this.buildings){
            names.put(building.getShortName(), building.getLongName());
        }
        return names;
    }

    /**
     * Finds the shortest path, by distance, between the two provided buildings.
     *
     * @param startShortName The short name of the building at the beginning of this path.
     * @param endShortName   The short name of the building at the end of this path.
     * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
     * if none exists.
     * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
     *                                  {@literal null}, or not valid short names of buildings in
     *                                  this campus map.
     */
    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName){
        // TODO: Implement this method exactly as it is specified in ModelAPI
        if(startShortName == null || endShortName == null) {
            throw new IllegalArgumentException("Null building name");
        }else if(!this.shortNameExists(startShortName) || !this.shortNameExists(endShortName)){
            throw new IllegalArgumentException("Invalid building name");
        }

        Graph<Point, Double>.GraphNode start = this.nodes.get(startShortName);
        Graph<Point, Double>.GraphNode end = this.nodes.get(endShortName);
        Dijkstra<Point> shortestPath = new Dijkstra<>(this.map, start, end);

        return shortestPath.findMinPath();
    }

    //Private helper method to map names of buildings to a GraphNode object
    private void mapStringToNode(){
        this.nodes = new HashMap<>();
        for(CampusBuilding building : this.buildings){
            for(Graph<Point, Double>.GraphNode node : this.map.nodes()){
                if(building.getX() == node.getData().getX() && building.getY() == node.getData().getY()){
                    nodes.put(building.getShortName(), node);
                }
            }
        }
    }
}
