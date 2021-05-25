// import SidePost from "./SidePost/SidePost";

// export default function SideTopPosts(props) {
//     const projects = props.projects

//     return (
//         <div className="sidebar bg-white">
//             <div className="sidebar-title mb-0">
//                 <div className="sidebar-title-center w-100">
//                     <span className="p-2 w-100">Popular posts</span>
//                 </div>
//             </div>
//             <div className="sidebar-block bg-white border pt-3 pb-2">
//                 <div className="popular-posts">
//                     {
//                         projects
//                             .filter(p => {
//                                 return (p.id !== props.projectId)
//                             })
//                             .map(p => {
//                                 return (
//                                     <>
//                                         <SidePost project={p}/>
//                                         <hr/>
//                                     </>
//                                 )
//                             })
//                     }
//                 </div>
//             </div>
//         </div>
//     )
// }
