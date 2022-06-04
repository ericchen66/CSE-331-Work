import React, {Component} from 'react';

interface BuildingSelectorProps {
    onChange(edges: any[]): void; //Passes an array of points to the app. To be rendered by Map
}


interface BuildingSelectorState{
    startBuilding: string|undefined //The starting building, undefined at beginning, or reset
    endBuilding: string|undefined //The ending building, undefined at beginning, or reset
}

/**
 * Contains two drop down selectors, each with a list of all buildings
 * on campus, as well as two buttons, one to draw the nearest path between
 * two selected buildings and one to reset the state of the app, as if it
 * was just opened.
 */
class BuildingSelector extends Component<BuildingSelectorProps, BuildingSelectorState> {

    constructor(props: BuildingSelectorProps){
        super(props);
        this.state = {
            startBuilding: undefined,
            endBuilding: undefined,
        };
    }

    //Fetches information on the shortest path between two
    //buildings. Then sends information to App to be rendered by Map.
    async findMinPath(startName: string, endName: string){
        let response = await fetch("http://localhost:4567/minPath?startBuilding="
            + startName + "&endBuilding=" + endName);
        if (!response.ok) {
            return alert("Error! Expected: 200, Was: " + response.status);
        }
        let responseObj = await response.json();
        this.props.onChange(responseObj);
    }

    //Creates two drop down selectors for building selection by the user.
    //Creates two buttons, one to draw the shortest path between two selected
    //buildings and another to reset the state of the app.
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
                    this.setState({startBuilding: undefined}); this.props.onChange([])}}>Reset</button>
            </div>
        );
    }
}

export default BuildingSelector;