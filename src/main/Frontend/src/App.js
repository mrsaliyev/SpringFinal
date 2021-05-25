import './css/fontawesome/css/all.css'
import './css/bootstrap.min.css'
import './css/style.css'
import './css/rate-style.css'
import './css/admin.css'
import {Route, Switch, useParams} from 'react-router-dom';
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Home from "./components/Home/Home";
import Register from "./components/Auth/Register/Register";
import Login from "./components/Auth/Login/Login";
import Admin from "./components/Admin/Admin";
import ProgrammerPage from "./components/ProgrammerPage/ProgrammerPage";
import ProjectPage from "./components/ProjectPage/ProjectPage";
import AddBook from "./components/Home/AddBook";

function App() {
    return (
        <div className="App">
            <Switch>
                <Route path={'/admin'} render={() => (
                    <Switch>
                        <Route path='/admin' render={() => <Admin/>}/>
                    </Switch>
                )}/>
                <Route path={''} render={() => (
                    <>
                        <Header/>
                        <Switch>
                            <Route path='/register' render={() => <Register/>}/>
                            <Route path='/login' render={() => <Login/>}/>
                            <Route path={'/programmer/:id'} children={<ProgrammerChild/>}/>
                            <Route path={'/project/:id'} children={<ProjectChild/>}/>
                            <Route path='/addBook' exact render={() => <AddBook/>}/>
                            <Route path='/' exact render={() => <Home/>}/>
                        </Switch>
                        <Footer/>
                    </>
                )}/>
            </Switch>
        </div>
    );
}

function ProgrammerChild() {
    let {id} = useParams();
    return (
        <ProgrammerPage id={id} key={window.location.pathname}/>
    );
}

function ProjectChild() {
    let {id} = useParams();
    return (
        <ProjectPage id={id} key={window.location.pathname}/>
    );
}

export default App;
