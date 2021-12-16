
import React, {Component} from 'react';
import Header from "../header/Header";
import {Cookies} from "react-cookie";
import './Repositories.css';
import AuthElement from "../auth_element_component/AuthElement";
import TextField from "@material-ui/core/TextField";

class Repositories extends Component {
    constructor(props) {
        super(props);
        this.state = {
            repositories: [],
            corepositories: [],
            frmpp: "form-popup-none",
            name: "",
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }

        this.renderRepos = this.renderRepos.bind(this);
        this.btnHndl = this.btnHndl.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.createRepoHnld = this.createRepoHnld.bind(this);
    }

    async getRepos(a) {
        return await fetch('/api/private/repos/repos_short', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async getCollabRepos(a) {
        return await fetch('/api/private/repos/my_collab_repos', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        // React.createElement(CheckAcsessComponent);

        //TODO: вставить получение картинки.
        // fetch(process.env.REACT_APP_BASE_BACKEND_URL + '/api/user/get_user_img_url?userId=22' )
        //     .then(response => response.json())
        //     .then(res => /*console.log(result.imgUrl) );*/ this.setState({data_p : res.img}));
        //
        // console.log(this.state.data_p);
        // .catch(e => {
        //         console.log(e);
        //         this.setState({data: result, isFetching: false, error: e }));
        // });

        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        if (b) {
            const _repos = await this.getRepos(a);
            const _corepos = await this.getCollabRepos(a);
            // console.log(_repos);
            this.setState({ repositories: [..._repos] });
            this.setState({ corepositories: [..._corepos] });

            this.setState({loading: false});
        } else {
           // pass
        }
    }

    renderRepos() {

        const repoList = [];

        let k = 0;
        try {
            k = this.state.repositories.length;
        } catch (e) {
            k = 0;
        }

        const cookies = new Cookies();
        let b = cookies.get('username');

        // Проход по листу квартир
        for(let i = 0; i < k; i++) {
            // let name = `${this.state.flats_p[i].name.first} ${this.state.flats_p[i].name.last}`;
            let name = this.state.repositories[i].name;
            let id = this.state.repositories[i].id;

            // console.log(name + " " + id)
            repoList.push(
                <div className='card-body'>
                    <a href={'repository/'+id}  className='main-a'>
                        <h3>{b} / {name}</h3>
                    </a>
                </div>

            );
        }

        return repoList;
    }

    renderCollabRepos() {
        const repoList = [];

        let k = 0;
        try {
            k = this.state.corepositories.length;
        } catch (e) {
            k = 0;
        }

        const cookies = new Cookies();
        let b = cookies.get('username');

        // Проход по листу квартир
        for(let i = 0; i < k; i++) {
            // let name = `${this.state.flats_p[i].name.first} ${this.state.flats_p[i].name.last}`;
            let name = this.state.corepositories[i].name;
            let id = this.state.corepositories[i].id;
            let owner = this.state.corepositories[i].owner;

            // console.log(name + " " + id)
            repoList.push(
                <div className='card-body'>
                    <a href={'repository/'+id}  className='main-a'>
                        <h3>{owner} / {name}</h3>
                    </a>
                </div>

            );
        }

        return repoList;
    }

    btnHndl() {
        if (this.state.frmpp == "form-popup-none") {
            this.setState({frmpp : "form-popup"});
        } else {
            this.setState({frmpp : "form-popup-none"});
        }
    }

    handleInputChange(event) {

        //console.log(name, " ", value)
        this.setState({
            value: event.target.value
        });
    }

    async createRepoHnld() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let body = {
            name: this.state.value
        };

        await fetch('/api/private/repos/create', {
            method: 'post',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
                body: JSON.stringify(body)
        }).then(window.location = '/repositories');
    }

    render() {
        const {code, description, frmpp} = this.state;
        return (
            <div>
                <AuthElement />
                <Header />

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                        <div className='my-row'>
                            <h1>Ваши репозитории:</h1>
                            <button type="button" className="btn btn-success btn-my" onClick={this.btnHndl}>+</button>
                        </div>

                        <div className={frmpp} id="myForm">
                            <h3>Создание репозитория</h3>
                            {/*<input type="text" value={this.state.value} placeholder="Введите ваше название" name="name" onChange={this.handleInputChange} required /> <br />*/}
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="name"
                                label="Введите название репозитория"
                                name="_repo_name"
                                // autoComplete="email"
                                onChange={this.handleInputChange}
                                autoFocus
                            />

                            <button type="submit" className="btn" onClick={this.createRepoHnld}>Создать</button>
                        </div>

                        <div className='flex-column justify-content-center repo-card'>
                            <div className="card">
                                {this.renderRepos()}
                            </div>
                        </div>
                    </div>
                </div>

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                        <div className='my-row'>
                            <h1>Совместные репозитории:</h1>
                        </div>

                        <div className='flex-column justify-content-center repo-card'>
                            <div className="card">
                                {this.renderCollabRepos()}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Repositories;