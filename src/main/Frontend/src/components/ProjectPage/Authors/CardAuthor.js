import {Link} from "react-router-dom";

export default function CardAuthor() {
    return (
        <div className="card-author">
            <Link className="author-avatar" to={"/programmer/1"}>
                <img src="https://prvera.ru/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png" alt="img"/>
            </Link>
            <div className="author-name">
                <Link className="author-name-prefix" to={"/programmer/1"}>Dos Dos</Link>
            </div>
        </div>
    )
}