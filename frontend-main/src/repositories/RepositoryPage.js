
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
import TextField from "@material-ui/core/TextField";

class RepositoryPage extends Component {
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    }

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            frmcrbr: "form-popup-none",
            repo: "",
            files: [],
            nbranch: "",
            currentBranch: "",
            currentCommit: "",
            dropdownOpen: false,
            labelField: "awdawd",
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
        this.toggle = this.toggle.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
        this.handlebranchchange = this.handlebranchchange.bind(this);
        this.createBrHnld = this.createBrHnld.bind(this);
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

    async getFilesFromCommit(a, commit_id) {
        return await fetch('/api/private/commit/files?commitId='+commit_id, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }


    async componentDidMount() {
        const id = this.props.match.params.id;

        const cbranch = this.props.match.params.branch;
        const ccommit = this.props.match.params.commit;

        // console.log("br" + cbranch);

        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        if (a) {
            const _repos = await this.getRepositoryInfo(a, id);
            // console.log(_repos);
            this.setState({ repo: _repos });
            // console.log(this.state.repo);

            this.setState({loading: false});
        } else {
            // pass
        }

        if (cbranch !== undefined) {
            this.setState({currentBranch: cbranch});
        } else {
            this.setState({currentBranch: this.state.repo.mainBranch});
        }
        if (ccommit !== undefined) {
            this.setState({currentCommit: ccommit});
        } else {
            let _branches = this.state.repo.branches;

            this.setState({currentCommit: _branches.find((elem) => {
                    if (elem.name === this.state.currentBranch) {return true;} else {return false;}
                }).head
            });
        }

        let files = await this.getFilesFromCommit(a, this.state.currentCommit);
        this.setState({files:files});
    }

    toggle() {
        // this.setState(prevState => ({
        //     dropdownOpen: !prevState.dropdownOpen
        // }));
        this.setState({dropdownOpen:true});
        // console.log("1");
    }

    // could write this as an arrow function to avoid binding issue
    // handleKeyDown = (e) => { ... }
    handleKeyDown(e) {
        const { keyCode } = e;
        // console.log('keyCode:', e.keyCode);
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

        let arr =[];
        for(let j = 0; j < k; j++) {
            if (branches[j].name == this.state.currentBranch) {continue;}
            arr.push(branches[j]);
        }
        console.log(arr);
        branches = arr;
        branchesList.push(
            <option>{this.state.currentBranch}</option>
        );

        for(let i = 0; i < branches.length; i++) {
            branchesList.push(
                <option>{branches[i].name}</option>
            );
        }
        branchesList.push(
            <option>Создать ветку</option>
        );
        return branchesList;
    }

     renderFiles() {
        let files = this.state.files;
        let filesList = [];

        console.log(files);

        let k = 0;
        try {
            k = files.length;
        } catch (e) {
            k = 0;
        }
        for(let i = 0; i < k; i++) {
            filesList.push(
                <div className="card">
                    <div className="card-body">
                        <a href={"/repository/"+this.state.repo.id+"/" + this.state.currentBranch + "/" + this.state.currentCommit + "/file/" + files[i].filderId}>{files[i].name}</a>

                        <span className="mspn"></span>
                    </div>
                </div>
            );
        }
        return filesList;
    }

    handlebranchchange(event) {
        if (event.target.value == "Создать ветку") {
            if (this.state.frmcrbr == "form-popup-none") {
                this.setState({frmcrbr : "form-popup"});
            } else {
                this.setState({frmcrbr : "form-popup-none"});
            }
        } else {
            window.location = '/repository/'+this.state.repo.id+'/'+event.target.value;
        }
    }

    async createBrHnld() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let body = {
            repoId:this.state.repo.id,
            name:this.state.nbranch
        };

        await fetch('/api/private/branch/create', {
            method: 'post',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify(body)
        }).then(window.location = window.location = '/repository/'+this.state.repo.id+'/');
    }

    render() {
        const {code, description} = this.state;
        const {repo, dropdownOpen, currentBranch,
            currentCommit, items, frmcrbr} = this.state;
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
                                            defaultValue={this.state.currentBranch}
                                            onChange={this.handlebranchchange } >
                                            { this.renderBranches() }
                                        </StyledHtmlSelect>

                                        <a href={"/repository/"+repo.id+"/" + currentBranch + "/commits"} className="commit-name">commit #{currentCommit}</a>
                                    </div>

                                    <div id="myForm1" className={frmcrbr}>
                                        <h5>Создание ветки</h5>
                                        {/*<input type="text" value={this.state.value} placeholder="Введите ваше название" name="name" onChange={this.handleInputChange} required /> <br />*/}
                                        <TextField
                                            variant="outlined"
                                            margin="normal"
                                            required
                                            fullWidth
                                            id="name"
                                            label="Введите название ветки"
                                            name="_br_name"
                                            onChange={event => {
                                                this.setState({
                                                    nbranch: event.target.value
                                                });
                                            }}
                                            autoFocus
                                        />

                                        <button type="submit" className="btn"
                                                onClick={this.createBrHnld}
                                        >Создать</button>
                                    </div>


                                    <div className="card-body mcd">
                                        {this.renderFiles()}
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