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

interface EdgeListProps {
    onChange(edges: number[]): void;  // called when a new edge list is ready
                                 // TODO: once you decide how you want to communicate the edges to the App, you should
                                 // change the type of edges so it isn't `any`
}

//boxText: the text currently in the text area
//warningText: the warning to be displayed if user enters invalid edge
interface EdgeListState{
    startBuilding: string|undefined
    endBuilding: string|undefined
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 * This file will parse a (valid) user input into an array as well.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {

    constructor(props: EdgeListProps){
        super(props);
        this.state = {
            startBuilding: undefined,
            endBuilding: undefined,
        };
    }

    async findMinPath(startName: string, endName: string){
        let response = await fetch("http://localhost:4567/minPath?startBuilding="
            + startName + "&endBuilding=" + endName);
        if (!response.ok) {
            return alert("Error! Expected: 200, Was: " + response.status);
        }
        let responseObj = await response.json();
        this.props.onChange(responseObj);
    }

    //Creates the text area where a user could type in edges
    //as well as the draw and clear buttons. If at least one line of user
    //input (a blank line is counted as a line as well!) is invalid
    //while the draw button is clicked, a warning will pop up above
    //the text area indicating the invalid line and what mistake should be fixed.
    render() {
        return (
            <div id ="edge-list">
                <div>
                    <label>Starting Building</label>
                    <br/>
                    <select id = "startBuilding" onChange={event => this.setState({startBuilding: event.target.value})}>Start
                        <option value = "Default">Select a Building</option>
                    <option value = "BAG">Bagley Hall (East Entrance)</option>
                    <option value = "BAG (NE)">Bagley Hall (Northeast Entrance)</option>
                    <option value = "BGR">By George</option>
                    <option value = "CSE">Paul G. Allen Center for Computer Science & Engineering</option>
                    <option value = "CS2">Bill & Melinda Gates Center For Computer Science & Engineering</option>
                    <option value = "DEN">Denny Hall</option>
                    <option value = "EEB">Electrical Engineering Building (North Entrance)</option>
                    <option value = "EEB (S)">Electrical Engineering Building (South Entrance)</option>
                    <option value = "GWN">Gowen Hall</option>
                    <option value = "KNE">Kane Hall (North Entrance)</option>
                    <option value = "KNE (E)">Kane Hall (East Entrance)</option>
                    <option value = "KNE (SE)">Kane Hall (Southeast Entrance)</option>
                    <option value = "KNE (S)">Kane Hall (South Entrance)</option>
                    <option value = "KNE (SW)">Kane Hall (Southwest Entrance)</option>
                    <option value = "LOW">Loew Hall</option>
                    <option value = "MGH">Mary Gates Hall (North Entrance)</option>
                    <option value = "MGH (E)">Mary Gates Hall (East Entrance)</option>
                    <option value = "MGH (S)">Mary Gates Hall (South Entrance)</option>
                    <option value = "MGH (SW)">Mary Gates Hall (Southwest Entrance)</option>
                    <option value = "MLR">Miller Hall</option>
                    <option value = "MOR">Moore Hall</option>
                    <option value = "MUS">Music Building (Northwest Entrance)</option>
                    <option value = "MUS (E)">Music Building (East Entrance)</option>
                    <option value = "MUS (SW)">Music Building (Southwest Entrance)</option>
                    <option value = "MUS (S)">Music Building (South Entrance)</option>
                    <option value = "OUG">Odegaard Undergraduate Library</option>
                    <option value = "PAA">Physics/Astronomy Building A</option>
                    <option value = "PAB">Physics/Astronomy Building</option>
                    <option value = "SAV">Savery Hall</option>
                    <option value = "SUZ">Suzzallo Library</option>
                    <option value = "T65">Thai 65</option>
                    <option value = "FSH">Fishery Sciences Building</option>
                    <option value = "MCC">McCarty Hall (Main Entrance)</option>
                    <option value = "MCC (S)">McCarty Hall (South Entrance)</option>
                    <option value = "UBS">University Bookstore</option>
                    <option value = "UBS (Secret)">University Bookstore (Secret Entrance)</option>
                    <option value = "RAI">Raitt Hall (West Entrance)</option>
                    <option value = "RAI (E)">Raitt Hall (East Entrance)</option>
                    <option value = "ROB">Roberts Hall</option>
                    <option value = "CHL">Chemistry Library (West Entrance)</option>
                    <option value = "CHL (NE)">Chemistry Library (Northeast Entrance)</option>
                    <option value = "CHL (SE)">Chemistry Library (Southeast Entrance)</option>
                    <option value = "IMA">Intramural Activities Building</option>
                    <option value = "HUB">Student Union Building (Main Entrance)</option>
                    <option value = "HUB (West Food)">Student Union Building (West Food Entrance)</option>
                    <option value = "HUB (South Food)">Student Union Building (South Food Entrance)</option>
                    <option value = "MNY">Meany Hall (Northeast Entrance)</option>
                    <option value = "MNY (NW)">Meany Hall (Northwest Entrance)</option>
                    <option value = "PAR">Parrington Hall</option>
                    <option value = "MCM">McMahon Hall (Northwest Entrance)</option>
                    <option value = "MCM (SW)">McMahon Hall (Southwest Entrance)</option>
                    <option value = "CMU">Communications Building</option>
                </select>
                </div>
                <br/>
                <div>
                    <label>Destination Building</label>
                    <br/>
                    <select id = "endBuilding" onChange={event => this.setState({endBuilding: event.target.value})}>End
                        <option value = "Default">Select a Building</option>
                    <option value = "BAG">Bagley Hall (East Entrance)</option>
                    <option value = "BAG (NE)">Bagley Hall (Northeast Entrance)</option>
                    <option value = "BGR">By George</option>
                    <option value = "CSE">Paul G. Allen Center for Computer Science & Engineering</option>
                    <option value = "CS2">Bill & Melinda Gates Center For Computer Science & Engineering</option>
                    <option value = "DEN">Denny Hall</option>
                    <option value = "EEB">Electrical Engineering Building (North Entrance)</option>
                    <option value = "EEB (S)">Electrical Engineering Building (South Entrance)</option>
                    <option value = "GWN">Gowen Hall</option>
                    <option value = "KNE">Kane Hall (North Entrance)</option>
                    <option value = "KNE (E)">Kane Hall (East Entrance)</option>
                    <option value = "KNE (SE)">Kane Hall (Southeast Entrance)</option>
                    <option value = "KNE (S)">Kane Hall (South Entrance)</option>
                    <option value = "KNE (SW)">Kane Hall (Southwest Entrance)</option>
                    <option value = "LOW">Loew Hall</option>
                    <option value = "MGH">Mary Gates Hall (North Entrance)</option>
                    <option value = "MGH (E)">Mary Gates Hall (East Entrance)</option>
                    <option value = "MGH (S)">Mary Gates Hall (South Entrance)</option>
                    <option value = "MGH (SW)">Mary Gates Hall (Southwest Entrance)</option>
                    <option value = "MLR">Miller Hall</option>
                    <option value = "MOR">Moore Hall</option>
                    <option value = "MUS">Music Building (Northwest Entrance)</option>
                    <option value = "MUS (E)">Music Building (East Entrance)</option>
                    <option value = "MUS (SW)">Music Building (Southwest Entrance)</option>
                    <option value = "MUS (S)">Music Building (South Entrance)</option>
                    <option value = "OUG">Odegaard Undergraduate Library</option>
                    <option value = "PAA">Physics/Astronomy Building A</option>
                    <option value = "PAB">Physics/Astronomy Building</option>
                    <option value = "SAV">Savery Hall</option>
                    <option value = "SUZ">Suzzallo Library</option>
                    <option value = "T65">Thai 65</option>
                    <option value = "FSH">Fishery Sciences Building</option>
                    <option value = "MCC">McCarty Hall (Main Entrance)</option>
                    <option value = "MCC (S)">McCarty Hall (South Entrance)</option>
                    <option value = "UBS">University Bookstore</option>
                    <option value = "UBS (Secret)">University Bookstore (Secret Entrance)</option>
                    <option value = "RAI">Raitt Hall (West Entrance)</option>
                    <option value = "RAI (E)">Raitt Hall (East Entrance)</option>
                    <option value = "ROB">Roberts Hall</option>
                    <option value = "CHL">Chemistry Library (West Entrance)</option>
                    <option value = "CHL (NE)">Chemistry Library (Northeast Entrance)</option>
                    <option value = "CHL (SE)">Chemistry Library (Southeast Entrance)</option>
                    <option value = "IMA">Intramural Activities Building</option>
                    <option value = "HUB">Student Union Building (Main Entrance)</option>
                    <option value = "HUB (West Food)">Student Union Building (West Food Entrance)</option>
                    <option value = "HUB (South Food)">Student Union Building (South Food Entrance)</option>
                    <option value = "MNY">Meany Hall (Northeast Entrance)</option>
                    <option value = "MNY (NW)">Meany Hall (Northwest Entrance)</option>
                    <option value = "PAR">Parrington Hall</option>
                    <option value = "MCM">McMahon Hall (Northwest Entrance)</option>
                    <option value = "MCM (SW)">McMahon Hall (Southwest Entrance)</option>
                    <option value = "CMU">Communications Building</option>
                </select>
                </div>
                <br/>
                <button onClick={event => {
                    if (this.state.startBuilding !== undefined && this.state.startBuilding !== "Default"
                        && this.state.endBuilding !== undefined && this.state.endBuilding !== "Default") {
                        this.findMinPath(this.state.startBuilding, this.state.endBuilding);
                    }
                    }}>
                    Draw</button>
                <button onClick={event => {
                    const select1 = document.querySelector('#startBuilding');
                    const select2 = document.querySelector('#endBuilding');
                    // @ts-ignore
                    select1.value = 'Default'
                    // @ts-ignore
                    select2.value = 'Default'
                    this.setState({endBuilding: undefined});
                    this.setState({startBuilding: undefined}); this.props.onChange([])}}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
