import React, {Component} from "react";
import Header from "../header/Header";

class MainPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
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
                               <h1><b>ПОМИНКИ</b></h1>
                           </div>
                        </div>
                        <div className='row'>
                            Наша система контроля версий разработана с любовью!
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default MainPage;