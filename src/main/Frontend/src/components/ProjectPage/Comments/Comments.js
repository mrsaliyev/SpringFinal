import DbService, {ADD, TABLE_COMMENTS} from "../../../_services/DbService";
import {useState} from "react";
import {Form} from "react-bootstrap";

export default function Comments(props) {
    const isAuthorized = DbService.getCurrentToken()
    const comments = props.comments
    const projectId = props.project.id

    const [comment, setComment] = useState('');
    const onChangeComment = event => {
        setComment(event.target.value);
    }
    const onSubmitForm = event => {
        let d = {comment, projectId}
        addItem(d)
            .then(_ => {
                setComment('')
            })
            .catch(e => console.log(e))
        event.preventDefault()
    }

    async function addItem(data) {
        DbService.changeItem(ADD, TABLE_COMMENTS, data)
            .then(_ => props.loadData())
            .catch(e => console.log(e))
    }

    return (
        <>
            {/* <div className="d-flex flex-row align-items-center text-left comment-top p-2 bg-white border-bottom px-4">
                <div className="d-flex flex-column-reverse flex-grow-0 align-items-center votings ml-1">
                    <i className="fa fa-sort-up fa-2x hit-voting"/>
                    <span>{comments ? comments.length : '0'}</span>
                    <i className="fa fa-sort-down fa-2x hit-voting"/> */}
                {/* </div> */}
                {/* <div className="d-flex flex-column ml-3">
                    <div className="d-flex flex-row post-title">
                        <h5>COMMENTS</h5>
                    </div>
                </div> */}
            {/* </div> */}
            <div className="coment-bottom bg-white p-2 px-4">
                {
                    !isAuthorized ? '' :
                        <Form onSubmit={onSubmitForm}>
                            <div className="d-flex flex-row add-comment-section mt-4 mb-4">
                                <Form.Control value={comment} onChange={onChangeComment} type={'text'}
                                              className={'mr-3'} placeholder={'Add comment'} required/>
                                <button className="btn btn-warning" type="submit">Comment</button>
                            </div>
                        </Form>
                }
                {
                    !comments ? '' :
                        comments.map(c => {
                            return <Comment comment={c}/>
                        })
                }
            </div>
        </>
    )
}

function Comment(props) {
    let date = new Date(props.comment.createdAt)

    return (
        <div className="commented-section mt-2 mb-3 p-2 border-top border-bottom">
            <div className="d-flex flex-row align-items-center commented-user">
                <h5 className="mr-2">{props.comment.author.username}</h5>
                <span className="mb-1 ml-2">
                    {((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '.' + ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '.' + date.getFullYear()}
                </span>
            </div>
            <div className="comment-text-sm">
                <span>{props.comment.comment}</span>
            </div>
        </div>
    )
}
