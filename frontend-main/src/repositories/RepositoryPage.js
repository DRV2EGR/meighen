
import React, {Component} from 'react';
import {Route, Switch} from "react-router-dom";
import {withRouter} from "react-router";
import PropTypes from "prop-types";
import {Cookies} from "react-cookie";
import Select from "react-dropdown-select";
import styled from "@emotion/styled";
import Header from "../header/Header";
// import Dropdown from 'react-css-dropdown';
// import {withRouter} from "react-router-dom";
import './RepositoryPage.css';

import AuthElement from "../auth_element_component/AuthElement";

class RepositoryPage extends Component {
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    }

    constructor(props) {
        super(props);
        this.state = {
            repo: "",
            files: [],
            currentBranch: "main",
            currentCommit: "",
            dropdownOpen: false,
            labelField: "awdawd",
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
        this.toggle = this.toggle.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
        // console.log(this.props)
    }

    async getRepositoryInfo(a, id) {
        return await fetch('/api/private/repos/repo?repoId='+id, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        const id = this.props.match.params.id;

        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        if (a) {
            const _repos = await this.getRepositoryInfo(a, id);
            console.log(_repos);
            this.setState({ repo: _repos });
            console.log(this.state.repo);

            this.setState({loading: false});
        } else {
            // pass
        }

    }

    toggle() {
        // this.setState(prevState => ({
        //     dropdownOpen: !prevState.dropdownOpen
        // }));
        this.setState({dropdownOpen:true});
        console.log("1");
    }

    // could write this as an arrow function to avoid binding issue
    // handleKeyDown = (e) => { ... }
    handleKeyDown(e) {
        const { keyCode } = e;
        console.log('keyCode:', e.keyCode);
        const enterKeyCode = 13;
        if (keyCode === enterKeyCode) {
            this.toggle();
        }
    }

    renderBranches() {
        let branches = this.state.repo.branches;
        let branchesList = [];

        let k = 0;
        try {
            k = branches.length;
        } catch (e) {
            k = 0;
        }

        for(let i = 0; i < k; i++) {
            branchesList.push(
                <option>{branches[i].name}</option>
            );
        }
        return branchesList;
    }

    renderFiles() {
        let files = this.state.files;
        let filesList = [];

        let k = 0;
        try {
            k = files.length;
        } catch (e) {
            k = 0;
        }
        for(let i = 0; i < k; i++) {
            filesList.push(

            );
        }
        return filesList;
    }

    render() {
        const {code, description} = this.state;
        const {repo, dropdownOpen, currentBranch} = this.state;
        return (
            <div>
                <AuthElement />
                <Header />

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                            <h2>Репозиторий: {repo.name}</h2>
                            <div className='row justify-content-center'>
                                <br/>
                            <div className='col-md-12 text-center'>
                                <div className="card">
                                    {/*<h5 className="card-header">Featured</h5>*/}
                                    <div className="mmcard">
                                        <StyledHtmlSelect
                                            defaultValue={this.state.labelField}
                                            onChange={event => this.setState({
                                                    currentBranch: event.target.value,
                                                    searchBy: event.target.value, }) } >
                                            { this.renderBranches() }
                                        </StyledHtmlSelect>

                                        <a href={"/repository/"+repo.id+"/" + currentBranch + "/commits"} className="commit-name">commit #768b5835-9736-4572-b3a9-a30f732c38d5</a>
                                    </div>


                                    <div className="card-body">

                                        <div className="card">
                                            <div className="card-body">
                                                <span>hsejf.js</span>

                                                <span></span>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
const StyledHtmlSelect = styled.select`
  padding: 0;
  margin: 0 0 0 10px;
  height: 30px !important;
  color: #121212;
  background: #fff;
  border: 1px solid #0071dc;
  width: 200px;
  border-radius: 10px
`;

export default (RepositoryPage);