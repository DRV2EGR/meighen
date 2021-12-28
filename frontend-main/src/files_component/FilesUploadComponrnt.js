
import React, {Component} from 'react';
import Dropzone from "react-dropzone";
import Header from "../header/Header";
import AuthElement from "../auth_element_component/AuthElement";
import './FilesUploadComponent.css';
import TextField from "@material-ui/core/TextField";
import {Cookies} from "react-cookie";

class FilesUploadComponrnt extends Component {
    constructor(props) {
        super(props);
        this.onDrop = (files) => {
            this.setState({files})
        };
        this.state = {
            files: [],
            message:"",
            curst: "activate",
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
        this.uploadFiles = this.uploadFiles.bind(this);
    }

    uploadFiles() {
        this.setState({error: '', msg: ''});

        if(!this.state.files) {
            this.setState({error: 'Please upload a file.'})
            return;
        }

        this.setState({curst:"activate loading"})

        // console.log(this.state.file);

        // if(this.state.file.size >= 2000000) {
        //     this.setState({error: 'File size exceeds limit of 2MB.'})
        //     return;
        // }

        // let data = new FormData();
        // data.append('file', this.state.file);
        // data.append('name', this.state.file.name);

        var formdata = new FormData();
        for (let i = 0; i < this.state.files.length; ++i) {
            formdata.append("files", this.state.files[i], this.state.files[i].name);
        }
        // formdata.append("files", fileInput.files[0], "/C:/Users/16642/Pictures/BAD_TST.png");
        // formdata.append("files", fileInput.files[0], "/C:/Users/16642/Pictures/олрлор.pdf");
        // formdata.append("files", fileInput.files[0], "/C:/Users/16642/Pictures/Снимок экрана 2021-11-08 152128.png");

        // console.log(formdata);

        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        fetch('/api/private/commit/create?branchId='+this.props.match.params.branch+'&message='+this.state.message, {
            method: 'POST',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
            }),
            body: formdata
        }).then(response => {
            this.setState({error: '', msg: 'Sucessfully uploaded file', curst:"activate loading done", files: []});
        }).catch(err => {
            this.setState({error: err});
        });
    }


    render() {
        const {code, description, curst} = this.state;
        const files = this.state.files.map(file => (
            <li key={file.name}>
                <span className="file-upload-span">{file.name}</span> - {file.size} bytes
            </li>
        ));
        return (
            <div>
                <AuthElement/>
                <Header />



                <div className="upload">
                    <div className="upload-files">
                        <header>
                            <p>
                                <i className="fa fa-cloud-upload" aria-hidden="true"></i>
                                <span className="up">Загрузка файлов в ветку</span>
                                <span className="load">{this.props.match.params.name}</span>
                            </p>
                        </header>
                        <TextField
                            variant="outlined"
                            margin="normal"
                            multiline
                            required
                            fullWidth
                            id="name"
                            label="Введите сообщение для комита"
                            name="_br_name"
                            onChange={event => {
                                this.setState({
                                    message: event.target.value
                                });
                            }}
                            autoFocus
                        />
                        <div className="body" id="drop">
                            <i className="fa fa-file-text-o pointer-none" aria-hidden="true"></i>

                            <Dropzone onDrop={this.onDrop}>
                                {({getRootProps, getInputProps}) => (
                                    <section className="upload-container">
                                        <div {...getRootProps({className: 'dropzone'})}>
                                            <input {...getInputProps()} />
                                            <p>Нажмите здесь для загрузки файлов в ветку
                                                или просто перетащите их сюда</p>
                                        </div>
                                        <aside>
                                            <h4>Выбранные файлы</h4>
                                            <ol class="gradient-list">{files}</ol>
                                        </aside>
                                    </section>
                                )}
                            </Dropzone>

                        </div>
                        <footer>
                            <div className="divider">
                                <span>FILES</span>
                            </div>
                            <div className="list-files">
                            </div>
                            <button className="importar">UPDATE FILES</button>
                        </footer>
                    </div>
                </div>

                <div className="my-upload-div">
                    <a className={curst} onClick={this.uploadFiles}>
                        <span>
                            <svg>
                                <use xlinkHref="#circle" />
                            </svg>
                            <svg>
                                <use xlinkHref="#arrow" />
                            </svg>
                            <svg>
                                <use xlinkHref="#check" />
                            </svg>
                        </span>
                        <ul>
                            <li>Загрузить</li>
                            <li>Загрузка</li>
                            <li>Готово</li>
                        </ul>
                    </a>

                    <svg xmlns="http://www.w3.org/2000/svg" className="disnn">
                        <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" id="circle">
                            <circle cx="8" cy="8" r="7.5"></circle>
                        </symbol>
                        <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 12" id="arrow">
                            <path
                                d="M2.7008908,5.37931459 L2.7008908,5.37931459 C2.9224607,5.60207651 3.2826628,5.60304283 3.50542472,5.38147293 C3.52232305,5.36466502 3.53814843,5.34681177 3.55280728,5.32801875 L5.34805194,3.02646954 L5.34805194,10.3480519 C5.34805194,10.7081129 5.63993903,11 6,11 L6,11 C6.36006097,11 6.65194806,10.7081129 6.65194806,10.3480519 L6.65194806,3.02646954 L8.44719272,5.32801875 C8.6404327,5.57575732 8.99791646,5.61993715 9.24565503,5.42669716 C9.26444805,5.41203831 9.28230129,5.39621293 9.2991092,5.37931459 L9.2991092,5.37931459 C9.55605877,5.12098268 9.57132199,4.70855346 9.33416991,4.43193577 L6.75918715,1.42843795 C6.39972025,1.00915046 5.76841509,0.960656296 5.34912761,1.32012319 C5.31030645,1.35340566 5.27409532,1.38961679 5.24081285,1.42843795 L2.66583009,4.43193577 C2.42867801,4.70855346 2.44394123,5.12098268 2.7008908,5.37931459 Z"></path>
                        </symbol>
                        <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 12" id="check">
                            <path id="test"
                                  d="M4.76499011,6.7673683 L8.2641848,3.26100386 C8.61147835,2.91299871 9.15190114,2.91299871 9.49919469,3.26100386 C9.51164115,3.27347582 9.52370806,3.28637357 9.53537662,3.29967699 C9.83511755,3.64141434 9.81891834,4.17816549 9.49919469,4.49854425 L5.18121271,8.82537365 C4.94885368,9.05820878 4.58112654,9.05820878 4.34876751,8.82537365 L2.50080531,6.97362503 C2.48835885,6.96115307 2.47629194,6.94825532 2.46462338,6.93495189 C2.16488245,6.59321455 2.18108166,6.0564634 2.50080531,5.73608464 C2.84809886,5.3880795 3.38852165,5.3880795 3.7358152,5.73608464 L4.76499011,6.7673683 Z"></path>
                        </symbol>
                    </svg>

                    <a className="dribbble" href="https://dribbble.com/shots/5709751-Activate-Button" target="_blank">
                        <img src="https://cdn.dribbble.com/assets/dribbble-ball-mark-2bd45f09c2fb58dbbfb44766d5d1d07c5a12972d602ef8b32204d28fa3dda554.svg"
                        alt=""/>
                    </a>
                </div>
            </div>
        );
    }
}

export default FilesUploadComponrnt;