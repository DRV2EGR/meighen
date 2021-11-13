import React, { Component } from 'react';
import './awad.css';
import {Cookies} from "react-cookie";
import Dropzone from 'react-dropzone';


class FilesComponent extends Component {
    constructor(props) {
        super();

        this.onDrop = (files) => {
            this.setState({files})
        };

        this.state = {
            files: []
        };
    }

    uploadFile = (event) => {
        event.preventDefault();
        this.setState({error: '', msg: ''});

        if(!this.state.file) {
            this.setState({error: 'Please upload a file.'})
            return;
        }

        console.log(this.state.file);

        // if(this.state.file.size >= 2000000) {
        //     this.setState({error: 'File size exceeds limit of 2MB.'})
        //     return;
        // }

        // let data = new FormData();
        // data.append('file', this.state.file);
        // data.append('name', this.state.file.name);

        // var formdata = new FormData();
        // formdata.append("files", fileInput.files[0], "/C:/Users/16642/Pictures/BAD_TST.png");
        // formdata.append("files", fileInput.files[0], "/C:/Users/16642/Pictures/олрлор.pdf");
        // formdata.append("files", fileInput.files[0], "/C:/Users/16642/Pictures/Снимок экрана 2021-11-08 152128.png");

        // fetch('http://localhost:8080/api/files', {
        //     method: 'POST',
        //     body: data
        // }).then(response => {
        //     this.setState({error: '', msg: 'Sucessfully uploaded file'});
        // }).catch(err => {
        //     this.setState({error: err});
        // });

    }

    downloadRandomImage = () => {

        fetch('/api/private/commit/download_zip?commitId=141a8a63-29f4-4480-a2af-9be380107f59', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huX3RoZV9hZG1pbiIsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE2MzY4MjYxMDIsImV4cCI6MTYzNjgyNzAwMn0.mJd9FiTV_x3eLR4T4gS0C_ZVKNJM8H5A-P5dOrGgPS0',
                'Content-Type': 'application/json'
            }),
        })
            .then(response => {
                const filename =  'commiting.zip';//response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url=  window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            });
    }

    onFileChange = (event) => {
        let vr = this.state.file;
        this.setState({
            file: this.state.file.push(event.target.files[0])
        });
    }

    render() {
        const files = this.state.files.map(file => (
            <li key={file.name}>
                {file.name} - {file.size} bytes
            </li>
        ));
        return (
            <div className="App">
                <div className="App-intro">
                    <h3>Upload a file</h3>
                    <h4 style={{color: 'red'}}>{this.state.error}</h4>
                    <h4 style={{color: 'green'}}>{this.state.msg}</h4>
                    <input onChange={this.onFileChange} type="file"/>
                    <button onClick={this.uploadFile}>Upload</button>
                </div>
                <div className="App-intro">
                    <h3>Download a random file</h3>
                    <button onClick={this.downloadRandomImage}>Download</button>
                </div>

                <Dropzone onDrop={this.onDrop}>
                    {({getRootProps, getInputProps}) => (
                        <section className="container">
                            <div {...getRootProps({className: 'dropzone'})}>
                                <input {...getInputProps()} />
                                <p>Drag 'n' drop some files here, or click to select files</p>
                            </div>
                            <aside>
                                <h4>Files</h4>
                                <ul>{files}</ul>
                            </aside>
                        </section>
                    )}
                </Dropzone>
            </div>
        );
    }
}

export default FilesComponent;