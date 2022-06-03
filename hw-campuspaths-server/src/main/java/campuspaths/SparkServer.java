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

import java.util.Set;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().
        CampusMap map = new CampusMap();
        Gson gson = new Gson();

        Spark.get("/buildings", (req, res) -> {
            String longName = req.queryParams("buildingName");
            Set<String> shortNames = map.buildingNames().keySet();
            for(String shortName : shortNames){
                if(map.longNameForShort(shortName).equals(longName)){
                    return gson.toJson(shortName);
                }
            }
            return null;
        });

        Spark.get("/minPath", (req, res) -> {
            String start = req.queryParams("startBuilding");
            String end = req.queryParams("endBuilding");
            Path<Point> shortestPath= map.findShortestPath(start, end);
            return gson.toJson(shortestPath);
        });

        // TODO: Create all the Spark Java routes you need here.
    }

}
