import {Form} from "react-bootstrap";
import {useState} from "react";
import {useHistory} from "react-router-dom";
import DbService from "../../_services/DbService";
import Login from "../Auth/Login/Login";

export default function AddBook() {

    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");
    const [footer, setFooter] = useState("");
    const [imgPath, setImgPath] = useState("");
    const [shortDesc, setShortDesc] = useState("");

    const onChangeTitle = event => {
        setTitle(event.target.value);
    }
    const onChangeBody = event => {
        setBody(event.target.value);
    }
    const onChangeFooter = event => {
        setFooter(event.target.value);
    }
    const onChangeImgPath = event => {
        setImgPath(event.target.value);
    }
    const onChangeShortDesc = event => {
        setShortDesc(event.target.value);
    }
    const isAuthorized = DbService.getCurrentToken()


    const onSubmitForm = event => {
        event.preventDefault()
        DbService.addBook(title, body, footer, imgPath, shortDesc)
            .then(r => {
                if (r) {
                }
            })
            .catch(err => console.log(err))
    }


    return (
        <div className="card mb-3 mt-2">

            {!isAuthorized ? <Login></Login> :

                <div className="v-100">
                    <h5 className="card-header">Add Book</h5>
                <div className="row">
                <div className="col-6 offset-3 vh-75">
                <div className="h-100 d-flex flex-column justify-content-center align-items-center">
                <div className="p-4 shadow w-100">
                    <Form onSubmit={onSubmitForm} className="w-100">
                        <h2 className="text-center">Add Book</h2>

                        <div className="form-group">
                            <input id="title" type="text" value={title} onChange={onChangeTitle} name="title"
                                   placeholder="Title:" className="form-control" required/>
                        </div>

                        <div className="form-group">
                            <input id="bodyText" type="text" value={body} onChange={onChangeBody} name="bodyText"
                                   placeholder="Enter body text:" className="form-control" required/>
                        </div>

                        <div className="form-group">
                            <input id="footerText" type="text" value={footer} onChange={onChangeFooter}
                                   name="footerText" placeholder="Enter footer text:" className="form-control"
                                   required/>
                        </div>

                        <div className="form-group">
                            <input id="imgPath" type="text" value={imgPath} onChange={onChangeImgPath} name="imgPath"
                                   placeholder="Enter image path:" className="form-control" required/>
                        </div>

                        <div className="form-group">
                            <input id="shortDecs" type="text" value={shortDesc} onChange={onChangeShortDesc}
                                   name="shortDecs" className="form-control" placeholder="Enter short Description:"
                                   required/>
                        </div>

                        <button type="submit" className="btn btn-pri">Add Book</button>
                    </Form>
                </div>
                </div>
                </div>
                </div>
                </div>
            }

        </div>
    )
}