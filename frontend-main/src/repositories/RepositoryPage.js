
import React, {Component} from 'react';
import {Route, Switch} from "react-router-dom";
import {withRouter} from "react-router";
import PropTypes from "prop-types";
import {Cookies} from "react-cookie";
import Header from "../header/Header";
// import Dropdown from 'react-css-dropdown';
// import {withRouter} from "react-router-dom";
import {
    Dropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem
} from "reactstrap";

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
            dropdownOpen: false,
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

    render() {
        const {code, description} = this.state;
        const {repo, dropdownOpen} = this.state;
        return (
            <div>
                <Header />

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                        <h2>Репозиторий: {repo.name}</h2>
                        <div className='row justify-content-center'>
                            <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
                                <DropdownToggle caret>Dropdown</DropdownToggle>
                                <DropdownMenu role="xena">
                                    <div tabIndex={1} onKeyDown={this.handleKeyDown} onClick={this.toggle}>Custom dropdown item</div>
                                    <div tabIndex={2} onKeyDown={this.handleKeyDown} onClick={this.toggle}>Something else</div>
                                    <div tabIndex={3} onKeyDown={this.handleKeyDown} onClick={this.toggle}>Custom dropdown item</div>
                                    <div tabIndex={4} onKeyDown={this.handleKeyDown} onClick={this.toggle}>Custom dropdown item</div>
                                </DropdownMenu>
                            </Dropdown>

                            <div className='col-md-12 text-center'>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default (RepositoryPage);