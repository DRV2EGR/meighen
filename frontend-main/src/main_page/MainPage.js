import React, {Component} from "react";
import Header from "../header/Header";
import './MainPage.css';
import {Cookies} from "react-cookie";

class MainPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
    }


    renderMainButtons() {
        const cookies = new Cookies();
        let b = cookies.get('username');

        if (b) { //войдено
            return (
                <div className="m-buttons-div">
                    <a href="/repositories">
                        <button className="button-27" role="button">Мои репозитории</button>
                    </a>
                </div>
            );
        } else {
            return (
                <div className="m-buttons-div">
                    <a href="/login" className="ma">
                        <button className="button-27" role="button">Войти</button>
                    </a>
                    <a href="/signup" className="ma">
                        <button className="button-27" role="button">Зарегистрироваться</button>
                    </a>
                </div>
            );
        }
    }

    render() {
        const {code, description} = this.state;
        return (
            <div>
                <Header />

                <div className='page-wrap d-flex flex-row align-items-center pt-5'>
                    <div className='container'>
                        <div className='row justify-content-center'>
                           <div>
                               <h2>СКВ</h2>
                               <h1><b>MEIGHGEN</b></h1>
                           </div>
                        </div>
                        <div className='row'>
                            Наша система контроля версий разработана с любовью!
                        </div>
                    </div>
                </div>

                {this.renderMainButtons()}

            </div>
        );
    }
}

export default MainPage;