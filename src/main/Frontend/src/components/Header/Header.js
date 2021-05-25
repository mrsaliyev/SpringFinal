import React from 'react'
import $ from 'jquery'
import {NavDropdown} from "react-bootstrap"
import {Link} from "react-router-dom";
import DbService, {TABLE_CATEGORIES, TABLE_PROGRAMMERS} from "../../_services/DbService";

const NavItems = () => {
    const logout = () => {
        DbService.logout()
    }
    const token = DbService.getCurrentToken()
    return (
        token ?
            <>
               
                    <Link to={'/login'} onClick={logout}>Log Out</Link>
               
            </>
            :
            <>
                
                    <Link to={'/login'}>Login</Link>
                
                
                    <Link to={'/register'}>/Registration</Link>
                
            </>
    )
}

class Header extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            categories: [],
            programmers: []
        }
    }

    componentDidMount() {
        $('.search-box').hover(function () {
            $(this).width('250px');
            $(this).find('input').show();
            $(this).find('button[type="submit"]').width('12.8%');
        }, function () {
            if (!($(this).find('input').is(':focus'))) {
                $(this).removeAttr('style');
                $(this).find('input').removeAttr('style');
                $(this).find('button[type="submit"]').removeAttr('style');
            }
        });

        $('.search-box').find('input').blur(function () {
            $('.search-box').removeAttr('style');
            $('.search-box').find('input').removeAttr('style');
            $('.search-box').find('button[type="submit"]').removeAttr('style');
        });

        DbService.getAllByTable(TABLE_CATEGORIES)
            .then(r => {
                this.setState({categories: r.data})
            })
        DbService.getAllByTable(TABLE_PROGRAMMERS)
            .then(r => {
                this.setState({programmers: r.data})
            })
    }

    render() {
        return (
            <header className="position-relative" >
                <nav className="navbar navbar-expand-lg main-nav position-absolute ">

                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                            onClick={() => {
                            }}>
                        <i className="fas fa-bars"/>
                    </button>

                    <div className="collapse navbar-collapse w-100 m-auto" id="navbarSupportedContent">
                        <div className="nav-soc">
                            <li className="nav-item active">
                                <Link className="nav-link" to={'/'}><h3>Book Store</h3></Link>
                            </li>
                        </div>

                        <div className="nav-soc">
                            <li className="nav-item active">
                                <Link className="nav-link" to={'/addBook'}>Add Book</Link>
                            </li>
                        </div>
                        <ul className="navbar-nav d-flex align-items-right m-auto">

                        </ul>
                            <ul id="nav-mobile" className="right hide-on-med-and-down">
                            <li className="nav-item  right-align">
                                <NavDropdown title="Registration" id="basic-nav">
                                    <NavItems/>
                                </NavDropdown>
                            </li>
                            
                            
                        </ul>
                    </div>
                </nav>
            </header>
        )
    }
}

export default Header
