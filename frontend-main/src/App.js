import logo from './logo.svg';
import './App.css';
import {Component} from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
// import Layout from '../containers/Layout'
import MainPage from "./main_page/MainPage";
import ErrorPage from "./ErrorPage";
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
    render()
    {
        return (
            // <div className="App">
            //   <header className="App-header">
            //     <img src={logo} className="App-logo" alt="logo" />
            //     <p>
            //       Edit <code>src/App.js</code> and save to reload.
            //     </p>
            //     <a
            //       className="App-link"
            //       href="https://reactjs.org"
            //       target="_blank"
            //       rel="noopener noreferrer"
            //     >
            //       Learn React
            //     </a>
            //   </header>
            // </div>


        <Router>
            <Routes>
                <Route exact path="/" element={<MainPage/>}/>
                {/*<Route exact path="/login" element={<Login/>}/>*/}
                {/*<Route exact path="/recovery-password" element={<RecoveryPassword/>}/>*/}
                <Route path="*"  element={<ErrorPage code={404} description={'Страница не найдена.'}/>} />
            </Routes>
        </Router>
        );
    }
}

export default App;
