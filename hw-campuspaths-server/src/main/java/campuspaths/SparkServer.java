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

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Spark;

import java.util.LinkedList;
import java.util.List;

/**
 * Java server that sends info to the Campus Paths app.
 * Used for finding shortest path between two buildings on
 * the UW campus.
 */
public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        // Creates a CampusMap object to calculate the shortest path between two buildings
        CampusMap map = new CampusMap();
        Gson gson = new Gson();

        // Returns the shortest path between two buildings as a list of points.
        // Returns a 404 error if either startBuilding or endBuilding is not defined
        // as a query parameter
        Spark.get("/minPath", (req, res) -> {
            String start = req.queryParams("startBuilding");
            String end = req.queryParams("endBuilding");
            Path<Point> shortestPath= map.findShortestPath(start, end);
            List<Object> segments = new LinkedList<>();
            for(Path.Segment s : shortestPath){
                segments.add(s.getStart());
                segments.add(s.getEnd());
            }
            return gson.toJson(segments);
        });

        // TODO: Create all the Spark Java routes you need here.
    }

}
