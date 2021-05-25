import {Link} from "react-router-dom";

const Post = (props) => {
    let date = new Date(props.date)
    const categories = props.categories

    return (
        <div className="col-lg-4 col-12 col-md-6 p-1 p-md-3">
            <div className="home_post_card p-0"
                 style={{backgroundImage: `url(\'${props.img ? props.img : 'https://s23527.pcdn.co/wp-content/uploads/2019/02/moon-1-745x517.jpg.optimal.jpg'}\')`}}>
                <div className="home_post_card_info">
                    <div>
                        {
                            categories ?
                                categories.map(c => {
                                    return (
                                        <span className={'home_post_card_cat_name'}>{c.category}</span>
                                    )
                                })
                                :
                                ''
                        }
                        <p>
                            <Link to={`/project/${props.id}`} className="home_post_card_title">
                                {props.title}
                            </Link>
                            <span className="home_post_card_author">
                                by <span><Link to={"/programmer/1"}>Dos</Link></span>
                            </span>
                        </p>
                    </div>

                    <p className="home_post_card_pubdate">{((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '.' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '.' + date.getFullYear()}</p>
                </div>
            </div>
        </div>
    )
}

export default Post
