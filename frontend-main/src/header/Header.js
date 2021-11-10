import React, {Component} from 'react';

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
    }

    render() {
        // const {code, description} = this.state;
        return (
            <div>
                {/*<CheckAcsessComponent />*/}

                <div className='opana d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm'>
                    <h4 className='my-0 mr-md-auto font-weight-bold'><a className='main-lbl p-2 text-dark' href='/'>Pominki</a></h4>

                    <nav className='my-2 my-md-0 mr-md-3'>
                        {/*<a className='p-2 text-dark' href='/user/1'>Личный кабинет</a>*/}
                        <a className='p-2 text-dark' href='/realtors'></a>
                        <a className='p-2 text-dark' href='/about'>О нас</a>

                        {/*<a href={userhref} >*/}
                        {/*    <img className='user-nav-img' src={data_p} />*/}

                        {/*</a>*/}

                        {/*<div className="dropdown">*/}
                        {/*    <div >*/}
                        {/*        <img className='user-nav-img' src={data_p} />*/}

                        {/*    </div>*/}
                        {/*    { this.addFunctions() }*/}
                        {/*</div>*/}
                    </nav>
                    {/*<a className='btn btn-outline-primary' href='/#'>Выход</a>*/}

                </div>
            </div>
        );
    }
}

export default Header;