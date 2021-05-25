import {Form} from "react-bootstrap";
import {useState} from "react";
import {useHistory} from "react-router-dom";
import DbService from "../../../_services/DbService";

const Register = () => {
    const history = useHistory()
    if (DbService.getCurrentToken()) {
        history.push('/')
    }

    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [repassword, setRepassword] = useState("");
    const onChangeLogin = event => {
        setLogin(event.target.value);
    }
    const onChangePassword = event => {
        setPassword(event.target.value);
    }
    const onChangeRepassword = event => {
        setRepassword(event.target.value);
    }
    const onSubmitForm = event => {
        event.preventDefault()
        if (password !== repassword) {
            alert('Passwords do not match!')
            return
        }
        DbService.register(login, password)
        history.push('/login')
    }
    
    return (
        <div className="container">
            <div className="row">
                <div className="col-6 offset-3 vh-75">
                    <div className="h-100 d-flex flex-column justify-content-center align-items-center">
                        <div className="p-3 shadow w-100">
                            <Form onSubmit={onSubmitForm} className="w-100">
                                <h2 className="text-center">Registration</h2>
                                <div className="form-group">
                                    <label htmlFor="username">Enter login: </label>
                                    <input id="username" type="text" value={login} onChange={onChangeLogin} name="username" placeholder="Username" className="form-control" required/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password">Enter password: </label>
                                    <input id="password" type="password" value={password} onChange={onChangePassword} name="password" placeholder="Password" className="form-control" required/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password2">Confirm password: </label>
                                    <input id="password2" type="password" value={repassword} onChange={onChangeRepassword} name="passwordConfirm" className="form-control" placeholder="Repeat password" required/>
                                </div>
                                <button type="submit" className="btn btn-warning">Sign Up</button>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Register
