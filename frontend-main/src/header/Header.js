
import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
// import CheckAcsessComponent from "./CheckAcsessComponent";
import './Header.css';
import AuthElement from "../auth_element_component/AuthElement";

class Header extends Component {
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        const { cookies } = props;
        this.state = {
            user: cookies.get("username") || "",
            data_p: '',
            userhref: '/login',
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        };

    }

    componentDidMount() {
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
            this.setState({userhref: 'user_cabinet'});


            //console.log(b)

            fetch(process.env.REACT_APP_BASE_BACKEND_URL + '/api/user/public/get_user_img_url_by_username?username=' + b, {
                method: 'post',
                headers: new Headers({
                    'Authorization': 'Bearer ' + a,
                    'Content-Type': 'application/json'
                }),
                body: JSON.stringify(cookies.get('username'))
            }).then(response => response.json())
                .then(res => /*console.log(result.imgUrl) );*/ this.setState({data_p: res.img}));
        } else {
            this.setState({data_p: 'https://iconorbit.com/icons/256-watermark/1611201511385554301-Girl%20User.jpg'}); //TODO: update img
        }
    }

    handleRemoveCookie = () => {
        const { cookies } = this.props;
        cookies.remove("username"); // remove the cookie
        cookies.remove("accessToken"); // remove the cookie
        cookies.remove("refreshToken"); // remove the cookie
        // this.setState({ user: cookies.get("user") });
    };

    addFunctions() {
        const cookies = new Cookies();
        let b = cookies.get('username');

        if (b) { //войдено
            return (
                <div className="dropdown-child">
                    <a href="/user_profile">Мой кабинет</a>
                    <a href="/add_order">Разместить объявление</a>
                    <a href="/user_settings">Настройки</a>
                    <a href="/" onClick={this.handleRemoveCookie}>Выйти</a>
                </div>
            )
        } else {
            return (
                <div className="dropdown-child">
                    <a href="/login">Войти</a>
                    <a href="/signup">Зарегистрироваться</a>
                </div>
            )
        }


    }

    render() {
        const { data_p } = this.state;
        const { userhref } = this.state;
        // const {code, description} = this.state;
        return (
            <div>
                {/*<AuthElement />*/}
                {/*<CheckAcsessComponent />*/}

                <div className='opana d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm'>
                    <h5 className='my-0 mr-md-auto font-weight-bold'><a className='main-lbl p-2 text-dark' href='/'>Pominki</a></h5>

                    <nav className='my-2 my-md-0 mr-md-3'>
                        {/*<a className='p-2 text-dark' href='/user/1'>Личный кабинет</a>*/}
                        <a className='p-2 text-dark' href='/about'>О нас</a>

                        {/*<a href={userhref} >*/}
                        {/*    <img className='user-nav-img' src={data_p} />*/}

                        {/*</a>*/}

                        <div className="dropdown">
                            <div >
                                <img className='user-nav-img' src={data_p}  alt='avatar'/>

                            </div>
                            { this.addFunctions() }
                        </div>
                    </nav>
                    {/*<a className='btn btn-outline-primary' href='/#'>Выход</a>*/}

                </div>
            </div>
        );
    }
}

export default withCookies(Header);