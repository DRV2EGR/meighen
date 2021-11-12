
import React, {Component} from 'react';
import {Cookies} from "react-cookie";

class AuthElement extends Component {
    constructor(props) {
        super(props);
        this.state = {
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
    }

    async componentDidMount() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let r = cookies.get('refreshToken');
        let b = cookies.get('username');

        let status = -1;
        await fetch('/api/private/check_auth', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(function(response) {  status = response.status; });

        // console.log("status: ", status );

        if (status != 200) {
            let body = {
                username: b,
                accessToken: a,
                refreshToken: r
            };
            let rstatus = await fetch('/api/auth/refresh', {
                method: 'post',
                headers: new Headers({
                    'Authorization': 'Bearer ' + a,
                    'Content-Type': 'application/json'
                }),
                body: JSON.stringify(body)
            }).then(response => response.json());

            console.log(rstatus);

            if (status != 200) {
                const cookies = new Cookies();
                cookies.remove('accessToken');
                cookies.remove('refreshToken');
                cookies.remove('username');
                window.location = '/login';
            } else {
                const cookies = new Cookies();
                cookies.set('accessToken', rstatus.accessToken, {path: '/'});
                cookies.set('refreshToken', rstatus.refreshToken, {path: '/'});
                cookies.set('username', rstatus.username, {path: '/'});
            }
        }
    }

    render() {
        return (
            <div></div>
        );
    }
}

export default AuthElement;