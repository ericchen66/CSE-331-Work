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
    onChange(edges: string[]): void;  // called when a new edge list is ready
                                 // TODO: once you decide how you want to communicate the edges to the App, you should
                                 // change the type of edges so it isn't `any`
}

//boxText: the text currently in the text area
//warningText: the warning to be displayed if user enters invalid edge
interface EdgeListState{
    boxText: string
    warningText: string
    startBuilding: string
    endBuilding: string
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
            boxText: "",
            warningText: "",
            startBuilding: "",
            endBuilding: ""
        };
    }


    async findBuilding(buildingLongName: string){
        let response = await fetch("http://localhost:4567/buildings?buildingName=" + buildingLongName);
        if (!response.ok) {
            alert("Error! Expected: 200, Was: " + response.status);
            return;
        }
        let responseText = await response.text();
        return responseText;
    }


    //Creates the text area where a user could type in edges
    //as well as the draw and clear buttons. If at least one line of user
    //input (a blank line is counted as a line as well!) is invalid
    //while the draw button is clicked, a warning will pop up above
    //the text area indicating the invalid line and what mistake should be fixed.
    render() {
        return (
            <div id="edge-list">
                <p id="warning">{this.state.warningText}</p>
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={event => {this.setState({boxText: event.target.value})}}
                    value={this.state.boxText}
                /> <br/>
                <p>{this.state.startBuilding}</p>
                <button onClick={event => {
                    let text: any[] = this.state.boxText.split(`\n`);
                    let isFormatted: boolean = true;
                    for(let i: number = 0; i < text.length; i++){
                        text[i] = text[i].trim();
                        let line: any[] = text[i].split(` `);
                        if(line.length !== 5){
                            isFormatted = false;
                            this.setState({warningText: `Line ${i+1} does not have 5 elements!`})
                            break;
                        }else if(isNaN(line[0]) || isNaN(line[1]) || isNaN(line[2]) || isNaN(line[3])){
                            isFormatted = false;
                            this.setState({warningText: `Line ${i+1} contains a coordinate that is not
                            a number!`})
                            break;
                        }else if(!isNaN(line[4]) || typeof line[4] !== "string") {
                            isFormatted = false;
                            this.setState({warningText: `Line ${i+1} contains an invalid color!`})
                            break;
                        }else if((line[0] < 0 || line[0] > 4000) || (line[1] < 0 || line[1] > 4000) ||
                            (line[2] < 0 || line[2] > 4000) || (line[3] < 0 || line[3] > 4000)){
                            isFormatted = false;
                            this.setState({warningText: `Line ${i+1} contains a coordinate not
                                between 0 and 4000!`})
                            break;
                        }
                    }
                    if(isFormatted){
                        this.setState({warningText: ""});
                        this.props.onChange(text);
                    }else{
                        this.props.onChange([]);
                    }
                    }}>
                    Draw</button>
                <button onClick={event => {this.setState({boxText: ""});
                    this.setState({warningText: ""}); this.props.onChange([])}}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
