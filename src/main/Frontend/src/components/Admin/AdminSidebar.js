import {Link} from "react-router-dom";
import React from "react";

export default function AdminSidebar() {
    return (<div className="side-bar fixed">
            <div className="side-bar-links">
                <div className="side-bar-logo text-center py-3">
                    {/* <img
                        src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/LEGO_logo.svg/768px-LEGO_logo.svg.png"
                        className="img-fluid rounded-circle border bg-secoundry mb-3" alt='img'/> */}
                    <h5>Books Store</h5>
                </div>
                <ul className="navbar-nav" >
                    <li className="nav-item">
                        <Link to="/" className="nav-links d-block" style={{color: 'red'}}>
                            {/* <i className="fa fa-home pr-2"/> Books Store */}
                            Books Store
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/projects" className="nav-links d-block"style={{color: 'red'}}>
                            {/* <i className="fa fa-folder-open pr-2"/> BOOKS */}
                            BOOKS
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/users" className="nav-links d-block" style={{color: 'red'}}>
                            {/* <i className="fa fa-users pr-2"/> USERS */}
                            USERS
                        </Link>
                    </li>
                    {/* <li className="nav-item">
                        <Link to="/admin/programmers" className="nav-links d-block">
                            <i className="fa fa-file-code pr-2"/> PROGRAMMERS
                        </Link>
                    </li> */}
                    <li className="nav-item">
                        <Link to="/admin/roles" className="nav-links d-block" style={{color: 'red'}}>
                            {/* <i className="fa fa-hand-peace pr-2"/> ROLES */}
                            ROLES
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/categories" className="nav-links d-block" style={{color: 'red'}}>
                            {/* <i className="fa fa-list-alt pr-2"/> CATEGORIES */}
                            CATEGORIES
                        </Link>
                    </li>
                </ul>
            </div>
        </div>
      
        
    )
}