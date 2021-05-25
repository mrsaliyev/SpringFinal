import SideTopPosts from "./SideTopPosts/SideTopPosts";
import Authors from "./Authors/Authors";
import Technologies from "./Technologies/Technologies";
import Categories from "./Categories/Categories";
import {useEffect, useState} from "react";
import DbService, {TABLE_PROJECTS} from "../../_services/DbService";
import Comments from "./Comments/Comments";

export default function ProjectPage(props) {
    const [project, setProject] = useState({})
    const [posts, setPosts] = useState([])
    let date = new Date(project.createdAt)

    const loadData = () => {
        DbService.getItemByIdAndTable(props.id, TABLE_PROJECTS)
            .then(response => {
                setProject(response.data);
            })
        DbService.getAllByTable(TABLE_PROJECTS)
            .then(response => {
                setPosts(response.data)
            })
    }

    useEffect(() => {
        loadData();
    }, [])

    return (
        <div className="container">
            <div className="row  mt-3">
                <div className="col-2">
                    {!project.imgPath ? '' :

                        <div className="container"
                             style={{
                                 minHeight: '500px',
                                 backgroundImage: `url(\'${project.imgPath}\')`,
                                 backgroundRepeat: 'no-repeat',
                                 backgroundSize: 'auto'
                             }}>
                        </div>
                    }

                    {/* <h1 className={'mt-5'}>{project.title}</h1>
                    <span className="home_post_card_pubdate mr-2">
                        {((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '.' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '.' + date.getFullYear()}
                    </span> */}

                    {/*<form id="likeForm1" action="#" method="get" hidden>*/}

                    {/*</form>*/}

                    {/*<a className="btnModifyLike" type="button" onClick={() => {*/}
                    {/*   */}
                    {/*}}>*/}
                    {/*    <i className="fa fa-heart ml-1" id="likeIcon1"/> <span id="likeCount1"> 11</span>*/}
                    {/*</a>*/}

                    {/*<span className="text-nowrap m-2">*/}
                    {/*    <i className="far fa-eye ml-1"/> 45*/}
                    {/*</span>*/}

                    {/* <div className="col s6">
                        <div>{project.body}</div>
                        <h1 className={'mt-5'}>{project.title}</h1>
                    <span className="home_post_card_pubdate mr-2">
                        {((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '.' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '.' + date.getFullYear()}
                    </span>
                    </div>  */}

                    {/* <span className="home_post_card_pubdate mr-2">
                        {((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '.' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '.' + date.getFullYear()}
                    </span> */}

                    {/*<span className="text-nowrap m-2">*/}
                    {/*    <i className="far fa-eye ml-1"/> 45*/}
                    {/*</span>*/}

                    {/*<div className="mt-5">*/}
                        {/*footer*/}
                    {/*</div>*/}

                    {/* <Comments loadData={loadData} comments={project.comments} project={project}/> */}
                </div>
                <div className="col s10">
                <h1 className={'mt-5'}>{project.title}</h1>
                        <div>{project.body}</div>
                    <span className="home_post_card_pubdate mr-2">
                        {((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '.' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '.' + date.getFullYear()}
                    </span>
                    </div>
                    <Comments loadData={loadData} comments={project.comments} project={project}/>


                {/* <div className="col-12 col-lg-4">
                    <Categories categories={project.category}/>
                    <Technologies/>
                    <Authors/>
                    <SideTopPosts projectId={project.id} projects={posts}/>
                </div> */}
            </div>
        </div>
    )
}
