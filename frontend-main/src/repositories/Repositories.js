
import React, {Component} from 'react';
import Header from "../header/Header";
import {Cookies} from "react-cookie";

class Repositories extends Component {
    constructor(props) {
        super(props);
        this.state = {
            repositories: [],
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }

        this.renderRepos = this.renderRepos.bind(this);
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
            console.log(_repos);
            this.setState({ repositories: [..._repos] });

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


        // Проход по листу квартир
        for(let i = 0; i < k; i++) {
            // let name = `${this.state.flats_p[i].name.first} ${this.state.flats_p[i].name.last}`;
            let name = this.state.repositories[i].name;
            let id = this.state.repositories[i].id;

            console.log(name + " " + id)

            repoList.push(
                <div className='ocard m-md-auto'>
                    <a href={'repository/'+id}  className='main-a'>
                        <h3>{name}</h3>
                    </a>
                </div>

            );
        }

        return repoList;
    }

    render() {
        const {code, description} = this.state;
        return (
            <div>
                <Header />

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                        <div className='row justify-content-center'>
                            {this.renderRepos()}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Repositories;