
import '../App.css';
import React, {Component} from 'react';
import { useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {withStyles} from "@material-ui/core";
import Header from "../header/Header";
// import printValue from "yup/es/util/printValue";
import {useCookies} from "react-cookie";
import Cookies from 'universal-cookie';
import {useHistory} from "react-router";
import history from './history';
import PropTypes from "prop-types";
import {useNavigate} from 'react-router-dom';
// import {withRouter} from 'react-router';

export const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api';

const useStyles = (theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
});

// const [username, setUserName] = useState();
// const [password, setPassword] = useState();

async function loginUser(credentials) { //credentials as param
    //console.log(JSON.stringify(credentials));

    let data = '';
    return fetch( 'api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())

}

// async function CookiesWork(access, refresh) {
//     const [cookies, setCookie, removeCookie] = useCookies(['cookie-name']);
//
//     const MyCookies = (a, r) => {
//
//
//         setCookie('accessToken', a);
//         setCookie('refreshToken', r);
//     };
//     MyCookies(access, refresh);
// }

// function setCookies(access, refresh) {
//     const [cookies, setCookie, removeCookie] = useCookies(['cookie-name']);
//
//     setCookie('accessToken', access);
//     setCookie('refreshToken', refresh);
// }


class Login extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _email: '',
            _password: ''
        };



        //const [token, setToken] = useState();
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    Copyright() {
        return (
            <Typography variant="body2" color="textSecondary" align="center">
                {'Copyright © '}
                <Link color="inherit" href="https://material-ui.com/">
                    pominki
                </Link>{' '}
                {new Date().getFullYear()}
                {'.'}
            </Typography>
        );
    }

    // componentDidMount() {
    //     //TODO: вставить получение картинки.
    //     fetch(process.env.REACT_APP_BASE_BACKEND_URL + '/api/user/get_user_img_url?userId=22' )
    //         .then(response => response.json())
    //         .then(res => /*console.log(result.imgUrl) );*/ this.setState({data_p : res.img}));
    //
    //     console.log(this.state.data_p);
    //     // .catch(e => {
    //     //         console.log(e);
    //     //         this.setState({data: result, isFetching: false, error: e }));
    //     // });
    // }

    // async loginUser(credentials) { //credentials as param
    //     console.log(credentials);
    //
    //     return fetch(process.env.REACT_APP_BASE_BACKEND_URL + 'http://localhost:8080/api/auth/login', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(credentials)
    //     })
    //         .then(data => data.json())
    // }

    // export default function Login({ setToken }) {
    // const [username, setUserName] = useState();
    // const [password, setPassword] = useState();

    // handleChange(event) {
    //     this.setState({value: event.target.value});
    // }




    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        //console.log(name, " ", value)
        this.setState({
            [name]: value
        });
    }

    handleSubmit = async e => {
        e.preventDefault();

        // alert('Ваш любимый вкус: ' + this.state.value);
        // console.log(this.refs)

        let email = this.state._email;
        let password = this.state._password;

        const token = await loginUser({
            email,
            password
            // username,
            // password
        });

        //console.log(token);
        if (token.accessToken) {
            const cookies = new Cookies();
            cookies.set('accessToken', token.accessToken, {path: '/'});
            cookies.set('refreshToken', token.refreshToken, {path: '/'});
            cookies.set('username', token.username, {path: '/'});

            //history.push('/');
            // this.context.router.history.push('/');
            // this.props.history.push("/");
            // window.open("/insert/your/path/here");
            window.location = '/';
        }
        // console.log(cookies.get('accessToken'));
        // console.log(cookies.get('refreshToken'));
        // const cookies = new Cookies();
        // cookies.set('myCat', 'Pacman', { path: '/' });
        // console.log(cookies.get('myCat')); // Pacman

        //CookiesWork(token.accessToken, token.refreshToken);

        //setToken(token);
    }


    render() {
        const { classes } = this.props;

        return (
            <div>
                <Header {...this.props}/>

                <Container component="main" maxWidth="xs" >
                    <CssBaseline />

                    <div className='main_container_top'>
                        <div className={classes.paper}>
                            <Avatar className={classes.avatar}>
                                <LockOutlinedIcon />
                            </Avatar>
                            <Typography component="h1" variant="h5">
                                Войти
                            </Typography>
                            <form className={classes.form} noValidate>
                                <TextField
                                    variant="outlined"
                                    margin="normal"
                                    required
                                    fullWidth
                                    id="email"
                                    label="Email адрес"
                                    name="_email"
                                    autoComplete="email"
                                    onChange={this.handleInputChange}
                                    autoFocus
                                />
                                <TextField
                                    variant="outlined"
                                    margin="normal"
                                    required
                                    fullWidth
                                    name="_password"
                                    label="Пароль"
                                    type="password"
                                    id="password"
                                    onChange={this.handleInputChange}
                                    autoComplete="current-password"
                                />
                                <FormControlLabel
                                    control={<Checkbox value="remember" color="primary" />}
                                    label="Запомнить меня"
                                />
                                <Button
                                    type="submit"
                                    onClick={this.handleSubmit}
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className={classes.submit}
                                >
                                    Войти!
                                </Button>
                                <Grid container>
                                    <Grid item xs>
                                        {/*TODO: */}
                                        <Link href="#" variant="body2">
                                            Забыли пароль?
                                        </Link>
                                    </Grid>
                                    <Grid item sm>
                                        <Link href="/signup" variant="body2">
                                            {"Еще нет у нас аккаунта? Зарегистрироваться!"}
                                        </Link>
                                    </Grid>
                                </Grid>
                            </form>
                        </div>
                    </div>

                    <Box mt={8}>
                        {/*<Copyright />*/}
                        {this.Copyright()}
                    </Box>
                </Container>
            </div>
        );
    }

}

export default withStyles(useStyles)(Login);