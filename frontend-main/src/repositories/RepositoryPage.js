
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

//import classnames function
function classNames () {
    var hasOwn = {}.hasOwnProperty;
    var classes = [];

    for (var i = 0; i < arguments.length; i++) {
        var arg = arguments[i];
        if (!arg) continue;

        var argType = typeof arg;

        if (argType === 'string' || argType === 'number') {
            classes.push(arg);
        } else if (Array.isArray(arg) && arg.length) {
            var inner = classNames.apply(null, arg);
            if (inner) {
                classes.push(inner);
            }
        } else if (argType === 'object') {
            for (var key in arg) {
                if (hasOwn.call(arg, key) && arg[key]) {
                    classes.push(key);
                }
            }
        }
    }

    return classes.join(' ');
}

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
            mischecked: false,
            curBrId: -1,
            isToggleOn: false,
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
        this.toggle = this.toggle.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
        this.handlebranchchange = this.handlebranchchange.bind(this);
        this.createBrHnld = this.createBrHnld.bind(this);
        this.mdownloadFile = this.mdownloadFile.bind(this);
        this.mdownloadAllCommit = this.mdownloadAllCommit.bind(this);
        this.renderSettingsBtn = this.renderSettingsBtn.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.createClHnld = this.createClHnld.bind(this);


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
                }).head,
                curBrId: _branches.find((elem) => {
                    if (elem.name === this.state.currentBranch) {return true;} else {return false;}
                }).branchId
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
            if (branches[j].name == this.state.currentBranch) {
                continue;
            }
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
                <div className="card mwi">
                    <div className="card-body">
                        <a href={"/repository/"+this.state.repo.id+"/" + this.state.currentBranch + "/" + this.state.currentCommit + "/file/" + files[i].fileId}>{files[i].name}</a>

                        <span className="mspn"></span>

                        {/*<span onClick={(e) => this.mdownloadFile(files[i].fileId, files[i].name, e)}>*/}
                        {/*    /!*<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"*!/*/}
                        {/*    /!*     className="bi bi-file-earmark-arrow-down mdwn" viewBox="0 0 16 16">*!/*/}
                        {/*    /!*    <path*!/*/}
                        {/*    /!*        d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>*!/*/}
                        {/*    /!*    <path*!/*/}
                        {/*    /!*        d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>*!/*/}
                        {/*    /!*</svg>*!/*/}
                        {/*</span>*/}


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

    async createClHnld() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        await fetch('/api/private/repos/add_collaborator?email='+this.state.coemail+'&repoId='+this.state.repo.id, {
            method: 'post',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            })
        });
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

    mdownloadFile(fileid, name) {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        fetch('/api/private/commit/download?fileId='+fileid+'&fileName='+name, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        })
            .then(response => {
                const filename =  'commiting.zip';//response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url=  window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            });
    }

    mdownloadAllCommit(event) {
        if (this.state.mischecked) { this.setState({mischecked:false}); return;}
        this.setState({mischecked:true});
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        fetch('/api/private/commit/download_zip?commitId='+this.state.currentCommit, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        })
            .then(response => {
                const filename =  'commiting.zip';//response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url=  window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            });
    }

    renderSettingsBtn() {
        if(!this.state.repo.hasOwnProperty('owner')){
            return <img className="mimg" onClick={this.handleClick.bind(null, !this.state.isToggleOn)} src="http://habrastorage.org/files/7cd/799/944/7cd79994458f4fc6a9345aa7444650a3.png" />;

        }
    }

    handleClick(isActive) {
        this.setState({ isToggleOn: isActive });
    }

    render() {
        const {code, description} = this.state;
        const {repo, dropdownOpen, currentBranch,
            currentCommit, items, frmcrbr} = this.state;

        let className = classNames('wrapper', {
            'wrapper--open': this.state.isToggleOn
        });
        return (
            <div>
                <AuthElement />
                <Header />

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                        <div className="r-div-repo-name">
                            <h2>Репозиторий: {repo.name}</h2> {this.renderSettingsBtn()}
                        </div>
                        <div className='row justify-content-center'>
                            <br/>
                            <div className='col-md-12 text-center'>
                                <div className={className}>
                                    <div className="menu">
                                        <h3>Настройки</h3>
                                        <div className="item">
                                            Добавить коллаборатора
                                                <TextField
                                                    variant="outlined"
                                                    margin="normal"
                                                    required
                                                    fullWidth
                                                    id="name"
                                                    label="Введите email"
                                                    name="_co_email"
                                                    onChange={event => {
                                                        this.setState({
                                                            coemail: event.target.value
                                                        });
                                                    }}
                                                    autoFocus
                                                />

                                                <button type="submit" className="btn"
                                                        onClick={this.createClHnld}
                                                >Добавить</button>
                                        </div>
                                        {/*<div className="item">Item 2</div>*/}
                                        {/*<div className="item">Item 3</div>*/}
                                        {/*<div className="item">Item 4</div>*/}
                                        {/*<div className="item">Item 5</div>*/}
                                    </div>
                                </div>
                            </div>
                        </div>
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

                    <div className="mcurdiv">
                        <div id="ms-container" onClick={this.mdownloadAllCommit}>
                            <label htmlFor="ms-download">
                                <div className="ms-content">
                                    <h3 className="mh3">Скачать коммит</h3>
                                    <div className="ms-content-inside">
                                        <input type="checkbox" id="ms-download"/>
                                        <div className="ms-line-down-container">
                                            <div className="ms-line-down"></div>
                                        </div>
                                        <div className="ms-line-point"></div>
                                    </div>
                                </div>
                            </label>
                        </div>
                        <a href={"/upload/"+this.state.curBrId+"/"+this.state.currentBranch+"/"}>
                            <div id="ms-container">
                                <h3 className="mh3">Загрузить файлы в ветку</h3>
                                <svg cxmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     className="bi bi-cloud-upload cursvg" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M4.406 1.342A5.53 5.53 0 0 1 8 0c2.69 0 4.923 2 5.166 4.579C14.758 4.804 16 6.137 16 7.773 16 9.569 14.502 11 12.687 11H10a.5.5 0 0 1 0-1h2.688C13.979 10 15 8.988 15 7.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 2.825 10.328 1 8 1a4.53 4.53 0 0 0-2.941 1.1c-.757.652-1.153 1.438-1.153 2.055v.448l-.445.049C2.064 4.805 1 5.952 1 7.318 1 8.785 2.23 10 3.781 10H6a.5.5 0 0 1 0 1H3.781C1.708 11 0 9.366 0 7.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383z"/>
                                    <path fill-rule="evenodd"
                                          d="M7.646 4.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V14.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3z"/>
                                </svg>
                            </div>
                        </a>
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