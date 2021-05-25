export default function About(props) {
    return (
        <div className="card-box text-center">
            <img
                src={props.programmer.profile_img ? props.programmer.profile_img : 'https://prvera.ru/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png'}
                className="rounded-circle avatar-xl img-thumbnail" alt="profile-image"/>
            <p className="mb-0">{props.programmer.name + ' ' + props.programmer.last_name}</p>
            <p className="text-left">
                <span className="ml-1">Email</span>:
                <span className="ml-1">{props.programmer.email}</span>
            </p>
            <p className="text-left">
                <span className="ml-1">LinkedIn</span>:
                <span className="ml-1">{props.programmer.email}</span>
            </p>
            {/*<p className="text-left">*/}
            {/*    <span className="ml-1">GitHub</span>:*/}
            {/*    <span className="ml-1">Elon</span>*/}
            {/*</p>*/}

            {!props.programmer.description ? '' :
                <div className=" mt-3" style={{float: 'left'}}>
                    <p>
                        <strong className="font-13">About Me :</strong>
                        <br/>
                        <span>{props.programmer.description}</span>
                    </p>
                </div>
            }
            <ul className="social-list list-inline mt-3 mb-0">
                <li className="list-inline-item">
                    {/*<a href="https://github.com/"*/}
                    {/*   className="social-list-item border-secondary text-secondary">*/}
                    {/*    <i className="fab fa-github"/>*/}
                    {/*</a>*/}
                </li>
            </ul>
        </div>
    )
}