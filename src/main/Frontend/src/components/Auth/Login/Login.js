import {Form} from "react-bootstrap";
import {useState} from "react";
import {useHistory} from "react-router-dom";
import DbService from "../../../_services/DbService";

const Login = () => {
    const history = useHistory()
    if (DbService.getCurrentToken()) {
        history.push('/')
    }

    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const onChangeLogin = event => {
        setLogin(event.target.value);
    }
    const onChangePassword = event => {
        setPassword(event.target.value);
    }
    const onSubmitForm = event => {
        event.preventDefault()
        DbService.login(login, password)
            .then(r => {
                if (r) {
                    history.push('/')
                }
            })
            .catch(err => console.log(err))
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-6 offset-3 vh-75">
                    <div className="h-100 d-flex flex-column justify-content-center align-items-center">
                        <div className="p-3 shadow w-100">
                            <h2 className="text-center">Login</h2>
                            {/* if param.error */}
                            {/*<div>*/}
                            {/*    Invalid username or password.*/}
                            {/*</div>*/}
                            {/* if param.logout */}
                            {/*<div>*/}
                            {/*    You have been logged out.*/}
                            {/*</div>*/}
                            <Form onSubmit={onSubmitForm}>
                                <div className="form-group">
                                    <label htmlFor="login">Enter login: </label>
                                    <input type="text" name="username" value={login} onChange={onChangeLogin} className="form-control" id="login" required/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password"> Password: </label>
                                    <input type="password" name="password" value={password} onChange={onChangePassword} className="form-control" id="password" required/>
                                </div>
                                <div className="form-group">
                                    <button type="submit" className="btn btn-warning">Sign In</button>
                                </div>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login
