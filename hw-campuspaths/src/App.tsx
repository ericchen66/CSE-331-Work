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

import React, {Component} from 'react';
import BuildingSelector from "./BuildingSelector";
import Map from "./Map";

// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    edges: any[] //Contains each of the points in the shortest path between two selected buildings
}

/**
 * Creates all components of the Campus Paths app and passes
 * information from BuildingSelector to Map.
 */
class App extends Component<{}, AppState> {

    constructor(props: any) {
        super(props);
        this.state = {
            edges: []
        };
    }

    //Adds all components of the Campus Paths app to the webpage
    render() {
        return (
            <div>
                <h1 id="app-title">Campus Paths Mapper!</h1>
                <div>
                    <Map edges = {this.state.edges} />
                </div>
                <BuildingSelector
                    onChange={(path) => {
                        this.setState({edges: path});
                    }}
                />
            </div>
        );
    }
}

export default App;
