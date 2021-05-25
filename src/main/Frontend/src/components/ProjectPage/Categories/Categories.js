import {Link} from "react-router-dom";

export default function Categories(props) {
    let categories = props.categories

    return (
        <div className="card mb-3 mt-2">
            <h5 className="card-header">Categories</h5>
            <div className="card-body">
                <div className="row">
                    <ul className="list-unstyled mb-0 d-flex flex-wrap">
                        {!categories ? '' : categories.map(c => {
                            return (
                                <li key={`c${c.id}`} className={'col-lg-6'}>
                                    <Link to={'/'}>{c.category}</Link>
                                </li>
                            )
                        })}
                        <li className="col-lg-6">
                            <Link to={'/'}>Analytics</Link>
                        </li>
                        <li className="col-lg-6">
                            <Link to={'/'}>Web</Link>
                        </li>
                        <li className="col-lg-6">
                            <Link to={'/'}>Mobile Dev</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    )
}