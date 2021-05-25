export default function AdminHeader() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light position-fixed w-100">
            <a className="navbar-brand" href="#"/>
            <div id="open-menu" className="menu-bar">
                <div className="bars"/>
            </div>
            {/* <ul className="navbar-nav ml-auto">
                <li className="nav-item dropdown ets-right-0">
                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img
                            src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/LEGO_logo.svg/768px-LEGO_logo.svg.png"
                            className="img-fluid rounded-circle border user-profile" alt='img'/>
                    </a>
                </li>
            </ul> */}
        </nav>
    )
}